package com.mycompany.quanlydiemthidaihoc.controller;

import com.mycompany.quanlydiemthidaihoc.action.ManagerDiemThi;
import com.mycompany.quanlydiemthidaihoc.entity.DiemThi;
import com.mycompany.quanlydiemthidaihoc.entity.DiemThiXML;
import com.mycompany.quanlydiemthidaihoc.view.AdminView;
import com.mycompany.quanlydiemthidaihoc.view.DiemThiView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DiemThiController {
    private DiemThiView diemThiView;
    private AdminView mainView;
    private ManagerDiemThi managerDiemThi;
    private ArrayList<DiemThi> listDiemThi;
    private final String filePath = "diemthi.xml";

    public DiemThiController(DiemThiView view) {
        this.diemThiView = view;
        this.listDiemThi = DiemThiXML.docDiemThi();
        this.managerDiemThi = new ManagerDiemThi();
        view.addAddListener(new AddListener());
        view.addEditListener(new EditListener());
        view.addDeleteListener(new DeleteListener());
        view.addClearListener(new ClearListener());
        view.addUndoListener(new UndoListener());
        

        
    }
public void showManagerView() {
        List<DiemThi> list = managerDiemThi.getListDiem();
        diemThiView.setVisible(true);
        diemThiView.loadThiSinhDaNhap(list);
    }
   // Thêm điểm (cập nhật hoặc thêm mới)
class AddListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        List<DiemThi> diemMoiList = diemThiView.getTatCaDiemThiInfo();

        if (diemMoiList != null && !diemMoiList.isEmpty()) {
            // Lấy thông tin thí sinh đang được chọn
            String ten = diemThiView.getTenThiSinhDangChon();
            String sbd = diemThiView.getSoBaoDanhDangChon();
            String khoi = diemThiView.getKhoiThiDangChon();

            // Đọc toàn bộ điểm từ XML
            List<DiemThi> danhSachGoc = DiemThiXML.docDiemThi();

            // Xóa tất cả điểm cũ của thí sinh này
            danhSachGoc.removeIf(d -> d.getTenThiSinh().equalsIgnoreCase(ten)
                                   && d.getSoBaoDanh().equalsIgnoreCase(sbd)
                                   && d.getKhoiThi().equalsIgnoreCase(khoi));

            // Thêm điểm mới vào danh sách
            danhSachGoc.addAll(diemMoiList);

            // Ghi lại vào XML
            DiemThiXML.luuDiemThi(new ArrayList<>(danhSachGoc));

            // Cập nhật lại giao diện
            diemThiView.showDiem(ten, sbd, khoi);
            diemThiView.loadThiSinhDaNhap(danhSachGoc);
            diemThiView.showMessage("✅ Đã lưu điểm cho thí sinh \"" + ten + "\"!");
        } else {
            diemThiView.showMessage("❌ Không có điểm nào được lưu!");
        }
    }
}



    // Sửa điểm
    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = diemThiView.getSelectedRow();
            if (selectedIndex >= 0 && selectedIndex < listDiemThi.size()) {
                DiemThi diem = diemThiView.getDiemThiInfo();
                if (diem != null) {
                    listDiemThi.set(selectedIndex, diem);
                    DiemThiXML.luuDiemThi(listDiemThi);
                    
                    diemThiView.showMessage("✅ Cập nhật điểm thành công!");
                } else {
                    diemThiView.showMessage("❌ Dữ liệu không hợp lệ!");
                }
            } else {
                diemThiView.showMessage("⚠️ Vui lòng chọn dòng cần sửa.");
            }
        }
    }

   class DeleteListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        DiemThi diem = diemThiView.getDiemThiInfo();
        if (diem != null) {
            boolean deleted = managerDiemThi.delete(diem);
            if (deleted) {
                diemThiView.clearDiemThiInfo();
                String ten = diemThiView.getTenThiSinhDangChon();
                String sbd = diemThiView.getSoBaoDanhDangChon();
                String khoi = diemThiView.getKhoiThiDangChon();
                diemThiView.showDiem(ten, sbd, khoi);
                diemThiView.showMessage("Xóa thành công!");
            } else {
                diemThiView.showMessage("Không tìm thấy điểm thi để xóa.");
            }
        } else {
            diemThiView.showMessage("Vui lòng chọn điểm thi để xóa.");
        }
    }
}

    // Xóa trắng form
    class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            diemThiView.clearForm();
        }
    }

    // Quay lại giao diện Admin
    class UndoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainView = new AdminView();
            AdminController controller = new AdminController(mainView);
            controller.showAdminView();
            diemThiView.setVisible(false);
        }
    }

    
}
