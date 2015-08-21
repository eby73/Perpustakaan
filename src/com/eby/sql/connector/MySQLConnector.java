package com.eby.sql.connector;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author gen5x4
 */
public class MySQLConnector {

    private final static String path = System.getProperty("user.dir") + "/conf/";
    private final static String filename = "mysql.conf";

    //method untuk melakukan koneksi
    public static Connection OpenConnection() {
        try {

            String[] config = MySQLConnector.loadConfig();
            String url = config[0];
            String user = config[1];
            String pass = config[2];

            //register Driver MySQL
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static String[] loadConfig() {
        String[] result = new String[3];
        Properties prop = new Properties();
        InputStream input = null;
        String file = path + filename;

        try {
            input = new FileInputStream(file);
            // load a properties file
            prop.load(input);
            result[0] = prop.getProperty("url");
            result[1] = prop.getProperty("username");
            result[2] = prop.getProperty("password");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;

    }

}
