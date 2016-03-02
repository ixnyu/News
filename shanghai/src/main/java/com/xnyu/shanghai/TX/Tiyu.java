package com.xnyu.shanghai.TX;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.xnyu.shanghai.Global.GlobalData;

/**
 * Created by Administrator on 2016/3/1 0001.
 */
public class Tiyu extends BaseTXNewsPager {
    public Tiyu(Context context) {
        super(context);
    }

    @Override
    public String getUrl() {
        return GlobalData.TX_TIYU;
    }
}
