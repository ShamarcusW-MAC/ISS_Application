package com.example.iss_application;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import static org.junit.Assert.*;


public class ConversionTest {

    @Test
    public void getDate() {
        long milliSeconds = 1578933653;
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(milliSeconds * 1000);

        assertEquals("01/13/2020", format.format(calendar.getTime()));

    }
}
