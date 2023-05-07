package com.example.clouddisk.common;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author:faryhao
 * @create: 2023-05-07 13:38
 * @Description: riqi
 */
public class DateFormatUtil {
    public static String format(Date date){
        DateFormat df1 = DateFormat.getDateInstance(DateFormat.LONG, Locale.CHINA);
        DateFormat df2 = DateFormat.getTimeInstance(DateFormat.LONG, Locale.CHINA);
        return df1.format(date)+df2.format(date);
    }
}
