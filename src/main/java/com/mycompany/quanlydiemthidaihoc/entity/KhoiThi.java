package com.mycompany.quanlydiemthidaihoc.entity;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class KhoiThi {
    private String tenKhoi;
    private List<MonThi> monThiList;

    public KhoiThi() {}

    public KhoiThi(String tenKhoi, List<MonThi> monThiList) {
        this.tenKhoi = tenKhoi;
        this.monThiList = monThiList;
    }

    public String getTenKhoi() {
        return tenKhoi;
    }

    public void setTenKhoi(String tenKhoi) {
        this.tenKhoi = tenKhoi;
    }

    public List<MonThi> getMonThiList() {
        return monThiList;
    }

    public void setMonThiList(List<MonThi> monThiList) {
        this.monThiList = monThiList;
    }
    @Override
    public String toString() {
        return tenKhoi;
    }

    public Object getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public CharSequence[] getMonThi() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setMonThi(ArrayList<String> arrayList) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setTenKhoiThi(String capitalizeWords) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getTenKhoiThi() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
