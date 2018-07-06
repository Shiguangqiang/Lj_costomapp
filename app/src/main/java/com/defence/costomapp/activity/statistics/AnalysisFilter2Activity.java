package com.defence.costomapp.activity.statistics;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseNewActivity;
import com.defence.costomapp.fragment.AnalysFundFragment;
import com.defence.costomapp.fragment.AnalysUserFragment;
import com.defence.costomapp.fragment.AnalysmachineFragment;
import com.defence.costomapp.fragment.AnalysmachineGroupFragment;
import com.defence.costomapp.net.Constant;
import com.defence.costomapp.utils.SgqUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import tech.linjiang.pandora.Pandora;


@Route(path = "/statistics/AnalysisFilter2Activity")
public class AnalysisFilter2Activity extends BaseNewActivity implements View.OnClickListener {

    @Autowired
    public String stypeId;

    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.filter_tablayout)
    TabLayout filterTablayout;
    @BindView(R.id.filter_viewpager)
    ViewPager filterViewpager;
    private List<Fragment> list;
    private MyAdapter adapter;
    private String[] titles = new String[]{"机器", "机器组"};

    public static void start(String stypeId) {
        ARouter.getInstance().build("/statistics/AnalysisFilter2Activity").withString("stypeId", stypeId).navigation();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_analysis_filter;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }


    @Override
    protected void initView() {

        back.setText("返回");
        rightTitle.setText("保存");
        rightTitle.setTextColor(getResources().getColor(R.color.bule_light));
        back.setTextColor(getResources().getColor(R.color.bule_light));
        back.setOnClickListener(this);
        rightTitle.setOnClickListener(this);

        //页面，数据源
        list = new ArrayList<>();
        list.add(new AnalysmachineFragment());
        list.add(new AnalysmachineGroupFragment());
        //ViewPager的适配器
        adapter = new MyAdapter(getSupportFragmentManager());
        filterViewpager.setAdapter(adapter);
        //绑定
        filterTablayout.setupWithViewPager(filterViewpager);
        SgqUtils.setIndicator(filterTablayout, 50, 50);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.right_title:

                break;
        }
    }

    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        //重写这个方法，将设置每个Tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }
}
