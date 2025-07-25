package com.mycompany.quanlydiemthidaihoc.view;

import com.mycompany.quanlydiemthidaihoc.controller.AdminController;
import javax.swing.*;
import java.awt.event.ActionListener;

public class AdminMainView extends JFrame {
    private JButton btnQuanLyThiSinh = new JButton("Quản lý thí sinh");
    private JButton btnQuanLyKhoiThi = new JButton("Quản lý khối thi");
    private JButton btnQuanLyMonThi = new JButton("Quản lý môn thi");
    private JButton btnQuanLyDiemThi = new JButton("Quản lý điểm thi"); // ✅ Nút mới

    public AdminMainView() {
        setTitle("Admin - Quản lý điểm thi");
        setSize(300, 300); // Tăng chiều cao để đủ 4 nút
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        btnQuanLyThiSinh.setAlignmentX(CENTER_ALIGNMENT);
        btnQuanLyKhoiThi.setAlignmentX(CENTER_ALIGNMENT);
        btnQuanLyMonThi.setAlignmentX(CENTER_ALIGNMENT);
        btnQuanLyDiemThi.setAlignmentX(CENTER_ALIGNMENT); // ✅

        panel.add(Box.createVerticalStrut(20));
        panel.add(btnQuanLyThiSinh);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnQuanLyKhoiThi);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnQuanLyMonThi);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnQuanLyDiemThi); // ✅ Thêm nút mới

        add(panel);
    }

    public void addManageThiSinhListener(ActionListener listener) {
        btnQuanLyThiSinh.addActionListener(listener);
    }

    public void addManageKhoiThiListener(ActionListener listener) {
        btnQuanLyKhoiThi.addActionListener(listener);
    }

    public void addManageMonThiListener(ActionListener listener) {
        btnQuanLyMonThi.addActionListener(listener);
    }

    public void addManageDiemThiListener(ActionListener listener) { // ✅ Đã hỗ trợ listener
        btnQuanLyDiemThi.addActionListener(listener);
    }
}
