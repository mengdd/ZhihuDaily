package com.ddmeng.zhihudaily.injection.component;

import com.ddmeng.zhihudaily.imageloader.ImageLoaderFactory;
import com.ddmeng.zhihudaily.injection.module.MainModule;
import com.ddmeng.zhihudaily.newslist.NewsListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {

    ImageLoaderFactory getImageLoaderFactory();

    void inject(NewsListFragment newsListFragment);
}
