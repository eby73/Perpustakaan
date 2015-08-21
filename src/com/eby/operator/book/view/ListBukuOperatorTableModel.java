/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.operator.book.view;

import com.eby.orm.entity.Buku;
import com.eby.orm.entity.Category;
import com.eby.orm.entity.Penerbit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author 3b1
 */
public class ListBukuOperatorTableModel {
    
    private final List<Buku> list;
    private ObservableList<Buku> row;
    private final Collection<TableColumn<Buku, String>> column;
    
    public ListBukuOperatorTableModel() {
        list = new ArrayList<Buku>();
        row = FXCollections.observableArrayList(list);
        column = new ArrayList<TableColumn<Buku, String>>();
        this.initColumn();
        
    }
    
    public void initColumn() {
        column.add(this.addTableColumn1("ID", "id"));
        column.add(this.addTableColumn2("JUDUL", "judul"));
        column.add(this.getCategory());
        column.add(this.getPenerbit());
        column.add(this.addTableColumn6("PENGARANG", "pengarang"));
        column.add(this.addTableColumn3("Thn TERBIT", "tahunTerbit"));
        column.add(this.addTableColumn4("Jmlh HALAMAN", "jumlahHalaman"));
        column.add(this.addTableColumn5("SYNOPSIS", "synopsis"));
//        column.add(this.getCategory());
        
    }
    
    public void removeAllColumn() {
        while (column.iterator().hasNext()) {
            column.remove(column.iterator().next());
        }
    }
    
    private TableColumn addTableColumn1(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(100);
        t.setCellValueFactory(new PropertyValueFactory<Buku, String>(entityAttribute));
        return t;
    }

    private TableColumn addTableColumn2(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(140);
        t.setCellValueFactory(new PropertyValueFactory<Buku, String>(entityAttribute));
        return t;
    }

    private TableColumn addTableColumn3(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(100);
        t.setCellValueFactory(new PropertyValueFactory<Buku, String>(entityAttribute));
        return t;
    }

    private TableColumn addTableColumn4(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(100);
        t.setCellValueFactory(new PropertyValueFactory<Buku, String>(entityAttribute));
        return t;
    }

    private TableColumn addTableColumn5(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(230);
        t.setCellValueFactory(new PropertyValueFactory<Buku, String>(entityAttribute));
        return t;
    }

    private TableColumn addTableColumn(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setCellValueFactory(new PropertyValueFactory<Buku, String>(entityAttribute));
        return t;
    }

    private TableColumn addTableColumn6(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(150);
        t.setCellValueFactory(new PropertyValueFactory<Buku, String>(entityAttribute));
        return t;
    }
    
    private TableColumn getCategory() {
        TableColumn t = this.addTableColumn("CATEGORY", "category");
        t.setPrefWidth(150);
        t.setCellFactory(new Callback< TableColumn<Category, Category>, TableCell<Category, Category>>() {
            
            @Override
            public TableCell<Category, Category> call(TableColumn<Category, Category> param) {
                TableCell<Category, Category> parentCell = new TableCell<Category, Category>() {
                    
                    @Override
                    protected void updateItem(Category cat, boolean empty) {
                        if (cat != null) {
                            Label label = new Label(cat.getNama());
                            Button btn = new Button(cat.getNama());
                            setGraphic(label);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
                return parentCell;
            }
        ;
        });
         
         return t;
        
    }

    private TableColumn getPenerbit() {
        TableColumn t = this.addTableColumn("PENERBIT", "penerbit");
        t.setPrefWidth(150);
        t.setCellFactory(new Callback< TableColumn<Penerbit, Penerbit>, TableCell<Penerbit, Penerbit>>() {
            
            @Override
            public TableCell<Penerbit, Penerbit> call(TableColumn<Penerbit, Penerbit> param) {
                TableCell<Penerbit, Penerbit> parentCell = new TableCell<Penerbit, Penerbit>() {
                    
                    @Override
                    protected void updateItem(Penerbit pen, boolean empty) {
                        if (pen != null) {
                            Label label = new Label(pen.getNama());
                            Button btn = new Button(pen.getNama());
                            setGraphic(label);
                            
                        }
                    }
                };
                return parentCell;
            }
        ;
        });
         
         return t;
        
    }
    
    public Collection<TableColumn<Buku, String>> getAllColumn() {
        return column;
    }
    
    public ObservableList<Buku> getItem() {
        return row;
    }
    
    public void remove(int index) {
        row.remove(index);
        this.removeAllColumn();
        this.initColumn();
    }

    public void removeAllElement() {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.remove(i);
        }
        
        this.removeAllColumn();;
//        this.initColumn();
        
    }
    
}
