package com.mycompany.quanlydiemthidaihoc.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.table.JTableHeader;

public class ManagerMonThiView extends JFrame {
    private JTextField txtId = new JTextField(10);
    private JTextField txtMaMon = new JTextField(15);
    private JTextField txtTenMon = new JTextField(15);
    private JLabel lblTongMon = new JLabel("0");

    private JButton btnAdd = new JButton("Thêm");   
    private JButton btnUpdate = new JButton("Cập nhật");
    private JButton btnDelete = new JButton("Xóa");
    private JButton btnReset = new JButton("Làm mới");
    private JButton btnSearch = new JButton("Tìm kiếm");
    private JButton btnCancelSearch = new JButton("Hủy tìm kiếm");
    private JButton btnBack = new JButton("Quay lại");

    private JPanel leftPanel = new JPanel(); 
    private JCheckBox checkSortByName = new JCheckBox("Sắp xếp theo tên môn");
    private JCheckBox checkSortByMa = new JCheckBox("Sắp xếp theo mã môn");
    private JButton btnSort = new JButton("Sắp xếp");

//  private JTextField txtSearch = new JTextField(15);
//  private JComboBox<String> cbSearchType = new JComboBox<>(new String[]{"Mã môn", "Tên môn"});
    
    private JTable table;
    private DefaultTableModel tableModel;

    public ManagerMonThiView() {
        Font defaultFont = new Font("Times New Roman", Font.BOLD, 16);

        // Áp dụng font mặc định cho toàn bộ UI (trừ JTable)
        UIManager.put("Label.font", defaultFont);
        UIManager.put("Button.font", defaultFont);
        UIManager.put("TextField.font", defaultFont);
        UIManager.put("CheckBox.font", defaultFont);
        UIManager.put("ComboBox.font", defaultFont);
        UIManager.put("RadioButton.font", defaultFont);
        UIManager.put("TitledBorder.font", defaultFont);
        UIManager.put("ToolTip.font", defaultFont);

        // Riêng JTable set font riêng (không bold)
        UIManager.put("Table.font", new Font("Times New Roman", Font.PLAIN, 16));
        
        
        setTitle("Quản lý Môn Thi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ========== LEFT PANEL ==========
//        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(51, 154, 252));
        leftPanel.setPreferredSize(new Dimension(250, 0));

//        // ======= Thêm ảnh logo hình tròn =======
//        ImageIcon icon;
//        icon = new ImageIcon(getClass().getResource("/com/mycompany/quanlydiemthidaihoc/view/logoHN.png"));
//        Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//        JLabel lblLogo = new JLabel(new ImageIcon(scaledImage));
//        System.out.println("Icon: " + icon); // test
//        
//        // Căn giữa và thêm khoảng cách
//        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
//        lblLogo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // top, left, bottom, right
//
//        leftPanel.add(lblLogo);
        
        JLabel title = new JLabel("Chọn tiêu chí sắp xếp:");
        title.setForeground(Color.BLACK);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        checkSortByMa.setBackground(new Color(51, 154, 252));
        checkSortByMa.setForeground(Color.BLACK);
        checkSortByName.setBackground(new Color(51, 154, 252));
        checkSortByName.setForeground(Color.BLACK);

        btnSort.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCancelSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);

//        // Trên cùng sidebar:
//        leftPanel.add(Box.createVerticalStrut(20));
//        leftPanel.add(lblLogo); // hình ảnh logo căn giữa
//        leftPanel.add(Box.createVerticalStrut(20));
//        leftPanel.add(title);   // "Chọn tiêu chí sắp xếp"

        // Các nút:
        leftPanel.add(Box.createVerticalStrut(50));
        leftPanel.add(title);
        leftPanel.add(Box.createVerticalStrut(15));
        leftPanel.add(checkSortByMa);
        leftPanel.add(Box.createVerticalStrut(15));
        leftPanel.add(checkSortByName);
        leftPanel.add(Box.createVerticalStrut(25));
        leftPanel.add(btnSort);
        leftPanel.add(Box.createVerticalStrut(25));
        leftPanel.add(btnSearch);
        leftPanel.add(Box.createVerticalStrut(25));
        leftPanel.add(btnCancelSearch);
        leftPanel.add(Box.createVerticalStrut(25));
        
//        leftPanel.add(new JLabel("Tổng số môn:"));
//        lblTongMon.setForeground(Color.WHITE);
//        lblTongMon.setFont(new Font("Times new roman", Font.BOLD, 16));
//        leftPanel.add(Box.createVerticalStrut(25));
//        leftPanel.add(lblTongMon);

        JPanel tongMonPanel = new JPanel();
        tongMonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tongMonPanel.setOpaque(false); // Giữ cho nền trong suốt nếu leftPanel có màu

        JLabel lblTitle = new JLabel("Tổng số môn:");
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 16));

        lblTongMon.setForeground(Color.BLACK);
        lblTongMon.setFont(new Font("Times New Roman", Font.BOLD, 16));

        tongMonPanel.add(lblTitle);
        tongMonPanel.add(lblTongMon);

        // Thêm vào leftPanel
        leftPanel.add(tongMonPanel);
//        leftPanel.add(Box.createVerticalStrut(25)); 

//        leftPanel.add(Box.createVerticalStrut(25));
        leftPanel.add(btnBack);
        add(leftPanel, BorderLayout.WEST);
        
        checkSortByMa.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkSortByName.setAlignmentX(Component.CENTER_ALIGNMENT);
//        lblTongMon.setAlignmentX(Component.CENTER_ALIGNMENT);
//        leftPanel.add(checkSortByMa);
//        leftPanel.add(Box.createVerticalStrut(15));
//        leftPanel.add(checkSortByName);
//        leftPanel.add(Box.createVerticalStrut(15));
//        leftPanel.add(btnSort);
//        leftPanel.add(Box.createVerticalStrut(15));

//        leftPanel.add(new JLabel(" "));
//      leftPanel.add(cbSearchType);
//      leftPanel.add(txtSearch);
//        leftPanel.add(btnSearch);
//        leftPanel.add(btnCancelSearch);
        
//        leftPanel.add(Box.createVerticalStrut(25)); // khoảng cách giữa các nút
//        leftPanel.add(btnSort);
//        leftPanel.add(Box.createVerticalStrut(25));
//        leftPanel.add(btnSearch);
//        leftPanel.add(Box.createVerticalStrut(25));
//        leftPanel.add(btnCancelSearch);
//        leftPanel.add(Box.createVerticalStrut(25));

//        leftPanel.add(Box.createVerticalStrut(10));
        
        styleButton(btnSort);
        styleButton(btnSearch);
        styleButton(btnCancelSearch);
        styleButton(btnBack);
      
        // ========== RIGHT PANEL ==========
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        // Nhập liệu
        JPanel formPanel = new JPanel(new BorderLayout());

        // Tiêu đề căn giữa
        JLabel titleLabel = new JLabel("Quản lý môn thi", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times new roman", Font.BOLD, 30));  
        formPanel.add(titleLabel, BorderLayout.NORTH);
        
        titleLabel.setPreferredSize(new Dimension(0, 100)); // Tăng chiều cao
        // Căn giữa dọc
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Panel chứa các ô nhập liệu
//        JPanel inputPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); // padding

        JLabel lblMa = new JLabel("Mã môn:");
        lblMa.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // khoảng cách phải
        inputPanel.add(lblMa);
        inputPanel.add(txtMaMon);
        txtMaMon.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txtMaMon.setOpaque(false);              // Bỏ nền mặc định
        txtMaMon.setBackground(new Color(0,0,0,0));  // Màu nền trong suốt (alpha = 0)
        
        inputPanel.add(Box.createHorizontalStrut(30)); // khoảng cách giữa các cặp
        JLabel lblTen = new JLabel("Tên môn:");
        lblTen.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        inputPanel.add(lblTen);
        inputPanel.add(txtTenMon);
        txtTenMon.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txtTenMon.setOpaque(false);              // Bỏ nền mặc định
        txtTenMon.setBackground(new Color(0,0,0,0));  // Màu nền trong suốt (alpha = 0)
        
        inputPanel.add(new JLabel());  // trống để lấp grid
//        inputPanel.setPreferredSize(new Dimension(0, 100)); // Tăng chiều cao
        formPanel.add(inputPanel, BorderLayout.CENTER);

        // Nút chức năng chính
//        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
//        buttonPanel.add(btnAdd);
//        buttonPanel.add(btnUpdate);
//        buttonPanel.add(btnDelete);
//        buttonPanel.add(btnReset);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));  // Layout dọc

        // Thêm glue ở trên để đẩy nút xuống
        buttonPanel.add(Box.createVerticalGlue());

        // Panel chứa nút theo FlowLayout
        JPanel innerButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        innerButtonPanel.add(btnAdd);
        innerButtonPanel.add(btnUpdate);
        innerButtonPanel.add(btnDelete);
        innerButtonPanel.add(btnReset);

        // Thêm vào chính giữa
        buttonPanel.add(innerButtonPanel);

        // Thêm glue phía dưới để đẩy lên giữa
        buttonPanel.add(Box.createVerticalGlue());

        // Đặt kích thước chiều cao mong muốn
        buttonPanel.setPreferredSize(new Dimension(0, 100));        
        
        // Bảng dữ liệu
        tableModel = new DefaultTableModel(new String[]{"ID", "Mã môn", "Tên môn"}, 0);
        table = new JTable(tableModel);
        
        // Header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Times new roman", Font.BOLD, 16));
        header.setBackground(new Color(7, 8, 134));
        header.setForeground(Color.WHITE);

        // Custom renderer
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground((row % 2 == 0) ? new Color(135, 206, 250) : new Color(191, 239, 255));
                } else {
                    c.setBackground(new Color(100, 149, 237));
                }
                c.setFont(new Font("Times new roman", Font.PLAIN, 16));
                return c;
            }
        });

       JScrollPane scrollPane = new JScrollPane(table);
table.getSelectionModel().addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
        int selectedRow = table.getSelectedRow();

        Object idObj = tableModel.getValueAt(selectedRow, 0);
        Object maMonObj = tableModel.getValueAt(selectedRow, 1);
        Object tenMonObj = tableModel.getValueAt(selectedRow, 2);

        String id = (idObj != null) ? idObj.toString() : "";
        String maMon = (maMonObj != null) ? maMonObj.toString() : "";
        String tenMon = (tenMonObj != null) ? tenMonObj.toString() : "";

        // Gán dữ liệu vào ô nhập
//      txtId.setText(id);
        txtMaMon.setText(maMon);
        txtTenMon.setText(tenMon);
    }
});

        table.setRowHeight(20);

        scrollPane.setPreferredSize(new Dimension(600, 280)); // 13 dòng * 20px
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        rightPanel.add(formPanel, BorderLayout.NORTH);
        rightPanel.add(buttonPanel, BorderLayout.CENTER);
        rightPanel.add(scrollPane, BorderLayout.SOUTH);

        add(rightPanel, BorderLayout.CENTER);

        checkSortByMa.addActionListener(e -> {
            if (checkSortByMa.isSelected()) {
                checkSortByName.setSelected(false);
            }
        });

        checkSortByName.addActionListener(e -> {
            if (checkSortByName.isSelected()) {
                checkSortByMa.setSelected(false);
            }
        });
        
        styleButton(btnAdd);
        styleButton(btnUpdate);
        styleButton(btnDelete);
        styleButton(btnReset);

//JPanel sidebar = new JPanel() {
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        ImageIcon icon = new ImageIcon("Lovepik_com-500330964-blue-blazed-background.jpg");
//        g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
//    }
//};
//sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

    }

    public String getId() {
        return txtId.getText().trim();
    }
    
    public String getMaMon() {
        return txtMaMon.getText().trim();
    }

    public String getTenMon() {
        return txtTenMon.getText().trim();
    }
    
    public JButton getBtnAdd() {
        return btnAdd;
    }
    
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
    
    public int getSelectedMonThiId() {
        int row = table.getSelectedRow();
        if (row == -1) return -1;
        return Integer.parseInt(tableModel.getValueAt(row, 0).toString());
    }

    public JButton getBtnCancelSearch() {
        return btnCancelSearch;
    }
    
    public void clearMaMon() {
        txtMaMon.setText("");
    }

    public void clearTenMon() {
        txtTenMon.setText("");
    }

//    public String getSearchKeyword() {
//        return txtSearch.getText().trim();
//    }
//
//    public String getSearchType() {
//        return cbSearchType.getSelectedItem().toString();
//    }
//    
//    public void setSearchText(String text) {
//        txtSearch.setText(text);
//    }

    public void setMonThiTable(List<String[]> data) {
        tableModel.setRowCount(0);
        int id = 1;
        for (String[] row : data) {
            tableModel.addRow(new Object[]{id++, row[1], row[2]});
        }
        lblTongMon.setText(String.valueOf(data.size()));
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public JCheckBox getCheckSortByName() {
        return checkSortByName;
    }

    public JCheckBox getCheckSortByMa() {
        return checkSortByMa;
    }
    
    public JButton getBtnSort() {
        return btnSort;
    }
    
    public JButton getBtnSearch() {
        return btnSearch;
    }
    
    public void addAddMonThiListener(java.awt.event.ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addUpdateMonThiListener(java.awt.event.ActionListener listener) {
        btnUpdate.addActionListener(listener);
    }

    public void addDeleteMonThiListener(java.awt.event.ActionListener listener) {
        btnDelete.addActionListener(listener);
    }

    public void addSearchListener(java.awt.event.ActionListener listener) {
        btnSearch.addActionListener(listener);
    }
    public void addUndoListener(ActionListener undoListener) {
    btnBack.addActionListener(undoListener);
    }

//    public void addShowAllListener(java.awt.event.ActionListener listener) {
//        btnShowAll.addActionListener(listener);
//    }
        
    public String[] showSearchDialog() {
        JTextField txtKeyword = new JTextField(50);
        txtKeyword.setOpaque(false); // Nền trong suốt
        txtKeyword.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        txtKeyword.setFont(new Font("Times new roman", Font.PLAIN, 18));

        // Tạo dòng kẻ đen dưới ô nhập
        JPanel keywordPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.drawLine(10, getHeight() - 5, 210, getHeight() - 5); // dòng kẻ 100px
            }
        };
        keywordPanel.setLayout(new BorderLayout());
        keywordPanel.setOpaque(false);
        keywordPanel.add(txtKeyword, BorderLayout.CENTER);
        keywordPanel.setPreferredSize(new Dimension(200, 40)); // Chiều cao phù hợp

        JComboBox<String> combo = new JComboBox<>(new String[]{"Mã môn", "Tên môn"});
        combo.setPreferredSize(new Dimension(200, 30));

        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, Color.WHITE, getWidth(), getHeight(), Color.decode("#97d5f8"));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(500, 250)); // Panel to hơn
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Chọn tiêu chí tìm kiếm:"), gbc);

        gbc.gridx = 1;
        panel.add(combo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Nhập nội dung tìm kiếm:"), gbc);

        gbc.gridx = 1;
        panel.add(keywordPanel, gbc);

        // Tùy chỉnh nút
        UIManager.put("OptionPane.okButtonText", "Tìm kiếm");
        UIManager.put("OptionPane.cancelButtonText", "Cancel");

        UIManager.put("Button.background", new Color( 4, 4, 99));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("Times new roman", Font.BOLD, 16));

        mainPanel.add(panel);
        int result = JOptionPane.showConfirmDialog(this, panel, "Tìm kiếm", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return new String[]{combo.getSelectedItem().toString(), txtKeyword.getText().trim()};
        } else {
            return null;
        }
    }
    
    private void styleButton(JButton button) {
        button.setMaximumSize(new Dimension(250, 40)); // full chiều ngang của sidebar
        button.setBackground(new Color(4, 4, 99));      // màu #040463
        button.setForeground(Color.WHITE);             // chữ trắng
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Times New Roman", Font.BOLD, 16));

        button.setPreferredSize(new Dimension(150, 35));
    }

}