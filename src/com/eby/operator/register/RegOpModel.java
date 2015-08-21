/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.operator.register;


import com.eby.orm.dao.GenericDAO;
import com.eby.orm.entity.Anggota;

/**
 *
 * @author eby
 */
public class RegOpModel {
    
     private RegisterForOperatorController operatorCOntroller;
     private GenericDAO dao;

    public RegOpModel() {
        dao = new GenericDAO();
    }

    public RegisterForOperatorController getOperatorCOntroller() {
        return operatorCOntroller;
    }

    public void setOperatorCOntroller(RegisterForOperatorController operatorCOntroller) {
        this.operatorCOntroller = operatorCOntroller;
    }
    
    public void save(Anggota anggota){
        dao.save(anggota);
    }

    
    
    
    
}
