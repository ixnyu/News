package com.xnyu.shanghai.TX;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.xnyu.shanghai.Base.BaseNewsPager;
import com.xnyu.shanghai.Global.GlobalData;
import com.xnyu.shanghai.Pager.NewsPager;

/**
 * Created by Administrator on 2016/3/1 0001.
 */
public class GuoNei extends BaseTXNewsPager {
    public GuoNei(Context context) {
        super(context);
    }

    @Override
    public String getUrl() {
        return GlobalData.TX_GUONEI;
    }
}
