package com.ddmeng.zhihudaily.data.local;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = ZhihuDatabase.NAME, version = ZhihuDatabase.VERSION)
public class ZhihuDatabase {
    public static final String NAME = "ZhihuDatabase";
    public static final int VERSION = 1;
}
