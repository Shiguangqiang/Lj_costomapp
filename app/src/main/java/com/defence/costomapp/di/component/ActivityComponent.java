package com.defence.costomapp.di.component;

import android.app.Activity;
import android.content.Context;


import com.defence.costomapp.activity.statistics.AnalysisFilter2Activity;
import com.defence.costomapp.activity.statistics.CreatGoodsActivity;
import com.defence.costomapp.activity.statistics.CreatMachineActivity;
import com.defence.costomapp.di.module.ActivityModule;
import com.defence.costomapp.di.scope.ContextLife;
import com.defence.costomapp.di.scope.PerActivity;

import dagger.Component;

/**
 * Created by lw on 2017/1/19.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(CreatGoodsActivity activity);

    void inject(CreatMachineActivity activity);
}
