package com.xnyu.shanghai.Pager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.xnyu.shanghai.Acitivty.NewsActivity;
import com.xnyu.shanghai.Base.BaseNewsPager;
import com.xnyu.shanghai.Bean.NewsData;
import com.xnyu.shanghai.Bean.NewsTabData;
import com.xnyu.shanghai.Global.GlobalData;
import com.xnyu.shanghai.R;
import com.xnyu.shanghai.View.RefreshListView;
import com.xnyu.shanghai.View.TabNewsViewPager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/2/17 0017.
 */
public class TXTabNewsPager extends BaseNewsPager {

    private NewsData.DataEntity.ChildrenEntity mTabData;
    private TabNewsViewPager mTabNewsViewPager;
    private NewsTabData mNewsTabData;
    private LinearLayout mLinearLayout;
    private TextView mTvRound;
    private int mCurrent;
    private RefreshListView mListView;
    private View mHeadView;
    /**
     * 新闻List数据
     */
    private List<NewsTabData.DataEntity.NewsEntity> mNewsListData;
    private String mMoreUrl;
    private NewsListViewAdapter mNewsListViewAdapter;

    public TXTabNewsPager(Context context) {
        super(context);
    }


    public TXTabNewsPager(Context context, NewsData.DataEntity.ChildrenEntity childrenEntity) {
        super(context);
        mTabData = childrenEntity;
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext,R.layout.tab_news_pager,null);
        mListView = (RefreshListView) view.findViewById(R.id.lv_content);
        //mHeadView = View.inflate(mContext, R.layout.head_viewpager, null);
        //mListView.addHeaderView(mHeadView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent  intent = new Intent(mContext, NewsActivity.class);
                intent.putExtra("url", mNewsListData.get(position).getUrl());
                mContext.startActivity(intent);

            }
        });

        // 设置下拉刷新监听
        mListView.setOnRefreshListener(new RefreshListView.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // 从网络加载数据
                getDataFromNetwork();
            }

            @Override
            public void loadMore() {
                // 加载更多数据
                if (mMoreUrl != null) {
                    getMoreDataFromNetwork();
                } else {
                    mListView.onRefreshComplete(true);// 收起加载更多布局
                    Toast.makeText(mContext, "没有更多数据了", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        //mTabNewsViewPager = (TabNewsViewPager) mHeadView.findViewById(R.id.vp_tab_news);
        //mLinearLayout = (LinearLayout) mHeadView.findViewById(R.id.ll_round);
        //mTvRound = (TextView) mHeadView.findViewById(R.id.tv_round);
        return view;
    }

    //加载更多
    private void getMoreDataFromNetwork() {
        OkHttpUtils.get().url(GlobalData.SERVER_URL+ mNewsTabData.getData().getMore()).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                mListView.onRefreshComplete(false);
                Toast.makeText(mContext,"没有更多数据了",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                parserData(response,true);
                mListView.onRefreshComplete(true);
            }
        });
    }


    class NewsListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mNewsListData.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(mContext,R.layout.list_news_item,null);
                holder.titleIv = (ImageView) convertView.findViewById(R.id.iv_news);
                holder.titleTv = (TextView) convertView.findViewById(R.id.tv_title);
                holder.timeTv = (TextView) convertView.findViewById(R.id.time_tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.titleTv.setText(mNewsListData.get(position).getTitle());
            holder.timeTv.setText(mNewsListData.get(position).getPubdate());
            OkHttpUtils.get().url(GlobalData.replaceURL(mNewsListData.get(position).getListimage())).build().execute(new BitmapCallback() {
                @Override
                public void onError(Call call, Exception e) {
                    Logger.e("网络错误");
                }

                @Override
                public void onResponse(Bitmap response) {
                    holder.titleIv.setImageBitmap(response);
                }
            });
            return convertView;
        }
    }

    static class ViewHolder{
        ImageView titleIv ;
        TextView titleTv;
        TextView timeTv;
    }
    @Override
    public void initData() {
        Logger.i(mTabData.getTitle());
        getDataFromNetwork();
    }

    private void getDataFromNetwork() {
        OkHttpUtils.get().url(GlobalData.SERVER_URL + mTabData.getUrl()).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Logger.e("获取网络数据失败", e);
                mListView.onRefreshComplete(false);
            }

            @Override
            public void onResponse(String response) {
                Logger.json(response);
                mListView.onRefreshComplete(true);
                parserData(response, false);
            }
        });
    }

    private void parserData(String response, boolean isMore) {
        mNewsTabData = new Gson().fromJson(response, NewsTabData.class);
        mNewsListData = mNewsTabData.getData().getNews();
        if (!TextUtils.isEmpty(mNewsTabData.getData().getMore())){
            mMoreUrl = GlobalData.SERVER_URL+mNewsTabData.getData().getMore();
        } else {
            mMoreUrl = null;
        }

        if (!isMore) {
            //新闻ListView设置适配器
            mNewsListViewAdapter = new NewsListViewAdapter();
            mListView.setAdapter(mNewsListViewAdapter);
        } else {
            List<NewsTabData.DataEntity.NewsEntity> moreNews = mNewsTabData.getData().getNews();
            mNewsListData.addAll(moreNews);
            mNewsListViewAdapter.notifyDataSetChanged();
        }

    }

}
