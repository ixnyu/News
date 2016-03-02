package com.xnyu.shanghai.Pager;

import android.content.Context;

import com.xnyu.shanghai.Base.BaseNewsPager;
import com.xnyu.shanghai.Base.BasePager;
import com.xnyu.shanghai.Bean.NewsData;
import com.xnyu.shanghai.Bean.TXNewsData;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xnyu on 2016/1/29.
 */
public class TXNewsPager extends BasePager {

    public static final String SET_MENU_PAGER = "setMenuPager";

    private ArrayList<BaseNewsPager> mNewsPagers;
    private NewsData mNewsData;
    private TXNewsData mTx_newsData;
    private List<TXNewsData.NewslistEntity> mNewslist;


    public TXNewsPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        mTitleTv.setText("新闻");
        mFrContent.removeAllViews();
        mFrContent.addView(mNewsPagers.get(0).mRootView);
    }


    /**
     * 设置新闻菜单页
     * @param position
     */
    @Subscriber(tag = SET_MENU_PAGER)
    private void setMenuPager(int position) {
        mFrContent.removeAllViews();
        mFrContent.addView(mNewsPagers.get(position).mRootView);
        mNewsPagers.get(position).initData();
    }


}
