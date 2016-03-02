package com.xnyu.shanghai.Pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.xnyu.shanghai.Base.BasePager;
import com.xnyu.shanghai.Acitivty.MainActivity;

import org.simple.eventbus.EventBus;

/**
 * Created by xnyu on 2016/1/29.
 */
public class SettingPager extends BasePager {
    public SettingPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        mTitleTv.setText("设置");
        TextView textView = new TextView(mContext);
        textView.setText("设置");
        textView.setTextColor(Color.RED);
        textView.setTextSize(22);
        textView.setGravity(Gravity.CENTER);
        mFrContent.addView(textView);
        mTitleIbtn.setVisibility(View.GONE);
        EventBus.getDefault().post(false, MainActivity.SET_SLIDING_MENU_ENABLE);
    }
}
