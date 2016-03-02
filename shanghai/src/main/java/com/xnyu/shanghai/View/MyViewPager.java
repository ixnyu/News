package com.xnyu.shanghai.View;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * Created by xnyu on 2016/1/29.
 */
public class MyViewPager extends ViewPager{
    private boolean isTouch = false;

    public boolean isTouch() {
        return isTouch;
    }



    public void setIsTouch(boolean isTouch) {
        this.isTouch = isTouch;
    }

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isTouch;
    }
}
