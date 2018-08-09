package com.defence.costomapp.activity.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.net.Constant;
import com.defence.costomapp.utils.DateAndTimeUtil;
import com.defence.costomapp.utils.MyEvent;
import com.defence.costomapp.utils.SgqUtils;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/statistics/LineCutoverActivity")
public class LineCutoverActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ll_PreviousCycle)
    LinearLayout llPreviousCycle;
    @BindView(R.id.ll_nextCycle)
    LinearLayout llNextCycle;
    @BindView(R.id.ll_filter)
    LinearLayout llFilter;
    @BindView(R.id.ll_quit)
    LinearLayout llQuit;
    private String mLinechartdate;
    private String mDate;

    public static void start() {
        ARouter.getInstance().build("/statistics/LineCutoverActivity").navigation();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_cutover);
        ButterKnife.bind(this);
        llPreviousCycle.setOnClickListener(this::onClick);
        llNextCycle.setOnClickListener(this::onClick);
        llFilter.setOnClickListener(this::onClick);
        llQuit.setOnClickListener(this::onClick);


        mLinechartdate = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.LINECHARTDATE);
        mDate = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.SDATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_PreviousCycle:
//                日  24时
                if (mDate.equals("1")) {
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.LINECHARTDATE, DateAndTimeUtil.checkOption("pre", mLinechartdate));
//                月   31日
                } else if (mDate.equals("2")) {
                    try {
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.LINECHARTDATE, DateAndTimeUtil.subMonth(mLinechartdate));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                startActivity(new Intent(LineCutoverActivity.this, MpLineChartActivity.class));
                finish();
                break;
            case R.id.ll_nextCycle:
                //                日  24时
                if (mDate.equals("1")) {
                    if (mLinechartdate.equals(SgqUtils.getNowDate())) {
                        Toast.makeText(LineCutoverActivity.this, "已是最新周期数据!", Toast.LENGTH_SHORT).show();
                    } else {
                        //展示数据
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.LINECHARTDATE, DateAndTimeUtil.checkOption("next", mLinechartdate));
                        startActivity(new Intent(LineCutoverActivity.this, MpLineChartActivity.class));
                        finish();
                    }
                    //             月   31日
                } else if (mDate.equals("2")) {

                    if (mLinechartdate.substring(0, 7).equals(SgqUtils.getNowYmDate())) {
                        Toast.makeText(LineCutoverActivity.this, "已是最新周期数据!", Toast.LENGTH_SHORT).show();
                    } else {
                        //展示数据
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.LINECHARTDATE, DateAndTimeUtil.dateaddFormat(mLinechartdate));
                        startActivity(new Intent(LineCutoverActivity.this, MpLineChartActivity.class));
                        finish();
                    }
                }

                break;
            case R.id.ll_filter:
                AnalysisFilterXYActivity.start();
                finish();
                break;
            case R.id.ll_quit:
                finish();
                break;
            default:
                break;
        }
    }
}
