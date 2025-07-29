/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.quanlydiemthidaihoc.view;
import com.mycompany.quanlydiemthidaihoc.controller.DiemThiController;
import com.mycompany.quanlydiemthidaihoc.entity.KhoiThi;
import com.mycompany.quanlydiemthidaihoc.entity.KhoiThiXML;
import com.mycompany.quanlydiemthidaihoc.entity.MonThi;
import com.mycompany.quanlydiemthidaihoc.entity.MonThiXML;
import com.mycompany.quanlydiemthidaihoc.entity.DiemThi;
import com.mycompany.quanlydiemthidaihoc.entity.DiemThiXML;
import static com.mycompany.quanlydiemthidaihoc.entity.DiemThiXML.listDiemThi;
import com.mycompany.quanlydiemthidaihoc.entity.ThiSinh;
import com.mycompany.quanlydiemthidaihoc.entity.ThiSinhXML;
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
import java.util.HashSet;
import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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
public class DiemThiView extends javax.swing.JFrame {

    /**
     * Creates new form ResidentView
     */
    public DiemThiView() {
        initComponents(); 
        btnLuu.setEnabled(true);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        tableThongTinThiSinh.setDefaultRenderer(Object.class, new DiemThiView.MyRenderer());
        tableMonVaDiem.setDefaultRenderer(Object.class, new DiemThiView.MyRenderer2());
        tableThiSinhDaNhap.setDefaultRenderer(Object.class, new DiemThiView.MyRenderer2());
        

    }
    
    private ImageIcon ImageIconSize(JLabel label, String filename)
    {
        Image image = new ImageIcon(filename).getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon=new ImageIcon(image);
        //jLabel14.setIcon(new ImageIcon(getCircleImage(imageIcon.getImage())));
        return imageIcon;
    }
    

  public void addAddListener(ActionListener addListener) {
    btnLuu.addActionListener(addListener);
}

public void addEditListener(ActionListener editListener) {
    btnEdit.addActionListener(editListener);
}

public void addDeleteListener(ActionListener deleteListener) {
    btnDelete.addActionListener(deleteListener);
}

public void addClearListener(ActionListener clearListener) {
    btnClear.addActionListener(clearListener);
}

public void addUndoListener(ActionListener undoListener) {
    btnResidentUndo.addActionListener(undoListener);
}
public String getTenThiSinhDangChon() {
    return tableThongTinThiSinh.getValueAt(0, 0).toString();
}

public String getSoBaoDanhDangChon() {
    return tableThongTinThiSinh.getValueAt(0, 1).toString();
}

public String getKhoiThiDangChon() {
    return tableThongTinThiSinh.getValueAt(0, 2).toString();
}


public void loadThiSinhDaNhap(List<DiemThi> list) {
    DefaultTableModel model = (DefaultTableModel) tableThiSinhDaNhap.getModel();
    model.setRowCount(0); // Xóa dữ liệu cũ

    Set<String> sbdDaCo = new HashSet<>();
    for (DiemThi diem : list) {
        if (!sbdDaCo.contains(diem.getSoBaoDanh())) {
            sbdDaCo.add(diem.getSoBaoDanh());
            model.addRow(new Object[]{
                diem.getSoBaoDanh(),
                diem.getTenThiSinh(),
                diem.getKhoiThi(),
                "Xóa" 
            });
        }
    }

    
    tableThiSinhDaNhap.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
    tableThiSinhDaNhap.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox(), tableThiSinhDaNhap, this));

}





  public DiemThi getDiemThiInfo() {
    int selectedRow = tableMonVaDiem.getSelectedRow();
    if (selectedRow == -1) return null;

    String ten = tableThongTinThiSinh.getValueAt(0, 0).toString();
    String sbd = tableThongTinThiSinh.getValueAt(0, 1).toString();
    String khoi = tableThongTinThiSinh.getValueAt(0, 2).toString();
    String mon = tableMonVaDiem.getValueAt(selectedRow, 0).toString();
    String diemStr = tableMonVaDiem.getValueAt(selectedRow, 1).toString().trim();

    try {
        double diem = Double.parseDouble(diemStr);
        
        return new DiemThi(ten, sbd, khoi, mon, diem);
    } catch (NumberFormatException e) {
        showMessage("Điểm không hợp lệ: " + diemStr);
        return null;
    }
}
 public List<DiemThi> getTatCaDiemThiInfo() {
    List<DiemThi> list = new ArrayList<>();

    // Lấy thông tin thí sinh
    String ten = tableThongTinThiSinh.getValueAt(0, 0).toString();
    String sbd = tableThongTinThiSinh.getValueAt(0, 1).toString();
    String khoi = tableThongTinThiSinh.getValueAt(0, 2).toString();

    for (int i = 0; i < tableMonVaDiem.getRowCount(); i++) {
        String mon = tableMonVaDiem.getValueAt(i, 0).toString();
        String diemStr = tableMonVaDiem.getValueAt(i, 1).toString().trim();

        
        diemStr = diemStr.replace(",", ".");

        // Kiểm tra điểm
        try {
            double diem = Double.parseDouble(diemStr);
            if (diem < 0 || diem > 10) {
                showMessage("❌ Điểm phải trong khoảng 0 - 10 tại môn: " + mon);
                return null;
            }
            list.add(new DiemThi(ten, sbd, khoi, mon, diem));
        } catch (NumberFormatException e) {
            showMessage("❌ Điểm không hợp lệ tại môn: " + mon);
            return null;
        }
    }

    return list;
}

public void showDiem(String ten, String sbd, String khoi) {
    // Duyệt từng dòng trong bảng môn và điểm
    listDiemThi = DiemThiXML.docDiemThi();
    for (int i = 0; i < tableMonVaDiem.getRowCount(); i++) {
        String mon = tableMonVaDiem.getValueAt(i, 0).toString();  // Lấy tên môn ở cột đầu tiên

        // Tìm điểm thi ứng với tên, sbd, khối, môn
        Double diem = null;
        for (DiemThi d : listDiemThi) {
            if (d.getTenThiSinh().equals(ten) &&
                d.getSoBaoDanh().equals(sbd) &&
                d.getKhoiThi().equals(khoi) &&
                d.getMonThi().equals(mon)) {
                diem = d.getDiem();
                break;
            }
        }

        // Nếu có điểm -> hiển thị; nếu không có -> để trống
        if (diem != null) {
            tableMonVaDiem.setValueAt(diem, i, 1); // Cột thứ 2 là cột điểm
        } else {
            tableMonVaDiem.setValueAt("", i, 1);
        }
    }
    btnEdit.setEnabled(true);
    btnDelete.setEnabled(true);
    btnLuu.setEnabled(true);
    btnClear.setEnabled(true);
}

public void clearDiemThiInfo() {
    int selectedRow = tableMonVaDiem.getSelectedRow();
    if (selectedRow != -1) {
        // Xóa cột "Điểm" (giả sử cột 1) của dòng đang chọn
        DefaultTableModel model = (DefaultTableModel) tableMonVaDiem.getModel();
        model.setValueAt("", selectedRow, 1); // Cột 1 là cột "Điểm"
    }

    // Không xóa FieldID nếu bạn muốn giữ nguyên SBD
    // FieldID.setText(""); // => Bỏ dòng này nếu chỉ muốn xóa điểm

    // Disable Edit and Delete buttons
    btnEdit.setEnabled(false);
    btnDelete.setEnabled(false);
    // Enable Add button
    btnLuu.setEnabled(true);
}


   public int getSelectedRow() {
    return tableMonVaDiem.getSelectedRow();
}


 public void clearForm() {
    DefaultTableModel model = (DefaultTableModel) tableMonVaDiem.getModel();

    // Tìm chỉ số của cột "Điểm thi"
    int diemColumnIndex = -1;
    for (int i = 0; i < model.getColumnCount(); i++) {
        if ("Điểm thi".equalsIgnoreCase(model.getColumnName(i))) {
            diemColumnIndex = i;
            break;
        }
    }

    // Nếu tìm thấy cột điểm, xóa giá trị trong từng hàng
    if (diemColumnIndex != -1) {
        for (int row = 0; row < model.getRowCount(); row++) {
            model.setValueAt("", row, diemColumnIndex);
        }
    }
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnResidentUndo = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        FieldID = new javax.swing.JTextField();
        comboBoxThiSinh = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableThongTinThiSinh = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableMonVaDiem = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableThiSinhDaNhap = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý điểm thi");
        setName("Quản lý điểm thi"); // NOI18N
        setSize(new java.awt.Dimension(1207, 665));

        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

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

        btnLuu.setBackground(new java.awt.Color(0, 0, 102));
        btnLuu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydiemthidaihoc/view/add.png"));
        btnLuu.setText("Lưu");
        btnLuu.setBorder(null);
        btnLuu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
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

        ImageIcon imageIcon = new ImageIcon("src/main/java/com/mycompany/quanlydiemthidaihoc/view/logoHN.png");
        Image image = imageIcon.getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);
        imageIcon=new ImageIcon(image);
        jLabel2.setIcon(imageIcon);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnResidentUndo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(btnResidentUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 230, 780);
        jPanel2.setOpaque(true);

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

        initComboBoxType();
        comboBoxThiSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxTypeActionPerformed(evt);
            }
        });
        comboBoxThiSinh.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jPanel1.add(comboBoxThiSinh);
        comboBoxThiSinh.setBounds(400, 170, 180, 40);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Chọn Thí Sinh");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(270, 160, 120, 40);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("<html>Quản lý Điểm Thi<br> ");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(620, 0, 300, 80);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setText("Danh Sách Thí Sinh");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(240, 350, 190, 20);

        jScrollPane2.setBackground(new java.awt.Color(0, 102, 255));
        jScrollPane2.setForeground(new java.awt.Color(0, 102, 255));
        // Ví dụ màu xanh nhạt

        tableThongTinThiSinh.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tableThongTinThiSinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
            },
            new String [] {
                "SBD", "Họ và tên", "Khối Thi",
            }
        ));
        jScrollPane2.setViewportView(tableThongTinThiSinh);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(230, 250, 590, 90);

        jScrollPane3.setBackground(new java.awt.Color(0, 102, 255));
        jScrollPane3.setForeground(new java.awt.Color(0, 102, 255));

        tableMonVaDiem.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tableMonVaDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},

            },
            new String [] {
                "Môn Thi", "Điểm"
            }
        ));
        jScrollPane3.setViewportView(tableMonVaDiem);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(820, 160, 450, 180);

        tableThiSinhDaNhap.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tableThiSinhDaNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "SBD", "Họ Tên", "Khối Thi", "Hành Động"
            }
        ));
        jScrollPane4.setViewportView(tableThiSinhDaNhap);

        jPanel1.add(jScrollPane4);
        jScrollPane4.setBounds(230, 380, 1040, 402);

        jLabel9.setIcon(new ImageIcon("src/main/java/com/mycompany/quanlydiemthidaihoc/view/Lovepik_com-500330964-blue-blazed-background.jpg"));
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel9);
        jLabel9.setBounds(-20, -40, 1640, 890);

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

    private void FieldIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldIDActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnResidentUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResidentUndoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResidentUndoActionPerformed
    private void ComboBoxTypeActionPerformed(java.awt.event.ActionEvent evt) {
     KhoiThiXML.loadFromFile("khoithi.xml");   
    String tenThiSinh = comboBoxThiSinh.getSelectedItem().toString();

    // Tìm thí sinh theo tên từ file XML
    ThiSinh ts = ThiSinhXML.getThiSinhTheoTen(tenThiSinh);
    if (ts != null) {
        // Hiển thị thông tin thí sinh lên bảng trái
        hienThiThongTinThiSinh(ts);

        // Lấy khối thi từ thí sinh
        String khoi = ts.getType();
        String sbd = ts.getSBD();

        // Hiển thị môn thi + điểm thi lên bảng phải
        DiemThiXML.docFile("diemthi.xml");
        hienThiBangDiemThi(sbd, khoi); // Bạn cần viết hoặc gọi đúng tên hàm của mình
       showDiem(ts.getName(), ts.getSBD(), ts.getType());
    } else {
        JOptionPane.showMessageDialog(this, "Không tìm thấy thí sinh: " + tenThiSinh);
    }
}
    

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
            java.util.logging.Logger.getLogger(DiemThiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DiemThiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DiemThiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DiemThiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DiemThiView().setVisible(true);
            }
        });
    }
    
    private void initComboBoxType() {
    ThiSinhXML.loadFromFile("thisinh.xml");

    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
    model.addElement("Tất cả");

    for (ThiSinh thisinh : ThiSinhXML.listThiSinh) {
        model.addElement(thisinh.getName());
    }

    comboBoxThiSinh.setModel(model);  
}


    public void hienThiThongTinThiSinh(ThiSinh ts) {
    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(new String[]{"Họ tên", "SBD", "Khối thi"});
    
    // Thêm dòng dữ liệu
    model.addRow(new Object[]{
        ts.getName(), ts.getSBD(), ts.getType()
    });

    // Set model cho bảng và không cho chỉnh sửa
    tableThongTinThiSinh.setModel(model);
    
    // Không cho sửa ô
    tableThongTinThiSinh.setEnabled(false); // hoặc override cell editor để chỉnh chi tiết hơn
}
public void hienThiBangDiemThi(String sbd, String khoi) {
    List<MonThi> danhSachMon = KhoiThiXML.getMonThiTheoKhoi(khoi);
    List<DiemThi> dsDiem = DiemThiXML.getDiemTheoSBD(sbd); // bạn cần viết hoặc đã có

    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 1; // chỉ cột Điểm thi được sửa
        }
    };

    model.setColumnIdentifiers(new String[]{"Môn thi", "Điểm thi"});

    for (MonThi mon : danhSachMon) {
        String tenMon = mon.getTenMon();
        Double diem = timDiemChoMon(dsDiem, tenMon);
        model.addRow(new Object[]{tenMon, diem != null ? diem : ""});
    }

    tableMonVaDiem.setModel(model);
    btnEdit.setEnabled(true);
    btnDelete.setEnabled(true);
    btnLuu.setEnabled(true);
    btnClear.setEnabled(true);
}

    private Double timDiemChoMon(List<DiemThi> dsDiem, String tenMon) {
    for (DiemThi dt : dsDiem) {
        if (dt.getMonThi().equalsIgnoreCase(tenMon)) {
            return dt.getDiem();
        }
    }
    return null;
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
    
 
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField FieldID;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.ButtonGroup btnGroupSearch;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnResidentUndo;
    private javax.swing.JComboBox<String> comboBoxThiSinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tableMonVaDiem;
    private javax.swing.JTable tableThiSinhDaNhap;
    private javax.swing.JTable tableThongTinThiSinh;
    // End of variables declaration//GEN-END:variables
}
