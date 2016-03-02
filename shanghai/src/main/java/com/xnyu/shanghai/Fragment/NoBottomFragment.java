package com.xnyu.shanghai.Fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gxz.PagerSlidingTabStrip;
import com.xnyu.shanghai.Base.BaseFragment;
import com.xnyu.shanghai.Base.BaseNewsPager;
import com.xnyu.shanghai.Global.GlobalData;
import com.xnyu.shanghai.R;
import com.xnyu.shanghai.TX.BaseTXNewsPager;
import com.xnyu.shanghai.TX.GuoNei;
import com.xnyu.shanghai.TX.Jian;
import com.xnyu.shanghai.TX.Keji;
import com.xnyu.shanghai.TX.Mei;
import com.xnyu.shanghai.TX.Qiwen;
import com.xnyu.shanghai.TX.Social;
import com.xnyu.shanghai.TX.Tiyu;
import com.xnyu.shanghai.TX.World;
import com.xnyu.shanghai.TX.Yule;

import java.util.ArrayList;

/**
 * Created by xnyu on 2016/1/29.
 */
public class NoBottomFragment extends BaseFragment {


    private PagerSlidingTabStrip mTabStrip;
    private ViewPager mViewPager;
    private NoBotViewPager mNoBotViewPager;
    private ArrayList<BaseNewsPager> mTXPager;

    @Override
    public View initView(LayoutInflater inflater) {
        View view = View.inflate(mContext, R.layout.fragment_nobottom, null);
        mTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.vp_title_nobot);
        mViewPager = (ViewPager) view.findViewById(R.id.pager_news_vp_nobot);
        return view;
    }

    @Override
    public void initDate() {
        mNoBotViewPager = new NoBotViewPager();
        mTXPager = new ArrayList<>();
       mTXPager.add(new Jian(mContext));
       mTXPager.add(new GuoNei(mContext));
       mTXPager.add(new Keji(mContext));
       mTXPager.add(new Mei(mContext));
       mTXPager.add(new Qiwen(mContext));
       mTXPager.add(new Social(mContext));
       mTXPager.add(new Tiyu(mContext));
       mTXPager.add(new World(mContext));
       mTXPager.add(new Yule(mContext));
        mViewPager.setAdapter(mNoBotViewPager);
        mTabStrip.setViewPager(mViewPager);
    }

   class NoBotViewPager extends PagerAdapter{

       @Override
       public CharSequence getPageTitle(int position) {
           return GlobalData.TX_TITLE[position];
       }

       @Override
       public int getCount() {
           return mTXPager.size();
       }

       @Override
       public boolean isViewFromObject(View view, Object object) {
           return view==object;
       }

       @Override
       public Object instantiateItem(ViewGroup container, int position) {
           BaseNewsPager baseNewsPager = mTXPager.get(position);
           container.addView(baseNewsPager.mRootView);
           baseNewsPager.initData();
           return baseNewsPager.mRootView;
       }

       @Override
       public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView((View)object);
       }
   }
}
