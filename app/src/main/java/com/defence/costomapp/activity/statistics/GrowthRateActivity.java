package com.defence.costomapp.activity.statistics;

import android.content.Intent;
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

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private String mGuigeids;
    private String mMachine_numbers;
    private String mData_stypeid;
    private String iszhengzhanglv = "0";
    private String mFiltername;
    private String mMachinename;
    private String mData_stypeidright;
    private String mMachinenumbersright;
    private String mData_filternameright;
    private String mMachinenameright;
    private String mGuigeidsright;
    private String mGoodsname;
    private String mGoodsnameright;

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
        tvTip.setOnClickListener(this::onClick);


        mData_stypeid = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_STYPEID);
        mData_stypeidright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_STYPEIDRIGHT);
        mMachine_numbers = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.MACHINE_NUMBERS);
        mMachinenumbersright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.MACHINENUMBERSRIGHT);
        mGuigeids = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.GUIGEIDS).replace("'", "");
        mGuigeidsright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.GUIGEIDSRIGHT).replace("'", "");

        mFiltername = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_FILTERNAME);
        mData_filternameright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_FILTERNAMERIGHT);
        mMachinename = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.DATA_MACHINENAME);
        mMachinenameright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.MACHINENAMERIGHT);
        mGoodsname = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.GOODSNAME);
        mGoodsnameright = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.GOODSNAMERIGHT);


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
                AnalysisFilterXYActivity.start();
                break;
            case R.id.tv_tip:
                getData();
                break;
        }
    }

    private void getData() {
        RequestParams params = new RequestParams();
        params.put("orderBy", "2");
        params.put("tongji_shijian", "2018-07-06");
        params.put("sdate", "1");
        params.put("leftTiaoJian", "{\"stypeId\":\"" + mData_stypeid + "\",\"machineNumbers\":\"" + mMachine_numbers + "\",\"guigeids\":\"" + mGuigeids + "\",\"iszhengzhanglv\":\"" + iszhengzhanglv + "\"}");
        params.put("rightTiaoJian", "{\"stypeId\":\"" + mData_stypeid + "\",\"machineNumbers\":\"" + mMachine_numbers + "\",\"guigeids\":\"" + mGuigeids + "\",\"iszhengzhanglv\":\"" + iszhengzhanglv + "\"}");

        httpUtils.doPost(Urls.filter(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result, String message) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                tvTip.setText(result.toString());
            }
        });
    }
}

