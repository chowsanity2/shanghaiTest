package com.chow.utils;



import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConvertUtils {
    public static Date convertDate(String dateStr){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if(!StringUtils.isEmpty(dateStr))
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String convertDate2Month(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(date);
    }
    public static String convertDate2Quarter(Date date){
        SimpleDateFormat sdf = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        if(month==Calendar.JANUARY||month==Calendar.FEBRUARY||month==Calendar.MARCH){
           sdf  = new SimpleDateFormat("yyyy-1");
        }else if(month==Calendar.APRIL||month==Calendar.MAY||month==Calendar.JUNE){
            sdf  = new SimpleDateFormat("yyyy-2");
        }else if(month==Calendar.JULY||month==Calendar.AUGUST||month==Calendar.SEPTEMBER){
            sdf  = new SimpleDateFormat("yyyy-3");
        }else if(month==Calendar.OCTOBER||month==Calendar.NOVEMBER||month==Calendar.DECEMBER){
            sdf  = new SimpleDateFormat("yyyy-4");
        }
        return sdf.format(date);
    }
    public static String convertDate2Year(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(date);
    }

    public static String convertDate2Day(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static Date convertStr2Date(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(str);
    }
}
