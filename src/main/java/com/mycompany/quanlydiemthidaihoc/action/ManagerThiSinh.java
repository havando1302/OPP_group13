package com.mycompany.quanlydiemthidaihoc.action;

import com.mycompany.quanlydiemthidaihoc.entity.ThiSinh;
import com.mycompany.quanlydiemthidaihoc.entity.ThiSinhXML;
import com.mycompany.quanlydiemthidaihoc.utils.FileUtils;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ManagerThiSinh {
    private static final String RESOURCE_PATH = "ThiSinh.xml";
    private List<ThiSinh> listSpecialPersons;

    public ManagerThiSinh() {
        this.listSpecialPersons = readListThiSinh();
        if (listSpecialPersons == null) {
            listSpecialPersons = new ArrayList<>();
        }
    }

    public void writeListSpecialPersons(List<ThiSinh> specialPersons) {
        ThiSinhXML specialPersonXML = new ThiSinhXML();
        specialPersonXML.setThiSinh(specialPersons);
        FileUtils.writeXMLtoDataDir(RESOURCE_PATH, specialPersonXML);
    }

    public List<ThiSinh> readListThiSinh() {
        List<ThiSinh> list = new ArrayList<>();
        ThiSinhXML specialPersonXML = (ThiSinhXML) FileUtils.readXMLFilePortable(RESOURCE_PATH, ThiSinhXML.class);
        if (specialPersonXML != null) {
            list = specialPersonXML.getThiSinh();
        }
        return list;
    }

    public List<ThiSinh> searchSpecialPersonName(String search) {
        List<ThiSinh> temp = new ArrayList<>();
        for (ThiSinh person : listSpecialPersons) {
            if (person.getName().toLowerCase().contains(search.toLowerCase())) {
                temp.add(person);
            }
        }
        return temp;
    }

    public List<ThiSinh> searchSpecialPersonAddress(String search) {
        List<ThiSinh> temp = new ArrayList<>();
        for (ThiSinh person : listSpecialPersons) {
            if (person.getAddress().toLowerCase().contains(search.toLowerCase())) {
                temp.add(person);
            }
        }
        return temp;
    }

    public List<ThiSinh> searchSpecialPersonYear(String year) {
        List<ThiSinh> temp = new ArrayList<>();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        for (ThiSinh person : listSpecialPersons) {
            String personYearStr = yearFormat.format(person.getBirthday());
            if (personYearStr.equals(year)) {
                temp.add(person);
            }
        }
        return temp;
    }

    public void add(ThiSinh specialPerson) {
        int max = 0;
        for (ThiSinh p : listSpecialPersons) {
            if (p.getId() > max) max = p.getId();
        }
        specialPerson.setId(max + 1);
        listSpecialPersons.add(specialPerson);
        writeListSpecialPersons(listSpecialPersons);
    }

    public void edit(ThiSinh specialPerson) throws ParseException {
        for (int i = 0; i < listSpecialPersons.size(); i++) {
            if (listSpecialPersons.get(i).getId() == specialPerson.getId()) {
                listSpecialPersons.set(i, specialPerson);
                writeListSpecialPersons(listSpecialPersons);
                break;
            }
        }
    }

    public void image(ThiSinh specialPerson) {
        // chưa dùng
    }

    public boolean delete(ThiSinh specialPerson) {
        boolean isFound = false;
        for (ThiSinh p : listSpecialPersons) {
            if (p.getId() == specialPerson.getId()) {
                isFound = true;
                specialPerson = p;
                break;
            }
        }
        if (isFound) {
            listSpecialPersons.remove(specialPerson);
            writeListSpecialPersons(listSpecialPersons);
            return true;
        }
        return false;
    }

    public void sortSpecialPersonByName() {
        Collections.sort(listSpecialPersons, new Comparator<ThiSinh>() {
            public int compare(ThiSinh p1, ThiSinh p2) {
                Collator collator = Collator.getInstance(new Locale("vi", "VN"));
                int result = collator.compare(p1.getLastName(), p2.getLastName());
                if (result == 0) {
                    result = collator.compare(p1.getFirstName(), p2.getFirstName());
                }
                return result;
            }
        });
    }

    public void sortSpecialPersonByID() {
        Collections.sort(listSpecialPersons, Comparator.comparingInt(ThiSinh::getId));
    }

    public void sortSpecialPersonByBirthDay() {
        Collections.sort(listSpecialPersons, Comparator.comparing(ThiSinh::getBirthday));
    }

    public List<ThiSinh> getListThiSinh() {
        return listSpecialPersons;
    }

    public void setListSpecialPersons(List<ThiSinh> listSpecialPersons) {
        this.listSpecialPersons = listSpecialPersons;
    }
}
