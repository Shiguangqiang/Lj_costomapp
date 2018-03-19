package com.defence.costomapp.activity.statistics;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.defence.costomapp.R;
import com.defence.costomapp.activity.buhuo.BuhuoMessageActivity;
import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.utils.SharePerenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StatisticsActivity extends BaseActivity {


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
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;

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
        back.setText("退出登录");
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
                    SharePerenceUtil.putBooleanValuetoSp(loginType + "isLogin", false);
                    MyApplication.getApp().setUserInfo(null);
                }
                finish();
                break;
            case R.id.liear_machicetj:
                //自助机统计
                startActivity(new Intent(StatisticsActivity.this, MachineTjActivity.class));
                break;
            // 用户统计
            case R.id.liear_usertj:
                startActivity(new Intent(StatisticsActivity.this, UserTjActivity.class));
//                startActivity(new Intent(StatisticsActivity.this,UserTjDetailActivity.class));
                break;
            case R.id.liear_liushui:
                startActivity(new Intent(StatisticsActivity.this,DailyCostActivity.class));
                break;
            case R.id.liear_dingdan:
                startActivity(new Intent(StatisticsActivity.this,DingdanActivity.class));
                break;
            case R.id.liear_jiesuan:
                startActivity(new Intent(StatisticsActivity.this,JieSuanActivity.class));
                break;
            case R.id.liear_tuikuan:
                break;
        }
    }

    //退出时的时间
    private long mExitTime;

    //对返回键进行监听
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(StatisticsActivity.this, "再按一次退出系统", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            int loginType = SharePerenceUtil.getIntValueFromSP("loginType");
            if (loginType != -1) {
                SharePerenceUtil.putStringValuetoSp(loginType + "", "");
                SharePerenceUtil.putIntValuetoSp("loginType", -1);
                SharePerenceUtil.putBooleanValuetoSp(loginType + "isLogin", false);
                MyApplication.getApp().setUserInfo(null);

            }
            finishAffinity();
            System.exit(0);
        }
    }
}
