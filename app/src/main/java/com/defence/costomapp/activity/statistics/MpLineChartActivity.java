package com.defence.costomapp.activity.statistics;


import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.LineChartBean;
import com.defence.costomapp.net.Constant;
import com.defence.costomapp.utils.AmountUtils;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.httputils.HttpInterface;
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
import com.github.mikephil.chartline.listener.OnChartValueSelectedListener;
import com.github.mikephil.chartline.utils.ColorTemplate;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MpLineChartActivity extends BaseActivity implements OnChartValueSelectedListener {

    private final ArrayList<Entry> mYValsleft1 = new ArrayList<Entry>();
    private final ArrayList<Entry> mYValsleft2 = new ArrayList<Entry>();
    private final ArrayList<Entry> mYValsright1 = new ArrayList<Entry>();
    private final ArrayList<Entry> mYValsright2 = new ArrayList<Entry>();
    private final ArrayList<Entry> mListNull = new ArrayList<Entry>();
    protected Typeface mTfRegular;
    protected Typeface mTfLight;
    @BindView(R.id.tv_selectionPeriod)
    TextView tvSelectionPeriod;
    private LineChart mChart;
    private TextView mTv_setting;
    private LineData mData;
    private TextView mTv_num1, mTv_num2, mTv_num3, mTv_num4;
    private TextView tv_selectup, tv_selectdown;
    private TextView tv_machinename1, tv_machinename2, tv_machinename3, tv_machinename4;
    //筛选条件
    private String mGuigeids;
    private String mMachine_numbers;
    private String mData_stypeid;
    private String mData_stypeidright;
    private String mMachinenumbersright;
    private String mGuigeidsright;
    private String mSdate;
    private String mIszhengzhanglv;
    private String miszhengzhanglvright;
    private String mGuigeidss;
    private String mMachine_numberss;
    private String mData_stypeids;
    private String mData_stypeidrights;
    private String mMachinenumbersrights;
    private String mGuigeidsrights;
    private String mSdates;
    private String mIszhengzhanglvs;
    private String miszhengzhanglvrights;
    private int time;
    private Float mMaxright1 = 0f;
    private Float mMinright1 = 0f;
    private Float mMinright = 0f;
    private Float mMaxright = 0f;
    private Float mMinleft1 = 0f;
    private Float mMaxleft1 = 0f;
    private Float mMinleft = 0f;
    private Float mMaxleft = 0f;
    private TextView mTv_show;
    private String mFiltername;
    private String mMachinename;
    private String mData_filternameright;
    private String mMachinenameright;
    private String mFilternames;
    private String mMachinenames;
    private String mData_filternamerights;
    private String mMachinenamerights;
    private String linechartdate;
    private String mCtypecyleft;
    private String mCtypecyright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mp_line_chart);
        ButterKnife.bind(this);
        mTv_setting = findViewById(R.id.tv_setting);
        mTv_num1 = findViewById(R.id.tv_num1);
        mTv_num2 = findViewById(R.id.tv_num2);
        mTv_num3 = findViewById(R.id.tv_num3);
        mTv_num4 = findViewById(R.id.tv_num4);
        tv_selectup = findViewById(R.id.tv_selectup);
        tv_selectdown = findViewById(R.id.tv_selectdown);
        tv_machinename1 = findViewById(R.id.tv_machinename1);
        tv_machinename2 = findViewById(R.id.tv_machinename2);
        tv_machinename3 = findViewById(R.id.tv_machinename3);
        tv_machinename4 = findViewById(R.id.tv_machinename4);


        mTv_show = findViewById(R.id.tv_show);
//     筛选条件
        initSave();

        mTv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LineCutoverActivity.start();
                finish();
            }
        });
    }

    private void initSave() {
        mData_stypeid = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_STYPEID);
        mData_stypeidright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_STYPEIDRIGHT);
        mMachine_numbers = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.MACHINE_NUMBERS);
        mMachinenumbersright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.MACHINENUMBERSRIGHT);
        mGuigeids = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.GUIGEIDS).replace("'", "");
        mGuigeidsright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.GUIGEIDSRIGHT).replace("'", "");
        mIszhengzhanglv = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.ISZHENGZHANGLV);
        miszhengzhanglvright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.ISZHENGZHANGLVRIGHT);

        mData_stypeids = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_STYPEIDS);
        mData_stypeidrights = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_STYPEIDRIGHTS);
        mMachine_numberss = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.MACHINE_NUMBERSS);
        mMachinenumbersrights = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.MACHINENUMBERSRIGHTS);
        mGuigeidss = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.GUIGEIDSS).replace("'", "");
        mGuigeidsrights = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.GUIGEIDSRIGHTS).replace("'", "");
        mIszhengzhanglvs = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.ISZHENGZHANGLVS);
        miszhengzhanglvrights = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.ISZHENGZHANGLVRIGHTS);


        mFiltername = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_FILTERNAME);
        mData_filternameright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_FILTERNAMERIGHT);
        mMachinename = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_MACHINENAME);
        mMachinenameright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.MACHINENAMERIGHT);
        mMachinenames = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_MACHINENAMES);
        mMachinenamerights = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.MACHINENAMERIGHTS);


        mCtypecyleft = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.CTYPECYLEFT);

        mCtypecyright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.CTYPECYRIGHT);

        if (!TextUtils.isEmpty(mFiltername)) {
            tv_selectup.setText(mFiltername);
        }
        if (!TextUtils.isEmpty(mData_filternameright)) {
            tv_selectdown.setText(mData_filternameright);
        }

        if (!TextUtils.isEmpty(mMachinename)) {
            tv_machinename1.setText(mMachinename);
        }
        if (!TextUtils.isEmpty(mMachinenames)) {
            tv_machinename2.setText(mMachinenames);
        }
        if (!TextUtils.isEmpty(mMachinenameright)) {
            tv_machinename3.setText(mMachinenameright);
        }
        if (!TextUtils.isEmpty(mMachinenamerights)) {
            tv_machinename4.setText(mMachinenamerights);
        }

        mSdate = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.SDATE);
        if ("1".equals(mSdate)) {
            time = 24;
        } else {
            time = 31;
        }
        for (int i = 0; i < time; i++) {
            mListNull.add(new Entry(i, Float.parseFloat("0")));
        }
        // 获取数据
        getChartData();
    }

    private void getChartData() {
        ArrayList<String> stringsleft = new ArrayList<>();
        ArrayList<String> stringsright = new ArrayList<>();

        linechartdate = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.LINECHARTDATE);
        if (TextUtils.isEmpty(linechartdate)) {
            linechartdate = SgqUtils.getNowDate();
            SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.LINECHARTDATE, linechartdate);
        } else {
            SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.LINECHARTDATE, linechartdate);
        }
        tvSelectionPeriod.setText(linechartdate);

        String sleft1 = "{\"stypeId\":\"" + mData_stypeid + "\",\"machineNumbers\":\"" + mMachine_numbers + "\",\"guigeids\":\"" + mGuigeids + "\",\"iszhengzhanglv\":\"" + mIszhengzhanglv + "\"}";
        String sleft2 = "{\"stypeId\":\"" + mData_stypeid + "\",\"machineNumbers\":\"" + mMachine_numberss + "\",\"guigeids\":\"" + mGuigeidss + "\",\"iszhengzhanglv\":\"" + mIszhengzhanglvs + "\"}";
        String sright1 = "{\"stypeId\":\"" + mData_stypeidright + "\",\"machineNumbers\":\"" + mMachinenumbersright + "\",\"guigeids\":\"" + mGuigeidsright + "\",\"iszhengzhanglv\":\"" + miszhengzhanglvright + "\"}";
        String sright2 = "{\"stypeId\":\"" + mData_stypeidright + "\",\"machineNumbers\":\"" + mMachinenumbersrights + "\",\"guigeids\":\"" + mGuigeidsrights + "\",\"iszhengzhanglv\":\"" + miszhengzhanglvrights + "\"}";

        if (!TextUtils.isEmpty(mMachine_numbers)) {
            stringsleft.add(sleft1);
        }
//        第二条线  没有选择机器的话   不添加
        if (!TextUtils.isEmpty(mMachine_numberss)) {
            stringsleft.add(sleft2);
        }
        if (!TextUtils.isEmpty(mMachinenumbersright)) {
            stringsright.add(sright1);
        }
        //        第四条线  没有选择机器的话   不添加
        if (!TextUtils.isEmpty(mMachinenumbersrights)) {
            stringsright.add(sright2);
        }


        RequestParams params = new RequestParams();
        params.put("orderBy", "2");
        params.put("tongji_shijian", linechartdate);
        params.put("sdate", mSdate);

        //测试方便查看参数   上线需修改
        params.put("leftTiaoJian", stringsleft.toString());
        params.put("rightTiaoJian", stringsright.toString());
//        try {
//            params.put("leftTiaoJian", URLEncoder.encode(stringsleft.toString(), "UTF-8"));
//            params.put("rightTiaoJian", URLEncoder.encode(stringsright.toString(), "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        httpUtils.doPost(Urls.filter(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result, String message) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                LineChartBean lineChartBean = gson.fromJson(jsonObject.toString(), LineChartBean.class);
//
                //      设置折线图数据
                setData(lineChartBean.getLeft_zhi(), lineChartBean.getRight_zhi());
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void setData(List<List<String>> left_zhi, List<List<String>> right_zhi) {

        try {

            if (left_zhi.get(0) != null && left_zhi.get(0).size() > 0) {
                for (int i = 0; i < left_zhi.get(0).size(); i++) {
                    if ("1".equals(mCtypecyleft)) {
                        mYValsleft1.add(new Entry(i, Float.parseFloat(left_zhi.get(0).get(i))));
                    } else {
                        mYValsleft1.add(new Entry(i, Float.parseFloat(AmountUtils.changeF2Y(left_zhi.get(0).get(i)))));
                    }
                }
                if ("1".equals(mCtypecyleft)) {
                    //获取最小值
                    mMinleft = Collections.min(SgqUtils.parseFloatList(left_zhi.get(0)));
                    //获取最大值
                    mMaxleft = Collections.max(SgqUtils.parseFloatList(left_zhi.get(0)));
                } else {
                    //获取最小值
                    mMinleft = Collections.min(SgqUtils.parseFloatListcy100(left_zhi.get(0)));
                    //获取最大值
                    mMaxleft = Collections.max(SgqUtils.parseFloatListcy100(left_zhi.get(0)));
                }

            }

            if (left_zhi.size() == 2) {
                if (left_zhi.get(1) != null && left_zhi.get(1).size() > 0) {
                    for (int i = 0; i < left_zhi.get(1).size(); i++) {
                        if ("1".equals(mCtypecyleft)) {
                            mYValsleft2.add(new Entry(i, Float.parseFloat(left_zhi.get(1).get(i))));
                        } else {
                            mYValsleft2.add(new Entry(i, Float.parseFloat(AmountUtils.changeF2Y(left_zhi.get(1).get(i)))));
                        }

                    }
                    if ("1".equals(mCtypecyleft)) {
                        //获取最小值
                        mMinleft1 = Collections.min(SgqUtils.parseFloatListcy100(left_zhi.get(1)));
                        //获取最大值
                        mMaxleft1 = Collections.max(SgqUtils.parseFloatListcy100(left_zhi.get(1)));
                    } else {
                        //获取最小值
                        mMinleft1 = Collections.min(SgqUtils.parseFloatList(left_zhi.get(1)));
                        //获取最大值
                        mMaxleft1 = Collections.max(SgqUtils.parseFloatList(left_zhi.get(1)));
                    }

                }
            }


            if (right_zhi.get(0) != null && right_zhi.get(0).size() > 0) {
                for (int i = 0; i < right_zhi.get(0).size(); i++) {
                    if ("1".equals(mCtypecyright)) {
                        mYValsright1.add(new Entry(i, Float.parseFloat(right_zhi.get(0).get(i))));
                    } else {
                        mYValsright1.add(new Entry(i, Float.parseFloat(AmountUtils.changeF2Y(right_zhi.get(0).get(i)))));
                    }
                }
                if ("1".equals(mCtypecyright)) {
                    //获取最小值
                    mMinright = Collections.min(SgqUtils.parseFloatList(right_zhi.get(0)));
                    //获取最大值
                    mMaxright = Collections.max(SgqUtils.parseFloatList(right_zhi.get(0)));
                } else {
                    //获取最小值
                    mMinright = Collections.min(SgqUtils.parseFloatListcy100(right_zhi.get(0)));
                    //获取最大值
                    mMaxright = Collections.max(SgqUtils.parseFloatListcy100(right_zhi.get(0)));
                }

            }
            if (right_zhi.size() == 2) {
                if (right_zhi.get(1) != null && right_zhi.get(1).size() > 0) {
                    for (int i = 0; i < right_zhi.get(1).size(); i++) {
                        if ("1".equals(mCtypecyright)) {
                            mYValsright2.add(new Entry(i, Float.parseFloat(right_zhi.get(1).get(i))));
                        } else {
                            mYValsright2.add(new Entry(i, Float.parseFloat(AmountUtils.changeF2Y(right_zhi.get(1).get(i)))));
                        }
                    }
                    if ("1".equals(mCtypecyright)) {
                        //获取最大值
                        mMaxright1 = Collections.max(SgqUtils.parseFloatList(right_zhi.get(1)));
                        //获取最小值
                        mMinright1 = Collections.min(SgqUtils.parseFloatList(right_zhi.get(1)));
                    } else {
                        //获取最大值
                        mMaxright1 = Collections.max(SgqUtils.parseFloatListcy100(right_zhi.get(1)));
                        //获取最小值
                        mMinright1 = Collections.min(SgqUtils.parseFloatListcy100(right_zhi.get(1)));
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //     初始化折线图
        initChart();

        LineDataSet set1, set2, set3, set4;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
            set3 = (LineDataSet) mChart.getData().getDataSetByIndex(2);
            set4 = (LineDataSet) mChart.getData().getDataSetByIndex(3);

            if (mYValsleft1 != null && mYValsleft1.size() > 0) {
                set1.setValues(mYValsleft1);
            } else {
                set1.setValues(mListNull);
            }
            if (mYValsleft2 != null && mYValsleft2.size() > 0) {
                set2.setValues(mYValsleft2);
            } else {
                set2.setValues(mListNull);
            }
            if (mYValsright1 != null && mYValsright1.size() > 0) {
                set3.setValues(mYValsright1);
            } else {
                set3.setValues(mListNull);
            }
            if (mYValsright2 != null && mYValsright2.size() > 0) {
                set4.setValues(mYValsright2);
            } else {
                set4.setValues(mListNull);
            }
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            if (mYValsleft1 != null && mYValsleft1.size() > 0) {
                set1 = new LineDataSet(mYValsleft1, "");
            } else {
                set1 = new LineDataSet(mListNull, "");
            }

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setDrawValues(false);
//          set1.setColor(Color.argb(1f, 0f, 0f, 0.5f));
            if (mYValsleft1 != null && mYValsleft1.size() > 0) {
                set1.setLineWidth(3f);
            } else {
                set1.setLineWidth(0f);
            }

            set1.setColor(Color.RED);
            set1.setCircleColor(Color.RED);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(50);
            set1.setLabel("");
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(false);
            set1.setDrawCircles(false);
//          定制节点水平和垂直的两条线
            set1.setHighlightLineWidth(1f); //设置线条宽度
            set1.setHighLightColor(Color.BLACK);  //设置线条颜色
            set1.setDrawHorizontalHighlightIndicator(false);

//            DataSet 2
            if (mYValsleft2 != null && mYValsleft2.size() > 0) {
                set2 = new LineDataSet(mYValsleft2, "");
            } else {
                set2 = new LineDataSet(mListNull, "");
            }

            set2.setAxisDependency(YAxis.AxisDependency.LEFT);
//          set2.setColor(Color.argb(1f, 0f, 1f, 0.5f));
            if (mYValsleft2 != null && mYValsleft2.size() > 0) {
                set2.setLineWidth(3f);
            } else {
                set2.setLineWidth(0f);
            }
            set2.setColor(Color.YELLOW);
            set2.setCircleColor(Color.RED);
            set2.setLabel("");
            set2.setDrawValues(false);
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
            if (mYValsright1 != null && mYValsright1.size() > 0) {
                set3 = new LineDataSet(mYValsright1, "");
            } else {
                set3 = new LineDataSet(mListNull, "");
            }
            set3.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set3.setColor(Color.BLUE);
            if (mYValsright1 != null && mYValsright1.size() > 0) {
                set3.setLineWidth(3f);
            } else {
                set3.setLineWidth(0f);
            }
            set3.setCircleColor(Color.RED);
            set3.setDrawValues(false);
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
            if (mYValsright2 != null && mYValsright2.size() > 0) {
                set4 = new LineDataSet(mYValsright2, " ");
            } else {
                set4 = new LineDataSet(mListNull, " ");
            }
            set4.setAxisDependency(YAxis.AxisDependency.RIGHT);
//        set4.setColor(Color.argb(1f, 0.5f, 0f, 0.5f));
            if (mYValsright2 != null && mYValsright2.size() > 0) {
                set4.setLineWidth(3f);
            } else {
                set4.setLineWidth(0f);
            }
            set4.setColor(Color.BLACK);
            set4.setCircleColor(Color.RED);
            set4.setDrawValues(false);
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

            mData = new LineData(set1, set2, set3, set4);
            mData.setValueTextColor(Color.BLUE);
            mData.setValueTextSize(14f);
            mChart.setData(mData);
            mChart.invalidate();
        }
    }

    private void initChart() {

        float maxl = (mMaxleft) >= (mMaxleft1) ? (mMaxleft) : (mMaxleft1);
        float minl = mMinleft >= mMinleft1 ? mMinleft1 : mMinleft;
        float maxr = mMaxright >= mMaxright1 ? mMaxright : mMaxright1;
        float minr = mMinright >= mMinright1 ? mMinright1 : mMinright;
        //左右纵轴最小的比较
//        float mmin = minl <= minr ? minl : minr;


        mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        mChart = findViewById(R.id.chart1);

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
        leftAxis.setAxisMaximum(maxl);
        leftAxis.setAxisMinimum(minl);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setTypeface(mTfLight);
        rightAxis.setTextSize(16f);
        rightAxis.setTextColor(Color.BLUE);
        rightAxis.setAxisMaximum(maxr);
        rightAxis.setAxisMinimum(minr);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        mChart.centerViewToAnimated(e.getX(), e.getY(), mChart.getData().getDataSetByIndex(h.getDataSetIndex())
                .getAxisDependency(), 500);

        if (mYValsleft1 != null && mYValsleft1.size() > 0) {
            mTv_num1.setText(mYValsleft1.get((int) e.getX()).getY() + "");
        } else {
            mTv_num1.setText(mListNull.get((int) e.getX()).getY() + "");
        }
        if (mYValsleft2 != null && mYValsleft2.size() > 0) {
            mTv_num2.setText(mYValsleft2.get((int) e.getX()).getY() + "");
        } else {
            mTv_num2.setText(mListNull.get((int) e.getX()).getY() + "");
        }
        if (mYValsright1 != null && mYValsright1.size() > 0) {
            mTv_num3.setText(mYValsright1.get((int) e.getX()).getY() + "");
        } else {
            mTv_num3.setText(mListNull.get((int) e.getX()).getY() + "");
        }

        if (mYValsright2 != null && mYValsright2.size() > 0) {
            mTv_num4.setText(mYValsright2.get((int) e.getX()).getY() + "");
        } else {
            mTv_num4.setText(mListNull.get((int) e.getX()).getY() + "");
        }

        if (TextUtils.isEmpty(mMachinename)) {
            mTv_num1.setText("");
        }
        if (TextUtils.isEmpty(mMachinenames)) {
            mTv_num2.setText("");
        }
        if (TextUtils.isEmpty(mMachinenameright)) {
            mTv_num3.setText("");
        }
        if (TextUtils.isEmpty(mMachinenamerights)) {
            mTv_num4.setText("");
        }


    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }
}

