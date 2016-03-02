package com.xnyu.shanghai.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xnyu.shanghai.Base.BaseFragment;
import com.xnyu.shanghai.Bean.NewsData;
import com.xnyu.shanghai.Acitivty.MainActivity;
import com.xnyu.shanghai.Pager.NewsPager;
import com.xnyu.shanghai.R;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.List;

/**
 * Created by xnyu on 2016/1/28.
 */
public class LeftMenuFragment extends BaseFragment {

    /**
     * 获得菜单数据事件回调
     */
    public static final String GET_MENU_DATA = "getMenuData";

    private ListView mListView;
    private List<NewsData.DataEntity> mData;
    private int mCurrent;
    private leftAdapter mLeftAdapter;


    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_left_menu, null);
        mListView = (ListView) view.findViewById(R.id.fragment_left_menu_lv);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrent = position;
                mLeftAdapter.notifyDataSetChanged();
                EventBus.getDefault().post(position, NewsPager.SET_MENU_PAGER);
                EventBus.getDefault().post(1, MainActivity.TOGGLE);
            }
        });
        return view;
    }


    @Override
    public void initDate() {

    }

    @Subscriber(tag = GET_MENU_DATA)
    private void getMenuData(NewsData newsData) {
        mData = newsData.getData();
        mLeftAdapter = new leftAdapter();
        mListView.setAdapter(mLeftAdapter);
        mCurrent = 0;
    }

    class leftAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public NewsData.DataEntity getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mContext, R.layout.list_left_menu_item, null);
            TextView textView = (TextView) view.findViewById(R.id.item_tv);
            textView.setText(getItem(position).getTitle());
            if (mCurrent == position) {
                textView.setEnabled(true);
            } else {
                textView.setEnabled(false);
            }
            return view;
        }
    }
}