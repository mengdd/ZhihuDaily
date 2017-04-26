package com.ddmeng.zhihudaily.imageloader;

import android.content.Context;
import android.support.v4.app.Fragment;

public interface ImageLoaderFactory {
    ImageLoader createImageLoader(Context context);

    ImageLoader createImageLoader(Fragment fragment);
}
