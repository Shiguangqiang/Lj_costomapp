package com.defence.costomapp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.defence.costomapp.R;
import com.defence.costomapp.activity.buhuo.BuhuoMessageActivity;
import com.defence.costomapp.activity.manage.ManagerActivity;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //判断是否为android6.0以上系统版本，如果是，需要动态添加权限

        if (Build.VERSION.SDK_INT >= 23) {
            showContacts();
        } else {
            new Handler().postDelayed(r, 0);// 2秒后关闭，并跳转到主页面
        }

    }

    //条状
    Runnable r = new Runnable() {
        @Override
        public void run() {

            if (SharePerenceUtil.getBooleanValueFromSp(SgqUtils.BUHUO_TYPE + "isLogin", true)) {
                //那用户名密码登录
                startActivity(new Intent(MainActivity.this, BuhuoMessageActivity.class));

            } else if (SharePerenceUtil.getBooleanValueFromSp(SgqUtils.MANAGER_TYPE + "isLogin", true)) {
                //那用户名密码登录
                startActivity(new Intent(MainActivity.this, ManagerActivity.class));
            } else {
                startActivity(new Intent(MainActivity.this, ChoiceTypeActivity.class));
            }
            finish();
        }
    };

    private static final int BAIDU_READ_PHONE_STATE = 100;

    public void showContacts() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
                ) {
            Toast.makeText(getApplicationContext(), "没有权限,请手动开启权限", Toast.LENGTH_SHORT).show();
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, BAIDU_READ_PHONE_STATE);
        } else {
            //初始化
            new Handler().postDelayed(r, 1000);// 1秒后关闭，并跳转到主页面
        }
    }


    //Android6.0申请权限的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case BAIDU_READ_PHONE_STATE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
                    new Handler().postDelayed(r, 0);// 1秒后关闭，并跳转到主页面
                } else {
                    // 没有获取到权限，做特殊处理
                    Toast.makeText(getApplicationContext(), "获取权限失败，请手动开启", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(r, 0);// 1秒后关闭，并跳转到主页面
                }
                break;
            default:
                break;
        }
    }

}
