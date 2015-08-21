/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.operator.anggota.edit;

import com.eby.orm.dao.GenericDAO;
import com.eby.orm.entity.Anggota;

/**
 *
 * @author eby
 */
public class AnggotaOpEditModel {

    private AnggotaOpEditController controller;
    private GenericDAO dao;

    public AnggotaOpEditModel() {
        dao = new GenericDAO();
    }

    public AnggotaOpEditController getController() {
        return controller;
    }

    public void setController(AnggotaOpEditController controller) {
        this.controller = controller;
    }

    public void update(Anggota a) {
        dao.update(a);
    }

}
