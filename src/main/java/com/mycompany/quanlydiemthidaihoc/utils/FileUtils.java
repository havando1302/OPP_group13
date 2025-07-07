package com.mycompany.quanlydiemthidaihoc.utils;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class FileUtils {

    public static void writeXMLtoFile(String fileName, Object data) {
        try {
            JAXBContext context = JAXBContext.newInstance(data.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(data, new File(fileName));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static <T> T readXMLFile(String fileName, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = new File(fileName);
            if (file.exists()) {
                return (T) unmarshaller.unmarshal(file);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
