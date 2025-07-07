package com.mycompany.quanlydiemthidaihoc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StudentMainView extends JFrame {
    private JTextField txtSBD = new JTextField(10);
    private JButton btnTraCuu = new JButton("Tra cứu điểm");
    private JTextArea resultArea = new JTextArea(5, 30);

    public StudentMainView() {
        setTitle("Thí sinh - Xem điểm thi");
        setSize(400, 250); // Tăng size để dễ nhìn
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(5, 5)); // BorderLayout cho gọn

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Nhập SBD:"));
        inputPanel.add(txtSBD);
        inputPanel.add(btnTraCuu);

        resultArea.setEditable(false); // Chặn sửa
        JScrollPane scrollPane = new JScrollPane(resultArea);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    public String getSBD() {
        return txtSBD.getText().trim(); // Trim để khớp dữ liệu file
    }

    public void addTraCuuListener(ActionListener listener) {
        btnTraCuu.addActionListener(listener);
    }

    public void showResult(String message) {
        resultArea.setText(message);
    }
}
