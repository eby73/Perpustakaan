/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.book.category;

import com.eby.orm.dao.GenericDAO;
import com.eby.orm.entity.Category;
import java.util.List;

/**
 *
 * @author eby
 */
public class CategoryModel {

    private CategoryController controller;
    private final GenericDAO dao;

    public CategoryModel() {
       dao = new GenericDAO();
    }

    public CategoryController getController() {
        return controller;
    }

    public void setController(CategoryController controller) {
        this.controller = controller;
    }

    public List<Category> list() {
        return dao.getAll(Category.class);
    }

    public void save(Category category) {
        dao.save(category);
    }

    public void update(Category category) {
        dao.update(category);
    }

    public void delete(Category category) {
       dao.delete(category);
    }

    public List<Category> findByNama(String nama) {
        return dao.findData("nama", nama, Category.class);
    }

    public Category findData(int id) {
        return dao.get(Category.class, id);
    }

    void saveOrUpdate(Category cat) {
        dao.saveOrUpdate(cat);
    }

}
