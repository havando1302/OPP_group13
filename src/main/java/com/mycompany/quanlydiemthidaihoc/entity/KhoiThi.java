package com.mycompany.quanlydiemthidaihoc.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

@XmlRootElement
public class KhoiThi {
    private int id;
    private String tenKhoi;
    private List<MonThi> monThiList;

  
    public KhoiThi() {
        this.monThiList = new ArrayList<>();
    }

    public KhoiThi(Integer id, String tenKhoi) {
        this.id = id;
        this.tenKhoi = tenKhoi;
    }

    public KhoiThi(String tenKhoi, List<MonThi> monThiList) {
        this.tenKhoi = tenKhoi;
        this.monThiList = monThiList;
    }

    public KhoiThi(int id, String tenKhoi, List<MonThi> monThiList) {
        this.id = id;
        this.tenKhoi = tenKhoi;
        this.monThiList = monThiList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
