/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author eby
 */
public class DatePickerHelper {

    public static Date date(Date date) {
        DateFormat dt = new SimpleDateFormat();
        Date lD = dt.getCalendar().getTime();
        return lD;
    }

}
