/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.operator.anggota.view;

import com.eby.orm.entity.Anggota;
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
public class ListAnggotaOpTableModel {

    private List<Anggota> list;
    private ObservableList<Anggota> row;
    private Collection<TableColumn<Anggota, String>> column;

    public ListAnggotaOpTableModel() {
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
//        column.add(this.getCategory());
        
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
    private TableColumn addTableColumn5(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(150);
        t.setCellValueFactory(new PropertyValueFactory<Anggota, String>(entityAttribute));
        return t;
    }

//    private TableColumn getCategory() {
//        TableColumn t = this.addTableColumn("CATEGORY", "categories");
//        t.setPrefWidth(150);
//        t.setCellFactory(new Callback< TableColumn<Anggota, Set>, TableCell<Anggota, Set>>() {
//
//            @Override
//            public TableCell<Anggota, Set> call(TableColumn<Anggota, Set> param) {
//                TableCell<Anggota, Set> bookcatCell = new TableCell<Anggota, Set>() {
//
//                    protected void updateItem(Set set, boolean empty) {
//                        if (set != null) {
//                            String out = "";
//                            Iterator<Category> iter = set.iterator();
//                            while (iter.hasNext()) {
//                                out = out + iter.next().getNama()+ ", ";
//                            }
//                            Label label = new Label(out);
//                            setGraphic(label);
//
//                        }
//                    }
//                };
//                return bookcatCell;
//            }
//        ;
//        });
//         
//         return t;
//    }

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
    public void removeAllElement(){
        int size = list.size();
        for (int i=0; i<size; i++){
            list.remove(i);
        }
        
        this.removeAllColumn();;
//        this.initColumn();
        
    }
            
   
}
