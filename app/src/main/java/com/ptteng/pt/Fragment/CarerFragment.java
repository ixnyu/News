package com.ptteng.pt.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.widget.SwipeRefreshLayout;

import com.bigkoo.pickerview.OptionsPickerView;
import com.ptteng.pt.Bean.CarerBean;
import com.ptteng.pt.R;

import java.util.ArrayList;

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

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                   mSrfl.setRefreshing(false);
                    mMyAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private MyAdapter mMyAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_find_carer, null);
        for (int i = 0; i < 20; i++) {
            if (i % 4 == 0) {
                mCarerBeans.add(i, new CarerBean("我是" + (i + 1), "上海.徐家汇", 2));
            } else if (i % 5 == 0) {
                mCarerBeans.add(i, new CarerBean("我是" + (i + 1), "深圳.华强北", 3));
            } else {
                mCarerBeans.add(i, new CarerBean("我是" + (i + 1), "广州.白云区", 1));
            }

        }
        ListView listView = (ListView) v.findViewById(R.id.fragment_find_carer_lv);
        mMyAdapter = new MyAdapter(inflater, mCarerBeans);
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
                        for (int i = 0; i < 20; i++) {
                            if (i % 4 == 0) {
                                mCarerBeans.add(i, new CarerBean("更新" + (i + 1), "北京.通州.果园", 2));
                            } else if (i % 5 == 0) {
                                mCarerBeans.add(i, new CarerBean("更新" + (i + 1), "深圳.华强北", 3));
                            } else {
                                mCarerBeans.add(i, new CarerBean("更新" + (i + 1), "广州.白云区", 1));
                            }
                        }

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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


    class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private ArrayList<CarerBean> mCarerBeans;

        public MyAdapter(LayoutInflater inflater, ArrayList<CarerBean> list) {
            mInflater = inflater;
            mCarerBeans = list;
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
            ViewHolder holder;
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
            holder.head_img.setImageResource(R.mipmap.head);
            if (mCarerBeans.get(position).getLv() == 1) {
                holder.lv_img.setImageResource(R.mipmap.carer_lv1);
            } else if (mCarerBeans.get(position).getLv() == 2) {
                holder.lv_img.setImageResource(R.mipmap.carer_lv2);
            } else {
                holder.lv_img.setImageResource(R.mipmap.carer_lv3);
            }
            holder.carer_name.setText(mCarerBeans.get(position).getName());
            holder.carer_location.setText(mCarerBeans.get(position).getLocation());
            return convertView;
        }
    }

    class ViewHolder {
        public ImageView head_img;
        public ImageView lv_img;
        public TextView carer_name;
        public TextView carer_location;
    }
}