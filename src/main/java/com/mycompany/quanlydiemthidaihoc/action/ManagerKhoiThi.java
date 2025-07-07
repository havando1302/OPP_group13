package com.mycompany.quanlydiemthidaihoc.action;

import com.mycompany.quanlydiemthidaihoc.entity.KhoiThi;
import com.mycompany.quanlydiemthidaihoc.entity.KhoiThiXML;
import com.mycompany.quanlydiemthidaihoc.entity.MonThi;
import com.mycompany.quanlydiemthidaihoc.utils.FileUtils;


import java.util.ArrayList;
import java.util.List;

public class ManagerKhoiThi {
    private static final String FILE_NAME = "KhoiThi.xml";
    private List<KhoiThi> listKhoiThi;
    private ManagerMonThi managerMonThi;

    public ManagerKhoiThi() {
        this.managerMonThi = new ManagerMonThi();
        this.listKhoiThi = new ArrayList<>();
        reload();
    }

    // Đọc dữ liệu từ file XML
    public void reload() {
        KhoiThiXML wrapper = FileUtils.readXMLFile(FILE_NAME, KhoiThiXML.class);
        if (wrapper != null && wrapper.getKhoiThi() != null) {
            this.listKhoiThi = wrapper.getKhoiThi();
        }
    }

    // Ghi dữ liệu vào file XML
    public void writeListKhoiThi() {
        KhoiThiXML wrapper = new KhoiThiXML();
        wrapper.setKhoiThi(this.listKhoiThi);
        FileUtils.writeXMLtoFile(FILE_NAME, wrapper);
    }

    // Trả về danh sách khối thi
    public List<KhoiThi> getListKhoiThi() {
        return listKhoiThi;
    }

    // Trả về danh sách môn thi (dành cho checkbox hoặc combobox)
    public List<MonThi> getListMonThi() {
        return managerMonThi.getListMonThi();
    }

    // Thêm khối thi
    public void add(KhoiThi khoi) {
        if (listKhoiThi == null) {
            listKhoiThi = new ArrayList<>();
        }
        listKhoiThi.add(khoi);
        writeListKhoiThi();
    }

    // Sửa khối thi theo vị trí
    public void edit(int index, KhoiThi khoi) {
        if (index >= 0 && index < listKhoiThi.size()) {
            listKhoiThi.set(index, khoi);
            writeListKhoiThi();
        }
    }

    // Xóa khối thi theo vị trí
    public void delete(int index) {
        if (index >= 0 && index < listKhoiThi.size()) {
            listKhoiThi.remove(index);
            writeListKhoiThi();
        }
    }

    // Tìm theo tên khối thi
    public KhoiThi findByTenKhoi(String tenKhoi) {
        for (KhoiThi k : listKhoiThi) {
            if (k.getTenKhoi().equalsIgnoreCase(tenKhoi)) {
                return k;
            }
        }
        return null;
    }

    public void delete(KhoiThi khoiThi) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
