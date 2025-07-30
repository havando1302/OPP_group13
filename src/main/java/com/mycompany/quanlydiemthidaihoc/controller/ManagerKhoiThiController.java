package com.mycompany.quanlydiemthidaihoc.controller;

import com.mycompany.quanlydiemthidaihoc.action.ManagerKhoiThi;
import com.mycompany.quanlydiemthidaihoc.entity.KhoiThi;
import com.mycompany.quanlydiemthidaihoc.view.AdminView;
import com.mycompany.quanlydiemthidaihoc.view.KhoiThiView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ManagerKhoiThiController {
    private KhoiThiView khoiThiView;
    private AdminView mainView;
    private ManagerKhoiThi managerKhoiThi;

    public ManagerKhoiThiController(KhoiThiView view, ManagerKhoiThi managerKhoiThi1) {
        this.khoiThiView = view;
        this.managerKhoiThi = new ManagerKhoiThi();

        view.addUndoListener(new UndoListener());
        view.addAddKhoiThiListener(new AddKhoiThiListener());
        view.addListKhoiThiSelectionListener(new ListKhoiThiSelectionListener());
        view.addEditKhoiThiListener(new EditKhoiThiListener());
        view.addClearListener(new ClearKhoiThiListener());
        view.addDeleteKhoiThiListener(new DeleteKhoiThiListener());
        view.addSearchListener(new SearchKhoiThiListener());
        view.addSearchDialogListener(new SearchDialogKhoiThiListener());
        view.addCancelSearchListener(new CancelSearchKhoiThiListener());
        view.addCancelDialogListener(new CancelDialogSearchKhoiThiListener());
    }

    public void showManagerView() {
        List<KhoiThi> list = managerKhoiThi.getListKhoiThi();
        khoiThiView.setVisible(true);
        khoiThiView.showListKhoiThi(list);
    }

    class UndoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainView = new AdminView();
            AdminController controller = new AdminController(mainView);
            controller.showAdminView();
            khoiThiView.setVisible(false);
        }
    }

   class AddKhoiThiListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        try {
            KhoiThi khoi = khoiThiView.getKhoiThiInfo();

            boolean exists = managerKhoiThi.getListKhoiThi().stream()
                    .anyMatch(k -> k.getTenKhoi().equalsIgnoreCase(khoi.getTenKhoi()));
            if (exists) {
                khoiThiView.showMessage(" Vui lòng nhập tên khác.");
                return;
            }

            managerKhoiThi.add(khoi);
            khoiThiView.showKhoiThi(khoi);
            khoiThiView.showListKhoiThi(managerKhoiThi.getListKhoiThi());
            khoiThiView.showMessage("✅ Thêm thành công!");
        } catch (IllegalArgumentException ex) {
            khoiThiView.showMessage(ex.getMessage());
        }
    }
}


  class EditKhoiThiListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        KhoiThi khoi = khoiThiView.getKhoiThiInfo();
        if (khoi != null) {
            try {
                boolean duplicateName = managerKhoiThi.getListKhoiThi().stream()
                        .anyMatch(k -> k.getTenKhoi().equalsIgnoreCase(khoi.getTenKhoi()) && k.getId() != khoi.getId());

                if (duplicateName) {
                    khoiThiView.showMessage(" Vui lòng chọn tên khác.");
                    return;
                }

                managerKhoiThi.edit(khoi);
                khoiThiView.showKhoiThi(khoi);
                khoiThiView.showListKhoiThi(managerKhoiThi.getListKhoiThi());
                khoiThiView.showMessage("✅ Cập nhật thành công!");
            } catch (ParseException ex) {
                Logger.getLogger(ManagerKhoiThiController.class.getName()).log(Level.SEVERE, null, ex);
                khoiThiView.showMessage("❌ Lỗi khi cập nhật khối thi!");
            }
        } else {
            khoiThiView.showMessage("⚠️ Vui lòng chọn khối thi để cập nhật.");
        }
    }
}



    class DeleteKhoiThiListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        KhoiThi khoi = khoiThiView.getKhoiThiInfo();
        if (khoi != null) {
            boolean deleted = managerKhoiThi.delete(khoi);
            if (deleted) {
                khoiThiView.clearKhoiThiInfo();
                khoiThiView.showListKhoiThi(managerKhoiThi.getListKhoiThi());
                khoiThiView.showMessage("Xóa thành công!");
            } else {
                khoiThiView.showMessage("Không tìm thấy khối thi để xóa.");
            }
        } else {
            khoiThiView.showMessage("Vui lòng chọn khối thi để xóa.");
        }
    }
}

    class ListKhoiThiSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            List<KhoiThi> list = managerKhoiThi.getListKhoiThi();
            try {
                khoiThiView.fillKhoiThiFromSelectedRow(list);
            } catch (ParseException ex) {
                Logger.getLogger(ManagerKhoiThiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class ClearKhoiThiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            khoiThiView.clearKhoiThiInfo();
        }
    }

    class SearchKhoiThiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            khoiThiView.searchKhoiThiInfo();
        }
    }

    class CancelDialogSearchKhoiThiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            khoiThiView.cancelDialogSearchKhoiThiInfo();
        }
    }

    class CancelSearchKhoiThiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            khoiThiView.showListKhoiThi(managerKhoiThi.getListKhoiThi());
            khoiThiView.cancelSearchKhoiThi();
        }
    }

    class SearchDialogKhoiThiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String keyword = khoiThiView.validateSearch();
            List<KhoiThi> result = managerKhoiThi.searchKhoiThiByTenKhoi(keyword);
            if (!result.isEmpty()) {
                khoiThiView.showListKhoiThi(result);
            } else {
                khoiThiView.showMessage("Không tìm thấy kết quả!");
            }
        }
    }
}
