package com.ddmeng.zhihudaily;

import android.app.Application;

import com.ddmeng.zhihudaily.injection.component.DaggerMainComponent;
import com.ddmeng.zhihudaily.injection.component.MainComponent;
import com.ddmeng.zhihudaily.injection.module.MainModule;

public class ZhihuDailyApplication extends Application {

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule())
                .build();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
