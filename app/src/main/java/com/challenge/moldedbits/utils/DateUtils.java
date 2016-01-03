package com.challenge.moldedbits.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by bharat on 03/01/16.
 */
public class DateUtils {

    public static Date convertStringToDate(String dateString, String dateFormat) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertStringToDateFormat(String dateString, String dateFormat, String convertedFormat) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            Date date = simpleDateFormat.parse(dateString);
            SimpleDateFormat convertedDateFormat = new SimpleDateFormat(convertedFormat);
            convertedDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
            return convertedDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
