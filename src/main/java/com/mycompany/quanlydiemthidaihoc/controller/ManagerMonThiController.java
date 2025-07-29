package com.mycompany.quanlydiemthidaihoc.controller;

import com.mycompany.quanlydiemthidaihoc.action.ManagerMonThi;
import com.mycompany.quanlydiemthidaihoc.entity.MonThi;
import com.mycompany.quanlydiemthidaihoc.view.AdminView;
import com.mycompany.quanlydiemthidaihoc.view.ManagerMonThiView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class ManagerMonThiController {
    private ManagerMonThiView monThiView;
    private ManagerMonThi managerMonThi;
    private AdminView mainView;
public void showManagerView() {
        List<MonThi> list = managerMonThi.getListMonThi();
        monThiView.setVisible(true);
        loadMonThiTable();
    }
    public ManagerMonThiController(ManagerMonThiView view) {
        this.monThiView = view;
        this.managerMonThi = new ManagerMonThi();

        monThiView.addAddMonThiListener(new AddMonThiListener());
        monThiView.addUpdateMonThiListener(new UpdateMonThiListener());
        monThiView.addDeleteMonThiListener(new DeleteMonThiListener());
        monThiView.addUndoListener(new UndoListener());
//      monThiView.addSearchListener(new SearchMonThiListener());
        monThiView.getBtnSearch().addActionListener(e -> {
            String[] searchData = monThiView.showSearchDialog();
            if (searchData != null) {
                String searchType = searchData[0];
                String keyword = searchData[1];
                List<MonThi> result = managerMonThi.searchMonThi(searchType, keyword);
                if (result.isEmpty()) {
                    monThiView.showMessage("Không tìm thấy kết quả!");
                } else {
                    List<String[]> data = result.stream()
                            .map(mt -> new String[]{String.valueOf(mt.getId()), mt.getMaMon(), mt.getTenMon()})
                            .collect(Collectors.toList());
                    monThiView.setMonThiTable(data);
                }
            }
        });
        
        

//        monThiView.addResetListener(new ResetListener());
//        monThiView.addBackListener(e -> ((java.awt.Window) monThiView).dispose());
        monThiView.getBtnSort().addActionListener(new SortMonThiListener());

        monThiView.getBtnCancelSearch().addActionListener(e -> {
            monThiView.clearMaMon();
            monThiView.clearTenMon();
//            monThiView.setSearchText(""); 
            loadMonThiTable(); 
        });
        
        
        
        loadMonThiTable();
    }

    private void loadMonThiTable() {
        List<MonThi> list = managerMonThi.getListMonThi();
        monThiView.setMonThiTable(
            list.stream()
                .map(mt -> new String[]{String.valueOf(mt.getId()), mt.getMaMon(), mt.getTenMon()})
                .collect(Collectors.toList())
        );
    }
 class UndoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainView = new AdminView();
            AdminController controller = new AdminController(mainView);
            controller.showAdminView();
            monThiView.setVisible(false);
        }
    }
 
    class AddMonThiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String tenMon = monThiView.getTenMon();
            String maMon = monThiView.getMaMon();

            if (tenMon.isEmpty() || maMon.isEmpty()) {
                monThiView.showMessage("Không được để trống mã hoặc tên môn!");
                return;
            }

            boolean existsTen = managerMonThi.getListMonThi().stream()
                    .anyMatch(mt -> mt.getTenMon().equalsIgnoreCase(tenMon));
            if (existsTen) {
                monThiView.showMessage("Tên môn đã tồn tại!");
                return;
            }

            boolean existsMa = managerMonThi.getListMonThi().stream()
                    .anyMatch(mt -> mt.getMaMon().equalsIgnoreCase(maMon));
            if (existsMa) {
                monThiView.showMessage("Mã môn đã tồn tại!");
                return;
            }

            MonThi monThi = new MonThi(maMon, tenMon);
            monThi.setId(generateNextId());
            monThi.setTenMon(tenMon);
            monThi.setMaMon(maMon);

            managerMonThi.add(monThi);
            loadMonThiTable();
            monThiView.clearMaMon();
            monThiView.clearTenMon();
            monThiView.showMessage("Thêm môn thi thành công!");
        }
    }

    class DeleteMonThiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int id = monThiView.getSelectedMonThiId();
             String tenMon = monThiView.getTenMon();
            String maMon = monThiView.getMaMon();
            if (id == -1) {
                monThiView.showMessage("Vui lòng chọn môn thi để xóa!");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa môn thi này?");
            if (confirm != JOptionPane.YES_OPTION) return;
            

             MonThi monThi = new MonThi(maMon, tenMon);
            monThi.setId(id);
            managerMonThi.delete(monThi);
            loadMonThiTable();
            monThiView.showMessage("Xóa thành công!");
        }
    }

    class UpdateMonThiListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int id = monThiView.getSelectedMonThiId();
            String tenMon = monThiView.getTenMon();
            String maMon = monThiView.getMaMon();

            if (id == -1) {
                monThiView.showMessage("Chọn môn thi để cập nhật!");
                return;
            }
            if (tenMon.isEmpty() || maMon.isEmpty()) {
                monThiView.showMessage("Không được để trống mã hoặc tên môn!");
                return;
            }

            boolean existsTen = managerMonThi.getListMonThi().stream()
                    .anyMatch(mt -> mt.getTenMon().equalsIgnoreCase(tenMon) && mt.getId() != id);
            if (existsTen) {
                monThiView.showMessage("Tên môn đã tồn tại!");
                return;
            }

            boolean existsMa = managerMonThi.getListMonThi().stream()
                    .anyMatch(mt -> mt.getMaMon().equalsIgnoreCase(maMon) && mt.getId() != id);
            if (existsMa) {
                monThiView.showMessage("Mã môn đã tồn tại!");
                return;
            }

            MonThi monThi = new MonThi(maMon, tenMon);
            monThi.setId(id);
            monThi.setTenMon(tenMon);
            monThi.setMaMon(maMon);

            boolean success = managerMonThi.update(monThi);
            if (success) {
                loadMonThiTable();
                monThiView.clearMaMon();
                monThiView.clearTenMon();
                monThiView.showMessage("Cập nhật thành công!");
            } else {
                monThiView.showMessage("Không tìm thấy môn thi để cập nhật!");
            }
        }
    }

//    class SearchMonThiListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            String keyword = monThiView.getSearchKeyword();
//            String type = monThiView.getSearchType();
//            if (keyword.isEmpty()) {
//                monThiView.showMessage("Nhập từ khóa tìm kiếm!");
//                return;
//            }
//
//            List<MonThi> results;
//            if (type.equalsIgnoreCase("Mã môn")) {
//                results = managerMonThi.searchByMaMon(keyword);
//            } else {
//                results = managerMonThi.searchByTenMon(keyword);
//            }
//
//            if (results.isEmpty()) {
//                monThiView.showMessage("Không tìm thấy kết quả!");
//            }
//
//            List<String[]> data = results.stream()
//                    .map(mt -> new String[]{String.valueOf(mt.getId()), mt.getMaMon(), mt.getTenMon()})
//                    .collect(Collectors.toList());
//
//            monThiView.setMonThiTable(data);
//        }
//    }

    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            monThiView.clearMaMon();
            monThiView.clearTenMon();
            monThiView.setMonThiTable(
                managerMonThi.getListMonThi().stream()
                        .map(mt -> new String[]{String.valueOf(mt.getId()), mt.getMaMon(), mt.getTenMon()})
                        .collect(Collectors.toList())
            );
        }
    }

    class SortMonThiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean sortByName = monThiView.getCheckSortByName().isSelected();
            boolean sortByMa = monThiView.getCheckSortByMa().isSelected();

            if (!sortByName && !sortByMa) {
                monThiView.showMessage("Vui lòng chọn tiêu chí sắp xếp!");
                return;
            }

            List<MonThi> list = new ArrayList<>(managerMonThi.getListMonThi());

            Comparator<MonThi> comparator = null;

            if (sortByMa) {
                comparator = Comparator.comparing(MonThi::getMaMon, String.CASE_INSENSITIVE_ORDER);
            }

            if (sortByName) {
                Comparator<MonThi> nameComparator =
                        Comparator.comparing(MonThi::getTenMon, String.CASE_INSENSITIVE_ORDER);
                comparator = (comparator == null) ? nameComparator : comparator.thenComparing(nameComparator);
            }

            list.sort(comparator); // sắp xếp danh sách theo tiêu chí đã chọn

            List<String[]> data = list.stream()
                    .map(mt -> new String[]{
                            String.valueOf(mt.getId()),
                            mt.getMaMon(),
                            mt.getTenMon()
                    })
                    .collect(Collectors.toList());

            monThiView.setMonThiTable(data);
        }
    }

    private int generateNextId() {
        List<Integer> ids = managerMonThi.getListMonThi()
                .stream()
                .map(MonThi::getId)
                .sorted()
                .toList();

        for (int i = 1; i <= ids.size(); i++) {
            if (ids.get(i - 1) != i) {
                return i; // Trả về ID còn trống đầu tiên
            }
        }
        return ids.size() + 1; // Nếu không có chỗ trống thì dùng ID tiếp theo
    }
}