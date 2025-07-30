package com.mycompany.quanlydiemthidaihoc.entity;

import com.mycompany.quanlydiemthidaihoc.utils.FileUtils;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;


@XmlRootElement(name = "ThiSinh")
@XmlAccessorType(XmlAccessType.FIELD)
public class ThiSinh implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String SBD;
    private String name;
    private String GT;
    private String Truong;
    private String address;
    private String type;

    // JAXB không hỗ trợ trực tiếp Date và byte[], ta dùng các trường trung gian
    @XmlTransient
    private Date birthday;

    @XmlTransient
    private byte[] picture;

    public ThiSinh() {
    }

    public ThiSinh(int id, String name, String SBD, String GT, Date birthday, String Truong, String address, String type, byte[] image) {
        this.id = id;
        this.name = name;
        this.SBD = SBD;
        this.GT = GT;
        this.birthday = birthday;
        this.Truong = Truong;
        this.address = address;
        this.type = type;
        this.picture = image;
    }

    // Getter/setter cơ bản
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSBD() {
        return SBD;
    }

    public void setSBD(String SBD) {
        this.SBD = SBD;
    }

    public String getGT() {
        return GT;
    }

    public void setGT(String GT) {
        this.GT = GT;
    }

    public String getTruong() {
        return Truong;
    }

    public void setTruong(String Truong) {
        this.Truong = Truong;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImage() {
        return picture;
    }

    public void setImage(byte[] image) {
        this.picture = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        String fullName = this.getName();
        int lastSpaceIndex = fullName.lastIndexOf(" ");
        return fullName.substring(lastSpaceIndex + 1);
    }

    public String getFirstName() {
        return this.getName().replace(this.getLastName(), "").trim();
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Trường chuyển tiếp cho Date
    @XmlElement(name = "birthday")
    public String getBirthdayString() {
        if (birthday == null) return "";
        return new SimpleDateFormat("dd/MM/yyyy").format(birthday);
    }

  public void setBirthdayString(String dateStr) {
    try {
        if (dateStr.contains("T")) {
            // ISO format
            DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            LocalDate localDate = LocalDate.parse(dateStr, isoFormatter);
            this.birthday = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } else {
            // dd/MM/yyyy format
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.birthday = sdf.parse(dateStr);
        }
    } catch (Exception e) {
        this.birthday = null;
        e.printStackTrace();
    }
}




    
    @XmlElement(name = "picture")
    public String getPictureBase64() {
        return picture != null ? Base64.getEncoder().encodeToString(picture) : "";
    }

    public void setPictureBase64(String base64) {
        this.picture = (base64 != null && !base64.isEmpty()) ? Base64.getDecoder().decode(base64) : null;
    }
}
