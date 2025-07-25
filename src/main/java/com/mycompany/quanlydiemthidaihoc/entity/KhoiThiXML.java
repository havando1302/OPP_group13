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

    // Danh sách khối thi (dùng chung toàn cục)
    private static List<KhoiThi> listKhoiThi = new ArrayList<>();

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
                listKhoiThi = list; // Gán dữ liệu cho biến static
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Hàm gọi riêng để load dữ liệu dùng trong chương trình chính
    public static void loadFromFile(String filePath) {
        docFile(filePath); // đã gán cho listKhoiThi bên trong
    }

    // Trả về danh sách môn thi theo tên khối
    public static List<MonThi> getMonThiTheoKhoi(String khoi) {
        if (listKhoiThi == null) return new ArrayList<>(); // tránh lỗi NullPointer
        for (KhoiThi k : listKhoiThi) {
            if (k.getTenKhoi().equalsIgnoreCase(khoi)) {
                return k.getMonThiList();
            }
        }
        return new ArrayList<>();
    }

   
}
