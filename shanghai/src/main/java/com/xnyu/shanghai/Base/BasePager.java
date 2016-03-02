package com.xnyu.shanghai.Base;

import android.content.Context;
import android.view.View;

import com.xnyu.shanghai.Acitivty.MainActivity;
import com.xnyu.shanghai.R;

import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import org.simple.eventbus.EventBus;

/**
 * 5个标签页的基类
 * Created by xnyu on 2016/1/29.
 */
public class BasePager {

    public Context mContext;
    public View mView;
    public TextView mTitleTv;
    public ImageButton mTitleIbtn;
    public FrameLayout mFrContent;
    public MainActivity mMainUI;
    public ImageButton mPhotosIbtn;

    public BasePager(Context context) {
        mContext = context;
        EventBus.getDefault().register(this);
        initView();
    }

    /**
     * 初始化布局
     */
    public void initView() {
        mView = View.inflate(mContext, R.layout.fragment_basepage, null);
        mTitleTv = (TextView) mView.findViewById(R.id.title_tv);
        mTitleIbtn = (ImageButton) mView.findViewById(R.id.title_ibtn);
        mFrContent = (FrameLayout) mView.findViewById(R.id.fragment_basepage_fl);
        mPhotosIbtn = (ImageButton) mView.findViewById(R.id.ibtn_photos);

        mTitleIbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(1,MainActivity.TOGGLE);
            }
        });
        mMainUI = (MainActivity) mContext;

    }

    public void initData() {
    }
}
