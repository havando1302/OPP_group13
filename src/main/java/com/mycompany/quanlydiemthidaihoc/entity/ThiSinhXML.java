package com.mycompany.quanlydiemthidaihoc.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "ThiSinhXML")
public class ThiSinhXML {
    private List<ThiSinh> thiSinh;

    @XmlElement(name = "ThiSinh")
    public List<ThiSinh> getThiSinh() {
        return thiSinh;
    }

    public void setThiSinh(List<ThiSinh> thiSinh) {
        this.thiSinh = thiSinh;
    }
}
