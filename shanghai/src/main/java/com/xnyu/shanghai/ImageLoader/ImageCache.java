package com.xnyu.shanghai.ImageLoader;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/2/24 0024.
 */
public interface ImageCache {
    void put(String url, Bitmap bitmap);
    Bitmap get(String url);
}
