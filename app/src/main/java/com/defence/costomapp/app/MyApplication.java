package com.defence.costomapp.app;

import android.app.Application;


import com.defence.costomapp.bean.UserInfo;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends Application {
    private UserInfo userInfo;
    private static MyApplication mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initJPUSH();

    }

    private void initJPUSH() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    public static MyApplication getApp() {
        return mInstance;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
