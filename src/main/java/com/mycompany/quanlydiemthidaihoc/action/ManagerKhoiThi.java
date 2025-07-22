package com.mycompany.quanlydiemthidaihoc.action;

import com.mycompany.quanlydiemthidaihoc.entity.KhoiThi;
import com.mycompany.quanlydiemthidaihoc.entity.KhoiThiXML;
import com.mycompany.quanlydiemthidaihoc.utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ManagerKhoiThi {
    private static final String FILE_NAME = "khoithi.xml";
    private List<KhoiThi> listKhoiThi;
    private Component parentComponent;

    public ManagerKhoiThi() {
        this.listKhoiThi = readListKhoiThi();
        if (listKhoiThi == null) {
            listKhoiThi = new ArrayList<>();
        }
    }

    // Đọc danh sách khối thi từ XML
    public List<KhoiThi> readListKhoiThi() {
        KhoiThiXML khoiThiXML = (KhoiThiXML) FileUtils.readXMLFile(FILE_NAME, KhoiThiXML.class);
        if (khoiThiXML != null) {
            return khoiThiXML.getKhoiThi();
        }
        return new ArrayList<>();
    }

    // Ghi danh sách khối thi ra file XML
    public void writeListKhoiThi(List<KhoiThi> khoiThis) {
        KhoiThiXML khoiThiXML = new KhoiThiXML();
        khoiThiXML.setKhoiThi(khoiThis);
        FileUtils.writeXMLtoFile(FILE_NAME, khoiThiXML);
    }

    // Thêm khối thi mới
    public void add(KhoiThi khoiThi) {
        int maxId = 0;
        for (KhoiThi k : listKhoiThi) {
            if (k.getId() > maxId) {
                maxId = k.getId();
            }
        }
        khoiThi.setId(maxId + 1);
        listKhoiThi.add(khoiThi);
        writeListKhoiThi(listKhoiThi);
    }

    // Cập nhật thông tin khối thi (sửa)
    public void edit(KhoiThi khoiThi) throws ParseException {
        for (int i = 0; i < listKhoiThi.size(); i++) {
            if (listKhoiThi.get(i).getId() == khoiThi.getId()) {
                listKhoiThi.set(i, khoiThi); // Cập nhật toàn bộ đối tượng
                writeListKhoiThi(listKhoiThi);
                break;
            }
        }
    }

    // Xóa khối thi
    public boolean delete(KhoiThi khoiThi) {
        for (int i = 0; i < listKhoiThi.size(); i++) {
            if (listKhoiThi.get(i).getId() == khoiThi.getId()) {
                listKhoiThi.remove(i);
                writeListKhoiThi(listKhoiThi);
                return true;
            }
        }
        return false;
    }

    // Tìm kiếm khối thi theo tên gần đúng (case-insensitive)
    public List<KhoiThi> searchKhoiThiByTenKhoi(String keyword) {
        List<KhoiThi> result = new ArrayList<>();
        for (KhoiThi khoi : listKhoiThi) {
            if (khoi.getTenKhoi().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(khoi);
            }
        }
        return result;
    }

    // Trả về danh sách khối thi hiện có
    public List<KhoiThi> getListKhoiThi() {
        return listKhoiThi;
    }

    // Hiển thị thông báo
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(parentComponent, message);
    }

    // Cho phép set component hiển thị thông báo
    public void setParentComponent(Component parentComponent) {
        this.parentComponent = parentComponent;
    }
}
