/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydiemthidaihoc.action;

import com.mycompany.quanlydiemthidaihoc.entity.ThiSinh;
import com.mycompany.quanlydiemthidaihoc.entity.ThiSinhXML;
import com.mycompany.quanlydiemthidaihoc.utils.FileUtils;
import com.mycompany.quanlydiemthidaihoc.view.ManagerView;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

/**
 *
 * @author PC
 */
public class ManagerThiSinh
{
    private static final String SPECIALPERSON_FILE_NAME = "ThiSinh.xml";
    private List<ThiSinh> listSpecialPersons;
    public ManagerThiSinh() {
        this.listSpecialPersons = readListThiSinh();
        if (listSpecialPersons == null) {
            listSpecialPersons = new ArrayList<ThiSinh>();
        }
    }

    /**
     * Lưu các đối tượng SpecialPerson vào file SpecialPerson.xml
     * 
     * @param specialPersons
     */
    public void writeListSpecialPersons(List<ThiSinh> specialPersons) 
    {
        ThiSinhXML specialPersonXML = new ThiSinhXML();
        specialPersonXML.setThiSinh(specialPersons);
        FileUtils.writeXMLtoFile(SPECIALPERSON_FILE_NAME, specialPersonXML);
    }

    /**
     * Đọc các đối tượng SpecialPerson từ file SpecialPerson.xml
     * 
     * @return list SpecialPerson
     */
    public List<ThiSinh> readListThiSinh() 
    {
        List<ThiSinh> list = new ArrayList<ThiSinh>();
       ThiSinhXML specialPersonXML = (ThiSinhXML) FileUtils.readXMLFile(
                SPECIALPERSON_FILE_NAME,ThiSinhXML.class);
        if (specialPersonXML != null) 
        {
            list = specialPersonXML.getThiSinh();
        }
        return list;
    }
    
    /* Hiển thị listSpecialPersons theo tên */
    public List<ThiSinh> searchSpecialPersonName(String search){
        List<ThiSinh>temp = new ArrayList<ThiSinh>();
        for(ThiSinh person : listSpecialPersons){
            if(person.getName().toLowerCase().contains(search.toLowerCase())){
                temp.add(person);
            }
        }
        return temp;
    }
    
    /* Hiển thị listSpecialPersons theo nơi ở */
    public List<ThiSinh> searchSpecialPersonAddress(String search){
        List<ThiSinh>temp = new ArrayList<ThiSinh>();
        for(ThiSinh person : listSpecialPersons){
            if(person.getAddress().toLowerCase().contains(search.toLowerCase())){
                temp.add(person);
            }
        }
        return temp;
    }
     /* Hiển thị listSpecialPersons theo năm sinh */
    public List<ThiSinh> searchSpecialPersonYear(String year) {
        List<ThiSinh> temp = new ArrayList<>();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        for (ThiSinh person : listSpecialPersons) {
            // Chuyển đổi ngày sinh thành chuỗi năm
            String personYearStr = yearFormat.format(person.getBirthday());

            // So sánh chuỗi năm với năm tìm kiếm
            if (personYearStr.equals(year)) {
                temp.add(person);
            }
        }

        return temp;
    }
    
    /**
     * thêm SpecialPerson vào listSpecialPersons và lưu listSpecialPersons vào file
     * 
     * @param SpecialPerson
     */
    public void add(ThiSinh specialPerson) 
    {
        int max = 0;
        for (int i=0;i<listSpecialPersons.size();i++)
        {
            if(listSpecialPersons.get(i).getId()>max) max=listSpecialPersons.get(i).getId();
        }
        specialPerson.setId(max+1);
        listSpecialPersons.add(specialPerson);
        writeListSpecialPersons(listSpecialPersons);
    }

    /**
     * cập nhật SpecialPerson vào listSpecialPersons và lưu listSpecialPersons vào file
     * 
     * @param SpecialPerson
     */
    public void edit(ThiSinh specialPerson) throws ParseException 
    {
        SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
        int size = listSpecialPersons.size();
        for (int i = 0; i < size; i++) 
        {
            if (listSpecialPersons.get(i).getId() == specialPerson.getId()) 
            {
                listSpecialPersons.get(i).setName(specialPerson.getName());
                listSpecialPersons.get(i).setSBD(specialPerson.getSBD());
                listSpecialPersons.get(i).setBirthday(specialPerson.getBirthday());
                listSpecialPersons.get(i).setAddress(specialPerson.getAddress());
                listSpecialPersons.get(i).setType(specialPerson.getType());
                listSpecialPersons.get(i).setGT(specialPerson.getGT());
                listSpecialPersons.get(i).setTruong(specialPerson.getTruong());
                listSpecialPersons.get(i).setImage(specialPerson.getImage());
                
                writeListSpecialPersons(listSpecialPersons);
                break;
            }
        }
    }

    /**
     * xóa SpecialPerson từ listSpecialPersons và lưu listSpecialPersons vào file
     * 
     * @param SpecialPerson
     */
    
    public void image(ThiSinh specialPerson) 
    {
        
    }
      
    public boolean delete(ThiSinh specialPerson) {
         boolean isFound = false;
        int size = listSpecialPersons.size();
        for (int i = 0; i < size; i++) 
        {
            if (listSpecialPersons.get(i).getId() == specialPerson.getId()) 
            {
                specialPerson = listSpecialPersons.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) 
        {
            listSpecialPersons.remove(specialPerson);
            writeListSpecialPersons(listSpecialPersons);
            return true;
        }
        return false;
    }

    
    
    /**
     * sắp xếp danh sách SpecialPerson theo name theo tứ tự tăng dần
     */
    
    
    public void sortSpecialPersonByName() 
    {
        Collections.sort(listSpecialPersons, new Comparator<ThiSinh>() {
            public int compare(ThiSinh p1, ThiSinh p2) {
                Collator collator = Collator.getInstance(new Locale("vi", "VN"));
                // So sánh tên
                int result = collator.compare(p1.getLastName(), p2.getLastName());
                if (result == 0) {
                    // Nếu tên bằng nhau, so sánh họ lót
                    result = collator.compare(p1.getFirstName(), p2.getFirstName());
                }
                return result;
            }
        });
        //Collections.sort(listSpecialPersons, (p1, p2) -> collator.compare(p1.getLastName(), p2.getLastName()));
    }
    
    public void sortSpecialPersonByID() 
    {
        Collections.sort(listSpecialPersons, new Comparator<ThiSinh>() 
        {
            public int compare(ThiSinh SpecialPerson1, ThiSinh SpecialPerson2) 
            {
                if (SpecialPerson1.getId() > SpecialPerson2.getId()) 
                {
                    return 1;
                }
                return -1;
            }
        });
    }
    
    
    /**
     * sắp xếp danh sách SpecialPerson theo năm sinh theo tứ tự tăng dần
     */
    public void sortSpecialPersonByBirthDay() {
        Collections.sort(listSpecialPersons, new Comparator<ThiSinh>() {
            public int compare(ThiSinh person1, ThiSinh person2) {
                return person1.getBirthday().compareTo(person2.getBirthday());
            }
        });
    }

    public List<ThiSinh> getListThiSinh() 
    {
        return listSpecialPersons;
    }

    public void setListSpecialPersons(List<ThiSinh> listSpecialPersons) 
    {
        this.listSpecialPersons = listSpecialPersons;
    }

  
}
