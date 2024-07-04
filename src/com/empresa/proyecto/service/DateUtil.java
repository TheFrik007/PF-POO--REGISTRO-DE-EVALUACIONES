package com.empresa.proyecto.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public static Date convertirStringADate(String fecha) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.parse(fecha);
    }

    public static String convertirDateAString(Date fecha) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.format(fecha);
    }
}
