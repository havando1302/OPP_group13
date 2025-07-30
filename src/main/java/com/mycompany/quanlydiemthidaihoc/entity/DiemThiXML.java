package com.mycompany.quanlydiemthidaihoc.entity;

import com.mycompany.quanlydiemthidaihoc.utils.FileUtils;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "DiemThiXML")
@XmlAccessorType(XmlAccessType.FIELD)
public class DiemThiXML {

    @XmlElement(name = "DiemThi")
    private List<DiemThi> diemThi = new ArrayList<>();

    // ✅ Dùng biến này để thống nhất đường dẫn tới file XML
    private static final String RESOURCE_PATH = "diemthi.xml";

    public static List<DiemThi> listDiemThi = docDiemThi();

    public List<DiemThi> getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(List<DiemThi> diemThi) {
        this.diemThi = diemThi;
    }

    // ✅ Đọc danh sách DiemThi từ file XML bất kỳ (dùng FileUtils hỗ trợ JAR)
  public static ArrayList<DiemThi> docFile(String fileName) {
    ArrayList<DiemThi> list = new ArrayList<>();

    // Sử dụng method đã xử lý sẵn của bạn
    DiemThiXML wrapper = (DiemThiXML) FileUtils.readXMLFilePortable(fileName, DiemThiXML.class);

    if (wrapper != null && wrapper.getDiemThi() != null) {
        list.addAll(wrapper.getDiemThi());
    }

    return list;
}


    // ✅ Ghi danh sách DiemThi ra file XML
   public static void ghiFile(String fileName, ArrayList<DiemThi> listDiemThi) {
    try {
        // Bọc danh sách DiemThi vào wrapper DiemThiXML
        DiemThiXML wrapper = new DiemThiXML();
        wrapper.setDiemThi(listDiemThi);

        // Ghi file bằng FileUtils vào thư mục data/
        FileUtils.writeXMLtoDataDir(fileName, wrapper);

        System.out.println("✅ Ghi file thành công: data/" + fileName);
    } catch (Exception e) {
        System.err.println("❌ Ghi file thất bại: " + e.getMessage());
        e.printStackTrace();
    }
}


    // ✅ Đọc mặc định từ RESOURCE_PATH
    public static ArrayList<DiemThi> docDiemThi() {
        return docFile(RESOURCE_PATH);
    }

    // ✅ Ghi mặc định ra RESOURCE_PATH
    public static void luuDiemThi(ArrayList<DiemThi> listDiemThi) {
        ghiFile(RESOURCE_PATH, listDiemThi);
    }

    // ✅ Lấy danh sách điểm theo SBD
    public static List<DiemThi> getDiemTheoSBD(String sbd) {
        List<DiemThi> result = new ArrayList<>();
        for (DiemThi dt : listDiemThi) {
            if (dt.getSoBaoDanh().equalsIgnoreCase(sbd)) {
                result.add(dt);
            }
        }
        return result;
    }

    // ✅ Xoá điểm theo SBD và lưu lại
    public static void xoaTheoSBD(String sbd) {
        listDiemThi.removeIf(d -> d.getSoBaoDanh().equalsIgnoreCase(sbd));
        luuDiemThi(new ArrayList<>(listDiemThi));
    }
}
