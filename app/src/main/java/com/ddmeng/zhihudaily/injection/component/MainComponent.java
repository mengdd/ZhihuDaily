package com.ddmeng.zhihudaily.injection.component;

import com.ddmeng.zhihudaily.imageloader.ImageLoaderFactory;
import com.ddmeng.zhihudaily.injection.module.MainModule;

import dagger.Component;

@Component(modules = {MainModule.class})
public interface MainComponent {

    ImageLoaderFactory getImageLoaderFactory();
}
