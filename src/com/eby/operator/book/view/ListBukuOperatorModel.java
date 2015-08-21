/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.operator.book.view;

import com.eby.orm.dao.GenericDAO;
import com.eby.orm.entity.Buku;
import java.util.List;

/**
 *
 * @author eby
 */
class ListBukuOperatorModel {

    private ListBukuOperatorController controller;
    private GenericDAO dao;

    public ListBukuOperatorModel() {
        dao = new GenericDAO();
    }

    public ListBukuOperatorController getController() {
        return controller;
    }

    public void setController(ListBukuOperatorController controller) {
        this.controller = controller;
    }

    public List<Buku> list() {
        return dao.getAll(Buku.class);
    }

    public void delete(Buku buku) {
        dao.delete(buku);
    }

    public List<Buku> findData(String keyword) {
        return dao.findData("judul", keyword, Buku.class);
    }

}
