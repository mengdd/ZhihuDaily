package com.ddmeng.zhihudaily.imageloader.glide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.ddmeng.zhihudaily.imageloader.ImageLoader;
import com.ddmeng.zhihudaily.utils.LogUtils;

public class GlideImageLoader implements ImageLoader {

    public static final String TAG = "GlideImageLoader";

    private final RequestManager requestManager;

    public GlideImageLoader(Context context) {
        LogUtils.i(TAG, "Glide with context");
        this.requestManager = Glide.with(context);
    }

    public GlideImageLoader(Fragment fragment) {
        LogUtils.i(TAG, "Glide with fragment");
        this.requestManager = Glide.with(fragment);
    }

    @Override
    public void load(String url, ImageView imageView) {
        LogUtils.i(TAG, "load: " + url);
        this.requestManager.load(url).into(imageView);
    }
}
