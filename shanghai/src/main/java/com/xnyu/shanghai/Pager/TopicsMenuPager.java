package com.xnyu.shanghai.Pager;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xnyu.shanghai.Base.BaseNewsPager;
import com.xnyu.shanghai.R;

/**
 * Created by Administrator on 2016/2/17 0017.
 */
public class TopicsMenuPager extends BaseNewsPager {

    private FrameLayout mFr_content;

    public TopicsMenuPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(mContext);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        textView.setText("菜单-专题");
        return textView;

    }
}
