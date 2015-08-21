/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.pengembalian;

import com.eby.orm.dao.GenericDAO;
import com.eby.orm.entity.Peminjaman;
import com.eby.sql.dao.PengembalianDAO;

/**
 *
 * @author eby
 */
public class PengembalianModel {

    private PengembalianController controller;
    private PengembalianDAO sqlDao;
    private GenericDAO dao;

    public PengembalianModel() {
        sqlDao = new PengembalianDAO();
    }

    public PengembalianController getController() {
        return controller;
    }

    public void setController(PengembalianController controller) {
        this.controller = controller;
    }

    public void kembalikan(int id) {
        sqlDao.kembalikan(id);
    }

    public void kembali(Peminjaman p) {
        dao.delete(p);
    }

}
