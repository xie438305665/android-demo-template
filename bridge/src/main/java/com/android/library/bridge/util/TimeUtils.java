package com.android.library.bridge.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author xcl
 */
public class TimeUtils {

    @SuppressLint("SimpleDateFormat")
    public static String stampToDate(long time) {
        if (time < 60) {
            return time + "秒";
        }
        int hours = (int) (time / 3600);
        int minute = (int) (time / 60);
        if (time % 3600 == 0) {
            return hours + "小时";
        } else if (time / 3600 >= 1) {
            return minute % 60 == 0 ? hours + "小时" + (time - hours * 3600) / 60 + "分钟" : hours + "小时" + minute % 60 + "分钟" + time % 60 + "秒";
        } else {
            return minute % 60 == 0 ? minute + "分钟" : minute + "分钟" + time % 60 + "秒";
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String stampToDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.getTime());
    }

    @SuppressLint("SimpleDateFormat")
    public static long strDayToLong(String strDay) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(strDay));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getTimeInMillis();
    }
}
