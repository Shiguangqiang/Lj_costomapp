package com.defence.costomapp.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseFragment;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.TimeSaleBean;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ValueFormatter;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by author Sgq
 * on 2018/3/14.
 */

public class Tab1Fragment extends BaseFragment {

    Unbinder unbinder;
    private String groupid;
    private String uid;
    private String wxid;
    private int zeroStarNum;
    private int oneStarNum;
    private int twoStarNum;
    private int threeStarNum;
    private int fourStarNum;
    private int fiveStarNum;
    private PieChart pieChart;
    private LinearLayout liearSx;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, null);
        pieChart = view.findViewById(R.id.pie_chart);
        liearSx = view.findViewById(R.id.liear_sx);

        initdata();

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initdata() {

        groupid = SharePerenceUtil.getStringValueFromSp("groupid");
        uid = getActivity().getIntent().getStringExtra("uid");
        wxid = getActivity().getIntent().getStringExtra("wxid");
        RequestParams params = new RequestParams();
        params.put("whoID", uid);
        params.put("wxOpenID", wxid);
        params.put("json_val", "@@@[{\"start_\":\"00:00:00\",\"to_\":\"04:00:00\"},{\"start_\":\"04:00:00\",\"to_\":\"08:00:00\"},{\"start_\":\"08:00:00\",\"to_\":\"12:00:00\"},{\"start_\":\"12:00:00\",\"to_\":\"16:00:00\"},{\"start_\":\"16:00:00\",\"to_\":\"20:00:00\"},{\"start_\":\"20:00:00\",\"to_\":\"24:00:00\"}]");
        params.put("adminGroupID", groupid);
        httpUtils.doPost(Urls.timesale(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {

                try {
                    if (result != null) {
                        List<Integer> data_list = new ArrayList<>();
                        JSONArray jsonArray = new JSONArray(result.toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            data_list.add((Integer) jsonArray.get(i));
                        }

                        PieData mPieData = getPieData(6, 100, data_list.get(0), data_list.get(1), data_list.get(2), data_list.get(3), data_list.get(4), data_list.get(5));
                        showChart(pieChart, mPieData);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void showChart(PieChart pieChart, PieData pieData) {
        pieChart.setHoleColorTransparent(true);

        pieChart.setHoleRadius(20f);  //半径
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

    /**
     * @param count        分成几部分
     * @param range
     * @param zeroStarNum
     * @param oneStarNum
     * @param twoStarNum
     * @param threeStarNum
     * @param fourStarNum
     * @param fiveStarNum
     */
    private PieData getPieData(int count, float range, int zeroStarNum, int oneStarNum, int twoStarNum, int threeStarNum, int fourStarNum, int fiveStarNum) {

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容


        xValues.add("00:00 - 04:00");  //饼块上显示成Quarterly1, Quarterly2, Quarterly3, Quarterly4
        xValues.add("04:00 - 08:00");
        xValues.add("08:00 - 12:00");
        xValues.add("12:00 - 16:00");
        xValues.add("16:00 - 20:00");
        xValues.add("20:00 - 24:00");

        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据

        // 饼图数据
        /**
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38
         * 所以 14代表的百分比就是14%
         */

        yValues.add(new Entry(zeroStarNum, 0));
        yValues.add(new Entry(oneStarNum, 1));
        yValues.add(new Entry(twoStarNum, 2));
        yValues.add(new Entry(threeStarNum, 3));
        yValues.add(new Entry(fourStarNum, 4));
        yValues.add(new Entry(fiveStarNum, 5));

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, ""/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        colors.add(Color.rgb(51, 51, 51));
        colors.add(Color.rgb(242, 242, 242));
        colors.add(Color.rgb(0, 204, 255));
        colors.add(Color.rgb(255, 204, 0));
        colors.add(Color.rgb(255, 51, 0));
        colors.add(Color.rgb(204, 0, 255));

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
                    return v + "%";
                }
            }
        });

        return pieData;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
