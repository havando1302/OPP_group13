package com.mycompany.quanlydiemthidaihoc.controller;

import com.mycompany.quanlydiemthidaihoc.view.LoginView;
import com.mycompany.quanlydiemthidaihoc.view.RoleSelect;
import com.mycompany.quanlydiemthidaihoc.view.TraCuuView;

public class RoleSelectController {
    private RoleSelect view;

    public RoleSelectController(RoleSelect view) {
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
                TraCuuView studentView = new TraCuuView();
                StudentController studentController = new StudentController(studentView);
                studentView.setVisible(true);
            }

            // Đóng form chọn quyền sau khi chọn
            view.dispose();
        });
    }
}
