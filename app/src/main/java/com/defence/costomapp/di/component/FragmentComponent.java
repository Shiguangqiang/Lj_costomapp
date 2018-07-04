package com.defence.costomapp.di.component;

import android.app.Activity;
import android.content.Context;

import com.defence.costomapp.di.module.FragmentModule;
import com.defence.costomapp.di.scope.ContextLife;
import com.defence.costomapp.di.scope.PerFragment;


import dagger.Component;

/**
 * Created by Sgq on 2017/1/19.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();


}
