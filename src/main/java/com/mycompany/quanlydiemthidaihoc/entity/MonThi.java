package com.mycompany.quanlydiemthidaihoc.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MonThi {
    private int id;         
    private String maMon;   
    private String tenMon;  

    public MonThi() {}

   public MonThi(String maMon, String tenMon) {
        this.maMon = maMon;
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

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }
    
    @Override
    public String toString() {
        return maMon + tenMon;
    }
}
