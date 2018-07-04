package com.defence.costomapp.activity.statistics;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JieSuanActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_shangjiesuan)
    TextView tvShangjiesuan;
    @BindView(R.id.tv_yijiesuan)
    TextView tvYijiesuan;
    @BindView(R.id.tv_weijiesuan)
    TextView tvWeijiesuan;
    @BindView(R.id.liear_left)
    LinearLayout liearLeft;
    @BindView(R.id.liear_right)
    LinearLayout liearRight;
    private String groupid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jie_suan);
        ButterKnife.bind(this);
        middleTitle.setText("结算");
        getdata();


    }

    private void getdata() {

        groupid = SharePerenceUtil.getStringValueFromSp("groupid");
        RequestParams params = new RequestParams();
        params.put("adminGroupID", groupid);
        httpUtils.doPost(Urls.jiesuan(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result, String message) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());

            }
        });

//        RequestParams params = new RequestParams();
//        params.put("orderBy", "2");
//        params.put("tongji_shijian", "2018-05-14");
//        params.put("sdate", "1");
//        try {
//            params.put("leftTiaoJian", URLEncoder.encode("{\"stypeId\":\"1\",\"machineNumbers\":\"'lj-010-04-001-002','lj-010-04-002-001'\",\"guigeids\":\"505,545,555\",\"iszhengzhanglv\":\"0\"}", "UTF-8"));
//            params.put("rightTiaoJian", URLEncoder.encode("{\"stypeId\":\"2\",\"machineNumbers\":\"'lj-010-04-001-002','lj-010-04-002-001'\",\"guigeids\":\"505,545,555\",\"iszhengzhanglv\":\"0\"}", "UTF-8"));
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        httpUtils.doPost(Urls.filter(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {
//            @Override
//            public void onSuccess(Gson gson, Object result, String message) throws JSONException {
//                JSONObject jsonObject = new JSONObject(result.toString());
//
//            }
//        });
    }


    @OnClick(R.id.liear_left)
    public void onViewClicked() {
        finish();
    }
}
