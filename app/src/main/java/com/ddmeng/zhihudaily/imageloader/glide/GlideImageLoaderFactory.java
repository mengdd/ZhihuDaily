package com.ddmeng.zhihudaily.imageloader.glide;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.ddmeng.zhihudaily.imageloader.ImageLoader;
import com.ddmeng.zhihudaily.imageloader.ImageLoaderFactory;

public class GlideImageLoaderFactory implements ImageLoaderFactory {

    @Override
    public ImageLoader createImageLoader(Context context) {
        return new GlideImageLoader(context);
    }

    @Override
    public ImageLoader createImageLoader(Fragment fragment) {
        return new GlideImageLoader(fragment);
    }
}
