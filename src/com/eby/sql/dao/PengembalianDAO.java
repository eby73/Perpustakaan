/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.sql.dao;

import com.eby.orm.entity.Peminjaman;
import com.eby.sql.connector.MySQLQuery;
import java.sql.PreparedStatement;

/**
 *
 * @author eby
 */
public class PengembalianDAO {

    public void kembalikan(int id) {
        MySQLQuery query = new MySQLQuery();
        String sql = "delete from peminjaman where id = ?";
        PreparedStatement pst = query.prepareQuery(sql);
        try {
            Peminjaman p = new Peminjaman();
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
