package com.defence.costomapp.activity.manage;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.defence.actionsheetmenumlib.JFActionSheetMenu;
import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.AlarmNotBean;
import com.defence.costomapp.utils.APPUtils;
import com.defence.costomapp.utils.ActionSheelUtil;
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
    private String machineID;
    private String machineNo;
    String groupid;

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


    }

    //已解决报警列表
    private void getAlarmYet() {
        RequestParams params = new RequestParams();
        params.put("machineID", machineID);
        params.put("machineNo", machineNo);
        params.put("adminGroupID", groupid);
        httpUtils.doPost(Urls.alarmYet(), SgqUtils.MANAGER_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                AlarmNotBean alarmNotBean = gson.fromJson(jsonObject.toString(), AlarmNotBean.class);
                liearAlarmyet.setAdapter(new ExceptionAdapter(ExceptionAlarmActivity.this, alarmNotBean.getList(),"yet"));

            }
        });

    }

    //未解决报警列表
    private void getAlarmNot() {
        RequestParams params = new RequestParams();
        params.put("machineID", machineID);
        params.put("machineNo", machineNo);
        params.put("adminGroupID", groupid);
        httpUtils.doPost(Urls.alarmNot(),SgqUtils.MANAGER_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                AlarmNotBean alarmNotBean = gson.fromJson(jsonObject.toString(), AlarmNotBean.class);

                liearAlarmnot.setAdapter(new ExceptionAdapter(ExceptionAlarmActivity.this, alarmNotBean.getList(),"not"));
                machineAddr.setText(alarmNotBean.getMachine().getDetailedinstalladdress());

                AMap aMap = map.getMap();
                LatLng latLng = new LatLng(alarmNotBean.getMachine().getLatitude(), alarmNotBean.getMachine().getLongitude());
                UiSettings uiSettings = aMap.getUiSettings();
                // 通过UISettings.setZoomControlsEnabled(boolean)来设置缩放按钮是否能显示
                uiSettings.setZoomControlsEnabled(false);
                uiSettings.setScrollGesturesEnabled(false);
                uiSettings.setZoomGesturesEnabled(false);
                //可视化区域，将指定位置指定到屏幕中心位置
                CameraUpdate cameraUpdate = CameraUpdateFactory
                        .newCameraPosition(new CameraPosition(latLng, 19, 0, 30));
                aMap.moveCamera(cameraUpdate);
                aMap.addMarker(new MarkerOptions().position(latLng));
                aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(final LatLng latLng) {
                        ActionSheelUtil.showMenu(ExceptionAlarmActivity.this, "选择导航",
                                new String[]{"百度地图"}, false, new JFActionSheetMenu.OnActionSheetItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int itemPosition) {
                                        switch (itemPosition) {
                                            case 0:
                                                APPUtils.jumpBaiduNav(ExceptionAlarmActivity.this, latLng);
                                                break;
                                        }
                                    }

                                    @Override
                                    public void onCanceClick(View view) {

                                    }
                                });
                    }
                });


            }
        });

    }

    private class ExceptionAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        private List<AlarmNotBean.ListBean> list;
        private String notyet;

        public ExceptionAdapter(ExceptionAlarmActivity context, List<AlarmNotBean.ListBean> list,String notyet) {
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

            if(notyet.equals("not")){
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
