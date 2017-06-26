package com.ddmeng.zhihudaily.injection.module;

import com.ddmeng.zhihudaily.data.StoriesRepository;
import com.ddmeng.zhihudaily.data.local.StoriesLocalDataSource;
import com.ddmeng.zhihudaily.data.remote.ServiceGenerator;
import com.ddmeng.zhihudaily.data.remote.StoriesRemoteDataSource;
import com.ddmeng.zhihudaily.data.remote.ZhihuService;
import com.ddmeng.zhihudaily.imageloader.ImageLoaderFactory;
import com.ddmeng.zhihudaily.imageloader.glide.GlideImageLoaderFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    ImageLoaderFactory provideImageLoaderFactory() {
        return new GlideImageLoaderFactory();
    }

    @Singleton
    @Provides
    StoriesRepository provideStoriesRepository(StoriesRemoteDataSource remoteDataSource,
                                               StoriesLocalDataSource localDataSource) {
        return new StoriesRepository(remoteDataSource, localDataSource);
    }

    @Singleton
    @Provides
    ZhihuService provideZhihuService() {
        return ServiceGenerator.createService(ZhihuService.class);
    }
}
