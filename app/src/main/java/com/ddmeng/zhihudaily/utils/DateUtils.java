package com.ddmeng.zhihudaily.utils;

import org.joda.time.DateTime;

public class DateUtils {

    private static final String TAG = "Date";
    private static final String DATE_FORMAT = "yyyyMMdd";

    public static String getCurrentDataString() {
        String dateString = DateTime.now().toString(DATE_FORMAT);
        LogUtils.d(TAG, "getCurrentDateString: " + dateString);
        return dateString;
    }

    public static String getDateString(int daysBefore) {
        String dateString = DateTime.now().minusDays(daysBefore).toString(DATE_FORMAT);
        LogUtils.d(TAG, "get: " + daysBefore + " days before current date: " + dateString);
        return dateString;
    }
}
