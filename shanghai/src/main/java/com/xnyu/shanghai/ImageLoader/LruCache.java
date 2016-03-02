package com.xnyu.shanghai.ImageLoader;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/3/1 0001.
 */
public class LruCache implements ImageCache {

    /**
     * 最大可用内存的1/8作为缓存
     */
    final int mCacheSize = (int) (Runtime.getRuntime().maxMemory()/1024)/8;
    public void put(String url, Bitmap bitmap) {

    }

    @Override
    public Bitmap get(String url) {
        return null;
    }
}
