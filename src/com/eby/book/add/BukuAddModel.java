/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.book.add;

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
public class BukuAddModel {

    private BukuAddController controller;
    private ListBukuController listController;
    private final GenericDAO dao;

    public BukuAddModel() {
        dao = new GenericDAO();
    }

    public ListBukuController getListController() {
        return listController;
    }

    public void setListController(ListBukuController listController) {
        this.listController = listController;
    }

    public BukuAddController getController() {
        return controller;
    }

    public void setController(BukuAddController controller) {
        this.controller = controller;
    }

    //methode save untuk melakukan penyimpanan dan eksekusi ke hibernate
    public void save(Buku buku) {
        dao.save(buku);
    }

    //methode update untuk melakukan perubahan dan eksekusi ke hibernate
    public void update(Buku buku) {
        dao.update(buku);
    }

    //methode delete untuk melakukan penghapusan dan eksekusi ke hibernate
    public void delete(Buku buku) {
        dao.delete(buku);
    }

    //methode list untuk eksekusi SELECT pada hibernate di table Buku
    public List<Buku> list() {
        return dao.getAll(Buku.class);
    }

    //methode list untuk eksekusi SELECT pada hibernate di table Category
    public List<Category> lisCat() {
        return dao.getAll(Category.class);
    }

    //methode list untuk eksekusi SELECT pada hibernate di table Penerbit
    public List<Penerbit> listPen() {
        return dao.getAll(Penerbit.class);
    }

}
