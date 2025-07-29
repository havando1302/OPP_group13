package com.mycompany.quanlydiemthidaihoc.controller;

import com.mycompany.quanlydiemthidaihoc.action.CheckLogin;
import com.mycompany.quanlydiemthidaihoc.entity.User;
import com.mycompany.quanlydiemthidaihoc.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView loginView;
    private CheckLogin checkLogin;
    private RoleSelect RoleSelectView;

    public LoginController(LoginView view) {
        this.loginView = view;
        this.checkLogin = new CheckLogin();
        view.addLoginListener(new LoginListener());
        view.addUndoListener(new UndoListener());
    }

    public void showLoginView() {
        loginView.setVisible(true);
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if ("admin".equals(user.getRole()) && checkLogin.checkAdmin(user)) {
                AdminView adminView = new AdminView();
                AdminController adminController = new AdminController(adminView);
                adminController.showAdminView();
                loginView.dispose();
            } else if ("student".equals(user.getRole()) && checkLogin.checkStudent(user, user.getUsername())) {
                TraCuuView studentView = new TraCuuView();
                StudentController studentController = new StudentController(studentView);
                studentController.showStudentView();
                loginView.dispose();
            } else {
                loginView.showMessage("Sai thông tin đăng nhập!");
            }
        }
    }
    class UndoListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RoleSelect roleSelectView = new RoleSelect();
        RoleSelectController controller = new RoleSelectController(roleSelectView);
        controller.showRoleSelectView(); 
        loginView.setVisible(false); 
    }
    }
}
