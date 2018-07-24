package com.defence.costomapp.activity.statistics;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseNewActivity;
import com.defence.costomapp.fragment.AnalysFundFragment;
import com.defence.costomapp.fragment.AnalysUserFragment;
import com.defence.costomapp.net.Constant;
import com.defence.costomapp.utils.SgqUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import tech.linjiang.pandora.Pandora;


@Route(path = "/statistics/AnalysisFilterActivity")
public class AnalysisFilterActivity extends BaseNewActivity implements View.OnClickListener {

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
    private String[] titles = new String[]{"资金", "用户"};
    private String mVerticalAxis;

    public static void start() {
        ARouter.getInstance().build("/statistics/AnalysisFilterActivity").navigation();
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
        back.setText("返回");
        back.setTextColor(getResources().getColor(R.color.bule_light));
        back.setOnClickListener(this);

        //页面，数据源
        list = new ArrayList<>();
        list.add(new AnalysFundFragment());
        list.add(new AnalysUserFragment());
        //ViewPager的适配器
        adapter = new MyAdapter(getSupportFragmentManager());
        filterViewpager.setAdapter(adapter);
        //绑定
        filterTablayout.setupWithViewPager(filterViewpager);
        SgqUtils.setIndicator(filterTablayout, 50, 50);
//        mVerticalAxis = getIntent().getStringExtra("verticalAxis");
//        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.VERTICALAXIS, mVerticalAxis);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
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
