/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.sql.dao;

import com.eby.orm.entity.Anggota;
import com.eby.orm.entity.Buku;
import com.eby.orm.entity.Category;
import com.eby.orm.entity.Peminjaman;
import com.eby.orm.entity.Penerbit;
import com.eby.sql.connector.MySQLQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eby
 */
public class PeminjamanDAO {

    public List<Peminjaman> cari(String where, String like) {
        List<Peminjaman> list = new ArrayList<Peminjaman>();
        MySQLQuery query = new MySQLQuery();
        String sql = "select * from peminjaman where " + where + " like '%" + like + "%'";
        ResultSet rs = query.DMLQuery(sql);
        try {
            while (rs.next()) {
                Peminjaman p = new Peminjaman();
                p.setId(rs.getInt("id"));
                Anggota a = new Anggota();
                a.setId(rs.getInt("anggota_id"));
                p.setAnggota(a);
                Buku b = new Buku();
                b.setId(rs.getInt("buku_id"));
                p.setBuku(b);
                p.setTglPinjam(rs.getDate("tgl_pinjam"));
                p.setTglKembali(rs.getDate("tgl_kembali"));
                list.add(p);
//                Anggota a = new Anggota();
//                a.setId(rs.getInt("id"));
//                a.setNama(rs.getString("nama"));
//                a.setAlamat(rs.getString("alamat"));
//                a.setKelas(rs.getString("kelas"));

//                Buku b = new Buku();
//
//                b.setId(rs.getInt("id"));
//
//                Category c = new Category();
//                c.setId(rs.getInt("id"));
//                c.setNama(rs.getString("nama"));
//                b.setCategory(c);
//
//                Penerbit pe = new Penerbit();
//                pe.setId(rs.getInt("id"));
//                pe.setNama(rs.getString("nama"));
//                pe.setAlamat(rs.getString("alamat"));
//                b.setPenerbit(pe);
//
//                b.setJudul(rs.getString("judul"));
//                b.setPengarang(rs.getString("pengarang"));
//                b.setTahunTerbit(rs.getString("tahun_Terbit"));
//                b.setJumlahHalaman(rs.getString("jumlah_Halaman"));
//                b.setSynopsis(rs.getString("synopsis"));
//                p.setBuku(b);
            }
        } catch (Exception e) {

        }
        return list;
    }
}
