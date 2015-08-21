/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.peminjaman;

import com.eby.orm.dao.GenericDAO;
import com.eby.orm.entity.Peminjaman;
import java.util.List;

/**
 *
 * @author eby
 */
public class PeminjamanModel {

    private PeminjamanController controller;
    private GenericDAO dao;

    public PeminjamanModel() {
        dao = new GenericDAO();
    }

    public PeminjamanController getController() {
        return controller;
    }

    public void setController(PeminjamanController controller) {
        this.controller = controller;
    }

    public List<Peminjaman> list() {
        return dao.getAll(Peminjaman.class);
    }

    public void save(Peminjaman p) {
        dao.save(p);
    }

    public void update(Peminjaman p) {
        dao.update(p);
    }

    public void delete(Peminjaman p) {
        dao.delete(p);
    }

    public Peminjaman getByID(int id) {
        return dao.get(Peminjaman.class, id);
    }

    public List<Peminjaman> findData(String key) {
        return dao.findData("nama", key, Peminjaman.class);
    }

}
