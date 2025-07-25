/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.quanlydiemthidaihoc.view;
import com.mycompany.quanlydiemthidaihoc.entity.KhoiThi;
import com.mycompany.quanlydiemthidaihoc.entity.MonThi;
import com.mycompany.quanlydiemthidaihoc.entity.MonThiXML;
import com.raven.chart.Chart;
import com.raven.chart.ModelChart;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author PC
 */
public class KhoiThiView extends javax.swing.JFrame {

    /**
     * Creates new form ResidentView
     */
    private String [] columnNames = new String [] {
        "STT", "Tên Khối Thi", "Môn Thi"};
    private SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
    FlowLayout flowLayout = new FlowLayout();
    private List<KhoiThi> listKhoiThi;
    public KhoiThiView() {
        initComponents();
        monThiPanel.setLayout(new BoxLayout(monThiPanel, BoxLayout.Y_AXIS)); 
        loadMonThiCheckBoxes(); 
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnSearch.setEnabled(true);
        tableKhoiThi.setDefaultRenderer(Object.class, new KhoiThiView.MyRenderer());
       
    }
    
    private static Image getCircleImage(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage circleImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = circleImage.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, width, height);
        graphics.setClip(circle);
        graphics.drawImage(image, 0, 0, null);
        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(2));
        graphics.draw(circle);
        return circleImage;
    }
    
    private ImageIcon ImageIconSize(JLabel label, String filename)
    {
        Image image = new ImageIcon(filename).getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon=new ImageIcon(image);
        //jLabel14.setIcon(new ImageIcon(getCircleImage(imageIcon.getImage())));
        return imageIcon;
    }

  private KhoiThi findKhoiThiByTen(List<KhoiThi> list, String tenKhoi) {
    for (KhoiThi khoi : list) {
        if (khoi.getTenKhoi().equalsIgnoreCase(tenKhoi)) {
            return khoi;
        }
    }
    return null; // Không tìm thấy
}
 public KhoiThi getKhoiThiInfo() {
    int selectedRow = tableKhoiThi.getSelectedRow();

    // Nếu đang thêm mới, thì ID có thể tự sinh, bạn cho id = -1 hoặc bỏ qua.
    int id = -1;
    if (selectedRow != -1) {
        id = (int) tableKhoiThi.getValueAt(selectedRow, 0);
    }

    String tenKhoi = FieldName.getText().trim();

    if (tenKhoi.isEmpty()) {
        throw new IllegalArgumentException("Vui lòng nhập tên khối thi.");
    }

    List<MonThi> selectedMonThis = new ArrayList<>();

    // Duyệt qua tất cả checkbox trong monThiPanel
    for (Component comp : monThiPanel.getComponents()) {
        if (comp instanceof JCheckBox) {
            JCheckBox checkBox = (JCheckBox) comp;
            if (checkBox.isSelected()) {
                String tenMon = checkBox.getText();
                selectedMonThis.add(new MonThi(tenMon));
            }
        }
    }

    return new KhoiThi(id, tenKhoi, selectedMonThis);
}



    private KhoiThi findKhoiThiByID(List<KhoiThi> list, int id) {
    for (KhoiThi khoi : list) {
        if (Objects.equals(khoi.getId(), id))
 {
            return khoi;
        }
    }
    return null; // Không tìm thấy
}




    
    public class MyRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            TableColumnModel columnModel=table.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(10);
            columnModel.getColumn(1).setPreferredWidth(250);
            columnModel.getColumn(2).setPreferredWidth(250);
            JTableHeader header = table.getTableHeader();
            header.setBackground(new Color(0, 0, 139));
            header.setForeground(Color.WHITE);
            header.setFont(new java.awt.Font("Times New Roman", 0, 18));
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            if (!isSelected) {
                if (row % 2 == 0) {
                    c.setBackground(new Color(191, 239, 255));
                } else {
                    c.setBackground(new Color(135, 206, 250));
                }
            } else {
                c.setBackground(new Color(193, 255, 193));
            }

            return c;
        }
    }
    
    public class MyRenderer2 extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            TableColumnModel columnModel=table.getColumnModel();
            //columnModel.getColumn(0).setPreferredWidth(5);
            JTableHeader header = table.getTableHeader();
            header.setBackground(new Color(0, 0, 139));
            header.setForeground(Color.WHITE);
            header.setFont(new java.awt.Font("Times New Roman", 0, 18));
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            if (!isSelected) {
                if (row % 2 == 0) {
                    c.setBackground(new Color(191, 239, 255));
                } else {
                    c.setBackground(new Color(135, 206, 250));
                }
            } else {
                c.setBackground(new Color(193, 255, 193));
            }

            return c;
        }
    }
    
    public static String capitalizeWords(String str) {
        str = str.toLowerCase();
        String[] words = str.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                if (word.equals("tt") || word.equals("tp") || word.equals("tx")) {
                    sb.append(word.toUpperCase());
                } else {
                    sb.append(Character.toUpperCase(word.charAt(0)));
                    sb.append(word.substring(1));
                }
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupSearch = new javax.swing.ButtonGroup();
        SearchDialog = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        FieldSearch = new javax.swing.JTextField();
        btnCancelDialog = new javax.swing.JButton();
        btnSearchDialog = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnCancelSearch = new javax.swing.JButton();
        btnResidentUndo = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKhoiThi = new javax.swing.JTable();
        FieldID = new javax.swing.JTextField();
        FieldName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        monThiPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();

        SearchDialog.setResizable(false);
        SearchDialog.setSize(new java.awt.Dimension(511, 390));

        jPanel3.setLayout(null);

        jLabel18.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel18.setText("Tìm kiếm");
        jPanel3.add(jLabel18);
        jLabel18.setBounds(210, 40, 110, 29);

        jLabel20.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/search.png"));
        jPanel3.add(jLabel20);
        jLabel20.setBounds(30, 130, 30, 30);

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setText("Nhập nội dung tìm kiếm:");
        jPanel3.add(jLabel21);
        jLabel21.setBounds(40, 120, 290, 29);

        FieldSearch.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        FieldSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 102)));
        jPanel3.add(FieldSearch);
        FieldSearch.setBounds(50, 170, 400, 40);
        FieldSearch.setOpaque(false);

        btnCancelDialog.setBackground(new java.awt.Color(255, 255, 255, 0));
        btnCancelDialog.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnCancelDialog.setText("Hủy");
        btnCancelDialog.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.add(btnCancelDialog);
        btnCancelDialog.setBounds(290, 270, 150, 50);
        btnCancelDialog.setBorder(new RoundedBorder(20));
        btnCancelDialog.setOpaque(false);

        btnSearchDialog.setBackground(new java.awt.Color(255, 255, 255, 0));
        btnSearchDialog.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSearchDialog.setText("Tìm kiếm");
        btnSearchDialog.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearchDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchDialogActionPerformed(evt);
            }
        });
        jPanel3.add(btnSearchDialog);
        btnSearchDialog.setBounds(70, 270, 140, 50);
        btnSearchDialog.setBorder(new RoundedBorder(20));
        btnSearchDialog.setOpaque(false);

        jLabel22.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydoituongdacbiet/view/viewSearchView.png"));
        jLabel22.setText("=");
        jPanel3.add(jLabel22);
        jLabel22.setBounds(-10, 0, 520, 360);

        javax.swing.GroupLayout SearchDialogLayout = new javax.swing.GroupLayout(SearchDialog.getContentPane());
        SearchDialog.getContentPane().setLayout(SearchDialogLayout);
        SearchDialogLayout.setHorizontalGroup(
            SearchDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
        );
        SearchDialogLayout.setVerticalGroup(
            SearchDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý khối thi");
        setName("Quản lý khối thi"); // NOI18N
        setSize(new java.awt.Dimension(1207, 665));

        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        btnCancelSearch.setBackground(new java.awt.Color(0, 0, 102));
        btnCancelSearch.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnCancelSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelSearch.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydiemthidaihoc/view/cancel.png"));
        btnCancelSearch.setText("Hủy tìm kiếm");
        btnCancelSearch.setToolTipText("");
        btnCancelSearch.setBorder(null);
        btnCancelSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelSearchActionPerformed(evt);
            }
        });
        btnCancelSearch.setEnabled(false);

        btnResidentUndo.setBackground(new java.awt.Color(0, 0, 102));
        btnResidentUndo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnResidentUndo.setForeground(new java.awt.Color(255, 255, 255));
        btnResidentUndo.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydiemthidaihoc/view/LogOut.png"));
        btnResidentUndo.setText("Quay lại");
        btnResidentUndo.setToolTipText("");
        btnResidentUndo.setBorder(null);
        btnResidentUndo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnResidentUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResidentUndoActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(0, 0, 102));
        btnSearch.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydiemthidaihoc/view/search.png"));
        btnSearch.setText("Tìm kiếm");
        btnSearch.setBorder(null);
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearch.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(0, 0, 102));
        btnAdd.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydiemthidaihoc/view/add.png"));
        btnAdd.setText("Thêm");
        btnAdd.setBorder(null);
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(0, 0, 102));
        btnEdit.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydiemthidaihoc/view/Edit.png"));
        btnEdit.setText("Cập nhật");
        btnEdit.setBorder(null);
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(0, 0, 102));
        btnDelete.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydiemthidaihoc/view/delete.png"));
        btnDelete.setText("Xóa");
        btnDelete.setBorder(null);
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(0, 0, 102));
        btnClear.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydiemthidaihoc/view/trash.png"));
        btnClear.setText("Làm mới");
        btnClear.setToolTipText("");
        btnClear.setBorder(null);
        btnClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnResidentUndo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(199, 199, 199))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnCancelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnResidentUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 230, 780);
        jPanel2.setOpaque(true);

        jScrollPane1.setBackground(new java.awt.Color(0, 51, 153, 125));

        tableKhoiThi.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tableKhoiThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            }, columnNames
        ));
        tableKhoiThi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tableKhoiThi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableKhoiThi.setRowHeight(30);
        jScrollPane1.setViewportView(tableKhoiThi);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(240, 440, 1030, 260);

        FieldID.setEditable(false);
        FieldID.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        FieldID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FieldID.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 51, 102)));
        FieldID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldIDActionPerformed(evt);
            }
        });
        jPanel1.add(FieldID);
        FieldID.setBounds(390, 50, 70, 40);
        FieldID.setOpaque(false);
        FieldID.setVisible(false);

        FieldName.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        FieldName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 51, 102)));
        FieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldNameActionPerformed(evt);
            }
        });
        jPanel1.add(FieldName);
        FieldName.setBounds(440, 220, 250, 40);
        FieldName.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Tên Khối  Thi");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(280, 220, 120, 40);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("<html>Quản lý Khối Thi<br> ");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(620, 0, 300, 80);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setText("Chọn môn Thi");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(740, 230, 140, 30);

        javax.swing.GroupLayout monThiPanelLayout = new javax.swing.GroupLayout(monThiPanel);
        monThiPanel.setLayout(monThiPanelLayout);
        monThiPanelLayout.setHorizontalGroup(
            monThiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        monThiPanelLayout.setVerticalGroup(
            monThiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jPanel1.add(monThiPanel);
        monThiPanel.setBounds(940, 110, 110, 300);

        jLabel9.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydiemthidaihoc/view/Lovepik_com-500330964-blue-blazed-background.jpg"));
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel9);
        jLabel9.setBounds(-30, -30, 1640, 890);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearActionPerformed

    private void FieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldNameActionPerformed

    private void FieldIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldIDActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnResidentUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResidentUndoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResidentUndoActionPerformed

    private void btnCancelSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelSearchActionPerformed

    private void btnSearchDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchDialogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchDialogActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KhoiThiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhoiThiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhoiThiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhoiThiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KhoiThiView().setVisible(true);
            }
        });
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    public class RoundedBorder implements Border {
        private int radius;
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }
        public boolean isBorderOpaque() {
            return true;
        }
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
    
 
    public void showListKhoiThi(List<KhoiThi> list) {
    if (list == null) return; 

    int size = list.size();
    Object[][] data = new Object[size][3];

    for (int i = 0; i < size; i++) {
        KhoiThi khoi = list.get(i);

        // Cột 0: ID
        data[i][0] = khoi.getId();

        // Cột 1: Tên khối
        data[i][1] = khoi.getTenKhoi();

        // Cột 2: Danh sách môn thi (ghép chuỗi)
        List<MonThi> monThiList = khoi.getMonThiList();
        StringBuilder monThiNames = new StringBuilder();

        if (monThiList != null && !monThiList.isEmpty()) {
            for (int j = 0; j < monThiList.size(); j++) {
                monThiNames.append(monThiList.get(j).getTenMon());
                if (j < monThiList.size() - 1) {
                    monThiNames.append(", ");
                }
            }
        } else {
            monThiNames.append("Không có môn");
        }

        data[i][2] = monThiNames.toString();
    }

   
    DefaultTableModel model = new DefaultTableModel(data, columnNames) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; 
        }
    };

    tableKhoiThi.setModel(model);
    tableKhoiThi.getColumnModel().getColumn(0).setPreferredWidth(30); 
    tableKhoiThi.getColumnModel().getColumn(1).setPreferredWidth(100); 
    tableKhoiThi.getColumnModel().getColumn(2).setPreferredWidth(200); 
}


    
    
   public void showKhoiThi(KhoiThi khoi) {
    
    FieldName.setText(khoi.getTenKhoi());

    
    for (Component comp : monThiPanel.getComponents()) {
        if (comp instanceof JCheckBox) {
            JCheckBox checkBox = (JCheckBox) comp;
            checkBox.setSelected(false); // reset tất cả
        }
    }

    
    List<MonThi> monThiList = khoi.getMonThiList();
    for (MonThi mon : monThiList) {
        for (Component comp : monThiPanel.getComponents()) {
            if (comp instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) comp;
                if (checkBox.getText().equalsIgnoreCase(mon.getTenMon())) {
                    checkBox.setSelected(true);
                }
            }
        }
    }

   
    btnEdit.setEnabled(true);
    btnDelete.setEnabled(true);
    btnAdd.setEnabled(false);
    btnClear.setEnabled(true);
}

    
 public void fillKhoiThiFromSelectedRow(List<KhoiThi> list) throws ParseException {
    int row = tableKhoiThi.getSelectedRow();

    if (row >= 0) {
        Object idObject = tableKhoiThi.getModel().getValueAt(row, 0);

        if (idObject != null) {
            int khoiID = Integer.parseInt(idObject.toString());
            KhoiThi selectedKhoi = findKhoiThiByID(list, khoiID);

            if (selectedKhoi != null) {
                // Gán thông tin cho text field
                FieldID.setText(String.valueOf(selectedKhoi.getId()));
                FieldName.setText(selectedKhoi.getTenKhoi());

                // Reset tất cả checkbox
                for (Component comp : monThiPanel.getComponents()) {
                    if (comp instanceof JCheckBox) {
                        ((JCheckBox) comp).setSelected(false);
                    }
                }

                // Tích lại checkbox các môn thi đã chọn
                List<MonThi> monThiList = selectedKhoi.getMonThiList();
                for (MonThi mon : monThiList) {
                    for (Component comp : monThiPanel.getComponents()) {
                        if (comp instanceof JCheckBox) {
                            JCheckBox checkBox = (JCheckBox) comp;
                            if (checkBox.getText().equalsIgnoreCase(mon.getTenMon())) {
                                checkBox.setSelected(true);
                            }
                        }
                    }
                }

                // Cập nhật trạng thái nút
                btnEdit.setEnabled(true);
                btnDelete.setEnabled(true);
                btnAdd.setEnabled(false);
                btnClear.setEnabled(true);
            }
        }
    }
}


 
    
   public void fillKhoiThiFromSelectedRow() throws ParseException {
    int row = tableKhoiThi.getSelectedRow();

    if (row >= 0) {
        // Lấy ID khối thi từ cột 0
        Object idObject = tableKhoiThi.getModel().getValueAt(row, 0);
        if (idObject != null) {
            int khoiID = Integer.parseInt(idObject.toString());

            // Sử dụng danh sách khối thi đang hiển thị
            List<KhoiThi> list = this.listKhoiThi; // hoặc gọi từ controller nếu bạn có getter

            KhoiThi selectedKhoi = findKhoiThiByID(list, khoiID);

            if (selectedKhoi != null) {
                FieldID.setText(String.valueOf(selectedKhoi.getId()));
                FieldName.setText(selectedKhoi.getTenKhoi());

                // Reset checkbox
                for (Component comp : monThiPanel.getComponents()) {
                    if (comp instanceof JCheckBox) {
                        ((JCheckBox) comp).setSelected(false);
                    }
                }

                // Tích lại các checkbox đúng với môn thi trong khối thi
                List<MonThi> monThiList = selectedKhoi.getMonThiList();
                if (monThiList != null) {
                    for (MonThi mon : monThiList) {
                        for (Component comp : monThiPanel.getComponents()) {
                            if (comp instanceof JCheckBox) {
                                JCheckBox checkBox = (JCheckBox) comp;
                                if (checkBox.getText().equalsIgnoreCase(mon.getTenMon())) {
                                    checkBox.setSelected(true);
                                }
                            }
                        }
                    }
                }

                btnEdit.setEnabled(true);
                btnDelete.setEnabled(true);
                btnAdd.setEnabled(false);
                btnClear.setEnabled(true);
            }
        }
    }
}



 
    
    public void clearKhoiThiInfo() {
        FieldID.setText("");
        FieldName.setText("");
        // disable Edit and Delete buttons
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        // enable Add button
        btnAdd.setEnabled(true);
    }
    
   
    public void SearchKhoiThiInfo() {
        //FrameSearch = new ManagerView();
        SearchDialog.setVisible(true);
    }
    
  private void loadMonThiCheckBoxes() {
    List<MonThi> listMonThi = MonThiXML.readFromFile("monthi.xml");
    monThiPanel.removeAll(); // Xóa hết nếu đã có

    for (MonThi mt : listMonThi) {
        JCheckBox checkBox = new JCheckBox(mt.getTenMon());
        monThiPanel.add(checkBox);
    }

    monThiPanel.revalidate();
    monThiPanel.repaint();
}


    
   
    
    
    
    
    public void searchKhoiThiInfo() {
        //FrameSearch = new ManagerView();
        SearchDialog.setVisible(true);
    }
    
    public void cancelDialogSearchKhoiThiInfo() {
        //FrameSearch = new ManagerView();
        SearchDialog.setVisible(false);
    }
    
    
    public void cancelSearchKhoiThi()
    {
        String id=FieldID.getText();
        btnCancelSearch.setEnabled(false);
        btnSearch.setEnabled(true);
        btnClear.setEnabled(true);
        if (id == null || "".equals(id.trim()))
        {
            
            btnAdd.setEnabled(true);
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
        }
        else
        {
            btnAdd.setEnabled(false);
            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
        }
    }
    
    public String validateSearch(){
        String search = FieldSearch.getText();
        if (search == null || "".equals(search.trim())) {
            FieldSearch.requestFocus();
            showMessage("Nội dung tìm kiếm không hợp lệ!");
            return "";
        }
        btnCancelSearch.setEnabled(true);
        SearchDialog.setVisible(false);
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnClear.setEnabled(false);
        btnSearch.setEnabled(false);
        return search;
    }
    
    public void addUndoListener(ActionListener listener){
        btnResidentUndo.addActionListener(listener);
    }
    
    public void addAddKhoiThiListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }
    
    public void addListKhoiThiSelectionListener(ListSelectionListener listener) {
        tableKhoiThi.getSelectionModel().addListSelectionListener(listener);
    }
    
    public void addEditKhoiThiListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }
    
    public void addClearListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }
    
    public void addDeleteKhoiThiListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }
    
    public void addSearchListener(ActionListener listener) {
        btnSearch.addActionListener(listener);
    }
    
    public void addSearchDialogListener(ActionListener listener) {
        btnSearchDialog.addActionListener(listener);
    }
    
    public void addCancelSearchListener(ActionListener listener){
        btnCancelSearch.addActionListener(listener);
    }
    
    public void addCancelDialogListener(ActionListener listener){
        btnCancelDialog.addActionListener(listener);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField FieldID;
    private javax.swing.JTextField FieldName;
    private javax.swing.JTextField FieldSearch;
    private javax.swing.JDialog SearchDialog;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelDialog;
    private javax.swing.JButton btnCancelSearch;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.ButtonGroup btnGroupSearch;
    private javax.swing.JButton btnResidentUndo;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchDialog;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel monThiPanel;
    private javax.swing.JTable tableKhoiThi;
    // End of variables declaration//GEN-END:variables
}
