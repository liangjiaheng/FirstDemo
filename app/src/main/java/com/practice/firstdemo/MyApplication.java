package com.practice.firstdemo;

import android.app.Application;

import org.xutils.x;

/**
 * Created by hui on 2017/7/24.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
