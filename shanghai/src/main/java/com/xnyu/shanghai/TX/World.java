package com.xnyu.shanghai.TX;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.xnyu.shanghai.Global.GlobalData;

/**
 * Created by Administrator on 2016/3/1 0001.
 */
public class World extends BaseTXNewsPager {
    public World(Context context) {
        super(context);
    }

    @Override
    public String getUrl() {
        return GlobalData.TX_WORLD;
    }
}
