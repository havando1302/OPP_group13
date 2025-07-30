package com.mycompany.quanlydiemthidaihoc.action;

import com.mycompany.quanlydiemthidaihoc.entity.DiemThi;
import com.mycompany.quanlydiemthidaihoc.entity.DiemThiXML;

import java.util.ArrayList;
import java.util.List;

public class ManagerDiemThi {
    private final String RESOURCE_PATH = "diemthi.xml"; 
    private List<DiemThi> listDiem;

    public ManagerDiemThi() {
        listDiem = DiemThiXML.docFile(RESOURCE_PATH);
        if (listDiem == null) {
            listDiem = new ArrayList<>();
        }
    }

    public List<DiemThi> getListDiem() {
        return listDiem;
    }

    public void add(DiemThi diem) {
        listDiem.add(diem);
        DiemThiXML.ghiFile(RESOURCE_PATH, (ArrayList<DiemThi>) listDiem);
    }

    public void edit(DiemThi diem) {
        for (int i = 0; i < listDiem.size(); i++) {
            DiemThi d = listDiem.get(i);
            if (d.getSoBaoDanh().equalsIgnoreCase(diem.getSoBaoDanh())
                    && d.getMonThi().equalsIgnoreCase(diem.getMonThi())) {
                listDiem.set(i, diem);
                DiemThiXML.ghiFile(RESOURCE_PATH, (ArrayList<DiemThi>) listDiem);
                return;
            }
        }
    }

    public boolean delete(DiemThi diem) {
        boolean removed = listDiem.removeIf(d ->
                d.getSoBaoDanh().equalsIgnoreCase(diem.getSoBaoDanh())
                        && d.getMonThi().equalsIgnoreCase(diem.getMonThi()));
        if (removed) {
            DiemThiXML.ghiFile(RESOURCE_PATH, (ArrayList<DiemThi>) listDiem);
        }
        return removed;
    }

    public void save() {
        DiemThiXML.ghiFile(RESOURCE_PATH, (ArrayList<DiemThi>) listDiem);
    }

    public List<DiemThi> findBySBD(String sbd) {
        List<DiemThi> result = new ArrayList<>();
        for (DiemThi d : listDiem) {
            if (d.getSoBaoDanh().equalsIgnoreCase(sbd)) {
                result.add(d);
            }
        }
        return result;
    }

    public DiemThi findBySBDAndMon(String sbd, String monThi) {
        for (DiemThi d : listDiem) {
            if (d.getSoBaoDanh().equalsIgnoreCase(sbd)
                    && d.getMonThi().equalsIgnoreCase(monThi)) {
                return d;
            }
        }
        return null;
    }
}
