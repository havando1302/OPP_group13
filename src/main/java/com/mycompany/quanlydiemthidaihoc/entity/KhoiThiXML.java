package com.mycompany.quanlydiemthidaihoc.entity;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.quanlydiemthidaihoc.utils.FileUtils;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "KhoiThiXML")
public class KhoiThiXML {

    // Đường dẫn đến file XML (đọc từ resources hoặc thư mục data)
    private static final String RESOURCE_PATH = "khoithi.xml";

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
            KhoiThiXML khoiThiXML = (KhoiThiXML) FileUtils.readXMLFilePortable(filePath, KhoiThiXML.class);
            if (khoiThiXML != null && khoiThiXML.getKhoiThi() != null) {
                list.addAll(khoiThiXML.getKhoiThi());
                listKhoiThi = list; // Gán dữ liệu cho biến static
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Hàm gọi riêng để load dữ liệu dùng trong chương trình chính
    public static void loadFromFile(String filePath) {
        docFile(filePath); // đã gán cho listKhoiThi bên trong
    }

    // Overload: Load từ mặc định RESOURCE_PATH
    public static void loadFromResource() {
        docFile(RESOURCE_PATH);
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
