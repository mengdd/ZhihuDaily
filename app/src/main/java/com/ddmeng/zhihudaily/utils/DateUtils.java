package com.ddmeng.zhihudaily.utils;

import android.content.Context;

import com.ddmeng.zhihudaily.R;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {

    private static final String TAG = "Date";
    private static final String DATE_FORMAT = "yyyyMMdd";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern(DATE_FORMAT);

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
        DateTime dateTime = DATE_TIME_FORMATTER.parseDateTime(date);
        String dateString = dateTime.plusDays(days).toString(DATE_FORMAT);
        LogUtils.d(TAG, "get: " + days + " days after: " + date + " = " + dateString);
        return dateString;
    }

    public static DateTime getDateTime(String date) {
        return DATE_TIME_FORMATTER.parseDateTime(date);
    }

    public static String getDateDisplayTitle(Context context, String date) {
        String displayPattern = context.getString(R.string.date_header_format);
        DateTime dateTime = DateUtils.getDateTime(date);
        String displayDateString = dateTime.toString(displayPattern);
        if (displayDateString.equals(new DateTime().toString(displayPattern))) {
            return context.getString(R.string.today_news);
        } else {
            return displayDateString;
        }
    }
}
