package com.ddmeng.zhihudaily.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {

    private static final String TAG = "Date";
    private static final String DATE_FORMAT = "yyyyMMdd";

    public static String getCurrentDataString() {
        String dateString = DateTime.now().toString(DATE_FORMAT);
        LogUtils.d(TAG, "getCurrentDateString: " + dateString);
        return dateString;
    }

    public static String getDateStringMinusDays(int days) {
        String dateString = DateTime.now().minusDays(days).toString(DATE_FORMAT);
        LogUtils.d(TAG, "get: " + days + " days before current date: " + dateString);
        return dateString;
    }

    public static String getDateStringPlusDays(String date, int days) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(DATE_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(date);
        String dateString = dateTime.plusDays(days).toString(DATE_FORMAT);
        LogUtils.d(TAG, "get: " + days + " days after: " + date + " = " + dateString);
        return dateString;
    }
}
