package com.xnyu.shanghai.TX;

import android.content.Context;

import com.xnyu.shanghai.Global.GlobalData;

/**
 * Created by Administrator on 2016/3/1 0001.
 */
public class Social extends BaseTXNewsPager {
    public Social(Context context) {
        super(context);
    }

    @Override
    public String getUrl() {
        return GlobalData.TX_SOCIAL;
    }
}
