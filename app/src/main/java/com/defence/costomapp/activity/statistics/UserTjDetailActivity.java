package com.defence.costomapp.activity.statistics;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.fragment.BehavAnalysisFragment;
import com.defence.costomapp.fragment.ShopHistoryFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserTjDetailActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.usertj_tablayout)
    TabLayout usertjTablayout;
    @BindView(R.id.usertj_viewpager)
    ViewPager usertjViewpager;

    private String uid;
    private String uname;
    private String wxid;

    private String[] titles = new String[]{"消费记录", "行为分析"};
    private List<Fragment> list;
    private MyAdapter adapter;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tj_detail);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        String ttype = getIntent().getStringExtra("ttype");

        if (!TextUtils.isEmpty(ttype)) {
            if (ttype.equals("4")) {
                uid = getIntent().getStringExtra("uid");
                wxid = getIntent().getStringExtra("wxid");
                middleTitle.setText(wxid);
            } else {
                phone = getIntent().getStringExtra("phone");
                uid = getIntent().getStringExtra("uid");
                uname = getIntent().getStringExtra("uname");
                middleTitle.setText(phone);

            }
        } else {
            finish();
        }

        //页面，数据源
        list = new ArrayList<>();
        list.add(new ShopHistoryFragment());
        list.add(new BehavAnalysisFragment());
        //ViewPager的适配器
        adapter = new MyAdapter(getSupportFragmentManager());
        usertjViewpager.setAdapter(adapter);
        //绑定
        usertjTablayout.setupWithViewPager(usertjViewpager);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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