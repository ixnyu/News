package com.xnyu.shanghai.Acitivty;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.xnyu.shanghai.Fragment.ContentFragment;
import com.xnyu.shanghai.Fragment.LeftMenuFragment;
import com.xnyu.shanghai.Fragment.NoBottomFragment;
import com.xnyu.shanghai.R;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import cn.jpush.android.api.JPushInterface;


public class MainActivity extends Activity {

    public static final String  TOGGLE = "toggle";
    public static final String  SET_SLIDING_MENU_ENABLE = "setSlidingMenuEnable";
    private SlidingMenu mSlidingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        JPushInterface.init(this);
        initView();

    }

    private void initView() {
        mSlidingMenu = new SlidingMenu(this);
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        //        slidingMenu.setShadowWidthRes();
        //        slidingMenu.setShadowDrawable();
        // 设置滑动菜单视图的宽度
        //mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        mSlidingMenu.setBehindWidthRes(R.dimen.slidingmenu_width);
        // 设置渐入渐出效果的值
        mSlidingMenu.setFadeDegree(0.35f);

        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        mSlidingMenu.setMenu(R.layout.left_menu);
        //禁止划出
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        LeftMenuFragment leftMenuFragment = new LeftMenuFragment();
        transaction.replace(R.id.fr_left_menu, leftMenuFragment,"leftFragment");
        //transaction.replace(R.id.activity_main_frcontext, new ContentFragment());
        transaction.replace(R.id.activity_main_frcontext, new NoBottomFragment());
        transaction.commit();
    }

    @Subscriber(tag = SET_SLIDING_MENU_ENABLE)
    private void setSlidingMenuEnable(boolean enable) {
        //if (enable) {
        //    mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        //} else {
        //    mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        //}

        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
    }

    @Subscriber(tag = TOGGLE)
    private void toggle(int i) {
        mSlidingMenu.toggle();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}

