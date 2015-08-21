/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.book.category;

import com.eby.orm.entity.Anggota;
import com.eby.orm.entity.Buku;
import com.eby.orm.entity.Category;
import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 3b1
 */
public final class CategoryTableModel extends AbstractTableModel {

    private final List<Category> list;
    private final ObservableList<Category> row;
    private final Collection<TableColumn<Category, String>> column;

    public CategoryTableModel() {
        list = new ArrayList<>();
        row = FXCollections.observableArrayList(list);
        column = new ArrayList<>();
        this.initColumn();

    }

    public void initColumn() {
        column.add(this.addTableColumn1("ID", "id"));
        column.add(this.addTableColumn2("NAMA CATEGORY", "nama"));
        column.add(this.getParent());

//        column.add(this.getCategory());
    }

    public void removeAllColumn() {
        while (column.iterator().hasNext()) {
            column.remove(column.iterator().next());
        }
    }

    private TableColumn addTableColumn1(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(62);
        t.setCellValueFactory(new PropertyValueFactory<>(entityAttribute));
        return t;
    }

    private TableColumn addTableColumn2(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setPrefWidth(250);
        t.setCellValueFactory(new PropertyValueFactory<>(entityAttribute));
        return t;
    }

    private TableColumn addTableColumn(String tableHeader, String entityAttribute) {
        TableColumn t = new TableColumn(tableHeader);
        t.setCellValueFactory(new PropertyValueFactory<>(entityAttribute));
        return t;
    }

    private TableColumn getParent() {
        TableColumn t = this.addTableColumn("PARENT CATEGORY", "category");
        t.setPrefWidth(350);
        t.setCellFactory(new Callback< TableColumn<Category, Category>, TableCell<Category, Category>>() {

            @Override
            public TableCell<Category, Category> call(TableColumn<Category, Category> param) {
                TableCell<Category, Category> parentCell = new TableCell<Category, Category>() {

                    @Override
                    protected void updateItem(Category cat, boolean empty) {
                        if (cat != null) {
                            Label label = new Label(cat.getNama());
                            setGraphic(label);

                        }else{
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

    public Collection<TableColumn<Category, String>> getAllColumn() {
        return column;
    }

    public ObservableList<Category> getItem() {
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

        this.removeAllColumn();
    }

    public void add(List<Category> obs) {
        for (Category c : obs) {
            row.add(c);
        }
        fireTableDataChanged();
    }

    public synchronized boolean add(Category o) {
        int index = row.size();
        boolean b = row.add(o);
        fireTableRowsInserted(index, index);
        return b;
    }

    @Override
    public int getRowCount() {
        return row.size();
    }

    @Override
    public int getColumnCount() {
        return column.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return row.get(rowIndex).getId();
        }
        if (columnIndex == 1) {
            return row.get(rowIndex).getNama();
        }
//        if (columnIndex == 2) {
//            return row.get(rowIndex).getCategory().getId();
//        }

        return null;
    }

    public ArrayList<Category> search(String keyword) {
        int tCol = getColumnCount();
        int tRow = getRowCount();
        int count = 0;
        boolean founded = false;
        int[] foundRow = new int[tRow];
        ArrayList<Category> filter = new ArrayList<>();
        for (int crow = 0; crow < tRow; crow++) {
            for (int ccol = 0; ccol < tCol; ccol++) {
                String next = String.valueOf(getValueAt(crow, count));
                if (next.contains(keyword)) {
                    foundRow[count] = crow;
                    count++;
                    filter.add(row.get(crow));
                    founded = true;
                }
                if (founded == true) {
                    break;
                }

            }
            founded = false;

        }
        return filter;

    }

}
