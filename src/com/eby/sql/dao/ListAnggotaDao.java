/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.sql.dao;

import com.eby.orm.entity.Anggota;
import com.eby.sql.connector.MySQLQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eby
 */
public class ListAnggotaDao {
    public List<Anggota> list(){
        List<Anggota> list = new ArrayList<Anggota>();
        MySQLQuery query = new MySQLQuery();
        String sql = "SELECT * from anggota";
        ResultSet rs = query.DMLQuery(sql);
        try {
            while (rs.next()) {
                Anggota anggota = new Anggota();
                anggota.setId(rs.getInt("id"));
                anggota.setNama(rs.getString("nama"));
                anggota.setAlamat(rs.getString("alamat"));
                anggota.setKelas(rs.getString("kelas"));
                list.add(anggota);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListAnggotaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
}
