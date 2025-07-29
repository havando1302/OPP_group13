package com.mycompany.quanlydiemthidaihoc.controller;

import com.mycompany.quanlydiemthidaihoc.action.ManagerKhoiThi;
import com.mycompany.quanlydiemthidaihoc.view.AdminView;
import com.mycompany.quanlydiemthidaihoc.view.ManagerView;
import com.mycompany.quanlydiemthidaihoc.view.KhoiThiView;
import com.mycompany.quanlydiemthidaihoc.view.ManagerMonThiView;
import com.mycompany.quanlydiemthidaihoc.view.DiemThiView;
import com.mycompany.quanlydiemthidaihoc.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController {
    private final AdminView adminView;
    private LoginView loginView;
    public AdminController(AdminView view) {
        this.adminView = view;
        view.addUndoListener(new UndoListener());
        adminView.addManageThiSinhListener(new OpenManagerThiSinh());
        adminView.addManageKhoiThiListener(new OpenManagerKhoiThi());
        adminView.addManageMonThiListener(new OpenManagerMonThi());
        adminView.addManageDiemThiListener(new OpenManagerDiemThi()); 
    }

    public void showAdminView() {
        adminView.setVisible(true);
    }
    class UndoListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        LoginView loginView = new LoginView();
        LoginController controller = new LoginController(loginView);
        controller.showLoginView(); 
        adminView.setVisible(false); 
    }
    }
    class OpenManagerThiSinh implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ManagerView view = new ManagerView();
            ManagerThiSinhController controller = new ManagerThiSinhController(view);
            controller.showManagerView();
            adminView.setVisible(false);
        }
    }

    class OpenManagerKhoiThi implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            KhoiThiView khoiThiView = new KhoiThiView();
            ManagerKhoiThi managerKhoiThi = new ManagerKhoiThi();
            ManagerKhoiThiController managerKhoiThiController = new ManagerKhoiThiController(khoiThiView, managerKhoiThi);
            managerKhoiThiController.showManagerView();
            adminView.setVisible(false);
        }
    }

    class OpenManagerMonThi implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ManagerMonThiView view = new ManagerMonThiView();
            ManagerMonThiController controller = new ManagerMonThiController(view);
            controller.showManagerView();
            adminView.setVisible(false);
        }
    }

    class OpenManagerDiemThi implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            DiemThiView view = new DiemThiView();
            DiemThiController controller = new DiemThiController(view);
            controller.showManagerView();
           adminView.setVisible(false);
        }
    }
}
