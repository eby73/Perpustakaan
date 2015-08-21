/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.operator.book.edit;


import com.eby.orm.dao.GenericDAO;
import com.eby.orm.entity.Buku;
import com.eby.orm.entity.Category;
import com.eby.orm.entity.Penerbit;
import java.util.List;

/**
 *
 * @author eby
 */
public class BukuEditOperatorModel {

    private BukuEditOperatorController controller;
    private GenericDAO dao;

    public BukuEditOperatorModel() {
        dao = new GenericDAO();
    }

    public BukuEditOperatorController getController() {
        return controller;
    }

    public void setController(BukuEditOperatorController controller) {
        this.controller = controller;
    }

    public List<Category> catList() {
        return dao.getAll(Category.class);
    }

    public List<Penerbit> penList() {
        return dao.getAll(Penerbit.class);
    }

    public void update(Buku buku) {
        dao.update(buku);
    }

}
