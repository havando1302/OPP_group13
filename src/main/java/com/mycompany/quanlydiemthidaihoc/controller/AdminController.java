package com.mycompany.quanlydiemthidaihoc.controller;

import com.mycompany.quanlydiemthidaihoc.action.ManagerKhoiThi;
import com.mycompany.quanlydiemthidaihoc.view.AdminMainView;
import com.mycompany.quanlydiemthidaihoc.view.ManagerView;
import com.mycompany.quanlydiemthidaihoc.view.KhoiThiView;
import com.mycompany.quanlydiemthidaihoc.view.ManagerMonThiView;
import com.mycompany.quanlydiemthidaihoc.view.DiemThiView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController {
    private final AdminMainView adminView;

    public AdminController(AdminMainView view) {
        this.adminView = view;

        adminView.addManageThiSinhListener(new OpenManagerThiSinh());
        adminView.addManageKhoiThiListener(new OpenManagerKhoiThi());
        adminView.addManageMonThiListener(new OpenManagerMonThi());
        adminView.addManageDiemThiListener(new OpenManagerDiemThi()); 
    }

    public void showAdminView() {
        adminView.setVisible(true);
    }

    class OpenManagerThiSinh implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ManagerView view = new ManagerView();
            ManagerThiSinhController controller = new ManagerThiSinhController(view);
            controller.showManagerView();
            view.setVisible(true);
        }
    }

    class OpenManagerKhoiThi implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            KhoiThiView khoiThiView = new KhoiThiView();
            ManagerKhoiThi managerKhoiThi = new ManagerKhoiThi();
            ManagerKhoiThiController managerKhoiThiController = new ManagerKhoiThiController(khoiThiView, managerKhoiThi);
            managerKhoiThiController.showManagerView();
            khoiThiView.setVisible(true);
        }
    }

    class OpenManagerMonThi implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ManagerMonThiView view = new ManagerMonThiView();
            ManagerMonThiController controller = new ManagerMonThiController(view);
            view.setVisible(true);
        }
    }

    class OpenManagerDiemThi implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            DiemThiView view = new DiemThiView();
            DiemThiController controller = new DiemThiController(view);
            controller.showManagerView();
            view.setVisible(true);
        }
    }
}
