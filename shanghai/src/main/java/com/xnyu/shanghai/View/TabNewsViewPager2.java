package com.xnyu.shanghai.View;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 根据情况确定父控件是否拦截事件的ViewPager
 * Created by Administrator on 2016/2/18 0018.
 */
public class TabNewsViewPager2 extends ViewPager {
    private int mStartX;
    private int mStartY;
    private int mEndX;
    private int mEndY;

    public TabNewsViewPager2(Context context) {
        super(context);
    }

    public TabNewsViewPager2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = (int) ev.getX();
                mStartY = (int) ev.getY();
                //请求父控件以及祖宗控件不要拦截
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                mEndX = (int) ev.getX();
                mEndY = (int) ev.getY();
                //左右滑
                if (Math.abs(mEndX - mStartX) > Math.abs(mEndY - mStartY)) {
                    //第一个item时
                    if (getCurrentItem() == 0) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                        //最后一个item时
                    } else if (getCurrentItem() == getAdapter().getCount() - 1) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    //上下滑
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);

                }
                break;
        }


        return super.dispatchTouchEvent(ev);
    }
}
