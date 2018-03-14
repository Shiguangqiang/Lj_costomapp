package com.defence.costomapp.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.model.LatLng;

import com.defence.costomapp.activity.buhuo.BuhuoMessageActivity;
import com.defence.costomapp.activity.manage.ManagerActivity;
import com.defence.costomapp.activity.statistics.StatisticsActivity;
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

import cn.jpush.android.api.JPushInterface;

/**
 * 选择
 */


public class ChoiceTypeActivity extends BaseActivity implements OnClickListener {


    //统计
    public static final int BUHUO_TYPE = 0;
    //管理
    public static final int MANAGER_TYPE = 10100;
    //统计
    private final int TONGJI_TYPE = 10110;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private ProgressDialog pd;
    private AlertDialog alertDialog;
    private String updateUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_type);
        findViewById(R.id.buhuoll).setOnClickListener(this);
//        findViewById(R.id.kefull).setOnClickListener(this);
        findViewById(R.id.tongji).setOnClickListener(this);
        findViewById(R.id.guanlill).setOnClickListener(this);
        LinearLayout liear_saoma = findViewById(R.id.liear_saoma);

        alertDialog = new AlertDialog.Builder(this).setMessage("有新版本").setNegativeButton("更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse(updateUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        }).create();


        pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
        pd.setMessage("正在定位....");

        liear_saoma.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();
                mLocationClient.startLocation();

            }
        });

        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {

                if(pd!=null){
                    pd.dismiss();
                }


                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        double[] lls = gaoDeToBaidu(amapLocation.getLongitude(), amapLocation.getLatitude());
                        LatLng latLng = new LatLng(lls[1], lls[0]);
                        Intent intent = new Intent();
                        intent.putExtra("latlng", latLng);
                        intent.setClass(ChoiceTypeActivity.this, CommonScanActivity.class);
                        startActivity(intent);
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }


            }
        };
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，
        // 启动定位时SDK会返回最近3s内精度最高的一次定位结果。
        // 如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，
        // 反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        checkNewVersion();


        //判断是否为android6.0以上系统版本，如果是，需要动态添加权限

        if (Build.VERSION.SDK_INT >= 23) {
            showContacts();
        }
//        else {
//            new Handler().postDelayed(r, 0);// 2秒后关闭，并跳转到主页面
//        }
    }

    private void checkNewVersion() {
        String versionCode = getVersionCode();
        RequestParams params = new RequestParams();
        params.put("versionInt", versionCode);
        httpUtils.doPost(Urls.checkNewVersion(), type, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result) {
                try {
                    JSONObject jsonObject = new JSONObject(result.toString());
                    updateUrl = jsonObject.getString("href");
                    alertDialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }




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
            ActivityCompat.requestPermissions(ChoiceTypeActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, BAIDU_READ_PHONE_STATE);
        }

    }



    public String getVersionCode() {
        PackageManager packageManager = this.getPackageManager();
        PackageInfo packageInfo;
        String versionCode = "";
        try {
            packageInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
            versionCode = packageInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    private double[] gaoDeToBaidu(double gd_lon, double gd_lat) {
        double[] bd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = gd_lon, y = gd_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * PI);
        bd_lat_lon[0] = z * Math.cos(theta) + 0.0065;
        bd_lat_lon[1] = z * Math.sin(theta) + 0.006;
        return bd_lat_lon;
    }

    private int type;

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ChoiceTypeActivity.this, LoginActivity.class);
        switch (view.getId()) {
            case R.id.buhuoll:
                type = BUHUO_TYPE;
                intent.putExtra("loginType", BUHUO_TYPE + "");
//                SharePerenceUtil.putIntValuetoSp("loginT",BUHUO_TYPE);
                break;
//            case R.id.liear_saoma:
//                //启动定位
//                pd.show();
//                mLocationClient.startLocation();
//                break;
            case R.id.tongji:
                type = TONGJI_TYPE;
                intent.putExtra("loginType", TONGJI_TYPE + "");
                break;
            case R.id.kefull:
                break;
            case R.id.guanlill:
                type = MANAGER_TYPE;
                intent.putExtra("loginType", MANAGER_TYPE + "");
                break;
        }


        switch (type) {
            case BUHUO_TYPE:
                //补货页面
                checkTypeOrLogined(intent, type, BuhuoMessageActivity.class);
                break;
            case TONGJI_TYPE:
                //统计页面
                checkTypeOrLogined(intent, type, StatisticsActivity.class);
                break;
            case MANAGER_TYPE:
                //管理页面
                checkTypeOrLogined(intent, type, ManagerActivity.class);
                break;
        }

    }

    //检查是否已经登录
    private void checkTypeOrLogined(Intent intent, final int type, final Class<?> clz) {
        String nameAndPsw = SharePerenceUtil.getStringValueFromSp(type + "");
        if (!TextUtils.isEmpty(nameAndPsw)) {
            final String userName = nameAndPsw.split("---")[0];
            final String psw = nameAndPsw.split("---")[1];
            RequestParams params = new RequestParams();
            params.put("account", userName);
            params.put("password", psw);

            httpUtils.doPost(Urls.BuhuoLogin(), type, params, new HttpInterface() {
                @Override
                public void onSuccess(Gson gson, Object result) {
                    try {
                        JSONObject jb = ((JSONObject) result).getJSONObject("data_one");
                        UserInfo userInfo = gson.fromJson(jb.toString(), UserInfo.class);
                        userInfo.setFuncType(type);
                        MyApplication.getApp().setUserInfo(userInfo);
                        SharePerenceUtil.putStringValuetoSp(type + "", userName + "---" + psw);
                        SharePerenceUtil.putIntValuetoSp("loginType", type);
                        SetRegistrationid(getApplicationContext());
                        startActivity(new Intent(ChoiceTypeActivity.this, clz));
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


        } else {
            startActivity(intent);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pd != null) {
            pd.dismiss();
        }
    }

    //设置注册id
    private void SetRegistrationid(Context context) {
        RequestParams params = new RequestParams();
        params.put("registrationid", JPushInterface.getRegistrationID(context));
        httpUtils.doPost(Urls.setRegistrationid(), type, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result) {
                Log.d("ChoiceTypeActivity", "registerId设置成功");
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
}
