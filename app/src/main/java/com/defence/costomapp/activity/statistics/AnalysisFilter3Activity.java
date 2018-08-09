package com.defence.costomapp.activity.statistics;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseNewActivity;
import com.defence.costomapp.fragment.AnalysGoodsFragment;
import com.defence.costomapp.fragment.AnalysGoodsGroupFragment;
import com.defence.costomapp.net.RxBus;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.event.EventUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import tech.linjiang.pandora.Pandora;


@Route(path = "/statistics/AnalysisFilter3Activity")
public class AnalysisFilter3Activity extends BaseNewActivity implements View.OnClickListener {

    @Autowired
    public String machineNumber;
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
    private String[] titles = new String[]{"商品", "商品组"};

    public static void start(String machineNumber) {
        ARouter.getInstance().build("/statistics/AnalysisFilter3Activity").withString("machineNumber", machineNumber).navigation();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_analysis_filter;
    }

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);

        back.setText("返回");
        back.setTextColor(getResources().getColor(R.color.bule_light));
        back.setOnClickListener(this);
        rightTitle.setOnClickListener(this);

        //页面，数据源
        list = new ArrayList<>();
        list.add(new AnalysGoodsFragment());
        list.add(new AnalysGoodsGroupFragment());
        //ViewPager的适配器
        adapter = new MyAdapter(getSupportFragmentManager());
        filterViewpager.setAdapter(adapter);
        //绑定
        filterTablayout.setupWithViewPager(filterViewpager);

/*        //TabLayout的监听
        filterTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 1) {
                    rightTitle.setText("");
                } else {
                    rightTitle.setText("保存");
                }
//                int position = tab.getPosition();
//                filterViewpager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    rightTitle.setText("");
                } else {
                    rightTitle.setText("保存");
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
        SgqUtils.setIndicator(filterTablayout, 50, 50);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;

            /* case R.id.right_title:
             *//*通知 保存商品*//*
                RxBus.getInstance().post(new EventUtils());
                GrowthRateActivity.start();
                break;*/
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
