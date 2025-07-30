package com.mycompany.quanlydiemthidaihoc.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MonThi")
@XmlAccessorType(XmlAccessType.FIELD)
public class MonThi {

    @XmlElement(name = "id")
    private int id;

    @XmlElement(name = "maMon")
    private String maMon;

    @XmlElement(name = "tenMon")
    private String tenMon;

    // Constructors
    public MonThi() {}

    public MonThi(String maMon, String tenMon) {
        this.maMon = maMon;
        this.tenMon = tenMon;
    }

    public MonThi(String tenMon) {
        this.tenMon = tenMon;
    }

    // Getters & Setters
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
