package com.mycompany.quanlydiemthidaihoc.view;

import com.mycompany.quanlydiemthidaihoc.controller.ManagerKhoiThiController;
import com.mycompany.quanlydiemthidaihoc.entity.KhoiThi;
import com.mycompany.quanlydiemthidaihoc.entity.MonThi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ManagerKhoiThiView extends JFrame {

    private final JTextField txtTenKhoi;
    private final JPanel panelCheckBoxMonThi;
    private final JTable tableKhoiThi;
    private final DefaultTableModel tableModel;
    private final JButton btnThem, btnXoa, btnSua;

    private final List<JCheckBox> monThiCheckBoxes = new ArrayList<>();

    private List<KhoiThi> currentKhoiThiList = new ArrayList<>();

    public ManagerKhoiThiView() {
        setTitle("Quản lý Khối Thi");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // ======== BẢNG HIỂN THỊ ==========
        String[] columnNames = {"Tên Khối Thi", "Môn Thi"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableKhoiThi = new JTable(tableModel);
        JScrollPane scrollPaneTable = new JScrollPane(tableKhoiThi);

        // ======== PANEL INPUT ==========
        JPanel panelInput = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTenKhoi = new JLabel("Tên khối thi:");
        gbc.gridx = 0; gbc.gridy = 0;
        panelInput.add(lblTenKhoi, gbc);

        txtTenKhoi = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        panelInput.add(txtTenKhoi, gbc);

        JLabel lblMonThi = new JLabel("Danh sách môn thi:");
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelInput.add(lblMonThi, gbc);

        panelCheckBoxMonThi = new JPanel();
        panelCheckBoxMonThi.setLayout(new BoxLayout(panelCheckBoxMonThi, BoxLayout.Y_AXIS));
        JScrollPane scrollCheckBox = new JScrollPane(panelCheckBoxMonThi);
        scrollCheckBox.setPreferredSize(new Dimension(200, 120));

        gbc.gridx = 1; gbc.gridy = 1;
        panelInput.add(scrollCheckBox, gbc);

        // ======== BUTTONS ==========
        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xóa");
        btnSua = new JButton("Sửa");

        JPanel panelButton = new JPanel();
        panelButton.add(btnThem);
        panelButton.add(btnSua);
        panelButton.add(btnXoa);

        JPanel panelForm = new JPanel(new BorderLayout());
        panelForm.add(panelInput, BorderLayout.CENTER);
        panelForm.add(panelButton, BorderLayout.SOUTH);

        // ======== JSplitPane ==========
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(scrollPaneTable);
        splitPane.setBottomComponent(panelForm);
        splitPane.setDividerLocation(250);

        add(splitPane, BorderLayout.CENTER);

        // ======== Table selection listener ==========
        tableKhoiThi.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && getSelectedKhoiIndex() >= 0) {
                loadSelectedKhoiThiToForm();
            }
        });
    }

    // === GETTERS ===
    public String getTenKhoi() {
        return txtTenKhoi.getText().trim();
    }

    public List<MonThi> getSelectedMonThi() {
        List<MonThi> selected = new ArrayList<>();
        for (JCheckBox cb : monThiCheckBoxes) {
            if (cb.isSelected()) {
                selected.add((MonThi) cb.getClientProperty("MonThi"));
            }
        }
        return selected;
    }

    public int getSelectedKhoiIndex() {
        return tableKhoiThi.getSelectedRow();
    }

    public KhoiThi getSelectedKhoiThi() {
        int index = getSelectedKhoiIndex();
        if (index >= 0 && index < currentKhoiThiList.size()) {
            return currentKhoiThiList.get(index);
        }
        return null;
    }

    // === SETTERS ===
    public void setMonThiList(List<MonThi> monThiList) {
        panelCheckBoxMonThi.removeAll();
        monThiCheckBoxes.clear();

        for (MonThi mt : monThiList) {
            JCheckBox cb = new JCheckBox(mt.getTenMon());
            cb.putClientProperty("MonThi", mt);
            monThiCheckBoxes.add(cb);
            panelCheckBoxMonThi.add(cb);
        }

        panelCheckBoxMonThi.revalidate();
        panelCheckBoxMonThi.repaint();
    }

    public void setKhoiThiTable(List<KhoiThi> khoiThiList) {
        currentKhoiThiList = khoiThiList;
        tableModel.setRowCount(0);
        for (KhoiThi k : khoiThiList) {
            String tenKhoi = k.getTenKhoi();
            String monThi = k.getMonThiList().stream()
                    .map(MonThi::getTenMon)
                    .reduce((a, b) -> a + ", " + b).orElse("");
            tableModel.addRow(new Object[]{tenKhoi, monThi});
        }
    }

    public void clearForm() {
        txtTenKhoi.setText("");
        for (JCheckBox cb : monThiCheckBoxes) {
            cb.setSelected(false);
        }
    }

    private void loadSelectedKhoiThiToForm() {
        KhoiThi selected = getSelectedKhoiThi();
        if (selected != null) {
            txtTenKhoi.setText(selected.getTenKhoi());
            for (JCheckBox cb : monThiCheckBoxes) {
                MonThi mt = (MonThi) cb.getClientProperty("MonThi");
                cb.setSelected(selected.getMonThiList().contains(mt));
            }
        }
    }

    // === LISTENERS ===
    public void addAddListener(ActionListener l) {
        btnThem.addActionListener(l);
    }

    

    public void addDeleteListener(ActionListener l) {
        btnXoa.addActionListener(l);
    }

    // === MESSAGES ===
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // === TEST ===
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ManagerKhoiThiView view = new ManagerKhoiThiView();
            view.setVisible(true);
        });
    }

   public void addUpdateListener(ActionListener listener) {
    btnSua.addActionListener(listener);
}

}
