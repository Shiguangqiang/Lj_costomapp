package com.defence.costomapp.activity.statistics;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.DailyCostBean;
import com.defence.costomapp.utils.AmountUtils;
import com.defence.costomapp.utils.DateAndTimeUtil;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 今日流水
 */

public class DailyCostActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_paycount)
    TextView tvPaycount;
    @BindView(R.id.tv_sendcount)
    TextView tvSendcount;
    @BindView(R.id.tv_xiaose)
    TextView tvXiaose;
    @BindView(R.id.tv_zerocb)
    TextView tvZerocb;
    @BindView(R.id.tv_fanxian)
    TextView tvFanxian;
    @BindView(R.id.tv_lirun)
    TextView tvLirun;
    @BindView(R.id.lastday)
    TextView lastday;
    @BindView(R.id.tv_nextday)
    TextView tvNextday;
    @BindView(R.id.liear_left)
    LinearLayout liearLeft;
    @BindView(R.id.liear_right)
    LinearLayout liearRight;
    private String groupid;
    String ddate = SgqUtils.getNowDate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_cost);
        ButterKnife.bind(this);
        middleTitle.setText("今日流水");
        initdata(ddate);
    }

    private void initdata(final String date) {

        groupid = SharePerenceUtil.getStringValueFromSp("groupid");
        RequestParams params = new RequestParams();
        params.put("adminGroupID", groupid);
        params.put("date", date);
        httpUtils.doPost(Urls.dailycost(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result, String message) throws JSONException {
                ddate = date;
                JSONObject jsonObject = new JSONObject(result.toString());
                DailyCostBean.ResultBean dailyCostBean = gson.fromJson(jsonObject.toString(), DailyCostBean.ResultBean.class);
                tvDate.setText(dailyCostBean.getDate() + "" + SgqUtils.dateToWeek(dailyCostBean.getDate() + ""));

                tvXiaose.setText(AmountUtils.changeF2Y(dailyCostBean.getMap().getSumJinE() + "") + "元");
                tvZerocb.setText(AmountUtils.changeF2Y(dailyCostBean.getMap().getSumLiRun() + "") + "元");
                tvFanxian.setText(AmountUtils.changeF2Y(dailyCostBean.getMap().getSumFanXian() + "") + "元");



                tvPaycount.setText(dailyCostBean.getMap().getSumCost() + "");
                tvSendcount.setText(dailyCostBean.getMap().getSumSaleCount() + "");

            }
        });


    }

    @OnClick({R.id.liear_left, R.id.lastday, R.id.tv_nextday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.liear_left:
                finish();
                break;
            case R.id.lastday:
                String pre = DateAndTimeUtil.checkOption("pre", ddate);
                initdata(pre);
                break;
            case R.id.tv_nextday:
                String next = DateAndTimeUtil.checkOption("next", ddate);
                if (ddate.equals(SgqUtils.getNowDate())) {
                    Toast.makeText(DailyCostActivity.this, "已是最新日期。", Toast.LENGTH_SHORT).show();
                } else {
                    initdata(next);
                }
                break;
        }
    }
}
