package com.defence.costomapp.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDexApplication;


import com.defence.costomapp.bean.UserInfo;

import java.util.List;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends MultiDexApplication {
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
