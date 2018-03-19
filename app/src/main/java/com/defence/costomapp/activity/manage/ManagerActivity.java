package com.defence.costomapp.activity.manage;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.defence.costomapp.R;
import com.defence.costomapp.activity.ChoiceTypeActivity;
import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.fragment.LogFragment;
import com.defence.costomapp.fragment.PoliceFragment;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManagerActivity extends BaseActivity implements PoliceFragment.FragmentInteraction {

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
    @BindView(R.id.img_redlittle)
    ImageView imgRedlittle;

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
            SharePerenceUtil.putBooleanValuetoSp(loginType + "isLogin", false);
            MyApplication.getApp().setUserInfo(null);
        }
        startActivity(new Intent(ManagerActivity.this, ChoiceTypeActivity.class));
        finish();

    }


    //退出时的时间
    private long mExitTime;

    //对返回键进行监听
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(ManagerActivity.this, "再按一次退出系统", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            int loginType = SharePerenceUtil.getIntValueFromSP("loginType");
            if (loginType != -1) {
                SharePerenceUtil.putStringValuetoSp(loginType + "", "");
                SharePerenceUtil.putIntValuetoSp("loginType", -1);
                SharePerenceUtil.putBooleanValuetoSp(loginType + "isLogin", false);
                MyApplication.getApp().setUserInfo(null);

            }
            finishAffinity();
            System.exit(0);
        }
    }

    @Override
    public void process(String str) {
        String str1 = str;

        if (TextUtils.isEmpty(str)) {
            imgRedlittle.setVisibility(View.GONE);
        }else {
            imgRedlittle.setVisibility(View.VISIBLE);
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
