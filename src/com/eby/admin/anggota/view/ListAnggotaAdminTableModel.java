/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.admin.anggota.view;

import com.eby.orm.entity.Anggota;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author 3b1
 */
public class ListAnggotaAdminTableModel {

    private List<Anggota> list;
    private ObservableList<Anggota> row;
    private Collection<TableColumn<Anggota, String>> column;

    public ListAnggotaAdminTableModel() {
        list = new ArrayList<Anggota>();
        row = FXCollections.observableArrayList(list);
        column = new ArrayList<TableColumn<Anggota, String>>();
        this.initColumn();

    }

    public void initColumn() {
        column.add(this.addTableColumn1("NIS", "id"));
        column.add(this.addTableColumn2("NAMA", "nama"));
        column.add(this.addTableColumn3("ALAMAT", "alamat"));
        column.add(this.addTableColumn4("KELAS", "kelas"));
        
    }
    
    public void removeAllColumn(){
        while (column.iterator().hasNext()){
            column.remove(column.iterator().next());
        }
    }

    private TableColumn addTableColumn1(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(200);
        t.setCellValueFactory(new PropertyValueFactory<Anggota, String>(entityAttribute));
        return t;
    }
    private TableColumn addTableColumn2(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(310);
        t.setCellValueFactory(new PropertyValueFactory<Anggota, String>(entityAttribute));
        return t;
    }
    private TableColumn addTableColumn3(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(390);
        t.setCellValueFactory(new PropertyValueFactory<Anggota, String>(entityAttribute));
        return t;
    }
    private TableColumn addTableColumn4(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(150);
        t.setCellValueFactory(new PropertyValueFactory<Anggota, String>(entityAttribute));
        return t;
    }

    public Collection<TableColumn<Anggota, String>> getAllColumn() {
        return column;
    }

    public ObservableList<Anggota> getItem() {
        return row;
    }
    
    public void remove(int index){
        row.remove(index);
        this.removeAllColumn();
        this.initColumn();
    }
            
   
}
