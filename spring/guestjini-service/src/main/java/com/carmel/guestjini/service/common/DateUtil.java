package com.carmel.guestjini.service.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateUtil {
    private static List<SimpleDateFormat>
            dateFormats = new ArrayList<SimpleDateFormat>() {
        {
            add(new SimpleDateFormat("M/dd/yyyy"));
            add(new SimpleDateFormat("dd.M.yyyy"));
            add(new SimpleDateFormat("M/dd/yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.M.yyyy hh:mm:ss a"));
            add(new SimpleDateFormat("dd.MMM.yyyy"));
            add(new SimpleDateFormat("dd-MMM-yyyy"));
            add(new SimpleDateFormat("yyyy-M-dd"));
            add(new SimpleDateFormat("yyyy-M-dd hh:mm:ss"));
            add(new SimpleDateFormat("dd/M/yyyy"));
            add(new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy"));
        }
    };

    public static Date convertToDate(String input) {
        Date date = null;
        if(null == input) {
            return null;
        }
        for (SimpleDateFormat format : dateFormats) {
            try {
                format.setLenient(false);
                date = format.parse(input);
            } catch (ParseException e) {
                // try other formats
            }
            if (date != null) {
                break;
            }
        }

        return date;
    }
}
