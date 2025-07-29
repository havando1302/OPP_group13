package com.mycompany.quanlydiemthidaihoc.controller;

import com.mycompany.quanlydiemthidaihoc.action.ManagerThiSinh;
import com.mycompany.quanlydiemthidaihoc.entity.ThiSinh;
import com.mycompany.quanlydiemthidaihoc.entity.MonThi;
import com.mycompany.quanlydiemthidaihoc.view.TraCuuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class StudentController {
    private TraCuuView studentView;
    private ManagerThiSinh managerThiSinh;

    public StudentController(TraCuuView view) {
        this.studentView = view;
        this.managerThiSinh = new ManagerThiSinh();
    }

    public void showStudentView() {
        studentView.setVisible(true);
    }
   
}
