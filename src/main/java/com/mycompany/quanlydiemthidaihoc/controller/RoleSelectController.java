package com.mycompany.quanlydiemthidaihoc.controller;

import com.mycompany.quanlydiemthidaihoc.view.LoginView;
import com.mycompany.quanlydiemthidaihoc.view.RoleSelect;
import com.mycompany.quanlydiemthidaihoc.view.TraCuuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
public class RoleSelectController {
    private RoleSelect view;

    public RoleSelectController(RoleSelect view) {
        this.view = view;
        

        this.view.addContinueListener(e -> {
            String selectedRole = view.getSelectedRole();
            if (selectedRole.equalsIgnoreCase("Admin")) {
                LoginView loginView = new LoginView();
                LoginController loginController = new LoginController(loginView);
                loginView.setVisible(true);
            } else {
                TraCuuView studentView = new TraCuuView();
                StudentController studentController = new StudentController(studentView);
                studentView.setVisible(true);
            }

            view.dispose();
        });
    }

    public void showRoleSelectView() {
        view.setVisible(true);
    }
}
