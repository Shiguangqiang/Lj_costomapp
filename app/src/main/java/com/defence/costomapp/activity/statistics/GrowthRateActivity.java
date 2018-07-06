package com.defence.costomapp.activity.statistics;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.net.Constant;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/statistics/GrowthRateActivity")
public class GrowthRateActivity extends BaseActivity {

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
    private String mGuigeids;
    private String mMachine_numbers;
    private String mData_stypeid;
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


        mData_stypeid = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_STYPEID);
        mMachine_numbers = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.MACHINE_NUMBERS);
        mGuigeids = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.GUIGEIDS).replace("'","");


        //1 时间范围内开通    0时间范围内 终止

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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    private void getData() {
        RequestParams params = new RequestParams();
        params.put("orderBy", "2");
        params.put("tongji_shijian", "2018-07-06");
        params.put("sdate", "1");
        try {
            params.put("leftTiaoJian", URLEncoder.encode("{\"stypeId\":\"" + mData_stypeid + "\",\"machineNumbers\":\"" + mMachine_numbers + "\",\"guigeids\":\"" + mGuigeids + "\",\"iszhengzhanglv\":\"" + iszhengzhanglv + "\"}", "UTF-8"));
            params.put("rightTiaoJian", URLEncoder.encode("{\"stypeId\":\"" + mData_stypeid + "\",\"machineNumbers\":\"" + mMachine_numbers + "\",\"guigeids\":\"" + mGuigeids + "\",\"iszhengzhanglv\":\"" + iszhengzhanglv + "\"}", "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        httpUtils.doPost(Urls.filter(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result, String message) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                tvTip.setText(result.toString());
            }
        });
    }
}
