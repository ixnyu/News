package com.xnyu.shanghai.Fragment;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.xnyu.shanghai.Base.BaseFragment;
import com.xnyu.shanghai.Base.BasePager;
import com.xnyu.shanghai.Pager.GovPager;
import com.xnyu.shanghai.Pager.HomePager;
import com.xnyu.shanghai.Pager.NewsPager;
import com.xnyu.shanghai.Pager.SettingPager;
import com.xnyu.shanghai.Pager.SmartPager;
import com.xnyu.shanghai.Pager.TXNewsPager;
import com.xnyu.shanghai.R;
import com.xnyu.shanghai.View.MyViewPager;

import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import java.util.ArrayList;

/**
 * Created by xnyu on 2016/1/29.
 */
public class ContentFragment extends BaseFragment {

    private MyViewPager mVp;
    private View mView;
    private ArrayList<BasePager> mPageArrayList ;
    private RadioButton mHome_home;
    private RadioButton mHome_news;
    private RadioButton mHome_smart;
    private RadioButton mHome_gov;
    private RadioButton mHome_setting;
    private RadioGroup mHome_bom;

    @Override
    public View initView(LayoutInflater inflater) {
        mView = inflater.inflate(R.layout.fragment_content, null);
        mVp = (MyViewPager) mView.findViewById(R.id.fragment_content_vp);
        mHome_home = (RadioButton) mView.findViewById(R.id.home_home_rb);
        mHome_home.setChecked(true);
        mHome_bom = (RadioGroup) mView.findViewById(R.id.home_bom_rg);
        return mView;
    }


    @Override
    public void initDate() {
        mPageArrayList = new ArrayList<>();
        mPageArrayList.add(new HomePager(mContext));
        mPageArrayList.add(new NewsPager(mContext));
        mPageArrayList.add(new GovPager(mContext));
        mPageArrayList.add(new SmartPager(mContext));
        mPageArrayList.add(new SettingPager(mContext));
        mVp.setAdapter(new VpAdapter());
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPageArrayList.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mHome_bom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.home_home_rb:
                        mVp.setCurrentItem(0, false);
                        break;
                    case R.id.home_news_rb:
                        mVp.setCurrentItem(1, false);
                        break;
                    case R.id.home_smart_rb:
                        mVp.setCurrentItem(2, false);
                        break;
                    case R.id.home_gov_rb:
                        mVp.setCurrentItem(3, false);
                        break;
                    case R.id.home_setting_rb:
                        mVp.setCurrentItem(4, false);
                        break;
                }
            }
        });

        mPageArrayList.get(0).initData();

    }

    class VpAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mPageArrayList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view ==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager page = mPageArrayList.get(position);
            //将页面添加到容器中
            container.addView(page.mView);
//            page.initData();
            return page.mView;
        }

    }
}
