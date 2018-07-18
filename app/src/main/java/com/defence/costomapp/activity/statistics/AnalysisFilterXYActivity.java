package com.defence.costomapp.activity.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseNewActivity;
import com.defence.costomapp.net.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.linjiang.pandora.Pandora;

@Route(path = "/statistics/AnalysisFilterXYActivity")
public class AnalysisFilterXYActivity extends BaseNewActivity implements View.OnClickListener {


    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.rb_day)
    RadioButton rbDay;
    @BindView(R.id.rb_mouth)
    RadioButton rbMouth;
    @BindView(R.id.rg_date)
    RadioGroup rgDate;
    @BindView(R.id.rb_left)
    RadioButton rbLeft;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.rb_right)
    RadioButton rbRight;
    @BindView(R.id.tv_description2)
    TextView tvDescription2;
    @BindView(R.id.tv_filterCondition)
    TextView tvFilterCondition;
    @BindView(R.id.tv_communityGoods)
    TextView tvCommunityGoods;
    @BindView(R.id.tv_filterCondition_right)
    TextView tvFilterConditionRight;
    @BindView(R.id.tv_communityGoods_right)
    TextView tvCommunityGoodsRight;
    @BindView(R.id.ll_left)
    LinearLayout llLeft;
    @BindView(R.id.ll_right)
    LinearLayout llRight;

    //日月视图
    private String sdate = "1";
    private String mFiltername;
    private String mMachinename;
    private String mData_filternameright;
    private String mMachinenameright;
    private String mGoodsname;
    private String mGoodsnameright;
    private String mFilternames;
    private String mMachinenames;
    private String mData_filternamerights;
    private String mMachinenamerights;
    private String mGoodsnames;
    private String mGoodsnamerights;


    public static void start() {
        ARouter.getInstance().build("/statistics/AnalysisFilterXYActivity").navigation();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                SPUtils.getInstance(Constant.SHARED_NAME).clear();
                startActivity(new Intent(AnalysisFilterXYActivity.this, StatisticsActivity.class));
                finish();
                break;
            case R.id.right_title:
                startActivity(new Intent(AnalysisFilterXYActivity.this, MpLineChartActivity.class));
                break;
            case R.id.rb_left:
                rbLeft.setChecked(true);
                rbRight.setChecked(false);
                break;
            case R.id.rb_right:
                rbLeft.setChecked(false);
                rbRight.setChecked(true);
                break;
            case R.id.tv_filterCondition:
                rbLeft.setChecked(true);
                rbRight.setChecked(false);
                Intent intenttv = new Intent(AnalysisFilterXYActivity.this, AnalysisFilterActivity.class);
                intenttv.putExtra("verticalAxis", "left");
                startActivity(intenttv);
                break;
            case R.id.tv_communityGoods:
                rbLeft.setChecked(true);
                rbRight.setChecked(false);
                Intent intenttvs = new Intent(AnalysisFilterXYActivity.this, AnalysisFilterActivity.class);
                intenttvs.putExtra("verticalAxis", "left");
                startActivity(intenttvs);
                break;
            case R.id.tv_filterCondition_right:
                rbLeft.setChecked(false);
                rbRight.setChecked(true);
                Intent intentrighttv = new Intent(AnalysisFilterXYActivity.this, AnalysisFilterActivity.class);
                intentrighttv.putExtra("verticalAxis", "right");
                startActivity(intentrighttv);
                break;
            case R.id.tv_communityGoods_right:
                rbLeft.setChecked(false);
                rbRight.setChecked(true);
                Intent intentrighttvs = new Intent(AnalysisFilterXYActivity.this, AnalysisFilterActivity.class);
                intentrighttvs.putExtra("verticalAxis", "right");
                startActivity(intentrighttvs);
                break;
            case R.id.tv_description:
                rbLeft.setChecked(true);
                rbRight.setChecked(false);
                Intent intent = new Intent(AnalysisFilterXYActivity.this, AnalysisFilterActivity.class);
                intent.putExtra("verticalAxis", "lefts");
                startActivity(intent);
                break;
            case R.id.tv_description2:
                rbLeft.setChecked(false);
                rbRight.setChecked(true);
                Intent intentright = new Intent(AnalysisFilterXYActivity.this, AnalysisFilterActivity.class);
                intentright.putExtra("verticalAxis", "rights");
                startActivity(intentright);
                break;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_analysis_filter_xy;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initView() {

        back.setText("返回");
        rightTitle.setText("制图");
        rightTitle.setTextColor(getResources().getColor(R.color.bule_light));
        back.setTextColor(getResources().getColor(R.color.bule_light));

//1，3折线描述
        mFiltername = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_FILTERNAME);
        mData_filternameright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_FILTERNAMERIGHT);
        mMachinename = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_MACHINENAME);
        mMachinenameright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.MACHINENAMERIGHT);
        mGoodsname = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.GOODSNAME);
        mGoodsnameright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.GOODSNAMERIGHT);
//2，4折线描述
        mFilternames = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_FILTERNAMES);
        mData_filternamerights = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_FILTERNAMERIGHTS);
        mMachinenames = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_MACHINENAMES);
        mMachinenamerights = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.MACHINENAMERIGHTS);
        mGoodsnames = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.GOODSNAMES);
        mGoodsnamerights = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.GOODSNAMERIGHTS);


        if (!TextUtils.isEmpty(mFiltername)) {
            tvFilterCondition.setText(mFiltername);
        }
        if (!TextUtils.isEmpty(mMachinename)) {
            tvCommunityGoods.setText(mMachinename);
        }
        if (!TextUtils.isEmpty(mGoodsname)) {
            tvCommunityGoods.setText(mMachinename + mGoodsname);
        }
        if (!TextUtils.isEmpty(mData_filternameright)) {
            tvFilterConditionRight.setText(mData_filternameright);
        }
        if (!TextUtils.isEmpty(mMachinenameright)) {
            tvCommunityGoodsRight.setText(mMachinenameright);
        }
        if (!TextUtils.isEmpty(mGoodsnameright)) {
            tvCommunityGoodsRight.setText(mMachinenameright + mGoodsnameright);
        }


        if (!TextUtils.isEmpty(mMachinenames)) {
            tvDescription.setText(mMachinenames);
        }
        if (!TextUtils.isEmpty(mGoodsnames)) {
            tvDescription.setText(mMachinenames + mGoodsnames);
        }

        if (!TextUtils.isEmpty(mMachinenamerights)) {
            tvDescription2.setText(mMachinenamerights);
        }
        if (!TextUtils.isEmpty(mGoodsnamerights)) {
            tvDescription2.setText(mMachinenamerights + mGoodsnamerights);
        }


        back.setOnClickListener(this);
        rightTitle.setOnClickListener(this::onClick);
        rbLeft.setOnClickListener(this::onClick);
        rbRight.setOnClickListener(this::onClick);
        tvFilterCondition.setOnClickListener(this::onClick);
        tvFilterConditionRight.setOnClickListener(this::onClick);
        llLeft.setOnClickListener(this::onClick);
        llRight.setOnClickListener(this::onClick);
        tvDescription.setOnClickListener(this::onClick);
        tvDescription2.setOnClickListener(this::onClick);
        tvCommunityGoods.setOnClickListener(this::onClick);
        tvCommunityGoodsRight.setOnClickListener(this::onClick);

        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.SDATE, sdate);


        rgDate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (R.id.rb_day == checkedId) {
                    sdate = "1";
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.SDATE, sdate);
                } else if (R.id.rb_mouth == checkedId) {
                    sdate = "2";
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.SDATE, sdate);
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            SPUtils.getInstance(Constant.SHARED_NAME).clear();
            startActivity(new Intent(AnalysisFilterXYActivity.this, StatisticsActivity.class));
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
