package com.ptteng.pt.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.ptteng.pt.R;

import java.util.ArrayList;

/**
 * Created by xnyu on 2016/1/17.
 */
public class BossFragment extends Fragment {

    private OptionsPickerView mPickerView1;
    private OptionsPickerView mPickerView2;
    private OptionsPickerView mPickerView3;
    private ArrayList<String> mStrfilter1 = new ArrayList<>();
    private ArrayList<String> mStrfilter2 = new ArrayList<>();
    private ArrayList<String> mStrfilter3 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_find_boss, null);
        ListView listView = (ListView) v.findViewById(R.id.fragment_find_boss_lv);
        listView.setAdapter(new MyAdapter(inflater));
        LinearLayout filter1 = (LinearLayout) v.findViewById(R.id.boss_filter1);
        LinearLayout filter2 = (LinearLayout) v.findViewById(R.id.boss_filter2);
        LinearLayout filter3 = (LinearLayout) v.findViewById(R.id.boss_filter3);
        mStrfilter1.add("不限");
        mStrfilter1.add("1公里");
        mStrfilter1.add("3公里");
        mStrfilter1.add("5公里");
        mStrfilter1.add("10公里");
        mStrfilter2.add("不限");
        mStrfilter2.add("住家含餐");
        mStrfilter2.add("住家不含餐");
        mStrfilter2.add("不住家含餐");
        mStrfilter2.add("不住家不含餐");
        mStrfilter3.add("不限");
        mStrfilter3.add("能自理");
        mStrfilter3.add("不能自理");
        mStrfilter3.add("半失能");
        mStrfilter3.add("手术后");
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
        return v;
    }


    class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public MyAdapter(LayoutInflater inflater) {
            mInflater = inflater;

        }

        @Override
        public int getCount() {
            return 40;
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
                convertView = mInflater.inflate(R.layout.fragment_boss_item, null);
                holder.boss_date = (TextView) convertView.findViewById(R.id.find_boss_date_tv);
                holder.boss_money = (TextView) convertView.findViewById(R.id.find_boss_money_tv);
                holder.boss_location = (TextView) convertView.findViewById(R.id.find_boss_location_tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.boss_date.setText("2016-1-11 至 2016-1-20");
            holder.boss_money.setText("25元/小时");
            holder.boss_location.setText("北京.通州.果园");
            return convertView;
        }
    }

    class ViewHolder {
        public TextView boss_date;
        public TextView boss_money;
        public TextView boss_location;
    }
}