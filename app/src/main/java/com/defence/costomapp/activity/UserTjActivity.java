package com.defence.costomapp.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.UserTjBean;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用户统计
 */
public class UserTjActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.regusernum)
    TextView regusernum;
    @BindView(R.id.saomausernum)
    TextView saomausernum;
    @BindView(R.id.pingtai_pay)
    TextView pingtaiPay;
    @BindView(R.id.weixinpay)
    TextView weixinpay;
    @BindView(R.id.list_usertj)
    ListView listUsertj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tj);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        middleTitle.setText("用户统计");
        getData();


    }

    private void getData() {

        RequestParams params = new RequestParams();
        params.put("length", "0");
        params.put("sortOrderBy", "timeline");
        params.put("order", "desc");
        params.put("orderBy", "2");
        params.put("endpag", "10");
        httpUtils.doPost(Urls.userTj(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                UserTjBean userTjBean = gson.fromJson(jsonObject.toString(), UserTjBean.class);
                regusernum.setText("注册人数:"+userTjBean.getReg_user());
                pingtaiPay.setText("平台付款次数:"+userTjBean.getPingtaiNum());
                weixinpay.setText("微信付款次数"+userTjBean.getWeixinNum());

            }
        });

    }
}
