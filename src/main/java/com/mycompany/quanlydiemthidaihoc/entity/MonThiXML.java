package com.mycompany.quanlydiemthidaihoc.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "MonThiList")
public class MonThiXML {

    public static List<String> docFile(String datamonthixml) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private List<MonThi> monThi;

    @XmlElement(name = "MonThi")
    public List<MonThi> getMonThi() {
        return monThi;
    }

    public void setMonThi(List<MonThi> monThi) {
        this.monThi = monThi;
    }
}
