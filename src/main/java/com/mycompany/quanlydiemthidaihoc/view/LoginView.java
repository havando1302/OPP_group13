package com.mycompany.quanlydiemthidaihoc.view;

import com.mycompany.quanlydiemthidaihoc.entity.User;
import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginView extends javax.swing.JFrame {

    public LoginView() {
        initComponents();
        setLocationRelativeTo(null);
    }
  


    public User getUser() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        return new User(username, password, "admin");
    }
    private void initComponents() {
    btnLogin = new javax.swing.JButton();
    txtUsername = new javax.swing.JTextField(20); 
    txtPassword = new javax.swing.JPasswordField(20);
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Đăng nhập");

    btnLogin.setText("Đăng nhập");
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

    panel.add(new JLabel("Tên đăng nhập:"));
    panel.add(Box.createVerticalStrut(5));
    panel.add(txtUsername);
    panel.add(Box.createVerticalStrut(10));

    panel.add(new JLabel("Mật khẩu:"));
    panel.add(Box.createVerticalStrut(5));
    panel.add(txtPassword);
    panel.add(Box.createVerticalStrut(15));

    btnLogin.setAlignmentX(CENTER_ALIGNMENT);
    panel.add(btnLogin);

    add(panel);
    pack();
}


    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private javax.swing.JButton btnLogin;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
   
}
