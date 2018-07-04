package com.defence.costomapp.di.component;

import android.content.Context;

import com.defence.costomapp.di.module.ApplicationModule;
import com.defence.costomapp.di.scope.ContextLife;
import com.defence.costomapp.di.scope.PerApp;


import dagger.Component;


/**
 * Created by Sgq on 2017/1/19.
 */
@PerApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    @ContextLife("Application")
    Context getApplication();
}