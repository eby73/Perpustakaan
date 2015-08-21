/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.book.penerbit;

import com.eby.orm.entity.Penerbit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author 3b1
 */
public class PenerbitTableModel {

    private List<Penerbit> list;
    private ObservableList<Penerbit> row;
    private Collection<TableColumn<Penerbit, String>> column;

    public PenerbitTableModel() {
        list = new ArrayList<Penerbit>();
        row = FXCollections.observableArrayList(list);
        column = new ArrayList<TableColumn<Penerbit, String>>();
        this.initColumn();

    }

    public void initColumn() {
        column.add(this.addTableColumn1("ID", "id"));
        column.add(this.addTableColumn2("NAMA PENERBIT", "nama"));
        column.add(this.addTableColumn3("ALAMAT", "alamat"));
      
        
//        column.add(this.getPenerbit());
        
    }
    
    public void removeAllColumn(){
        while (column.iterator().hasNext()){
            column.remove(column.iterator().next());
        }
    }

    private TableColumn addTableColumn1(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(65);
        t.setCellValueFactory(new PropertyValueFactory<Penerbit, String>(entityAttribute));
        return t;
    }
    private TableColumn addTableColumn2(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(200);
        t.setCellValueFactory(new PropertyValueFactory<Penerbit, String>(entityAttribute));
        return t;
    }
    private TableColumn addTableColumn3(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(350);
        t.setCellValueFactory(new PropertyValueFactory<Penerbit, String>(entityAttribute));
        return t;
    }
   
   

    

    public Collection<TableColumn<Penerbit, String>> getAllColumn() {
        return column;
    }

    public ObservableList<Penerbit> getItem() {
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
        
        this.removeAllColumn();;
//        this.initColumn();
        
    }
            
   
}
