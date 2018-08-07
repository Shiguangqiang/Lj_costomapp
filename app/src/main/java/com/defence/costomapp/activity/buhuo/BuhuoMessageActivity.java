package com.defence.costomapp.activity.buhuo;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.model.LatLng;
import com.defence.costomapp.R;
import com.defence.costomapp.activity.ChoiceTypeActivity;
import com.defence.costomapp.activity.CommonScanActivity;
import com.defence.costomapp.activity.WebViewActivity;
import com.defence.costomapp.adapter.BuhuoMessageAdapter;
import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.bean.BuhuoMessageEntity;
import com.defence.costomapp.bean.WorkLogDateBean;
import com.defence.costomapp.db.MachineGroupModel;
import com.defence.costomapp.db.TrackModel;
import com.defence.costomapp.service.LongRunningService;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.myinterface.RVItemClickListener;
import com.defence.costomapp.utils.RecyclerViewUtils;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 补货列表
 */

public class BuhuoMessageActivity extends BaseActivity {
    static Handler handler;
    private RecyclerView rv;
    private BuhuoMessageAdapter adapter;
    private LinearLayout buhuoNomessagell;
    private ArrayList<BuhuoMessageEntity> buhuoMessageEntities;
    private SwipeRefreshLayout sfl;
    private BuhuoMessageEntity buhuoMessageEntity;
    private LinearLayout ll_scan;
    //退出时的时间
    private long mExitTime;


    //对返回键进行监听
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(BuhuoMessageActivity.this, "再按一次退出系统", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
//            int loginType = SharePerenceUtil.getIntValueFromSP("loginType");
//            if (loginType != -1) {
//                SharePerenceUtil.putStringValuetoSp(loginType + "", "");
//                SharePerenceUtil.putIntValuetoSp("loginType", -1);
//                SharePerenceUtil.putBooleanValuetoSp(loginType + "isLogin", false);
//                MyApplication.getApp().setUserInfo(null);
//            }
//            mIntentservice = new Intent(getApplicationContext(), LongRunningService.class);
//            stopService(mIntentservice);
            finishAffinity();
            System.exit(0);
        }
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buhuo_message);
        buhuoNomessagell = findViewById(R.id.nomessageLL);
        rv = findViewById(R.id.buhuomessage_rv);
        RecyclerViewUtils.setReRecyclerView(this, rv);
        sfl = findViewById(R.id.srl);

        ll_scan = findViewById(R.id.ll_scan);
        ll_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuhuoMessageActivity.this, CommonScanActivity.class);
                intent.putExtra("km_scan", "open");
                startActivity(intent);
            }
        });
        sfl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        buhuoMessageEntities = new ArrayList<>();
        getData();

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int loginType = SharePerenceUtil.getIntValueFromSP("loginType");
//                if (loginType != -1) {
//                    SharePerenceUtil.putStringValuetoSp(loginType + "", "");
//                    SharePerenceUtil.putStringValuetoSp("groupid", "0");
//                    SharePerenceUtil.putIntValuetoSp("loginType", -1);
//                    SharePerenceUtil.putBooleanValuetoSp(loginType + "isLogin", false);
//                    MyApplication.getApp().setUserInfo(null);
//
//                }
//                mIntentservice = new Intent(getApplicationContext(), LongRunningService.class);
//                stopService(mIntentservice);
                startActivity(new Intent(BuhuoMessageActivity.this, ChoiceTypeActivity.class));
                finish();

            }
        });

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                buhuoMessageEntity.setNotRepairCount(0);
                adapter.notifyDataSetChanged();
            }
        };


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuhuoMessageActivity.this, WebViewActivity.class));

            }
        });

    }


    private void getData() {
        buhuoMessageEntities.clear();
        RequestParams params = new RequestParams();
        params.put("faultType", "102");
        httpUtils.doPost(Urls.getBuhuoMessage(), SgqUtils.BUHUO_TYPE, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result, String message) {
                sfl.setRefreshing(false);
                try {
                    JSONArray ja = ((JSONObject) result).getJSONArray("list");
                    if (ja.length() == 0) {
                        buhuoNomessagell.setVisibility(View.VISIBLE);
                    } else {
                        buhuoNomessagell.setVisibility(View.GONE);
                        for (int i = 0; i < ja.length(); i++) {
                            BuhuoMessageEntity entity = gson.fromJson(ja.get(i).toString(), BuhuoMessageEntity.class);
                            buhuoMessageEntities.add(entity);
                        }
                        Log.d("BuhuoMessageActivity", "buhuoMessageEntities.size():" + buhuoMessageEntities.size());
                        if (adapter == null) {
                            adapter = new BuhuoMessageAdapter(buhuoMessageEntities, BuhuoMessageActivity.this, new RVItemClickListener() {

                                public void onItemClick(int position) {
                                    Intent intent = new Intent(BuhuoMessageActivity.this, BuhuoMessageInfoActivity.class);
                                    buhuoMessageEntity = buhuoMessageEntities.get(position);
                                    intent.putExtra("bundle", buhuoMessageEntity);
                                    startActivity(intent);
                                }
                            });

                            rv.setAdapter(adapter);
                        } else {
                            adapter.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Context context, String message) {
                super.onError(context, message);
                sfl.setRefreshing(false);
            }

            @Override
            public void onFailure(Context context) {
                super.onFailure(context);
                sfl.setRefreshing(false);
            }

            @Override
            public void onSpecial(Context context, String sign, String message) {
                super.onSpecial(context, sign, message);
                sfl.setRefreshing(false);
            }
        });
    }
}
