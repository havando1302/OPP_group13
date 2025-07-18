package com.mycompany.quanlydiemthidaihoc.controller;

import com.mycompany.quanlydiemthidaihoc.action.ManagerKhoiThi;
import com.mycompany.quanlydiemthidaihoc.entity.KhoiThi;
import com.mycompany.quanlydiemthidaihoc.view.ManagerKhoiThiView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerKhoiThiController {

    private final ManagerKhoiThiView khoiThiView;
    private final ManagerKhoiThi managerKhoiThi;

    public ManagerKhoiThiController(ManagerKhoiThiView view, ManagerKhoiThi manager) {
        this.khoiThiView = view;
        this.managerKhoiThi = manager;

        // Gắn sự kiện
        khoiThiView.addAddListener(new AddKhoiThiListener());
        khoiThiView.addDeleteListener(new DeleteKhoiThiListener());
        khoiThiView.addUpdateListener(new UpdateKhoiThiListener()); // 🟡 THÊM NÚT SỬA

        loadKhoiThiTable();
    }

    private void loadKhoiThiTable() {
        khoiThiView.setKhoiThiTable(managerKhoiThi.getListKhoiThi());
    }

    // ✅ Thêm mới khối thi
    public class AddKhoiThiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tenKhoi = khoiThiView.getTenKhoi();
            if (tenKhoi.isEmpty()) {
                khoiThiView.showMessage("Tên khối thi không được để trống!");
                return;
            }

            if (khoiThiView.getSelectedMonThi().isEmpty()) {
                khoiThiView.showMessage("Vui lòng chọn ít nhất 1 môn thi!");
                return;
            }

            KhoiThi khoiThi = new KhoiThi();
            khoiThi.setTenKhoi(tenKhoi);
            khoiThi.setMonThiList(khoiThiView.getSelectedMonThi());

            managerKhoiThi.add(khoiThi);

            loadKhoiThiTable();
            khoiThiView.clearForm();
            khoiThiView.showMessage("Thêm khối thi thành công!");
        }
    }

    // ✅ Xoá khối thi
    public class DeleteKhoiThiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = khoiThiView.getSelectedKhoiIndex();
            if (index == -1) {
                khoiThiView.showMessage("Vui lòng chọn khối thi để xóa!");
                return;
            }

            managerKhoiThi.deleteAt(index);
            loadKhoiThiTable();
            khoiThiView.clearForm();
            khoiThiView.showMessage("Xóa khối thi thành công!");
        }
    }

    // ✅ Sửa khối thi
    public class UpdateKhoiThiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = khoiThiView.getSelectedKhoiIndex();
            if (index == -1) {
                khoiThiView.showMessage("Vui lòng chọn khối thi để sửa!");
                return;
            }

            String tenKhoi = khoiThiView.getTenKhoi();
            if (tenKhoi.isEmpty()) {
                khoiThiView.showMessage("Tên khối thi không được để trống!");
                return;
            }

            if (khoiThiView.getSelectedMonThi().isEmpty()) {
                khoiThiView.showMessage("Vui lòng chọn ít nhất 1 môn thi!");
                return;
            }

            KhoiThi khoiThi = managerKhoiThi.getListKhoiThi().get(index);
            khoiThi.setTenKhoi(tenKhoi);
            khoiThi.setMonThiList(khoiThiView.getSelectedMonThi());

            managerKhoiThi.updateAt(index, khoiThi); // 🟡 Hàm update cần tồn tại trong ManagerKhoiThi

            loadKhoiThiTable();
            khoiThiView.clearForm();
            khoiThiView.showMessage("Cập nhật khối thi thành công!");
        }
    }
}
