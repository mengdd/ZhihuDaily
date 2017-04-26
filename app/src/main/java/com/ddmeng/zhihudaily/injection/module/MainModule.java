package com.ddmeng.zhihudaily.injection.module;

import com.ddmeng.zhihudaily.imageloader.ImageLoaderFactory;
import com.ddmeng.zhihudaily.imageloader.glide.GlideImageLoaderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    ImageLoaderFactory provideImageLoaderFactory() {
        return new GlideImageLoaderFactory();
    }
}
