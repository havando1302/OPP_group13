package com.mycompany.quanlydiemthidaihoc.entity;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "MonThiList")
public class MonThiXML {

    private List<MonThi> monThi;

    @XmlElement(name = "MonThi")
    public List<MonThi> getMonThi() {
        return monThi;
    }

    public void setMonThi(List<MonThi> monThi) {
        this.monThi = monThi;
    }

    // Đọc danh sách môn thi từ file XML
    public static List<MonThi> readFromFile(String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(MonThiXML.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MonThiXML wrapper = (MonThiXML) unmarshaller.unmarshal(new File(filePath));
            return wrapper.getMonThi();
        } catch (JAXBException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Lấy danh sách tên môn thi
    public static List<String> docFile(String filePath) {
        List<MonThi> danhSach = readFromFile(filePath);
        List<String> tenMonThi = new ArrayList<>();
        for (MonThi mt : danhSach) {
            tenMonThi.add(mt.getTenMon());
        }
        return tenMonThi;
    }
}
