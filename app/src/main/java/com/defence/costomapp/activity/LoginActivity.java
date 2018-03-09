package com.defence.costomapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


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
    private TextView view_dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        icon = findViewById(R.id.login_iv);
        name = findViewById(R.id.login_name);
        username = findViewById(R.id.login_username);
        psw = findViewById(R.id.login_psw);
        view_dis = findViewById(R.id.view_dis);

//        loginType = SharePerenceUtil.getIntValueFromSP("loginT");
        loginType =Integer.valueOf(getIntent().getStringExtra("loginType")) ;

        switch (loginType) {
            case 0:
                icon.setBackgroundResource(R.mipmap.buhuoicon);
                name.setText("补货人员");
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
                            SharePerenceUtil.putBooleanValuetoSp(loginType + "isLogin",true);


                            switch (loginType) {
                                case 0:
                                    startActivity(new Intent(LoginActivity.this, BuhuoMessageActivity.class));
                                    break;
                                case 10100:
                                    MangerUserBean.ResultBean.DataOneBean mangerUserBean = gson.fromJson(jb.toString(), MangerUserBean.ResultBean.DataOneBean.class);
                                    Intent intent = new Intent(LoginActivity.this, ManagerActivity.class);
                                    SharePerenceUtil.putStringValuetoSp("groupid", mangerUserBean.getGroupID() + "");
                                    startActivity(intent);
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
}
