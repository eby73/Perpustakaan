/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.peminjaman;

import com.eby.orm.entity.Anggota;
import com.eby.orm.entity.Buku;
import com.eby.orm.entity.Peminjaman;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 *
 * @author 3b1
 */
public class PeminjamanTableModel {
    
    private List<Peminjaman> list;
    private ObservableList<Peminjaman> row;
    private Collection<TableColumn<Peminjaman, String>> column;
    
    public PeminjamanTableModel() {
        list = new ArrayList<Peminjaman>();
        row = FXCollections.observableArrayList(list);
        column = new ArrayList<TableColumn<Peminjaman, String>>();
        this.initColumn();
        
    }
    
    public void initColumn() {
        column.add(this.addTableColumnID("ID", "id"));
        column.add(this.getAnggota());
        column.add(this.getIdBuku());
        column.add(this.addTableColumnTglPinjam("Tgl. PINJAM", "tglPinjam"));
        column.add(this.addTableColumnTglKembali("Tgl. KEMBALI", "tglKembali"));
//        column.add(this.getAnggota());

    }
    
    public void removeAllColumn() {
        while (column.iterator().hasNext()) {
            column.remove(column.iterator().next());
        }
    }
    
    private TableColumn addTableColumnID(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(60);
        t.setCellValueFactory(new PropertyValueFactory<Peminjaman, String>(entityAttribute));
        return t;
    }
    
    private TableColumn addTableColumnNIS(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(140);
        t.setCellValueFactory(new PropertyValueFactory<Peminjaman, String>(entityAttribute));
        return t;
    }
    
    private TableColumn addTableColumnIdBuku(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(135);
        t.setCellValueFactory(new PropertyValueFactory<Peminjaman, String>(entityAttribute));
        return t;
    }
    
    private TableColumn addTableColumnTglPinjam(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(130);
        t.setCellValueFactory(new PropertyValueFactory<Peminjaman, String>(entityAttribute));
        return t;
    }
    
    private TableColumn addTableColumnTglKembali(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(150);
        t.setCellValueFactory(new PropertyValueFactory<Peminjaman, String>(entityAttribute));
        return t;
    }
    
    private TableColumn getAnggota() {
        TableColumn t = this.addTableColumnNIS("NIS", "anggota");
        t.setCellFactory(new Callback< TableColumn<Anggota, Anggota>, TableCell<Anggota, Anggota>>() {
            
            @Override
            public TableCell<Anggota, Anggota> call(TableColumn<Anggota, Anggota> param) {
                TableCell<Anggota, Anggota> parentCell = new TableCell<Anggota, Anggota>() {
                    
                    @Override
                    protected void updateItem(Anggota a, boolean empty) {
                        if (a != null) {
                            Text text = new Text(String.valueOf(a.getId()));
                            Button btn = new Button(a.getNama());
                            setGraphic(text);
                            
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
    
    private TableColumn getIdBuku() {
        TableColumn t = this.addTableColumnIdBuku("ID BUKU", "buku");
        t.setCellFactory(new Callback< TableColumn<Buku, Buku>, TableCell<Buku, Buku>>() {
            
            @Override
            public TableCell<Buku, Buku> call(TableColumn<Buku, Buku> param) {
                TableCell<Buku, Buku> parentCell = new TableCell<Buku, Buku>() {
                    
                    @Override
                    protected void updateItem(Buku buku, boolean empty) {
                        if (buku != null) {
                            Label label = new Label(String.valueOf(buku.getId()));
                            Button btn = new Button(buku.getJudul());
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
    
    public Collection<TableColumn<Peminjaman, String>> getAllColumn() {
        return column;
    }
    
    public ObservableList<Peminjaman> getItem() {
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
