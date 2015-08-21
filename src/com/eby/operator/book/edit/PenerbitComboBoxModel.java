/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.operator.book.edit;

import com.eby.orm.entity.Penerbit;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 3b1
 */
public class PenerbitComboBoxModel {

    private List<Penerbit> list;
    private ObservableList<String> items;

    public PenerbitComboBoxModel() {
        list = new ArrayList<Penerbit>();
        items = FXCollections.observableArrayList(new ArrayList<String>());
    }

    public void add(List<Penerbit> get) {
        for (Penerbit c : get) {
            list.add(c);
            items.add(c.getNama());
        }
    }

    public Penerbit get(int i) {
        return list.get(i);
    }

    public synchronized Penerbit remove(int index) {
        Penerbit t = list.remove(index);
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
