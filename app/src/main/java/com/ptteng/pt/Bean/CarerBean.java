package com.ptteng.pt.Bean;

/**
 * Created by xnyu on 2016/1/17.
 */
public class CarerBean {
    private String mName;
    private String mLocation;
    private int mLv;

    public CarerBean(String name, String location, int lv) {
        mName = name;
        mLocation = location;
        mLv = lv;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public int getLv() {
        return mLv;
    }

    public void setLv(int lv) {
        mLv = lv;
    }
}
