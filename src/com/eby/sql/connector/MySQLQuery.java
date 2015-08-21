/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eby.sql.connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author gen5x4
 */
public class MySQLQuery {

    public MySQLQuery() {
    }

    //method untuk mengeksekusi pernitah mysql DML
    public  ResultSet DMLQuery(String sql) {
        PreparedStatement prepare = null;
        try{
            prepare = MySQLConnector.OpenConnection().prepareStatement(sql);
            ResultSet rs = prepare.executeQuery();
            return rs;

        } catch (SQLException ex){
            ex.printStackTrace();
            return null;
        } 
    }

    //method untuk mengeksekusi pernitah mysql DDL
    public  boolean DDLQuery(String sql) {
        PreparedStatement prepare =null;
        try{
            prepare = MySQLConnector.OpenConnection().prepareStatement(sql);
            boolean status=false;
            status=prepare.execute(sql);
            return status;

        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        } finally{
            if (prepare!=null){
                try {
                    prepare.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MySQLQuery.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public  PreparedStatement prepareQuery(String sql){
        try{
              PreparedStatement prepare = MySQLConnector.OpenConnection().prepareStatement(sql);
              return prepare;
        } catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }

    }
}

