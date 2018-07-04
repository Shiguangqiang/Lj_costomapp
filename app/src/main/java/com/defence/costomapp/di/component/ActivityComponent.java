package com.defence.costomapp.di.component;

import android.app.Activity;
import android.content.Context;


import com.defence.costomapp.activity.statistics.Statisticstj_Activity;
import com.defence.costomapp.activity.statistics.VipStatic2NewActivity;
import com.defence.costomapp.di.module.ActivityModule;
import com.defence.costomapp.di.scope.ContextLife;
import com.defence.costomapp.di.scope.PerActivity;

import dagger.Component;

/**
 * Created by Sgq on 2017/1/19.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(Statisticstj_Activity activity);

    void inject(VipStatic2NewActivity activity);


}
