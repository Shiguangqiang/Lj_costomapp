package com.defence.costomapp.di.module;

import android.content.Context;


import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.di.scope.ContextLife;
import com.defence.costomapp.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;


/**
 * Created by lw on 2017/1/19.
 */
@Module
public class ApplicationModule {
    private MyApplication mApplication;

    public ApplicationModule(MyApplication application) {
        mApplication = application;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }
}
