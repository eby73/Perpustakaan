/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.orm.utils;

import java.io.File;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author eby
 */
public class HibernateUtils {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory(){
         try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            String configFile = "conf/hibernate.cfg.xml";
            String path = System.getProperty("user.dir");
            path = path + "/" + configFile;
            SessionFactory sessFactory = new Configuration().configure(new File(path)).buildSessionFactory();
            return sessFactory;
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Exception stack Trace ************** begin");
            System.err.println("Hibernate : Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            System.err.println("Exception Stack Trace ********* END");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {

        return sessionFactory;
    }
}
