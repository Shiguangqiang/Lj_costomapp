package com.defence.costomapp.di.module;

import android.app.Activity;
import android.content.Context;

import com.defence.costomapp.di.scope.ContextLife;
import com.defence.costomapp.di.scope.PerActivity;


import dagger.Module;
import dagger.Provides;

/**
 * Created by Sgq on 2017/1/19.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    @ContextLife("Activity")
    public Context provideActivityContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity() {
        return mActivity;
    }
}
