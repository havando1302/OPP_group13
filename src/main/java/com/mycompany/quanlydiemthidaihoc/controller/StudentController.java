package com.mycompany.quanlydiemthidaihoc.controller;

import com.mycompany.quanlydiemthidaihoc.action.ManagerThiSinh;
import com.mycompany.quanlydiemthidaihoc.entity.ThiSinh;
import com.mycompany.quanlydiemthidaihoc.entity.MonThi;
import com.mycompany.quanlydiemthidaihoc.view.AdminView;
import com.mycompany.quanlydiemthidaihoc.view.RoleSelect;
import com.mycompany.quanlydiemthidaihoc.view.TraCuuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class StudentController {
    private TraCuuView studentView;
    private RoleSelect RoleSelectView;
    private ManagerThiSinh managerThiSinh;

    public StudentController(TraCuuView view) {
        this.studentView = view;
        this.managerThiSinh = new ManagerThiSinh();
        view.addUndoListener(new UndoListener());
    }

    public void showStudentView() {
        studentView.setVisible(true);
    }
    class UndoListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RoleSelect roleSelectView = new RoleSelect();
        RoleSelectController controller = new RoleSelectController(roleSelectView);
        controller.showRoleSelectView(); 
        studentView.setVisible(false); 
    }
    }
   
}
