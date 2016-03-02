package com.xnyu.shanghai.Pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.xnyu.shanghai.Base.BasePager;
import com.xnyu.shanghai.Acitivty.MainActivity;

import org.simple.eventbus.EventBus;

/**
 * Created by xnyu on 2016/1/29.
 */
public class GovPager extends BasePager {
    public GovPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        mTitleTv.setText("政务");
        TextView textView = new TextView(mContext);
        textView.setText("政务");
        textView.setTextColor(Color.RED);
        textView.setTextSize(22);
        textView.setGravity(Gravity.CENTER);
        mFrContent.addView(textView);
        EventBus.getDefault().post(true, MainActivity.SET_SLIDING_MENU_ENABLE);
    }
}
