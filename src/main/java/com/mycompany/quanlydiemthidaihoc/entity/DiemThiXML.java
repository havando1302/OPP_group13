package com.mycompany.quanlydiemthidaihoc.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DiemThiXML")
public class DiemThiXML {

    public static List<DiemThi> listDiemThi = docDiemThi();

    private List<DiemThi> diemThi;

    @XmlElement(name = "DiemThi")
    public List<DiemThi> getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(List<DiemThi> diemThi) {
        this.diemThi = diemThi;
    }

    // Đọc file XML thành danh sách DiemThi
    public static ArrayList<DiemThi> docFile(String filePath) {
        ArrayList<DiemThi> list = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) return list;

            JAXBContext jaxbContext = JAXBContext.newInstance(DiemThiXML.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            DiemThiXML diemThiXML = (DiemThiXML) unmarshaller.unmarshal(file);
            if (diemThiXML.getDiemThi() != null) {
                list.addAll(diemThiXML.getDiemThi());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Ghi danh sách DiemThi ra file XML
    public static void ghiFile(String filePath, ArrayList<DiemThi> listDiemThi) {
        try {
            DiemThiXML diemThiXML = new DiemThiXML();
            diemThiXML.setDiemThi(listDiemThi);

            JAXBContext jaxbContext = JAXBContext.newInstance(DiemThiXML.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(diemThiXML, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    // Tiện lợi: Đọc nhanh mặc định từ file diemthi.xml
    public static ArrayList<DiemThi> docDiemThi() {
        return docFile("diemthi.xml");
    }

    // Tiện lợi: Ghi nhanh mặc định ra file diemthi.xml
    public static void luuDiemThi(ArrayList<DiemThi> listDiemThi) {
        ghiFile("diemthi.xml", listDiemThi);
    }
    public static List<DiemThi> getDiemTheoSBD(String sbd) {
    List<DiemThi> result = new ArrayList<>();
    for (DiemThi dt : listDiemThi) {
        if (dt.getSoBaoDanh().equalsIgnoreCase(sbd)) {
            result.add(dt);
        }
    }
    return result;
}
   
}
