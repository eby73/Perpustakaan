/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.operator.anggota.view;

import com.eby.orm.dao.GenericDAO;
import com.eby.orm.entity.Anggota;
import java.util.List;

/**
 *
 * @author eby
 */
public class ListAnggotaOpModel {

    private ListAnggotaOperatorController controller;
    private GenericDAO dao;

    public ListAnggotaOpModel() {
        dao = new GenericDAO();
    }

    public ListAnggotaOperatorController getController() {
        return controller;
    }

    public void setController(ListAnggotaOperatorController controller) {
        this.controller = controller;
    }
    
    public List<Anggota> list(){
        return dao.getAll(Anggota.class);
    }
    
    public void delete(Anggota anggota){
        dao.delete(anggota);
    }

    public List<Anggota> findData(String keyword) {
        return dao.findData("nama", keyword, Anggota.class);
    }

}
