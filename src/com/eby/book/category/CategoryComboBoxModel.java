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
public class CategoryComboBoxModel {

    private List<Category> list;
    private ObservableList<String> items;

    public CategoryComboBoxModel() {
        list = new ArrayList<Category>();
        items = FXCollections.observableArrayList(new ArrayList<String>());
    }

    public void add(List<Category> get) {
        for (Category c : get) {
            list.add(c);
            items.add(c.getNama());
        }
    }

    public Category get(int i) {
        return list.get(i);
    }

    public synchronized Category remove(int index) {
        Category t = list.remove(index);
        return t;
    }
    
     public void removeAllElement(){
        int size = list.size();
        for (int i=0; i<size; i++){
            list.remove(i);
        }
//        this.initColumn();
        
    }
     
    public ObservableList<String> getItems() {
        return items;
    }

    
    

}
