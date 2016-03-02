package com.xnyu.shanghai.Pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.xnyu.shanghai.Base.BasePager;
import com.xnyu.shanghai.Acitivty.MainActivity;

import org.simple.eventbus.EventBus;


/**
 * Created by xnyu on 2016/1/29.
 */
public class HomePager extends BasePager {
    public HomePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        mTitleTv.setText("首页");
        TextView textView = new TextView(mContext);
        textView.setText("首页");
        textView.setTextColor(Color.RED);
        textView.setTextSize(22);
        textView.setGravity(Gravity.CENTER);
        mFrContent.addView(textView);
        mTitleIbtn.setVisibility(View.GONE);
        EventBus.getDefault().post(false, MainActivity.SET_SLIDING_MENU_ENABLE);
        Logger.i("首页初始化");
    }

}
