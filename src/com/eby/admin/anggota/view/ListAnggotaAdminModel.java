/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.admin.anggota.view;
import com.eby.orm.dao.GenericDAO;
import com.eby.orm.entity.Anggota;
import java.util.List;

/**
 *
 * @author eby
 */
public class ListAnggotaAdminModel {

    private ListAnggotaAdminController controller;
    private GenericDAO dao;

    public ListAnggotaAdminModel() {
        dao = new GenericDAO();
    }

    public ListAnggotaAdminController getController() {
        return controller;
    }

    public void setController(ListAnggotaAdminController controller) {
        this.controller = controller;
    }

    //methode list untuk tampil data
    public List<Anggota> list() {
        return dao.getAll(Anggota.class);
    }

    void delete(Anggota a) {
        dao.delete(a);
    }

    //methode untuk pencarian data dengan nama
    public List<Anggota> findByName(String nama) {
        return dao.findData("nama", nama, Anggota.class);
    }
}
