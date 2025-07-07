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
    if (obj instanceof MonThiXML) {
        MonThiXML wrapper = (MonThiXML) obj;
        if (wrapper.getMonThi() != null) {
            this.listMonThi = wrapper.getMonThi();
        }
    } else {
        System.out.println(" Không đọc được MonThiXML từ file: " + FILE_NAME);
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
        listMonThi = new ArrayList<>(); // fallback nếu ai đó gán null
    }
    int maxId = listMonThi.stream().mapToInt(MonThi::getId).max().orElse(0);
    monThi.setId(maxId + 1);
    listMonThi.add(monThi);
    writeListMonThi();
}

    public void edit(MonThi monThi) {
        for (int i = 0; i < listMonThi.size(); i++) {
            if (listMonThi.get(i).getId() == monThi.getId()) {
                listMonThi.set(i, monThi);
                break;
            }
        }
        writeListMonThi();
    }

    public void delete(MonThi monThi) {
        listMonThi.removeIf(m -> m.getId() == monThi.getId());
        writeListMonThi();
    }

    public List<MonThi> searchByName(String tenMon) {
        reload();
        return listMonThi.stream()
                .filter(m -> m.getTenMon().equalsIgnoreCase(tenMon))
                .collect(Collectors.toList());
    }
}
