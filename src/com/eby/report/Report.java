/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;

/**
 *
 * @author eby
 */
public class Report {

    public void report() throws SQLException, ClassNotFoundException, DRException, FileNotFoundException, IOException {

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/perpustakaan", "root", "a");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        JasperReportBuilder report = DynamicReports.report();
        report
                .columns(
                        Columns.column("ID", "id", DataTypes.integerType()).setHorizontalAlignment(HorizontalAlignment.LEFT),
                        Columns.column("NIM", "anggota_id", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.LEFT),
                        Columns.column("ID BUKU", "buku_id", DataTypes.stringType()).setHorizontalAlignment(HorizontalAlignment.LEFT),
                        Columns.column("Tgl Pinjam", "tgl_pinjam", DataTypes.dateType()).setHorizontalAlignment(HorizontalAlignment.LEFT),
                        Columns.column("Tgl kembali", "tgl_kembali", DataTypes.dateType()).setHorizontalAlignment(HorizontalAlignment.LEFT));

        StyleBuilder plainStyle = stl.style().setFontName("Ubuntu");
        report.title(
                Components.text("Daftar Peminjaman").setStyle(plainStyle)
                .setHorizontalAlignment(HorizontalAlignment.CENTER));

        report.setDataSource("SELECT * FROM peminjaman", connection);

        try {
            report.show(false);
//            report.toDocx(new FileOutputStream("/home/eby/Report.docx"));
            report.toString();
        } catch (DRException e) {
            e.printStackTrace();
        }
    }
}
