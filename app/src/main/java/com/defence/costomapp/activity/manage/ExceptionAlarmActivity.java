package com.defence.costomapp.activity.manage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.AlarmNotBean;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 异常报警详情
 */

public class ExceptionAlarmActivity extends BaseActivity {

    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.machineAddr)
    TextView machineAddr;
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.liear_alarmnot)
    ListView liearAlarmnot;
    @BindView(R.id.liear_alarmyet)
    ListView liearAlarmyet;
    @BindView(R.id.cb_alarm)
    CheckBox cbAlarm;
    @BindView(R.id.tv_machineState)
    TextView tvMachineState;
    private String machineID;
    private String machineNo;
    String groupid;
    String showtype = "0";
    private ExceptionAdapter yet;
    private ExceptionAdapter not;
    private List<AlarmNotBean.ListBean> listyet;
    private List<AlarmNotBean.ListBean> listnot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception_alarm);
        ButterKnife.bind(this);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        map.onCreate(savedInstanceState);
        initdata();
    }

    private void initdata() {
        back.setText("返回");
        middleTitle.setText("异常报警");
        groupid = SharePerenceUtil.getStringValueFromSp("groupid");
        machineID = getIntent().getStringExtra("machineID");
        machineNo = getIntent().getStringExtra("machineNo");


        //未解决的报警
        getAlarmNot();
        //已解决的报警
        getAlarmYet();

        cbAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbAlarm.isChecked()) {
                    showtype = "1";
                    listnot.clear();
                    listyet.clear();
                    getAlarmYet();
                    getAlarmNot();
                    yet.notifyDataSetChanged();
                    not.notifyDataSetChanged();
                } else {
                    showtype = "0";
                    listnot.clear();
                    listyet.clear();
                    getAlarmYet();
                    getAlarmNot();
                    yet.notifyDataSetChanged();
                    not.notifyDataSetChanged();
                }
            }
        });

        tvMachineState.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog dialog = new AlertDialog.Builder(ExceptionAlarmActivity.this)
                        .setMessage("是否立即重启此机器?")//设置对话框的内容
                        //设置对话框的按钮
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //   是否立即重启
                                restartImmediately();
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
                return true;
            }
        });


    }

    //   是否立即重启
    private void restartImmediately() {
        RequestParams params = new RequestParams();
        params.put("machineID", machineID);
        params.put("machineNo", machineNo);
        httpUtils.doPost(Urls.restartImmediately(), SgqUtils.MANAGER_TYPE, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result, String message) throws JSONException {
                Toast.makeText(ExceptionAlarmActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        });
    }

    //已解决报警列表
    private void getAlarmYet() {
        RequestParams params = new RequestParams();
        params.put("machineID", machineID);
        params.put("machineNo", machineNo);
        params.put("adminGroupID", groupid);
        params.put("show_all", showtype);
        httpUtils.doPost(Urls.alarmYet(), SgqUtils.MANAGER_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result, String message) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                AlarmNotBean alarmNotBean = gson.fromJson(jsonObject.toString(), AlarmNotBean.class);
                listyet = alarmNotBean.getList();
                yet = new ExceptionAdapter(ExceptionAlarmActivity.this, listyet, "yet");
                liearAlarmyet.setAdapter(yet);

            }
        });

    }

    //未解决报警列表
    private void getAlarmNot() {
        RequestParams params = new RequestParams();
        params.put("machineID", machineID);
        params.put("machineNo", machineNo);
        params.put("adminGroupID", groupid);
        params.put("show_all", showtype);
        httpUtils.doPost(Urls.alarmNot(), SgqUtils.MANAGER_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result, String message) throws JSONException {
                if(result!=null){
                    JSONObject jsonObject = new JSONObject(result.toString());
                    AlarmNotBean alarmNotBean = gson.fromJson(jsonObject.toString(), AlarmNotBean.class);

                    if (!alarmNotBean.getContent().isEmpty()) {
                        tvMachineState.setText(alarmNotBean.getContent());
                    }
                    listnot = alarmNotBean.getList();
                    not = new ExceptionAdapter(ExceptionAlarmActivity.this, alarmNotBean.getList(), "not");
                    liearAlarmnot.setAdapter(not);
                    machineAddr.setText(alarmNotBean.getMachine().getDetailedinstalladdress());

                    AMap aMap = map.getMap();
                    LatLng latLng = new LatLng(alarmNotBean.getMachine().getLatitude(), alarmNotBean.getMachine().getLongitude());
                    UiSettings uiSettings = aMap.getUiSettings();
                    // 通过UISettings.setZoomControlsEnabled(boolean)来设置缩放按钮是否能显示
                    uiSettings.setZoomControlsEnabled(true);
                    uiSettings.setScrollGesturesEnabled(true);
                    uiSettings.setZoomGesturesEnabled(true);
                    //可视化区域，将指定位置指定到屏幕中心位置
                    CameraUpdate cameraUpdate = CameraUpdateFactory
                            .newCameraPosition(new CameraPosition(latLng, 19, 0, 30));
                    aMap.moveCamera(cameraUpdate);
                    aMap.addMarker(new MarkerOptions().position(latLng).title(alarmNotBean.getMachine().getDetailedinstalladdress()));
                }
            }
        });

    }

    private class ExceptionAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        private List<AlarmNotBean.ListBean> list;
        private String notyet;

        public ExceptionAdapter(ExceptionAlarmActivity context, List<AlarmNotBean.ListBean> list, String notyet) {
            super();
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.list = list;
            this.notyet = notyet;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if (list != null && list.size() > 0) {
                return list.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return list.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(final int position, View view, ViewGroup arg2) {
            // TODO Auto-generated method stub
            if (view == null) {
                view = inflater.inflate(R.layout.item_exception, null);
            }
            TextView tv_reason = view.findViewById(R.id.tv_reason);
            TextView tv_time = view.findViewById(R.id.tv_time);

            if (notyet.equals("not")) {
                tv_reason.setTextColor(Color.rgb(249, 6, 6));
            }

            tv_reason.setText(list.get(position).getFaultdescribe());
            tv_time.setText(list.get(position).getFaulttime());

            return view;

        }
    }


    @OnClick({R.id.back, R.id.map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
