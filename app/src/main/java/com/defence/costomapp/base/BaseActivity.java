package com.defence.costomapp.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.defence.costomapp.utils.httputils.HttpUtils;


public class BaseActivity extends AppCompatActivity {
    public HttpUtils httpUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpUtils = new HttpUtils(this);


    }
}
