/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.admin.view;

import com.eby.orm.dao.GenericDAO;
import com.eby.orm.entity.User;
import java.util.List;

/**
 *
 * @author eby
 */
public class ListAdminModel {

    private ListAdminController controller;
    private final GenericDAO dao;

    public ListAdminModel() {
        dao = new GenericDAO();
    }

    public ListAdminController getController() {
        return controller;
    }

    public void setController(ListAdminController controller) {
        this.controller = controller;
    }

    public List<User> list() {
        return dao.getAll(User.class);
    }

    public void delete(User user) {
        dao.delete(user);
    }

    public void update(User user) {
        dao.update(user);
    }

    public List<User> findData(String keyword) {
        return dao.findData("nama", keyword, User.class);
    }
}
