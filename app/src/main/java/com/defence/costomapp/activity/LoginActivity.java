package com.defence.costomapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.defence.costomapp.activity.buhuo.BuhuoMessageActivity;
import com.defence.costomapp.activity.manage.ManagerActivity;
import com.defence.costomapp.activity.statistics.StatisticsActivity;
import com.defence.costomapp.bean.MangerUserBean;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.R;
import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.bean.UserInfo;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity {
    private int loginType;
    private ImageView icon;
    private TextView name;
    private EditText username, psw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_login);

        icon = findViewById(R.id.login_iv);
        name = findViewById(R.id.login_name);
        username = findViewById(R.id.login_username);
        psw = findViewById(R.id.login_psw);


        psw.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                } else {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                }
            }
        });


        try {
            loginType = Integer.valueOf(getIntent().getStringExtra("loginType"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        initdata(loginType);


        switch (loginType) {
            case 0:
                icon.setBackgroundResource(R.mipmap.buhuoicon);
                name.setText("补货人员");
                break;
            case 10110:
                icon.setBackgroundResource(R.mipmap.tongji);
                name.setText("统计人员");
                break;
            case 10100:
                icon.setBackgroundResource(R.mipmap.guanli);
                name.setText("管理人员");
                break;
        }
        findViewById(R.id.login_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(username.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "帐号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(psw.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                RequestParams params = new RequestParams();
                params.put("account", username.getText().toString());
                params.put("password", psw.getText().toString());
                httpUtils.doPost(Urls.BuhuoLogin(), loginType, params, new HttpInterface() {
                    @Override
                    public void onSuccess(Gson gson, Object result) {
                        try {
                            JSONObject jb = ((JSONObject) result).getJSONObject("data_one");
                            UserInfo userInfo = gson.fromJson(jb.toString(), UserInfo.class);
                            userInfo.setFuncType(loginType);

                            MyApplication.getApp().setUserInfo(userInfo);
                            SharePerenceUtil.putStringValuetoSp(loginType + "", username.getText().toString() + "---" + psw.getText().toString());
                            SharePerenceUtil.putIntValuetoSp("loginType", loginType);
                            SharePerenceUtil.putBooleanValuetoSp(loginType + "isLogin", true);

                            MangerUserBean.ResultBean.DataOneBean mangerUserBean = gson.fromJson(jb.toString(), MangerUserBean.ResultBean.DataOneBean.class);
                            SharePerenceUtil.putStringValuetoSp("groupid", mangerUserBean.getGroupID() + "");

                            switch (loginType) {
                                case 0:
                                    startActivity(new Intent(LoginActivity.this, BuhuoMessageActivity.class));
                                    break;
                                case 10100:
                                    Intent intent = new Intent(LoginActivity.this, ManagerActivity.class);
//
                                    startActivity(intent);
                                    break;
                                case 10110:
                                    startActivity(new Intent(LoginActivity.this, StatisticsActivity.class));
                                    break;

                            }
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    //sign 等于2
                    @Override
                    public void onError(Context context, String message) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Context context) {

                    }
                });
            }
        });
    }

    private void initdata(int type) {

        String nameAndPsw = SharePerenceUtil.getStringValueFromSp(type + "");
        if (!TextUtils.isEmpty(nameAndPsw)) {
            final String userNamesp = nameAndPsw.split("---")[0];
            final String pswsp = nameAndPsw.split("---")[1];

            username.setText(userNamesp);
            psw.setText(pswsp);


        }

    }
}
