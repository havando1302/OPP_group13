package com.mycompany.quanlydiemthidaihoc.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DiemThi")
public class DiemThi {

    private String tenThiSinh;
    private String soBaoDanh;
    private String khoiThi;
    private String monThi;
    private double diem;

    // Constructor mặc định (bắt buộc cho JAXB)
    public DiemThi() {
    }

    public DiemThi(String tenThiSinh, String soBaoDanh, String khoiThi, String monThi, double diem) {
        this.tenThiSinh = tenThiSinh;
        this.soBaoDanh = soBaoDanh;
        this.khoiThi = khoiThi;
        this.monThi = monThi;
        this.diem = diem;
    }

    @XmlElement
    public String getTenThiSinh() {
        return tenThiSinh;
    }

    public void setTenThiSinh(String tenThiSinh) {
        this.tenThiSinh = tenThiSinh;
    }

    @XmlElement
    public String getSoBaoDanh() {
        return soBaoDanh;
    }

    public void setSoBaoDanh(String soBaoDanh) {
        this.soBaoDanh = soBaoDanh;
    }

    @XmlElement
    public String getKhoiThi() {
        return khoiThi;
    }

    public void setKhoiThi(String khoiThi) {
        this.khoiThi = khoiThi;
    }

    @XmlElement
    public String getMonThi() {
        return monThi;
    }

    public void setMonThi(String monThi) {
        this.monThi = monThi;
    }

    @XmlElement
    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }
}
