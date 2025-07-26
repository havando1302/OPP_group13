package com.mycompany.quanlydiemthidaihoc.view;

import com.mycompany.quanlydiemthidaihoc.entity.DiemThi;
import com.mycompany.quanlydiemthidaihoc.entity.DiemThiXML;
import com.mycompany.quanlydiemthidaihoc.view.DiemThiView;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean clicked;
    private JTable table;
    private String sbd;
    private DiemThiView diemThiView;

    public ButtonEditor(JCheckBox checkBox, JTable table, DiemThiView diemThiView) {
        super(checkBox);
        this.table = table;
        this.diemThiView = diemThiView;

        button = new JButton("Xóa");
        button.setOpaque(true);

        
        button.addActionListener((ActionEvent e) -> {
            fireEditingStopped();
        });
    }

  @Override
public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {
    this.table = table;
    if (row >= 0 && row < table.getRowCount()) {
        sbd = table.getValueAt(row, 0).toString();
        clicked = true;
    } else {
        clicked = false;
    }
    return button;
}


    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            int confirm = JOptionPane.showConfirmDialog(button,
                    "Bạn có chắc muốn xóa SBD " + sbd + "?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                // Xóa dữ liệu trong DiemThiXML
                DiemThiXML.xoaTheoSBD(sbd);
                
                
                // Đọc lại dữ liệu và load lại bảng
                List<DiemThi> list = DiemThiXML.docDiemThi();
                if (list != null) {
                  diemThiView.loadThiSinhDaNhap(list);
            }


                diemThiView.showMessage("✅ Đã xóa điểm của SBD: " + sbd);
            }
        }
        clicked = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
