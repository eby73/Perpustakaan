/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.admin.register;

import com.eby.orm.dao.GenericDAO;

import com.eby.orm.entity.Anggota;
import com.eby.orm.entity.User;

/**
 *
 * @author eby
 */
public class RegAdminModel {

    private RegisterForAdminController adminCOntroller;
    private GenericDAO dao;

    public RegAdminModel() {
        dao = new GenericDAO();
    }

    public RegisterForAdminController getAdminCOntroller() {
        return adminCOntroller;
    }

    public void setAdminCOntroller(RegisterForAdminController adminCOntroller) {
        this.adminCOntroller = adminCOntroller;
    }

    public void saveAdmin(User user) {
        dao.save(user);
    }

    public void saveAnggota(Anggota anggota) {
        dao.save(anggota);
    }

}
