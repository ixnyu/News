package com.xnyu.shanghai.TX;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.xnyu.shanghai.Acitivty.NewsActivity;
import com.xnyu.shanghai.Base.BaseNewsPager;
import com.xnyu.shanghai.Bean.TXGuoNeiData;
import com.xnyu.shanghai.Global.GlobalData;
import com.xnyu.shanghai.R;
import com.xnyu.shanghai.View.RefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/3/1 0001.
 */
public abstract class BaseTXNewsPager extends BaseNewsPager {

    private RefreshListView mListView;
    private TXGuoNeiData mTXGuoNeiData;
    private NoBotAdapter mNoBotAdapter;
    private List<TXGuoNeiData.NewslistEntity> mListData;

    private int more;

    public BaseTXNewsPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.nobot_pager, null);
        mListView = (RefreshListView) view.findViewById(R.id.lv_nobot);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, NewsActivity.class);
                intent.putExtra("url", mListData.get(position).getUrl());
                mContext.startActivity(intent);
            }
        });
        //设置下拉,上拉加载
        mListView.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromNetwork();
            }

            @Override
            public void loadMore() {
                getMoreDataFromNetwork();
            }
        });
        return view;
    }

    private void getMoreDataFromNetwork() {
        OkHttpUtils.get().url(getUrl() + "&page=" + more).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        mListView.onRefreshComplete(false);
                        Toast.makeText(mContext, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response) {
                        more++;
                        mListView.onRefreshComplete(true);
                        parserData(response, true);
                    }
                });
    }

    @Override
    public void initData() {
        more = 2;
        getDataFromNetwork();
    }

    public abstract String getUrl();

    private void getDataFromNetwork() {
        OkHttpUtils.get().url(getUrl()).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                mListView.onRefreshComplete(false);
                Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response) {
                mListView.onRefreshComplete(true);
                parserData(response, false);
            }
        });
    }

    private void parserData(String response, boolean isMore) {
        if (!isMore) {
            mTXGuoNeiData = new Gson().fromJson(response, TXGuoNeiData.class);
            mNoBotAdapter = new NoBotAdapter();
            mListData = mTXGuoNeiData.getNewslist();
            mListView.setAdapter(mNoBotAdapter);
        } else {
            TXGuoNeiData txGuoNeiDataMore = new Gson().fromJson(response, TXGuoNeiData.class);
            mListData.addAll(txGuoNeiDataMore.getNewslist());
            mNoBotAdapter.notifyDataSetChanged();
        }
    }

    class NoBotAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public TXGuoNeiData.NewslistEntity getItem(int position) {
            return mListData.get(position);
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
                convertView = View.inflate(mContext, R.layout.list_nobot_item, null);
                holder.mImageView = (ImageView) convertView.findViewById(R.id.iv_news);
                holder.mTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.mDescription = (TextView) convertView.findViewById(R.id.tv_description);
                holder.mTime = (TextView) convertView.findViewById(R.id.time_tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String url = mListData.get(position).getPicUrl();
            if (!TextUtils.isEmpty(url)) {
                holder.mImageView.setVisibility(View.VISIBLE);
                //Picasso.with(mContext).load(url).into(holder.mImageView);
                Picasso.with(mContext)
                        .load(url)
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .into(holder.mImageView);
            } else {
                holder.mImageView.setVisibility(View.GONE);
            }
            //holder.mImageView.setTag(url);
            //Logger.i(position+"--图片地址"+url);
            //if (!TextUtils.isEmpty(url)) {
            //    holder.mImageView.setVisibility(View.VISIBLE);
            //    OkHttpUtils.get().url(url).build().execute(new BitmapCallback() {
            //        @Override
            //        public void onError(Call call, Exception e) {
            //            Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
            //        }
            //
            //        @Override
            //        public void onResponse(Bitmap response) {
            //            holder.mImageView.setImageBitmap(response);
            //        }
            //    });
            //} else {
            //    holder.mImageView.setVisibility(View.GONE);
            //}
            holder.mTitle.setText(getItem(position).getTitle());
            holder.mDescription.setText(getItem(position).getDescription());
            holder.mTime.setText(getItem(position).getCtime());
            return convertView;
        }
    }

    static class ViewHolder {
        ImageView mImageView;
        TextView mTitle;
        TextView mDescription;
        TextView mTime;
    }
}
