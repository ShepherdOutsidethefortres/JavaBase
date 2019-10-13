package com.jdjr.knowledge.test.frame.internal.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final String DATETIME_FORMATE = "yyyy-MM-dd HH:mm:ss:ssss";
    private static final String DATE_FORMATE = "yyyy-MM-dd";

    public DateUtil() {
    }

    public static String timeToStr(long time) {
        try {
            return String.valueOf((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:ssss")).format(new Date(time)));
        } catch (Exception var3) {
            return null;
        }
    }

    public static String dateToStr(Date date) {
        try {
            return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
        } catch (Exception var2) {
            return null;
        }
    }
}
