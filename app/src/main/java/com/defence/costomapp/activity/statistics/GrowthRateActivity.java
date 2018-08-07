package com.defence.costomapp.activity.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.net.Constant;


import butterknife.BindView;
import butterknife.ButterKnife;
import tech.linjiang.pandora.Pandora;

@Route(path = "/statistics/GrowthRateActivity")
public class GrowthRateActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.rb_notSee)
    RadioButton rbNotSee;
    @BindView(R.id.rv_viewGrowth)
    RadioButton rvViewGrowth;
    @BindView(R.id.rg_timerange)
    RadioGroup rgTimerange;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    private String mVerticalaxis;
    private String iszhengzhanglv = "0";


    public static void start() {
        ARouter.getInstance().build("/statistics/GrowthRateActivity").navigation();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_growth_rate);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        back.setText("返回");
        rightTitle.setText("保存");
        rightTitle.setTextColor(getResources().getColor(R.color.bule_light));
        back.setTextColor(getResources().getColor(R.color.bule_light));
        back.setOnClickListener(this);
        rightTitle.setOnClickListener(this);
        mVerticalaxis = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.VERTICALAXIS);

        //部分文字改变颜色
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan redSpan = new ForegroundColorSpan(getResources().getColor(R.color.red));
        //这里注意一定要先给textview赋值
        SpannableStringBuilder builder = new SpannableStringBuilder(tvTip.getText().toString());
        //为不同位置字符串设置不同颜色
        builder.setSpan(redSpan, 20, tvTip.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //最后为textview赋值
        tvTip.setText(builder);

        rgTimerange.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (R.id.rb_notSee == checkedId) {
                    iszhengzhanglv = "0";
                } else if (R.id.rv_viewGrowth == checkedId) {
                    iszhengzhanglv = "1";
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.right_title:
                if (mVerticalaxis.equals("left")) {
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.ISZHENGZHANGLV, iszhengzhanglv);
                } else if (mVerticalaxis.equals("right")) {
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.ISZHENGZHANGLVRIGHT, iszhengzhanglv);
                } else if (mVerticalaxis.equals("lefts")) {
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.ISZHENGZHANGLVS, iszhengzhanglv);
                } else if (mVerticalaxis.equals("rights")) {
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.ISZHENGZHANGLVRIGHTS, iszhengzhanglv);
                }
                AnalysisFilterXYActivity.start();
                break;
        }
    }

}

