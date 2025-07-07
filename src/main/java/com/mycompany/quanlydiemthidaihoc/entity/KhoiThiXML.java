package com.mycompany.quanlydiemthidaihoc.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "KhoiThiXML")
public class KhoiThiXML {
    private List<KhoiThi> khoiThi;

    @XmlElement(name = "KhoiThi")
    public List<KhoiThi> getKhoiThi() {
        return khoiThi;
    }

    public void setKhoiThi(List<KhoiThi> khoiThi) {
        this.khoiThi = khoiThi;
    }
}
