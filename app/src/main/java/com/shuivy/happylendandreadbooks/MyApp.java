package com.shuivy.happylendandreadbooks;

import android.app.Application;

import com.antfortune.freeline.FreelineCore;

/**
 * Created by Administrator on 2016/9/10.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FreelineCore.init(this);
    }
}
