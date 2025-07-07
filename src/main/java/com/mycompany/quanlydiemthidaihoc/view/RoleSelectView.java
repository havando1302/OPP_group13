package com.mycompany.quanlydiemthidaihoc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RoleSelectView extends JFrame {
    private JComboBox<String> comboRole = new JComboBox<>(new String[]{"Admin", "Thí sinh"});
    private JButton btnContinue = new JButton("Tiếp tục");

    public RoleSelectView() {
        setTitle("Chọn quyền đăng nhập");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Quyền:"));
        panel.add(comboRole);
        panel.add(new JLabel(""));
        panel.add(btnContinue);

        add(panel);
    }

    public String getSelectedRole() {
        return comboRole.getSelectedItem().toString();
    }

    public void addContinueListener(ActionListener listener) {
        btnContinue.addActionListener(listener);
    }
}
