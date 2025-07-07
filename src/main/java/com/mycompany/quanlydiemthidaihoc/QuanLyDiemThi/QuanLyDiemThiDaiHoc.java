package com.mycompany.quanlydiemthidaihoc.QuanLyDiemThi;

import com.mycompany.quanlydiemthidaihoc.controller.RoleSelectController;
import com.mycompany.quanlydiemthidaihoc.view.RoleSelectView;

public class QuanLyDiemThiDaiHoc {
    public static void main(String[] args) {
        RoleSelectView view = new RoleSelectView();
        RoleSelectController controller = new RoleSelectController(view);
        view.setVisible(true);
    }
}
