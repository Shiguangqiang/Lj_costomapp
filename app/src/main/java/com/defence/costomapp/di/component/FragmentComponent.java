package com.defence.costomapp.di.component;

import android.app.Activity;
import android.content.Context;


import com.defence.costomapp.di.module.FragmentModule;
import com.defence.costomapp.di.scope.ContextLife;
import com.defence.costomapp.di.scope.PerFragment;
import com.defence.costomapp.fragment.AnalysFundFragment;
import com.defence.costomapp.fragment.AnalysGoodsFragment;
import com.defence.costomapp.fragment.AnalysGoodsGroupFragment;
import com.defence.costomapp.fragment.AnalysUserFragment;
import com.defence.costomapp.fragment.AnalysmachineFragment;
import com.defence.costomapp.fragment.AnalysmachineGroupFragment;

import dagger.Component;

/**
 * Created by lw on 2017/1/19.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(AnalysFundFragment fragment);

    void inject(AnalysUserFragment fragment);

    void inject(AnalysmachineFragment fragment);

    void inject(AnalysmachineGroupFragment fragment);

    void inject(AnalysGoodsFragment fragment);

    void inject(AnalysGoodsGroupFragment fragment);


}
