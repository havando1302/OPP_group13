package com.mycompany.quanlydiemthidaihoc.entity;
import com.mycompany.quanlydiemthidaihoc.utils.FileUtils;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import com.mycompany.quanlydiemthidaihoc.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "MonThiList")
@XmlAccessorType(XmlAccessType.FIELD) 
public class MonThiXML {

    @XmlElement(name = "MonThi")       
    private List<MonThi> monThi = new ArrayList<>();

    // Đường dẫn file XML trong resources hoặc data
    private static final String RESOURCE_PATH = "monthi.xml";

    public MonThiXML() {
    }

    public List<MonThi> getMonThi() {
        return monThi;
    }

    public void setMonThi(List<MonThi> monThi) {
        this.monThi = monThi;
    }

    // ✅ Đọc danh sách môn thi từ file XML (dùng FileUtils.readXMLFilePortable)
    public static List<MonThi> readFromFile() {
        MonThiXML wrapper = (MonThiXML) FileUtils.readXMLFilePortable(RESOURCE_PATH, MonThiXML.class);
        if (wrapper != null && wrapper.getMonThi() != null) {
            return wrapper.getMonThi();
        } else {
            return new ArrayList<>();
        }
    }

    // ✅ Lấy danh sách tên môn thi
    public static List<String> docFile() {
        List<MonThi> danhSach = readFromFile();
        List<String> tenMonThi = new ArrayList<>();
        for (MonThi mt : danhSach) {
            tenMonThi.add(mt.getTenMon());
        }
        return tenMonThi;
    }
}
