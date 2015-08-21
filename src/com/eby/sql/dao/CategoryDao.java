/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.sql.dao;

import com.eby.orm.entity.Category;
import com.eby.sql.connector.MySQLQuery;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eby
 */
public class CategoryDao {

    public List<Category> listCari(String where, String cari) {
        List<Category> list = new ArrayList<Category>();
        MySQLQuery query = new MySQLQuery();
        String sql = "select * from category where " + where + " like '%" + cari + "%'";
        ResultSet rs = query.DMLQuery(sql);
        try {
            while (rs.next()) {
                Category cat = new Category();
                cat.setId(rs.getInt("id"));
                cat.setNama(rs.getString("nama"));

                Category parent = new Category();

                cat.setCategory(parent);

                list.add(cat);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void update(Category cat) {
        MySQLQuery query = new MySQLQuery();
        String sql = "UPDATE `category` SET `parent_id`=?, `nama`=? where `id`=?";
        PreparedStatement pst = query.prepareQuery(sql);
        try {
            pst.setString(1, cat.getNama());
            pst.setInt(2, cat.getId());
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }

}
