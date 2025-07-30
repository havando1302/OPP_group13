package com.mycompany.quanlydiemthidaihoc.entity;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.quanlydiemthidaihoc.utils.FileUtils;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ThiSinhXML")
@XmlAccessorType(XmlAccessType.FIELD)
public class ThiSinhXML {

    private static final String RESOURCE_PATH = "thisinh.xml"; // ✅ sửa thành biến chuẩn

    public static List<ThiSinh> listThiSinh = new ArrayList<>();

    public static void loadFromFile(String RESOURCE_PATH) {
        listThiSinh = docFile(RESOURCE_PATH);
    }

    @XmlElement(name = "ThiSinh")
    private List<ThiSinh> thiSinh = new ArrayList<>();

    public List<ThiSinh> getThiSinh() {
        return thiSinh;
    }

    public void setThiSinh(List<ThiSinh> thiSinh) {
        this.thiSinh = thiSinh;
    }

    // ✅ Sử dụng FileUtils.readXMLFilePortable để đọc từ resources (hỗ trợ JAR/IDE)
    public static ArrayList<ThiSinh> docFile(String filePath) {
        ArrayList<ThiSinh> list = new ArrayList<>();
        try {
            ThiSinhXML wrapper = (ThiSinhXML) FileUtils.readXMLFilePortable(filePath, ThiSinhXML.class);
            if (wrapper != null && wrapper.getThiSinh() != null) {
                list.addAll(wrapper.getThiSinh());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ Dùng RESOURCE_PATH để load
    public static void loadFromFile() {
        listThiSinh = docFile(RESOURCE_PATH);
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
