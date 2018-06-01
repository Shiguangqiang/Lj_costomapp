package com.defence.costomapp.fragment;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.defence.costomapp.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/14.
 * <p>
 * 行为分析
 */

public class BehavAnalysisFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Fragment> list;
    private MyAdapter adapter;
    private String[] titles = {"", "", ""};
//    private int images[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_behavanalys, null);
        //实例化
        viewPager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.tablayout);
        init();
        return view;
    }

    private void init() {
        //页面，数据源
        list = new ArrayList<>();
        list.add(new Tab1Fragment());
        list.add(new Tab2Fragment());
        list.add(new Tab3Fragment());

        //ViewPager的适配器
        adapter = new MyAdapter(getChildFragmentManager(), getContext());
        viewPager.setAdapter(adapter);
        //绑定
        tabLayout.setupWithViewPager(viewPager);
        //设置自定义视图
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }
    }
    
    class MyAdapter extends FragmentPagerAdapter {

        private Context context;

        public MyAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        /**
         * 自定义方法，提供自定义Tab
         *
         * @param position 位置
         * @return 返回Tab的View
         */
        public View getTabView(int position) {
            View v = LayoutInflater.from(context).inflate(R.layout.tab_custom, null);
            TextView tv_title = v.findViewById(R.id.tv_title);
//            TextView tv_circle = (TextView) v.findViewById(R.id.tv_circle);
//            if (tabLayout.getSelectedTabPosition()==position) {
//                tv_title.setText(titles[position]);
//                tv_title.setVisibility(View.VISIBLE);
//                tv_circle.setVisibility(View.GONE);
//            }else{
//                tv_circle.setVisibility(View.VISIBLE);
//                tv_title.setVisibility(View.GONE);
//            }

//            ImageView imageView = (ImageView) v.findViewById(R.id.iv_icon);
//            tv_title.setText(titles[position]);
//            imageView.setImageResource(images[position]);
            //添加一行，设置颜色
//            textView.setTextColor(tabLayout.getTabTextColors());//

            return v;
        }
    }

}
