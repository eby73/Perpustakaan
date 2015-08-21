/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author eby
 */
public class JspReport {
    
    public void jspReport() throws FileNotFoundException, JRException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/perpustakaan", "root", "a");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        InputStream input = new FileInputStream(new File("/home/eby/NetBeansProjects/OtherPerpustakaan/src/com/eby/report/Report.jrxml"));
        JasperDesign jspDesign = JRXmlLoader.load(input);
        JasperReport jspReport = JasperCompileManager.compileReport(jspDesign);
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("jspReport", "empReport");
        
        JasperPrint jspPrint = JasperFillManager.fillReport(jspReport, null, connection);
        OutputStream out = new FileOutputStream(new File("/home/eby/test.pdf"));
        JasperExportManager.exportReportToPdfStream(jspPrint, out);
        
    }
    
}
