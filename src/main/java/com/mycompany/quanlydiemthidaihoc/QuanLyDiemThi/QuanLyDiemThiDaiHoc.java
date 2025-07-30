package com.mycompany.quanlydiemthidaihoc.QuanLyDiemThi;

import com.mycompany.quanlydiemthidaihoc.controller.RoleSelectController;
import com.mycompany.quanlydiemthidaihoc.view.RoleSelect;

public class QuanLyDiemThiDaiHoc {
    public static void main(String[] args) {
        System.setProperty("com.sun.xml.bind.v2.bytecode.ClassTailor.noOptimize", "true");
        RoleSelect view = new RoleSelect();
        RoleSelectController controller = new RoleSelectController(view);
        view.setVisible(true);
    }
}
