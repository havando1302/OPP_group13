package com.mycompany.quanlydiemthidaihoc.entity;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@XmlRootElement(name = "ThiSinhXML")
@XmlAccessorType(XmlAccessType.FIELD) 
public class ThiSinhXML {

    public static List<ThiSinh> listThiSinh = new ArrayList<>();

    @XmlElement(name = "ThiSinh")
    private List<ThiSinh> thiSinh;

    public List<ThiSinh> getThiSinh() {
        return thiSinh;
    }

    public void setThiSinh(List<ThiSinh> thiSinh) {
        this.thiSinh = thiSinh;
    }

    public static ArrayList<ThiSinh> docFile(String filePath) {
        ArrayList<ThiSinh> list = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) return list;

            JAXBContext jaxbContext = JAXBContext.newInstance(ThiSinhXML.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            ThiSinhXML ThiSinhXML = (ThiSinhXML) unmarshaller.unmarshal(file);
            if (ThiSinhXML.getThiSinh() != null) {
                list.addAll(ThiSinhXML.getThiSinh());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Gán dữ liệu tĩnh từ file
    public static void loadFromFile(String filePath) {
        listThiSinh = docFile(filePath);
    }

    public static ThiSinh getThiSinhTheoTen(String ten) {
        if (listThiSinh == null) return null;
        for (ThiSinh ts : listThiSinh) {
            if (ts.getName().equalsIgnoreCase(ten)) {
                return ts;
            }
        }
        return null;
    }
}

