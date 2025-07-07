package com.mycompany.quanlydiemthidaihoc.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AdminMainView extends JFrame {
    private JButton btnQuanLyThiSinh = new JButton("Quản lý thí sinh");
    private JButton btnQuanLyKhoiThi = new JButton("Quản lý khối thi");
    private JButton btnQuanLyMonThi = new JButton("Quản lý môn thi"); // ✅ Nút mới

    public AdminMainView() {
        setTitle("Admin - Quản lý điểm thi");
        setSize(300, 250); // Tăng height chút để đủ chỗ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        btnQuanLyThiSinh.setAlignmentX(CENTER_ALIGNMENT);
        btnQuanLyKhoiThi.setAlignmentX(CENTER_ALIGNMENT);
        btnQuanLyMonThi.setAlignmentX(CENTER_ALIGNMENT); // ✅ Căn giữa nút mới

        panel.add(Box.createVerticalStrut(20));
        panel.add(btnQuanLyThiSinh);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnQuanLyKhoiThi);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnQuanLyMonThi); // ✅ Thêm vào panel

        add(panel);
    }

    public void addManageThiSinhListener(ActionListener listener) {
        btnQuanLyThiSinh.addActionListener(listener);
    }

    public void addManageKhoiThiListener(ActionListener listener) {
        btnQuanLyKhoiThi.addActionListener(listener);
    }

    public void addManageMonThiListener(ActionListener listener) { // ✅ Listener cho nút mới
        btnQuanLyMonThi.addActionListener(listener);
    }
}
