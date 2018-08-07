package com.defence.costomapp.app;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.multidex.MultiDexApplication;


import com.alibaba.android.arouter.launcher.ARouter;

import com.blankj.utilcode.util.Utils;
import com.defence.costomapp.BuildConfig;
import com.defence.costomapp.activity.LoginActivity;
import com.defence.costomapp.bean.UserInfo;
import com.defence.costomapp.di.component.ApplicationComponent;

import com.defence.costomapp.di.module.ApplicationModule;
import com.defence.costomapp.service.LongRunningService;
import com.raizlabs.android.dbflow.config.FlowManager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import cn.jpush.android.api.JPushInterface;
import tech.linjiang.pandora.Pandora;

public class MyApplication extends MultiDexApplication {
    public static int screenWidth = 0;
    public static int screenHeight = 0;
    private static MyApplication mInstance;
    private UserInfo userInfo;
    private ApplicationComponent mApplicationComponent;


    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static MyApplication getApp() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initJPUSH();
        initApplicationComponent();
        Utils.init(this);
        intARouter();
//      调试利器
        Pandora.init(this).enableShakeOpen();
//      DbFlow数据库
        FlowManager.init(this);

        /*开启实时定位服务*/
        Intent intent = new Intent();
        intent.setClass(this, LongRunningService.class);
        startService(intent);

    }

    private void initJPUSH() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    /**
     * 初始化ApplicationComponent
     */
    private void initApplicationComponent() {
        mApplicationComponent = com.defence.costomapp.di.component.DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    /**
     * 初始化路由
     */
    private void intARouter() {
        if (BuildConfig.DEBUG) {         // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }


    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
