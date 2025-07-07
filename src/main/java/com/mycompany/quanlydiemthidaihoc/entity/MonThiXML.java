package com.mycompany.quanlydiemthidaihoc.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "MonThiList")
public class MonThiXML {
    private List<MonThi> monThi;

    @XmlElement(name = "MonThi")
    public List<MonThi> getMonThi() {
        return monThi;
    }

    public void setMonThi(List<MonThi> monThi) {
        this.monThi = monThi;
    }
}
