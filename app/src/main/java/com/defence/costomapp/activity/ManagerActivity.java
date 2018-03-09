package com.defence.costomapp.activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.activity.fragment.LogFragment;
import com.defence.costomapp.activity.fragment.PoliceFragment;
import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManagerActivity extends BaseActivity {

    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.manger_tablayout)
    TabLayout mangerTablayout;
    @BindView(R.id.manger_viewpager)
    ViewPager mangerViewpager;

    private List<Fragment> list;
    private MyAdapter adapter;
    private String[] titles = new String[]{"日志", "报警"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        back.setText("退出登录");
        middleTitle.setText("管理客户端");


        //页面，数据源
        list = new ArrayList<>();
        list.add(new LogFragment());
        list.add(new PoliceFragment());
        //ViewPager的适配器
        adapter = new MyAdapter(getSupportFragmentManager());
        mangerViewpager.setAdapter(adapter);
        //绑定
        mangerTablayout.setupWithViewPager(mangerViewpager);

//        TabLayout.Tab tab = mangerTablayout.newTab();
//        tab.setCustomView(R.layout.tab_item);
//        TextView tv_tab_title = tab.getCustomView().findViewById(R.id.tv_tab_title);
//        ImageView img_redlittle = tab.getCustomView().findViewById(R.id.img_redlittle);
//        tv_tab_title.setText("报警");
//        mangerTablayout.addTab(tab);


        SgqUtils.setIndicator(mangerTablayout, 60, 60);
        mangerTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        int loginType = SharePerenceUtil.getIntValueFromSP("loginType");
        if (loginType != -1) {
            SharePerenceUtil.putStringValuetoSp(loginType + "", "");
            SharePerenceUtil.putIntValuetoSp("loginType", -1);
            SharePerenceUtil.putBooleanValuetoSp(loginType + "isLogin",false);
            MyApplication.getApp().setUserInfo(null);
        }
        finish();

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

//        @Override
//        public void finishUpdate(ViewGroup container) {
//            try {
//                super.finishUpdate(container);
//            } catch (NullPointerException nullPointerException) {
//                System.out.println("Catch the NullPointerException in FragmentPagerAdapter.finishUpdate");
//            }
//        }
    }
}
