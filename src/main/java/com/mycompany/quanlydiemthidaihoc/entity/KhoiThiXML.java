package com.mycompany.quanlydiemthidaihoc.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "KhoiThiXML")
public class KhoiThiXML {

    private List<KhoiThi> khoiThi;

    @XmlElement(name = "KhoiThi")
    public List<KhoiThi> getKhoiThi() {
        return khoiThi;
    }

    public void setKhoiThi(List<KhoiThi> khoiThi) {
        this.khoiThi = khoiThi;
    }

    // Đọc file XML và trả về ArrayList<KhoiThi>
    public static ArrayList<KhoiThi> docFile(String filePath) {
        ArrayList<KhoiThi> list = new ArrayList<>();
        try {
            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(KhoiThiXML.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            KhoiThiXML khoiThiXML = (KhoiThiXML) unmarshaller.unmarshal(file);
            if (khoiThiXML.getKhoiThi() != null) {
                list.addAll(khoiThiXML.getKhoiThi());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return list;
    }
}
