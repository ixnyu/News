package com.xnyu.shanghai.Pager;

import android.content.Context;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xnyu.shanghai.Base.BaseNewsPager;
import com.xnyu.shanghai.Base.BasePager;
import com.xnyu.shanghai.Bean.NewsData;
import com.xnyu.shanghai.Fragment.LeftMenuFragment;
import com.xnyu.shanghai.Global.GlobalData;
import com.xnyu.shanghai.Acitivty.MainActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by xnyu on 2016/1/29.
 */
public class NewsPager extends BasePager {

    public static final String SET_MENU_PAGER = "setMenuPager";

    private ArrayList<BaseNewsPager> mNewsPagers;
    private NewsData mNewsData;


    public NewsPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        mTitleTv.setText("新闻");
        EventBus.getDefault().post(true, MainActivity.SET_SLIDING_MENU_ENABLE);
        getDataFromNetwork();


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

    private void getDataFromNetwork() {
        OkHttpUtils.get().url(GlobalData.CATEGORIES).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Logger.e("获取网络数据失败", e);
            }

            @Override
            public void onResponse(String response) {
                Logger.json(response);
                parserData(response);
            }
        });
    }

    private void parserData(String response) {
        mNewsData = new Gson().fromJson(response, NewsData.class);
        Logger.i(mNewsData.getData().get(0).getTitle());
        EventBus.getDefault().post(mNewsData, LeftMenuFragment.GET_MENU_DATA);
        mNewsPagers = new ArrayList<>();
        mNewsPagers.add(new NewsMenuPager(mContext,mNewsData.getData().get(0).getChildren()));
        mNewsPagers.add(new TopicsMenuPager(mContext));
        //把组图数据通过构造方法传递
        mNewsPagers.add(new PhotosPager(mContext,mPhotosIbtn));
        mNewsPagers.add(new InteractionMenuPager(mContext));
        setMenuPager(0);


    }

}
