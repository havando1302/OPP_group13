package com.mycompany.quanlydiemthidaihoc.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ManagerMonThiView extends JFrame {

    private JTextField txtTenMon = new JTextField(15);
    private JButton btnAdd = new JButton("Thêm");
    private JButton btnDelete = new JButton("Xóa");
    private JTable monThiTable = new JTable();
    private DefaultTableModel monThiModel;

    public ManagerMonThiView() {
        setTitle("Quản lý Môn Thi");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelInput = new JPanel();
        panelInput.add(new JLabel("Tên môn thi:"));
        panelInput.add(txtTenMon);
        panelInput.add(btnAdd);
        panelInput.add(btnDelete);

        monThiModel = new DefaultTableModel(new Object[]{"ID", "Tên môn thi"}, 0);
        monThiTable.setModel(monThiModel);
        JScrollPane scrollPane = new JScrollPane(monThiTable);

        setLayout(new BorderLayout());
        add(panelInput, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public String getTenMon() {
        return txtTenMon.getText().trim();
    }

    public void setMonThiTable(List<String[]> monThiList) {
        monThiModel.setRowCount(0);
        for (String[] row : monThiList) {
            monThiModel.addRow(row);
        }
    }

    public int getSelectedRow() {
        return monThiTable.getSelectedRow();
    }

    public int getSelectedMonThiId() {
        int row = getSelectedRow();
        if (row >= 0) {
            return Integer.parseInt(monThiModel.getValueAt(row, 0).toString());
        }
        return -1;
    }

    public void addAddMonThiListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addDeleteMonThiListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void clearTenMon() {
        txtTenMon.setText("");
    }
}
