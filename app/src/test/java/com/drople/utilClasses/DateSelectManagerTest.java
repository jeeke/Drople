package com.drople.utilClasses;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DateSelectManagerTest {

    @Test
    public void isSelectedDateValid() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
//        String date = new Date().toString();
        assertTrue(DateSelectManager.isSelectedDateValid(strDate));
    }

    @Test
    public void isSelectedSlotValid() {
        assertTrue(DateSelectManager.isSelectedSlotValid("10:00 AM"));
    }
}