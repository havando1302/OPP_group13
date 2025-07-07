/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydiemthidaihoc.controller;

import com.mycompany.quanlydiemthidaihoc.action.ManagerThiSinh;
import com.mycompany.quanlydiemthidaihoc.entity.ThiSinh;
import com.mycompany.quanlydiemthidaihoc.view.LoginView;
import com.mycompany.quanlydiemthidaihoc.view.AdminMainView;
import com.mycompany.quanlydiemthidaihoc.view.ManagerView;
import java.util.List;
//////import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class ManagerThiSinhController 
{
    private SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
    private ManagerThiSinh managerThiSinh;
    private ManagerView ThiSinhView;
    private LoginView loginView;
    private AdminMainView mainView;
    public ManagerThiSinhController(ManagerView view) 
    {
        this.ThiSinhView = view;
        managerThiSinh = new ManagerThiSinh();
        view.addAddSpecialPersonListener(new AddSpecialPersonListener());
        view.addEditSpecialPersonListener(new EditSpecialPersonListener());
        view.addClearListener(new ClearSpecialPersonListener());
        view.addDeleteSpecialPersonListener(new DeleteSpecialPersonListener());
        view.addListSpecialPersonSelectionListener(new ListSpecialPersonSelectionListener());
        view.addSortByNameListener(new SortSpecialPersonNameListener());
        //view.addSearchAddressListener(new SearchAddressSpecialPersonViewListener());
        //view.addSearchTypeListener(new SearchTypeSpecialPersonViewListener());
        view.addSearchListener(new SearchSpecialPersonViewListener());
        view.addSearchDialogListener(new SearchSpecialPersonListener());
        //view.addSearchDialogListener(new SearchSpecialPersonListener());
        view.addSortByYearListener(new SortSpecialPersonYearListener());
        view.addSortByIDListener(new SortSpecialPersonIDListener());
        view.addCancelSearchSpecialPersonListener(new CancelSearchSpecialPersonListener());
        view.addImageSpecialPersonListener(new ImageSpecialPersonListener());
        view.addCancelDialogListener(new CancelDialogSearchSpecialPersonListener());
        view.addUndoListener(new UndoListener());
        view.addStatisticListener(new StatisticViewListener());
        view.addStatisticTypeListener(new StatisticSpecialPersonTypeListener());
        view.addStatisticAgeListener(new StatisticSpecialPersonAgeListener());
        view.addStatisticUnderListener(new StatisticClearListener());
    }

    public void showManagerView() 
    {
        List<ThiSinh> specialPersonList = managerThiSinh.getListThiSinh();
       ThiSinhView.setVisible(true);
        ThiSinhView.showListSpecialPersons(specialPersonList);
       ThiSinhView.showCountListSpecialPersons(specialPersonList);
    }

    class AddSpecialPersonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            ThiSinh specialPerson = ThiSinhView.getSpecialPersonInfo();
            if (specialPerson != null) 
            {
                managerThiSinh.add(specialPerson);
                ThiSinhView.showSpecialPerson(specialPerson);
                ThiSinhView.showListSpecialPersons(managerThiSinh.getListThiSinh());
                ThiSinhView.showCountListSpecialPersons(managerThiSinh.getListThiSinh());
                ThiSinhView.showMessage("Thêm thành công!");
            }
        }
    }
    
    class EditSpecialPersonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            ThiSinh specialPerson = ThiSinhView.getSpecialPersonInfo();
            if (specialPerson != null) 
            {
                try {
                    managerThiSinh.edit(specialPerson);
                } catch (ParseException ex) {
                    Logger.getLogger(ManagerThiSinhController.class.getName()).log(Level.SEVERE, null, ex);
                }
                ThiSinhView.showSpecialPerson(specialPerson);
                ThiSinhView.showListSpecialPersons(managerThiSinh.getListThiSinh());
                ThiSinhView.showCountListSpecialPersons(managerThiSinh.getListThiSinh());
                ThiSinhView.showMessage("Cập nhật thành công!");
            }
        }
    }
    
    class DeleteSpecialPersonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            ThiSinh specialPerson = ThiSinhView.getSpecialPersonInfo();
            if (specialPerson != null) 
            {
                managerThiSinh.delete(specialPerson);
                ThiSinhView.clearSpecialPersonInfo();
                ThiSinhView.showListSpecialPersons(managerThiSinh.getListThiSinh());
                ThiSinhView.showCountListSpecialPersons(managerThiSinh.getListThiSinh());
                ThiSinhView.showMessage("Xóa thành công!");
            }
        }
    }
    
    
    class ImageSpecialPersonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            ThiSinhView.SpecialPersonImage();
        }
    }
    /**
     * Lớp ClearSpecialPersonListener 
     * chứa cài đặt cho sự kiện click button "Clear"
     * 
     * @author viettuts.vn
     */
    class ClearSpecialPersonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            ThiSinhView.clearSpecialPersonInfo();
        }
    }

    /**
     * Lớp SortSpecialPersonGPAListener 
     * chứa cài đặt cho sự kiện click button "Sort By GPA"
     * 
     * @author viettuts.vn
     *
    /**
     * Lớp SortSpecialPersonGPAListener 
     * chứa cài đặt cho sự kiện click button "Sort By Name"
     * 
     * @author viettuts.vn
     */
    class SortSpecialPersonNameListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            managerThiSinh.sortSpecialPersonByName();
            ThiSinhView.showListSpecialPersons(managerThiSinh.getListThiSinh());
        }
    }
    
    class SortSpecialPersonYearListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            managerThiSinh.sortSpecialPersonByBirthDay();
            ThiSinhView.showListSpecialPersons(managerThiSinh.getListThiSinh());
        }
    }
    
    class SortSpecialPersonIDListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            managerThiSinh.sortSpecialPersonByID();
            ThiSinhView.showListSpecialPersons(managerThiSinh.getListThiSinh());
        }
    }
    
    
    class SearchSpecialPersonViewListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            ThiSinhView.searchNameSpecialPersonInfo();
        }
    }
    
    class StatisticViewListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
           ThiSinhView.displayStatisticView();
        }
    }
    
    class SearchSpecialPersonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boolean kt = false;
            List<ThiSinh> temp = new ArrayList<>();
            int check = ThiSinhView.getChooseSelectSearch();
            String search = ThiSinhView.validateSearch();
            if(check == 1){
                // Tìm kiếm theo tên
                temp = managerThiSinh.searchSpecialPersonName(search);
            }else if(check == 2){
                // Tìm kiếm theo địa chỉ
                temp = managerThiSinh.searchSpecialPersonAddress(search);
            }else if(check == 3){
                // Tìm kiếm theo khối
                temp = managerThiSinh.searchSpecialPersonYear(search);
            }
            if(!temp.isEmpty())ThiSinhView.showListSpecialPersons(temp);
            else ThiSinhView.showMessage("Không tìm thấy kết quả!");
        }
    }
    
    class CancelDialogSearchSpecialPersonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            ThiSinhView.cancelDialogSearchSpecialPersonInfo();
        }
    }
    
    class CancelSearchSpecialPersonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            ThiSinhView.showListSpecialPersons(managerThiSinh.getListThiSinh());
            ThiSinhView.cancelSearchSpecialPerson();
        }
    }
    
    class UndoListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            mainView = new AdminMainView();
            AdminController mainController = new AdminController(mainView);
            mainController.showAdminView();
            ThiSinhView.setVisible(false);
        }
    }
    /**
     * Lớp ListSpecialPersonSelectionListener 
     * chứa cài đặt cho sự kiện chọn specialPerson trong bảng specialPerson
     */
    class ListSpecialPersonSelectionListener implements ListSelectionListener 
    {
        public void valueChanged(ListSelectionEvent e) 
        {
            try {
               ThiSinhView.fillSpecialPersonFromSelectedRow();
            } catch (ParseException ex) {
                Logger.getLogger(ManagerThiSinhController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class StatisticSpecialPersonTypeListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            ThiSinhView.displayStatisticView();
            //managerSpecialPerson.sortSpecialPersonByID();
           ThiSinhView.showStatisticTypeSpecialPersons(managerThiSinh.getListThiSinh());
        }
    }
    class StatisticSpecialPersonAgeListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
           ThiSinhView.displayStatisticView();
            //managerSpecialPerson.sortSpecialPersonByID();
            ThiSinhView.showStatisticAgeSpecialPersons(managerThiSinh.getListThiSinh());
        }
    }
    class StatisticClearListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            ThiSinhView.UnderViewSpecialPerson();
            //managerSpecialPerson.sortSpecialPersonByID();
            //specialPersonView.showStatisticAgeSpecialPersons(managerSpecialPerson.getListSpecialPersons());
        }
    }
}
