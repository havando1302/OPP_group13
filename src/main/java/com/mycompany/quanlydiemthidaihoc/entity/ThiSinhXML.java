package com.mycompany.quanlydiemthidaihoc.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "ThiSinhXML")
@XmlAccessorType(XmlAccessType.FIELD) 
public class ThiSinhXML {
    @XmlElement(name = "ThiSinh")
    private List<ThiSinh> thiSinh;

    public List<ThiSinh> getThiSinh() {
        return thiSinh;
    }

    public void setThiSinh(List<ThiSinh> thiSinh) {
        this.thiSinh = thiSinh;
    }
}
