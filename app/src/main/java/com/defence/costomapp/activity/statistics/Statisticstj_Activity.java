package com.defence.costomapp.activity.statistics;


import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.defence.costomapp.R;
import com.defence.costomapp.activity.viewPresenter.StaticstjContract;
import com.defence.costomapp.activity.viewPresenter.StaticstjPresenter;
import com.defence.costomapp.base.BaseNewActivity;
import com.defence.costomapp.bean.DailyCostBean;
import com.defence.costomapp.bean.LineChartBean;
import com.defence.costomapp.utils.SharePerenceUtil;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/statistics/Statisticstj_Activity")
public class Statisticstj_Activity extends BaseNewActivity<StaticstjPresenter> implements StaticstjContract.View {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.liear_left)
    LinearLayout liearLeft;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.liear_right)
    LinearLayout liearRight;

    public static void start() {
        ARouter.getInstance().build("/statistics/Statisticstj_Activity").navigation();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_statisticstj_;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        back.setImageResource(R.mipmap.back);
        middleTitle.setText(R.string.statistics_tj);
//        mPresenter.getLineChartData(SharePerenceUtil.getStringValueFromSp("groupid"), "10100");
        mPresenter.getGailycost(SharePerenceUtil.getStringValueFromSp("groupid"), "10100", "2018-07-02");
    }

    @OnClick(R.id.liear_left)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void setLineChartData(LineChartBean lineChartData) {

    }

    @Override
    public void setGailycost(DailyCostBean.ResultBean resultBean) {

        Toast.makeText(this, resultBean.getDate().toString(), Toast.LENGTH_SHORT).show();

    }
}
