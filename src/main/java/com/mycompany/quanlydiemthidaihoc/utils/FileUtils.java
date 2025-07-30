package com.mycompany.quanlydiemthidaihoc.utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;



public class FileUtils {
    
    private static final String DATA_DIR = "data";
    
    // Ensure data directory exists
    private static void ensureDataDirectory() {
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }
    
    // Copy resource from JAR to external file if it doesn't exist
    private static void ensureExternalFile(String resourceName) {
        ensureDataDirectory();
        
        File externalFile = new File(DATA_DIR, resourceName);
        if (!externalFile.exists()) {
            // Copy from classpath to external file
            try (InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(resourceName)) {
                if (inputStream != null) {
                    Path targetPath = Paths.get(DATA_DIR, resourceName);
                    Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Copied default " + resourceName + " to data directory");
                }
            } catch (IOException e) {
                System.err.println("Failed to copy " + resourceName + " to data directory: " + e.getMessage());
            }
        }
    }

 
    public static void writeXMLtoFile(String fileName, Object object) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            File xmlFile = new File(fileName);
            jaxbMarshaller.marshal(object, xmlFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    public static void writeXMLtoDataDir(String resourceName, Object object) {
        ensureDataDirectory();
        String filePath = DATA_DIR + File.separator + resourceName;
        writeXMLtoFile(filePath, object);
    }


    public static Object readXMLFile(String fileName, Class<?> clazz) {
        try {
            File xmlFile = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return jaxbUnmarshaller.unmarshal(xmlFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object readXMLFromClasspath(String resourcePath, Class<?> clazz) {
        try {
            InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(resourcePath);
            if (inputStream == null) {
                System.err.println("Resource not found: " + resourcePath);
                return null;
            }
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return jaxbUnmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object readXMLFileFlexible(String resourcePath, Class<?> clazz) {
        Object result = readXMLFromClasspath(resourcePath, clazz);
        if (result != null) {
            return result;
        }
        
        String filePath = "src/main/resources/" + resourcePath;
        return readXMLFile(filePath, clazz);
    }
    
    public static Object readXMLFilePortable(String resourceName, Class<?> clazz) {
        ensureExternalFile(resourceName);
        
        String externalPath = DATA_DIR + File.separator + resourceName;
        File externalFile = new File(externalPath);
        
        if (externalFile.exists()) {
            return readXMLFile(externalPath, clazz);
        }
        
        return readXMLFromClasspath(resourceName, clazz);
    }

    
}
