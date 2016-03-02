package com.ptteng.pt.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.ptteng.pt.Base.BaseApplication;
import com.ptteng.pt.Bean.CarerBean;
import com.ptteng.pt.R;
import com.ptteng.pt.Util.BitMapHelper;
import com.ptteng.pt.Util.BitmapCache;
import com.ptteng.pt.Util.HttpUtils;
import com.ptteng.pt.Util.T;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;


import org.xutils.x;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by xnyu on 2016/1/17.
 */
public class CarerFragment extends Fragment {

    private OptionsPickerView mPickerView1;
    private OptionsPickerView mPickerView2;
    private OptionsPickerView mPickerView3;
    private OptionsPickerView mPickerView4;
    private ArrayList<String> mStrfilter1 = new ArrayList<>();
    private ArrayList<String> mStrfilter2 = new ArrayList<>();
    private ArrayList<String> mStrfilter3 = new ArrayList<>();
    private ArrayList<String> mStrfilter4 = new ArrayList<>();
    private SwipeRefreshLayout mSrfl;
    private ArrayList<CarerBean> mCarerBeans = new ArrayList<>();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mSrfl.setRefreshing(false);
                    mMyAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private MyAdapter mMyAdapter;
    private List<CarerBean.DataEntity> mCarer = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_find_carer, null);
        ListView listView = (ListView) v.findViewById(R.id.fragment_find_carer_lv);
        mMyAdapter = new MyAdapter(inflater,mCarer);
        listView.setAdapter(mMyAdapter);
        mSrfl = (SwipeRefreshLayout) v.findViewById(R.id.find_carer_srfl);
        //设置圆圈颜色
        mSrfl.setColorSchemeResources(R.color.bgWidgetBule);
        mSrfl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread() {
                    @Override
                    public void run() {
                        mCarerBeans.clear();
                        OkHttpUtils.get().url(HttpUtils.URL + HttpUtils.GET_SEARCH_CARER).build()
                                .execute(new CarerCallback() {
                                    @Override
                                    public void onError(Call call, Exception e) {

                                    }

                                    @Override
                                    public void onResponse(CarerBean response) {
                                        if (response.getCode() == 200) {
                                            for (int i = 0; i < 40; i++) {
                                                mCarer.add(response.getData().get(i));
                                            }
                                            mHandler.sendEmptyMessage(1);
                                        } else {
                                            T.showShort(BaseApplication.getApplication(),response.getMessage());
                                            return;
                                        }
                                    }
                                });
                        mHandler.sendEmptyMessage(1);
                    }
                }.start();
            }
        });
        LinearLayout filter1 = (LinearLayout) v.findViewById(R.id.carer_filter1);
        LinearLayout filter2 = (LinearLayout) v.findViewById(R.id.carer_filter2);
        LinearLayout filter3 = (LinearLayout) v.findViewById(R.id.carer_filter3);
        LinearLayout filter4 = (LinearLayout) v.findViewById(R.id.carer_filter4);
        mStrfilter1.add("不限");
        mStrfilter1.add("普通护工");
        mStrfilter1.add("高级护工");
        mStrfilter1.add("护士");
        mStrfilter2.add("不限");
        mStrfilter2.add("16-25");
        mStrfilter2.add("26-35");
        mStrfilter2.add("36-45");
        mStrfilter2.add("46-55");
        mStrfilter2.add("56以上");
        mStrfilter3.add("不限");
        mStrfilter3.add("1公里");
        mStrfilter3.add("3公里");
        mStrfilter3.add("5公里");
        mStrfilter3.add("10公里");
        mStrfilter4.add("不限");
        mStrfilter4.add("住家含餐");
        mStrfilter4.add("住家不含餐");
        mStrfilter4.add("不住家含餐");
        mStrfilter4.add("不住家不含餐");
        mPickerView1 = new OptionsPickerView(getActivity());
        mPickerView1.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
            }
        });
        mPickerView1.setPicker(mStrfilter1);
        filter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPickerView1.show();
            }
        });
        mPickerView2 = new OptionsPickerView(getActivity());
        mPickerView2.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
            }
        });
        mPickerView2.setPicker(mStrfilter2);
        filter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPickerView2.show();
            }
        });
        mPickerView3 = new OptionsPickerView(getActivity());
        mPickerView3.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
            }
        });
        mPickerView3.setPicker(mStrfilter3);
        filter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPickerView3.show();
            }
        });
        mPickerView4 = new OptionsPickerView(getActivity());
        mPickerView4.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
            }
        });
        mPickerView4.setPicker(mStrfilter4);
        filter4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPickerView4.show();
            }
        });
        return v;
    }


    /**
     *  通过ImageLoader加载及缓存网络图片
     　　　*
     *  new ImageLoader(RequestQueue queue,ImageCache imageCache)
     *  queue：请求队列
     *  imageCache：一个用于图片缓存的接口，一般需要传入它的实现类
     *
     *  getImageListener(ImageView view, int defaultImageResId, int errorImageResId)
     *  view：ImageView对象
     *  defaultImageResId：默认的图片的资源Id
     *  errorImageResId：网络图片加载失败时显示的图片的资源Id
     */
    private void loadImageWithCache(ImageView image,String url) {
//        String url = "http://pic20.nipic.com/20120409/9188247_091601398179_2.jpg";
        ImageLoader loader = new ImageLoader(BaseApplication.getHttpQueues(), new BitmapCache());
        ImageLoader.ImageListener listener = loader.getImageListener(image,R.mipmap.home_head,R.mipmap.home_head);
        //加载及缓存网络图片
        loader.get(url,listener);
    }

    class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private List<CarerBean.DataEntity> mCarerBeans;

        public MyAdapter(LayoutInflater inflater, List<CarerBean.DataEntity> list) {
            mInflater = inflater;
            mCarerBeans =  list;
        }

        @Override
        public int getCount() {
            return mCarerBeans.size();
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
                convertView = mInflater.inflate(R.layout.fragment_carer_item, null);
                holder.head_img = (ImageView) convertView.findViewById(R.id.carer_head_iv_item);
                holder.lv_img = (ImageView) convertView.findViewById(R.id.carer_lv_iv_item);
                holder.carer_name = (TextView) convertView.findViewById(R.id.carer_name_tv_item);
                holder.carer_location = (TextView) convertView.findViewById(R.id.carer_location_tv_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
//            holder.head_img.setImageResource(R.mipmap.head);
            loadImageWithCache(holder.head_img,mCarerBeans.get(position).getPhoto());
            if (mCarerBeans.get(position).getLevel() == 1) {
                holder.lv_img.setImageResource(R.mipmap.carer_lv1);
            } else if (mCarerBeans.get(position).getLevel() == 2) {
                holder.lv_img.setImageResource(R.mipmap.carer_lv2);
            } else {
                holder.lv_img.setImageResource(R.mipmap.carer_lv3);
            }
            holder.carer_name.setText(mCarerBeans.get(position).getName());
            holder.carer_location.setText(mCarerBeans.get(position).getLocation());
            System.out.println(mCarerBeans.get(position).getPhoto());
//            x.image().bind(holder.head_img, mCarerBeans.get(position).getPhoto(), BitMapHelper.options);

            return convertView;
        }
    }

    class ViewHolder {
        public ImageView head_img;
        public ImageView lv_img;
        public TextView carer_name;
        public TextView carer_location;
    }

    abstract class CarerCallback extends Callback<CarerBean> {

        @Override
        public CarerBean parseNetworkResponse(Response response) throws IOException {
            String string = response.body().string();
            CarerBean carerBean = new Gson().fromJson(string, CarerBean.class);
            return carerBean;
        }
    }
}