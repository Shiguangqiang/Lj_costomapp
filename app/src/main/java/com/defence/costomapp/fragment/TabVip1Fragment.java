package com.defence.costomapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.activity.statistics.VipStatistDetailActivity;
import com.defence.costomapp.base.BaseFragment;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.VipCardNewBean;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.ValueFormatter;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by author Sgq
 * on 2018/4/28.
 */

public class TabVip1Fragment extends BaseFragment {


    private PieChart mPieChart;
    private RelativeLayout mRlVipst;
    private LinearLayout mLlVipsatist;
    private LinearLayout mLlVipintent;
    private TextView mTvZongshu;
    private TextView mTvKucun;
    private TextView mTvChukuweishou;
    private TextView mTvYifashou;
    private TextView mTvNewziduan;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabvip1, null);

        mPieChart = view.findViewById(R.id.pie_chart);
        mRlVipst = view.findViewById(R.id.rl_vipst);
        mLlVipsatist = view.findViewById(R.id.ll_vipsatist);
        mLlVipintent = view.findViewById(R.id.ll_vipintent);
        mTvZongshu = view.findViewById(R.id.tv_zongshu);
        mTvKucun = view.findViewById(R.id.tv_kucun);
        mTvChukuweishou = view.findViewById(R.id.tv_chukuweishou);
        mTvYifashou = view.findViewById(R.id.tv_yifashou);
        mTvNewziduan = view.findViewById(R.id.tv_newziduan);

        initdata();

        return view;
    }

    private void initdata() {

        mLlVipintent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), VipStatistDetailActivity.class));
            }
        });
        mPieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                //第一次点击执行的方法在这个方法内我们不做处理
            }

            @Override
            public void onNothingSelected() {
                //第二次点击统计图或者其他位置，统计图的状态恢复就会执行该方法，也就是我们想要的点击事件
                startActivity(new Intent(getActivity(), VipStatistDetailActivity.class));
            }
        });

        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);

        RequestParams params = new RequestParams();
        httpUtils.doPost(Urls.xiaofeika(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onSuccess(Gson gson, Object result, String message) {

                try {
                    if (result != null) {
                        JSONObject jsonObject = new JSONObject(result.toString());
                        VipCardNewBean vipCardBean = gson.fromJson(jsonObject.toString(), VipCardNewBean.class);

                        mTvZongshu.setText("(总量" + vipCardBean.getZongshu() + "张)");

                        mTvNewziduan.setText(vipCardBean.getIActiveCardCount() + "张(" + numberFormat.format((float) vipCardBean.getIActiveCardCount() / (float) vipCardBean.getZongshu() * 100) + "%)");
                        mTvKucun.setText(vipCardBean.getIUnbindCardCount() + "张(" + numberFormat.format((float) vipCardBean.getIUnbindCardCount() / (float) vipCardBean.getZongshu() * 100) + "%)");
                        mTvChukuweishou.setText(vipCardBean.getITestCardCount() + "张(" + numberFormat.format((float) vipCardBean.getITestCardCount() / (float) vipCardBean.getZongshu() * 100) + "%)");
                        mTvYifashou.setText(vipCardBean.getIBindNotActive() + "张(" + numberFormat.format((float) vipCardBean.getIBindNotActive() / (float) vipCardBean.getZongshu() * 100) + "%)");

                        PieData mPieData = getPieData(4, 100, vipCardBean.getIActiveCardCount(), vipCardBean.getIUnbindCardCount(), vipCardBean.getITestCardCount(), vipCardBean.getIBindNotActive());
                        showChart(mPieChart, mPieData);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }


    /**
     * @param count       分成几部分
     * @param range
     * @param zeroStarNum
     * @param oneStarNum
     * @param twoStarNum
     */
    private PieData getPieData(int count, float range, int startnum, int zeroStarNum, int oneStarNum, int twoStarNum) {

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容

        xValues.add("已激活");
        xValues.add("未绑定");  //饼块上显示成Quarterly1, Quarterly2, Quarterly3, Quarterly4
        xValues.add("测试卡");
        xValues.add("待销售");


        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据

        // 饼图数据
        /**
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38
         * 所以 14代表的百分比就是14%
         */
        yValues.add(new Entry(startnum, 0));
        yValues.add(new Entry(zeroStarNum, 1));
        yValues.add(new Entry(oneStarNum, 2));
        yValues.add(new Entry(twoStarNum, 3));


        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, ""/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        colors.add(Color.rgb(223, 38, 38));
        colors.add(Color.rgb(50, 139, 241));
        colors.add(Color.rgb(252, 252, 79));
        colors.add(Color.rgb(52, 247, 91));


        pieDataSet.setColors(colors);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度


        PieData pieData = new PieData(xValues, pieDataSet);
        pieData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float v) {
                if (v == 0) {
                    return String.valueOf(v);
                } else {
                    return String.format("%.2f", v) + "%";
                }
            }
        });

        return pieData;
    }


    private void showChart(PieChart pieChart, PieData pieData) {
        pieChart.setHoleColorTransparent(true);

        pieChart.setHoleRadius(60f);  //半径
        pieChart.setTransparentCircleRadius(4f); // 半透明圈
        //pieChart.setHoleRadius(0)  //实心圆
        pieChart.setDescription("饼状图");
        // mChart.setDrawYValues(true);
        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字

        pieChart.setDrawHoleEnabled(true);

        pieChart.setRotationAngle(90); // 初始旋转角度

        // draws the corresponding description value into the slice
        // mChart.setDrawXValues(true);

        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(false); // 可以手动旋转

        // display percentage values
        pieChart.setUsePercentValues(true);  //显示成百分比
        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
//      mChart.setOnChartValueSelectedListener(this);
        // mChart.setTouchEnabled(false);

//      mChart.setOnAnimationListener(this);

        pieChart.setCenterText("");  //饼状图中间的文字

        //设置数据
        pieChart.setData(pieData);

        // undo all highlights
//      pieChart.highlightValues(null);
//      pieChart.invalidate();

        Legend mLegend = pieChart.getLegend();  //设置比例图
        mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);  //最右边显示
//      mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形
        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(5f);

        pieChart.animateXY(1000, 1000);  //设置动画
        // mChart.spin(2000, 0, 360);
    }
//
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//
//        if (isVisibleToUser) {
//            initdata();
//        }
//        super.setUserVisibleHint(isVisibleToUser);
//    }
}
