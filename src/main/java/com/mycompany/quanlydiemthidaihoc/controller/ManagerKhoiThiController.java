package com.mycompany.quanlydiemthidaihoc.controller;

import com.mycompany.quanlydiemthidaihoc.action.ManagerKhoiThi;
import com.mycompany.quanlydiemthidaihoc.entity.KhoiThi;
import com.mycompany.quanlydiemthidaihoc.view.ManagerKhoiThiView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

   public class ManagerKhoiThiController {

    private ManagerKhoiThiView khoiThiView;
    private ManagerKhoiThi managerKhoiThi;

    public ManagerKhoiThiController(ManagerKhoiThiView view, ManagerKhoiThi manager) {
    this.khoiThiView = view;
    this.managerKhoiThi = manager;

    khoiThiView.addAddKhoiThiListener(new AddKhoiThiListener());
    khoiThiView.addDeleteKhoiThiListener(new DeleteKhoiThiListener());

    loadKhoiThiTable();
}


    private void loadKhoiThiTable() {
    khoiThiView.setKhoiThiTable(managerKhoiThi.getListKhoiThi()); 
}


    // ✅ Cần phải PUBLIC để lớp khác (như View) sử dụng được
    public class AddKhoiThiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tenKhoi = khoiThiView.getTenKhoi();
            if (tenKhoi.isEmpty()) {
                khoiThiView.showMessage("Tên khối thi không được để trống!");
                return;
            }
            KhoiThi khoiThi = new KhoiThi();
            khoiThi.setTenKhoi(tenKhoi);
            managerKhoiThi.add(khoiThi);
            loadKhoiThiTable();
            khoiThiView.clearTenKhoi();
            khoiThiView.showMessage("Thêm khối thi thành công!");
        }
    }

    // ✅ Cần phải PUBLIC để lớp khác (như View) sử dụng được
    public class DeleteKhoiThiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = khoiThiView.getSelectedKhoiThiId();
            if (id == -1) {
                khoiThiView.showMessage("Vui lòng chọn khối thi để xóa!");
                return;
            }
            KhoiThi khoiThi = new KhoiThi();
            khoiThi.setId(id);
            managerKhoiThi.delete(khoiThi);
            loadKhoiThiTable();
            khoiThiView.showMessage("Xóa khối thi thành công!");
        }
    }
}

