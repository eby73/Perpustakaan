/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.admin.view;

import com.eby.orm.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author 3b1
 */
public final class ListAdminTableModel {

    private final List<User> list;
    private final ObservableList<User> row;
    private final Collection<TableColumn<User, String>> column;

    public ListAdminTableModel() {
        list = new ArrayList<>();
        row = FXCollections.observableArrayList(list);
        column = new ArrayList<>();
        this.initColumn();

    }

    public void initColumn() {
        column.add(this.addTableColumn1("ID", "id"));
        column.add(this.addTableColumn2("NAMA", "nama"));
        column.add(this.addTableColumn3("EMAIL", "email"));
        column.add(this.addTableColumn4("USERNAME", "username"));
        column.add(this.addTableColumn5("LEVEL", "level"));
//        column.add(this.getCategory());
        
    }
    
    public void removeAllColumn(){
        while (column.iterator().hasNext()){
            column.remove(column.iterator().next());
        }
    }

    private TableColumn addTableColumn1(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(60);
        t.setCellValueFactory(new PropertyValueFactory<>(entityAttribute));
        return t;
    }
    private TableColumn addTableColumn2(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(300);
        t.setCellValueFactory(new PropertyValueFactory<>(entityAttribute));
        return t;
    }
    private TableColumn addTableColumn3(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(350);
        t.setCellValueFactory(new PropertyValueFactory<>(entityAttribute));
        return t;
    }
    private TableColumn addTableColumn4(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(170);
        t.setCellValueFactory(new PropertyValueFactory<>(entityAttribute));
        return t;
    }
    private TableColumn addTableColumn5(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(170);
        t.setCellValueFactory(new PropertyValueFactory<>(entityAttribute));
        return t;
    }

    public Collection<TableColumn<User, String>> getAllColumn() {
        return column;
    }

    public ObservableList<User> getItem() {
        return row;
    }
    
    public void remove(int index){
        row.remove(index);
        this.removeAllColumn();
        this.initColumn();
    }
    public void removeAllElement(){
        int size = list.size();
        for (int i=0; i<size; i++){
            list.remove(i);
        }
        
        this.removeAllColumn();        
    }
            
   
}
