package com.ddmeng.zhihudaily;

import android.app.Application;

import com.ddmeng.zhihudaily.injection.component.DaggerMainComponent;
import com.ddmeng.zhihudaily.injection.component.MainComponent;
import com.ddmeng.zhihudaily.injection.module.MainModule;
import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;

public class ZhihuDailyApplication extends Application {

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule())
                .build();
        FlowManager.init(this);
        Stetho.initializeWithDefaults(this);
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
