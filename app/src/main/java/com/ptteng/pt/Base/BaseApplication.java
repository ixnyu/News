package com.ptteng.pt.Base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.xutils.x;

/**
 * Created by xnyu on 2015-10-15.
 */
public class BaseApplication extends Application {
    private static BaseApplication application;
    private static int mainTid;
    private static Handler handler;
    private static RequestQueue queues ;

    //  在主线程运行的
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        application=this;
        queues = Volley.newRequestQueue(getApplicationContext());
        mainTid = android.os.Process.myTid();
        handler=new Handler();
    }
    public static Context getApplication() {
        return application;
    }
    public static int getMainTid() {
        return mainTid;
    }
    public static Handler getHandler() {
        return handler;
    }

    public static RequestQueue getHttpQueues() {
        return queues;
    }
}
