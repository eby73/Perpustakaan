/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.admin.book.edit;

import com.eby.admin.book.view.ListBukuController;
import com.eby.orm.dao.GenericDAO;
import com.eby.orm.entity.Buku;
import com.eby.orm.entity.Category;
import com.eby.orm.entity.Penerbit;
import java.util.List;

/**
 *
 * @author eby
 */
public class BukuEditAdminModel {

    BukuEditAdminController controller;
    private GenericDAO dao;

    public BukuEditAdminModel() {
        dao = new GenericDAO();
    }

    public BukuEditAdminController getController() {
        return controller;
    }

    public void setController(BukuEditAdminController controller) {
        this.controller = controller;
    }

    public void save(Buku buku) {
        dao.save(buku);
    }

    public void update(Buku buku) {
        dao.update(buku);
    }

    public void delete(Buku buku) {
        dao.delete(buku);
    }

    List<Penerbit> listPen() {
        return dao.getAll(Penerbit.class);
    }

    List<Category> lisCat() {
        return dao.getAll(Category.class);
    }
}
