package com.xnyu.shanghai.Base;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;


/**
 * 菜单详情页基类
 * Created by Administrator on 2016/2/16 0016.
 */
public abstract class BaseNewsPager {
    public  Context mContext;
    public View mRootView;
    public String[] mUrls = new String[]{};

    public BaseNewsPager(Context context) {
        mContext = context;
        mRootView= initView();
    }

    public abstract View initView();

    public void initData(){}

}
