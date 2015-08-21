/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.book.category;

import com.eby.orm.entity.Category;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 3b1
 */
public class CategoryListModel {

    private ObservableList<String> items;
    private List<Category> list;

    public CategoryListModel() {
        items = FXCollections.observableArrayList();
        list = new ArrayList<Category>();
    }

    public synchronized void add(Category c) {
        list.add(c);
        fireOnAdd(c);
    }
    
    private void fireOnAdd(Category c){
        items.add(c.getNama());
    }

    public synchronized void addList(List<Category> lc) {
        for (Category c : lc) {
            list.add(c);
        }
        fireOnAddList(lc);
    }
    
     private void fireOnAddList(List<Category> lc){
        for (Category c : lc) {
            items.add(c.getNama());
        }
    }
     
     public Category get(int index) {
        return list.get(index);
     }

    public ObservableList<String> getItems() {
        return items;
    }

    public void setItems(ObservableList<String> items) {
        this.items = items;
    }
    
    

}
