/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.book.penerbit;

import com.eby.orm.dao.GenericDAO;
import com.eby.orm.entity.Penerbit;
import java.util.List;

/**
 *
 * @author eby
 */
public class PenerbitModel {
    
    private PenerbitController controller;
    private final GenericDAO dao;

    public PenerbitModel() {
        dao = new GenericDAO();
    }

    public PenerbitController getController() {
        return controller;
    }

    public void setController(PenerbitController controller) {
        this.controller = controller;
    }
    
    public List<Penerbit> list(){
        return dao.getAll(Penerbit.class);
    }
    
    public void save(Penerbit pen){
        dao.save(pen);
    }
    
    public void update(Penerbit pen){
        dao.update(pen);
        
    }
    
    public void delete(Penerbit pen){
        dao.delete(pen);
    }

    public List<Penerbit> findData(String keyword) {
        return dao.findData("nama", keyword, Penerbit.class);
    }
    
}
