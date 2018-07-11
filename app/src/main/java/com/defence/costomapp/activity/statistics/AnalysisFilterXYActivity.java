package com.defence.costomapp.activity.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseNewActivity;
import com.defence.costomapp.net.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

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
                SPUtils.getInstance(Constant.SHARED_NAME).clear();
                break;
            case R.id.rb_left:
                rbLeft.setChecked(true);
                rbRight.setChecked(false);
                break;
            case R.id.rb_right:
                rbLeft.setChecked(false);
                rbRight.setChecked(true);
                break;
//            case R.id.tv_filterCondition:
//                Intent intent = new Intent(AnalysisFilterXYActivity.this, AnalysisFilterActivity.class);
//                intent.putExtra("verticalAxis", "left");
//                startActivity(intent);
//                break;
//            case R.id.tv_filterCondition_right:
//                Intent intentright = new Intent(AnalysisFilterXYActivity.this, AnalysisFilterActivity.class);
//                intentright.putExtra("verticalAxis", "right");
//                startActivity(intentright);
//                break;
            case R.id.ll_left:
                rbLeft.setChecked(true);
                rbRight.setChecked(false);
                Intent intent = new Intent(AnalysisFilterXYActivity.this, AnalysisFilterActivity.class);
                intent.putExtra("verticalAxis", "left");
                startActivity(intent);
                SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.SDATE, sdate);
                break;
            case R.id.ll_right:
                rbLeft.setChecked(false);
                rbRight.setChecked(true);
                Intent intentright = new Intent(AnalysisFilterXYActivity.this, AnalysisFilterActivity.class);
                intentright.putExtra("verticalAxis", "right");
                startActivity(intentright);
                SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.SDATE, sdate);
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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

        mFiltername = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_FILTERNAME);
        mData_filternameright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_FILTERNAMERIGHT);
        mMachinename = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_MACHINENAME);
        mMachinenameright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.MACHINENAMERIGHT);
        mGoodsname = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.GOODSNAME);
        mGoodsnameright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.GOODSNAMERIGHT);


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


        back.setOnClickListener(this);
        rightTitle.setOnClickListener(this::onClick);
        rbLeft.setOnClickListener(this::onClick);
        rbRight.setOnClickListener(this::onClick);
        tvFilterCondition.setOnClickListener(this::onClick);
        tvFilterConditionRight.setOnClickListener(this::onClick);
        llLeft.setOnClickListener(this::onClick);
        llRight.setOnClickListener(this::onClick);


        rgDate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (R.id.rb_day == checkedId) {
                    sdate = "1";
                } else if (R.id.rb_mouth == checkedId) {
                    sdate = "2";
                }
            }
        });
    }
}
