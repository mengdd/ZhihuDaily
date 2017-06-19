package com.ddmeng.zhihudaily.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final String TAG = "Date";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    public static String getCurrentDataString() {
        Date currentData = new Date();
        String dateString = DATE_FORMAT.format(currentData);
        LogUtils.d(TAG, "getCurrentDateString: " + dateString);
        return dateString;
    }
}
