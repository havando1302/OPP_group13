package com.mycompany.quanlydiemthidaihoc.action;

import com.mycompany.quanlydiemthidaihoc.entity.User;

public class CheckLogin {
    public boolean checkAdmin(User user) {
        return "admin".equals(user.getUsername()) && "admin".equals(user.getPassword());
    }

    public boolean checkStudent(User user, String sbd) {
        return user.getUsername().equals(sbd);
    }
}
