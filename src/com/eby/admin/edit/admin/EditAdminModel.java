/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.admin.edit.admin;

import com.eby.orm.dao.GenericDAO;

import com.eby.orm.entity.Anggota;
import com.eby.orm.entity.User;

/**
 *
 * @author eby
 */
public class EditAdminModel {

    private EditAdminController controller;
    private GenericDAO dao;

    public EditAdminModel() {
        dao = new GenericDAO();
    }

    public EditAdminModel(EditAdminController controller) {
        this.controller = controller;
    }

    public EditAdminController getController() {
        return controller;
    }

    public void setController(EditAdminController controller) {
        this.controller = controller;
    }

    public void updateAdmin(User user) {
        dao.update(user);
    }

    public void updateAnggota(Anggota anggota) {
        dao.update(anggota);
    }

}
