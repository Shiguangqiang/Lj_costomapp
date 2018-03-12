package com.defence.costomapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.utils.SharePerenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatisticsActivity extends BaseActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.liear_machicetj)
    LinearLayout liearMachicetj;
    @BindView(R.id.liear_usertj)
    LinearLayout liearUsertj;
    @BindView(R.id.liear_liushui)
    LinearLayout liearLiushui;
    @BindView(R.id.liear_dingdan)
    LinearLayout liearDingdan;
    @BindView(R.id.liear_jiesuan)
    LinearLayout liearJiesuan;
    @BindView(R.id.liear_tuikuan)
    LinearLayout liearTuikuan;

    /**
     * 统计
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        middleTitle.setText("统计");
    }


    @OnClick({R.id.back, R.id.liear_machicetj, R.id.liear_usertj, R.id.liear_liushui, R.id.liear_dingdan, R.id.liear_jiesuan, R.id.liear_tuikuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                int loginType = SharePerenceUtil.getIntValueFromSP("loginType");
                if (loginType != -1) {
                    SharePerenceUtil.putStringValuetoSp(loginType + "", "");
                    SharePerenceUtil.putIntValuetoSp("loginType", -1);
                    SharePerenceUtil.putBooleanValuetoSp(loginType + "isLogin",false);
                    MyApplication.getApp().setUserInfo(null);
                }
                finish();
                break;
            case R.id.liear_machicetj:
                //自助机统计
                startActivity(new Intent(StatisticsActivity.this,MachineTjActivity.class));
                break;
            case R.id.liear_usertj:
                break;
            case R.id.liear_liushui:
                break;
            case R.id.liear_dingdan:
                break;
            case R.id.liear_jiesuan:
                break;
            case R.id.liear_tuikuan:
                break;
        }
    }
}
