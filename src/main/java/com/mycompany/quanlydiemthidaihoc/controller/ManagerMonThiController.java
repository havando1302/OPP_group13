package com.mycompany.quanlydiemthidaihoc.controller;

import com.mycompany.quanlydiemthidaihoc.action.ManagerMonThi;
import com.mycompany.quanlydiemthidaihoc.entity.MonThi;
import com.mycompany.quanlydiemthidaihoc.view.ManagerMonThiView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

public class ManagerMonThiController {

    private ManagerMonThiView monThiView;
    private ManagerMonThi managerMonThi;

    public ManagerMonThiController(ManagerMonThiView view) {
        this.monThiView = view;
        this.managerMonThi = new ManagerMonThi();

        monThiView.addAddMonThiListener(new AddMonThiListener());
        monThiView.addDeleteMonThiListener(new DeleteMonThiListener());

        loadMonThiTable();
    }

    private void loadMonThiTable() {
        monThiView.setMonThiTable(
            managerMonThi.getListMonThi()
                .stream()
                .map(mt -> new String[]{String.valueOf(mt.getId()), mt.getTenMon()})
                .collect(Collectors.toList())
        );
    }

    class AddMonThiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String tenMon = monThiView.getTenMon();
            if (tenMon.isEmpty()) {
                monThiView.showMessage("Tên môn thi không được để trống!");
                return;
            }
            MonThi monThi = new MonThi();
            monThi.setTenMon(tenMon);
            managerMonThi.add(monThi);
            loadMonThiTable();
            monThiView.clearTenMon();
            monThiView.showMessage("Thêm môn thi thành công!");
        }
    }

    class DeleteMonThiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int id = monThiView.getSelectedMonThiId();
            if (id == -1) {
                monThiView.showMessage("Vui lòng chọn môn thi để xóa!");
                return;
            }
            MonThi monThi = new MonThi();
            monThi.setId(id);
            managerMonThi.delete(monThi);
            loadMonThiTable();
            monThiView.showMessage("Xóa môn thi thành công!");
        }
    }
}
