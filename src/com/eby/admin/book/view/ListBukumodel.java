/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.admin.book.view;

import com.eby.orm.dao.GenericDAO;
import com.eby.orm.entity.Buku;
import java.util.List;

/**
 *
 * @author eby
 */
public class ListBukumodel {

    private ListBukuController controller;
    private GenericDAO dao;

    public ListBukumodel() {
        dao = new GenericDAO();
    }

    public ListBukuController getController() {
        return controller;
    }

    public void setController(ListBukuController controller) {
        this.controller = controller;
    }

    //methode getAll data
    public List<Buku> list() {
        return dao.getAll(Buku.class);
    }

    public void delete(Buku buku) {
        dao.delete(buku);
    }

    //methode pencarian dengan judul
    public List<Buku> findData(String keyword) {
        return dao.findData("judul", keyword, Buku.class);
    }

}
