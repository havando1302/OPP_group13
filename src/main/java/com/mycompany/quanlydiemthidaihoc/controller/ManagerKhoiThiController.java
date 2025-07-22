package com.mycompany.quanlydiemthidaihoc.controller;

import com.mycompany.quanlydiemthidaihoc.action.ManagerKhoiThi;
import com.mycompany.quanlydiemthidaihoc.entity.KhoiThi;
import com.mycompany.quanlydiemthidaihoc.view.AdminMainView;
import com.mycompany.quanlydiemthidaihoc.view.KhoiThiView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Controller xử lý logic giữa KhoiThiView và lớp xử lý dữ liệu ManagerKhoiThi
 */
public class ManagerKhoiThiController {
    private KhoiThiView khoiThiView;
    private AdminMainView mainView;
    private ManagerKhoiThi managerKhoiThi;

    /**
     * Constructor khởi tạo controller
     */
    public ManagerKhoiThiController(KhoiThiView view, ManagerKhoiThi managerKhoiThi1) {
        this.khoiThiView = view;
        this.managerKhoiThi = new ManagerKhoiThi();

        // Gắn các listener
        view.addUndoListener(new UndoListener());
        view.addAddKhoiThiListener(new AddKhoiThiListener());
        view.addEditKhoiThiListener(new EditKhoiThiListener());
        view.addDeleteKhoiThiListener(new DeleteKhoiThiListener());
        view.addClearListener(new ClearKhoiThiListener());
        view.addListKhoiThiSelectionListener(new ListKhoiThiSelectionListener());
        view.addSearchListener(new SearchKhoiThiListener());
        view.addSearchDialogListener(new SearchDialogKhoiThiListener());
        view.addCancelSearchListener(new CancelSearchKhoiThiListener());
        view.addCancelDialogListener(new CancelDialogSearchKhoiThiListener());
    }

    /**
     * Hiển thị giao diện quản lý khối thi
     */
    public void showManagerView() {
        List<KhoiThi> list = managerKhoiThi.getListKhoiThi();
        khoiThiView.setVisible(true);
        khoiThiView.showListKhoiThi(list);
    }

    /**
     * Quay lại trang Admin chính
     */
    class UndoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainView = new AdminMainView();
            AdminController controller = new AdminController(mainView);
            controller.showAdminView();
            khoiThiView.setVisible(false);
        }
    }

    /**
     * Xử lý thêm khối thi
     */
    class AddKhoiThiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                KhoiThi khoi = khoiThiView.getKhoiThiInfo();
                managerKhoiThi.add(khoi);
                khoiThiView.showKhoiThi(khoi);
                khoiThiView.showListKhoiThi(managerKhoiThi.getListKhoiThi());
                khoiThiView.showMessage("Thêm thành công!");
            } catch (IllegalArgumentException ex) {
                khoiThiView.showMessage(ex.getMessage());
            }
        }
    }

    /**
     * Xử lý cập nhật khối thi
     */
    class EditKhoiThiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            KhoiThi khoi = khoiThiView.getKhoiThiInfo();
            if (khoi != null) {
                try {
                    managerKhoiThi.edit(khoi);
                    khoiThiView.showKhoiThi(khoi);
                    khoiThiView.showListKhoiThi(managerKhoiThi.getListKhoiThi());
                    khoiThiView.showMessage("Cập nhật thành công!");
                } catch (ParseException ex) {
                    Logger.getLogger(ManagerKhoiThiController.class.getName()).log(Level.SEVERE, null, ex);
                    khoiThiView.showMessage("Lỗi khi cập nhật khối thi!");
                }
            } else {
                khoiThiView.showMessage("Vui lòng chọn khối thi để cập nhật.");
            }
        }
    }

    /**
     * Xử lý xóa khối thi
     */
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

    /**
     * Xử lý khi chọn hàng trong bảng
     */
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

    /**
     * Xóa dữ liệu trên form nhập liệu
     */
    class ClearKhoiThiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            khoiThiView.clearKhoiThiInfo();
        }
    }

    /**
     * Mở hộp thoại tìm kiếm
     */
    class SearchKhoiThiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            khoiThiView.searchKhoiThiInfo();
        }
    }

    /**
     * Hủy tìm kiếm (hộp thoại phụ)
     */
    class CancelDialogSearchKhoiThiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            khoiThiView.cancelDialogSearchKhoiThiInfo();
        }
    }

    /**
     * Hủy kết quả tìm kiếm (quay về danh sách gốc)
     */
    class CancelSearchKhoiThiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            khoiThiView.showListKhoiThi(managerKhoiThi.getListKhoiThi());
            khoiThiView.cancelSearchKhoiThi();
        }
    }

    /**
     * Thực hiện tìm kiếm khối thi theo tên
     */
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
