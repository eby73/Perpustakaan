/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.tester;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.commons.validator.routines.EmailValidator;

/**
 *
 * @author eby
 */
public class test {

    public static void main(String[] args) {

        EmailValidator valid = EmailValidator.getInstance();
        if (valid.isValid("eby.clickhander7@gmail.com")) {
            System.out.println("valid");
        }else{
            System.out.println("invalid");
        }

    }

}
