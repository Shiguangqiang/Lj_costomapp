package com.defence.costomapp.activity.statistics;


import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.utils.view.DayAxisValueFormatter;
import com.github.mikephil.chartline.charts.LineChart;
import com.github.mikephil.chartline.components.Legend;
import com.github.mikephil.chartline.components.XAxis;
import com.github.mikephil.chartline.components.YAxis;
import com.github.mikephil.chartline.data.Entry;
import com.github.mikephil.chartline.data.LineData;
import com.github.mikephil.chartline.data.LineDataSet;
import com.github.mikephil.chartline.formatter.IAxisValueFormatter;
import com.github.mikephil.chartline.highlight.Highlight;
import com.github.mikephil.chartline.interfaces.datasets.ILineDataSet;
import com.github.mikephil.chartline.listener.OnChartValueSelectedListener;
import com.github.mikephil.chartline.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MpLineChartActivity extends BaseActivity implements OnChartValueSelectedListener {

    private final ArrayList<Entry> mYVals1 = new ArrayList<Entry>();
    private final ArrayList<Entry> mYVals2 = new ArrayList<Entry>();
    private final ArrayList<Entry> mYVals3 = new ArrayList<Entry>();
    private final ArrayList<Entry> mYVals4 = new ArrayList<Entry>();
    protected Typeface mTfRegular;
    protected Typeface mTfLight;
    int click = 3;
    private LineChart mChart;
    private TextView mTv_setting;
    private LineData mData;
    private TextView mTv_num1;
    private TextView mTv_num2;
    private TextView mTv_num3;
    private TextView mTv_num4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mp_line_chart);

        mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        mChart = findViewById(R.id.chart1);
        mTv_setting = findViewById(R.id.tv_setting);
        mTv_num1 = findViewById(R.id.tv_num1);
        mTv_num2 = findViewById(R.id.tv_num2);
        mTv_num3 = findViewById(R.id.tv_num3);
        mTv_num4 = findViewById(R.id.tv_num4);

        mTv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

//                if (click == 3) {
//                    click = 0;
//                } else {
//                    click++;
//                }
//                setData();
            }
        });

        mChart.setOnChartValueSelectedListener(this);
        mChart.getDescription().setEnabled(false);
        mChart.setTouchEnabled(true);
        mChart.setDragDecelerationFrictionCoef(4f);
        mChart.setDragEnabled(true);
        // 缩放
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);
        mChart.setPinchZoom(true);
        mChart.setBackgroundColor(Color.rgb(178, 252, 200));
        mChart.animateX(2500);

        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(mTfLight);
        l.setTextSize(16f);
        l.setTextColor(Color.BLACK);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        setData();

        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mChart);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setTypeface(mTfLight);
        //设置x轴位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(14f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setValueFormatter(xAxisFormatter);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setTextSize(16f);
        leftAxis.setTextColor(Color.BLUE);
        leftAxis.setAxisMaximum(400f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setTypeface(mTfLight);
        rightAxis.setTextSize(16f);
        rightAxis.setTextColor(Color.BLUE);
        rightAxis.setAxisMaximum(80);
        rightAxis.setAxisMinimum(0);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);


//          设置提示
// create a custom MarkerView (extend MarkerView) and specify the layout
//        // to use for it
//        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
//        mv.setChartView(mChart); // For bounds control
//        mChart.setMarker(mv); // Set the marker to the chart
    }


    @TargetApi(Build.VERSION_CODES.O)
    private void setData() {
        ArrayList<Integer> integers1 = new ArrayList<>();
        integers1.add(300);
        integers1.add(100);
        integers1.add(50);
        integers1.add(200);
        integers1.add(50);
        integers1.add(200);
        integers1.add(100);
        integers1.add(50);
        integers1.add(200);
        integers1.add(100);
        integers1.add(100);
        integers1.add(50);

        for (int i = 0; i < integers1.size(); i++) {
            mYVals1.add(new Entry(i, integers1.get(i)));
        }

        ArrayList<Integer> integers2 = new ArrayList<>();
        integers2.add(100);
        integers2.add(50);
        integers2.add(200);
        integers2.add(150);
        integers2.add(100);
        integers2.add(50);
        integers2.add(200);
        integers2.add(150);
        integers2.add(100);
        integers2.add(50);
        integers2.add(200);
        integers2.add(300);

        for (int i = 0; i < integers2.size(); i++) {
            mYVals2.add(new Entry(i, integers2.get(i)));
        }

        ArrayList<Integer> integers3 = new ArrayList<>();
        integers3.add(55);
        integers3.add(40);
        integers3.add(50);
        integers3.add(60);
        integers3.add(70);
        integers3.add(50);
        integers3.add(60);
        integers3.add(70);
        integers3.add(40);
        integers3.add(55);
        integers3.add(70);
        integers3.add(40);


        for (int i = 0; i < integers3.size(); i++) {
            mYVals3.add(new Entry(i, integers3.get(i)));
        }

        ArrayList<Integer> integer4 = new ArrayList<>();
        integer4.add(40);
        integer4.add(10);
        integer4.add(60);
        integer4.add(40);
        integer4.add(30);
        integer4.add(80);
        integer4.add(70);
        integer4.add(30);
        integer4.add(80);
        integer4.add(70);
        integer4.add(30);
        integer4.add(80);


        for (int i = 0; i < integer4.size(); i++) {
            mYVals4.add(new Entry(i, integer4.get(i)));
        }
        LineDataSet set1, set2, set3, set4;
        set1 = new LineDataSet(mYVals1, "");
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(Color.argb(1f, 0f, 0f, 0.5f));
        set1.setCircleColor(Color.RED);
        set1.setLineWidth(3f);
        set1.setCircleRadius(3f);
        set1.setFillAlpha(50);
        set1.setLabel("");
        set1.setFillColor(ColorTemplate.getHoloBlue());
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setDrawCircleHole(false);
        set1.setDrawCircles(false);
//        定制节点水平和垂直的两条线
        set1.setHighlightLineWidth(1f); //设置线条宽度
        set1.setHighLightColor(Color.BLACK);  //设置线条颜色
        set1.setDrawHorizontalHighlightIndicator(false);

//            DataSet 2
        set2 = new LineDataSet(mYVals2, "");
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        set2.setColor(Color.argb(1f, 0f, 1f, 0.5f));
        set2.setCircleColor(Color.RED);
        set2.setLabel("");
        set2.setLineWidth(3f);
        set2.setCircleRadius(3f);
        set2.setFillAlpha(50);
        set2.setFillColor(Color.RED);
        set2.setDrawCircleHole(false);
        set2.setHighLightColor(Color.rgb(244, 117, 117));
        set2.setDrawCircles(false);
        set2.setHighlightLineWidth(1f); //设置线条宽度
        set2.setHighLightColor(Color.BLACK);  //设置线条颜色
        set2.setDrawHorizontalHighlightIndicator(false);


//            DataSet 3
        set3 = new LineDataSet(mYVals3, "");
        set3.setAxisDependency(YAxis.AxisDependency.RIGHT);
        set3.setColor(Color.RED);
        set3.setCircleColor(Color.RED);
        set3.setLineWidth(3f);
        set3.setLabel("");
        set3.setCircleRadius(3f);
        set3.setFillAlpha(50);
        set3.setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW, 200));
        set3.setDrawCircleHole(false);
        set3.setHighLightColor(Color.rgb(244, 117, 117));
        set3.setDrawCircles(false);
        set3.setHighlightLineWidth(1f); //设置线条宽度
        set3.setHighLightColor(Color.BLACK);  //设置线条颜色
        set3.setDrawHorizontalHighlightIndicator(false);

//            DataSet 4
        set4 = new LineDataSet(mYVals4, " ");
        set4.setAxisDependency(YAxis.AxisDependency.RIGHT);
        set4.setColor(Color.argb(1f, 0.5f, 0f, 0.5f));
        set4.setCircleColor(Color.RED);
        set4.setLineWidth(3f);
        set4.setLabel("");
        set4.setCircleRadius(3f);
        set4.setFillAlpha(50);
        set4.setFillColor(ColorTemplate.getHoloBlue());
        set4.setHighLightColor(Color.rgb(244, 117, 117));
        set4.setDrawCircleHole(false);
        set4.setDrawCircles(false);
        set4.setHighlightLineWidth(1f); //设置线条宽度
        set4.setHighLightColor(Color.BLACK);  //设置线条颜色
        set4.setDrawHorizontalHighlightIndicator(false);

        switch (click) {
            case 0:
                mData = new LineData(set1);
                break;
            case 1:
                mData = new LineData(set1, set2);
                break;
            case 2:
                mData = new LineData(set1, set2, set3);
                break;
            case 3:
                mData = new LineData(set1, set2, set3, set4);
                break;
        }
        mData.setValueTextColor(Color.BLUE);
        mData.setValueTextSize(14f);
        mChart.setData(mData);
        mChart.invalidate();


        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
            set3 = (LineDataSet) mChart.getData().getDataSetByIndex(2);
            set4 = (LineDataSet) mChart.getData().getDataSetByIndex(3);

            switch (click) {
                case 0:
                    set1.setValues(mYVals1);
                    break;
                case 1:
                    set1.setValues(mYVals1);
                    set2.setValues(mYVals2);
                    break;
                case 2:
                    set1.setValues(mYVals1);
                    set2.setValues(mYVals2);
                    set3.setValues(mYVals3);
                    break;
                case 3:
                    set1.setValues(mYVals1);
                    set2.setValues(mYVals2);
                    set3.setValues(mYVals3);
                    set4.setValues(mYVals4);
                    break;
            }
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        }
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {

        mChart.centerViewToAnimated(e.getX(), e.getY(), mChart.getData().getDataSetByIndex(h.getDataSetIndex())
                .getAxisDependency(), 500);

        mTv_num1.setText(mYVals1.get((int) e.getX()).getY() + "");
        mTv_num2.setText(mYVals2.get((int) e.getX()).getY() + "");
        mTv_num3.setText(mYVals3.get((int) e.getX()).getY() + "");
        mTv_num4.setText(mYVals4.get((int) e.getX()).getY() + "");


    }


    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }
}

