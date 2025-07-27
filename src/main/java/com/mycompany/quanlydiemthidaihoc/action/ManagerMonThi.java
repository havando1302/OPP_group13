package com.mycompany.quanlydiemthidaihoc.action;

import com.mycompany.quanlydiemthidaihoc.entity.MonThi;
import com.mycompany.quanlydiemthidaihoc.entity.MonThiXML;
import com.mycompany.quanlydiemthidaihoc.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManagerMonThi {
    private static final String FILE_NAME = "MonThi.xml";
    private List<MonThi> listMonThi;

    public ManagerMonThi() {
        this.listMonThi = new ArrayList<>();
        reload();
    }

    public void reload() {
        Object obj = FileUtils.readXMLFile(FILE_NAME, MonThiXML.class);
        if (obj instanceof MonThiXML wrapper && wrapper.getMonThi() != null) {
            this.listMonThi = wrapper.getMonThi();
        } else {
            System.out.println("Không đọc được MonThiXML từ file: " + FILE_NAME);
        }
    }

    public void writeListMonThi() {
        MonThiXML wrapper = new MonThiXML();
        wrapper.setMonThi(this.listMonThi);
        FileUtils.writeXMLtoFile(FILE_NAME, wrapper);
    }

    public List<MonThi> getListMonThi() {
        return listMonThi;
    }

    public void add(MonThi monThi) {
        if (listMonThi == null) {
            listMonThi = new ArrayList<>();
        }
        int maxId = listMonThi.stream().mapToInt(MonThi::getId).max().orElse(0);
        monThi.setId(maxId + 1);
        listMonThi.add(monThi);
        writeListMonThi();
    }

    public boolean update(MonThi monThi) {
        for (int i = 0; i < listMonThi.size(); i++) {
            if (listMonThi.get(i).getId() == monThi.getId()) {
                listMonThi.set(i, monThi);
                writeListMonThi();
                return true;
            }
        }
        return false;
    }

    public void delete(MonThi monThi) {
        listMonThi.removeIf(m -> m.getId() == monThi.getId());
        writeListMonThi();
    }

    public List<MonThi> searchByTenMon(String tenMon) {
        reload(); 
        return listMonThi.stream()
                .filter(m -> m.getTenMon().toLowerCase().contains(tenMon.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<MonThi> searchByMaMon(String maMon) {
        reload(); 
        return listMonThi.stream()
                .filter(m -> m.getMaMon().toLowerCase().contains(maMon.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public List<MonThi> sortByName() {
        return listMonThi.stream()
                .sorted((a, b) -> a.getTenMon().compareToIgnoreCase(b.getTenMon()))
                .collect(Collectors.toList());
    }
    
    public List<MonThi> searchMonThi(String type, String keyword) {
        return listMonThi.stream()
                .filter(mon -> {
                    if ("Mã môn".equals(type)) {
                        return mon.getMaMon().toLowerCase().contains(keyword.toLowerCase());
                    } else if ("Tên môn".equals(type)) {
                        return mon.getTenMon().toLowerCase().contains(keyword.toLowerCase());
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
}
