package com.mycompany.quanlydiemthidaihoc.controller;

import com.mycompany.quanlydiemthidaihoc.view.LoginView;
import com.mycompany.quanlydiemthidaihoc.view.RoleSelectView;
import com.mycompany.quanlydiemthidaihoc.view.StudentMainView;

public class RoleSelectController {
    private RoleSelectView view;

    public RoleSelectController(RoleSelectView view) {
        this.view = view;

        this.view.addContinueListener(e -> {
            String selectedRole = view.getSelectedRole();
            if (selectedRole.equalsIgnoreCase("Admin")) {
                // Mở form đăng nhập Admin
                LoginView loginView = new LoginView();
                LoginController loginController = new LoginController(loginView);
                loginView.setVisible(true);
            } else {
                // Mở form tra cứu điểm cho Thí sinh
                StudentMainView studentView = new StudentMainView();
                StudentController studentController = new StudentController(studentView);
                studentView.setVisible(true);
            }

            // Đóng form chọn quyền sau khi chọn
            view.dispose();
        });
    }
}
