package com.example.ben.jpushdemo;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Ben on 2018/2/27.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplicationContext());
    }
}
