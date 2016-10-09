package com.aidar.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by paradise on 07.05.16.
 */
public class DateUtil {

    public static Date getMonthAgoDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        return c.getTime();
    }

}
