package com.xnyu.shanghai.Global;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by xnyu on 2016/1/29.
 */
public class GlobalApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = GlobalApplication.this;
        OkHttpUtils.getInstance().debug("http");
        com.orhanobut.logger.Logger.init("News");
        JPushInterface.setDebugMode(true);
    }

    public static Context getContext(){
        return mContext;
    }
}
