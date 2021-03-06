package com.eby.orm.entity;
// Generated Aug 16, 2015 10:48:52 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Buku generated by hbm2java
 */
public class Buku  implements java.io.Serializable {


     private int id;
     private Category category;
     private Penerbit penerbit;
     private String judul;
     private String pengarang;
     private String tahunTerbit;
     private String jumlahHalaman;
     private String synopsis;
     private Set peminjamans = new HashSet(0);

    public Buku() {
    }

	
    public Buku(int id, Category category, Penerbit penerbit, String judul, String pengarang) {
        this.id = id;
        this.category = category;
        this.penerbit = penerbit;
        this.judul = judul;
        this.pengarang = pengarang;
    }
    public Buku(int id, Category category, Penerbit penerbit, String judul, String pengarang, String tahunTerbit, String jumlahHalaman, String synopsis, Set peminjamans) {
       this.id = id;
       this.category = category;
       this.penerbit = penerbit;
       this.judul = judul;
       this.pengarang = pengarang;
       this.tahunTerbit = tahunTerbit;
       this.jumlahHalaman = jumlahHalaman;
       this.synopsis = synopsis;
       this.peminjamans = peminjamans;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    public Penerbit getPenerbit() {
        return this.penerbit;
    }
    
    public void setPenerbit(Penerbit penerbit) {
        this.penerbit = penerbit;
    }
    public String getJudul() {
        return this.judul;
    }
    
    public void setJudul(String judul) {
        this.judul = judul;
    }
    public String getPengarang() {
        return this.pengarang;
    }
    
    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }
    public String getTahunTerbit() {
        return this.tahunTerbit;
    }
    
    public void setTahunTerbit(String tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }
    public String getJumlahHalaman() {
        return this.jumlahHalaman;
    }
    
    public void setJumlahHalaman(String jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }
    public String getSynopsis() {
        return this.synopsis;
    }
    
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    public Set getPeminjamans() {
        return this.peminjamans;
    }
    
    public void setPeminjamans(Set peminjamans) {
        this.peminjamans = peminjamans;
    }




}


