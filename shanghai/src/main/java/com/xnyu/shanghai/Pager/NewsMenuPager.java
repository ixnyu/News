package com.xnyu.shanghai.Pager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.gxz.PagerSlidingTabStrip;
import com.xnyu.shanghai.Base.BaseNewsPager;
import com.xnyu.shanghai.Bean.NewsData;
import com.xnyu.shanghai.Acitivty.MainActivity;
import com.xnyu.shanghai.R;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


/**
 * 菜单详情页-新闻
 * Created by Administrator on 2016/2/17 0017.
 */
public class NewsMenuPager extends BaseNewsPager {

    private FrameLayout mFr_content;
    private List<NewsData.DataEntity.ChildrenEntity> mTabPager;
    private ViewPager mViewPager;
    private ArrayList<BaseNewsPager> mNewsPagers;
    private PagerSlidingTabStrip mTabStrip;

    public NewsMenuPager(Context context, List<NewsData.DataEntity.ChildrenEntity> data) {
        super(context);
        mTabPager = data;

    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.pager_news_tab, null);
        mViewPager = (ViewPager) view.findViewById(R.id.pager_news_vp);
        mTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.vp_title);
        return view;
    }

    @Override
    public void initData() {
        mNewsPagers = new ArrayList<>();
        for (NewsData.DataEntity.ChildrenEntity childrenEntity : mTabPager) {
            mNewsPagers.add(new TabNewsPager(mContext, childrenEntity));
        }
        mViewPager.setAdapter(new TabViewPagerAdapter());
        mTabStrip.setViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    EventBus.getDefault().post(true, MainActivity.SET_SLIDING_MENU_ENABLE);
                } else {
                    EventBus.getDefault().post(false, MainActivity.SET_SLIDING_MENU_ENABLE);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class TabViewPagerAdapter extends PagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabPager.get(position).getTitle();
        }

        @Override
        public int getCount() {
            return mTabPager.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabNewsPager pager = (TabNewsPager) mNewsPagers.get(position);
            container.addView(pager.mRootView);
            pager.initData();
            return pager.mRootView;
        }
    }

}
