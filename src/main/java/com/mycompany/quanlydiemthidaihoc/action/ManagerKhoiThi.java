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
    private final String RESOURCE_PATH = "khoithi.xml"; 
    private List<KhoiThi> listKhoiThi;
    private Component parentComponent;

    public ManagerKhoiThi() {
        this.listKhoiThi = readListKhoiThi();
        if (listKhoiThi == null) {
            listKhoiThi = new ArrayList<>();
        }
    }

    // Đọc danh sách khối thi từ XML (hỗ trợ cả IDE và khi đóng gói JAR)
    public List<KhoiThi> readListKhoiThi() {
        KhoiThiXML khoiThiXML = (KhoiThiXML) FileUtils.readXMLFilePortable(RESOURCE_PATH, KhoiThiXML.class);
        if (khoiThiXML != null) {
            return khoiThiXML.getKhoiThi();
        }
        return new ArrayList<>();
    }

    // Ghi danh sách khối thi ra file XML (dùng thư mục `data`)
    public void writeListKhoiThi(List<KhoiThi> khoiThis) {
        KhoiThiXML khoiThiXML = new KhoiThiXML();
        khoiThiXML.setKhoiThi(khoiThis);
        FileUtils.writeXMLtoDataDir(RESOURCE_PATH, khoiThiXML);
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

    // Cập nhật thông tin khối thi
    public void edit(KhoiThi khoiThi) throws ParseException {
        for (int i = 0; i < listKhoiThi.size(); i++) {
            if (listKhoiThi.get(i).getId() == khoiThi.getId()) {
                listKhoiThi.set(i, khoiThi);
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

    // Tìm kiếm gần đúng theo tên khối
    public List<KhoiThi> searchKhoiThiByTenKhoi(String keyword) {
        List<KhoiThi> result = new ArrayList<>();
        for (KhoiThi khoi : listKhoiThi) {
            if (khoi.getTenKhoi().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(khoi);
            }
        }
        return result;
    }

    // Trả về danh sách hiện tại
    public List<KhoiThi> getListKhoiThi() {
        return listKhoiThi;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(parentComponent, message);
    }

    public void setParentComponent(Component parentComponent) {
        this.parentComponent = parentComponent;
    }
}
