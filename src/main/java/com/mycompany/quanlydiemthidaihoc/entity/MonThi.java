package com.mycompany.quanlydiemthidaihoc.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MonThi {
    private int id;
    private String tenMon;

    public MonThi() {}

    public MonThi(int id, String tenMon) {
        this.id = id;
        this.tenMon = tenMon;
    }
    public MonThi(String tenMon) {
    this.tenMon = tenMon;
}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    @Override
    public String toString() {
        return tenMon;
    }
}
