package com.defence.costomapp.activity.buhuo;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.defence.costomapp.adapter.BuhuoMessageInfoAdapter;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.bean.BuhuoInfoEntity;
import com.defence.costomapp.bean.BuhuoMessageEntity;
import com.defence.costomapp.bean.MachineEntity;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.myinterface.RVItemClickListener;
import com.defence.costomapp.utils.APPUtils;
import com.defence.costomapp.utils.ActionSheelUtil;
import com.defence.costomapp.utils.RecyclerViewUtils;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 补货信息列表
 */
public class BuhuoMessageInfoActivity extends BaseActivity {
    private BuhuoMessageEntity buhuoMessageEntity;
    private int alarmStock;
    private MachineEntity machineEntity;
    private ArrayList<BuhuoInfoEntity> buhuoInfoEntities;
    private BuhuoMessageInfoAdapter buhuoMessageInfoAdapter;
    private RecyclerView rv;
    private TextView machineAddr;
    private MapView mMapView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buhuo_message_info);
        //获取地图控件引用
        mMapView = findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        rv = findViewById(R.id.buhuoinforv);
        machineAddr = findViewById(R.id.machineAddr);
        RecyclerViewUtils.setReRecyclerView(this, rv);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buhuoMessageEntity = (BuhuoMessageEntity) getIntent().getSerializableExtra("bundle");
        buhuoInfoEntities = new ArrayList<>();
        if (buhuoMessageEntity != null) {
            RequestParams params = new RequestParams();
            params.put("machineNo", buhuoMessageEntity.getMachinenumber());
            params.put("machineID", buhuoMessageEntity.getId());
            httpUtils.doPost(Urls.getBuhuoMessageInfo(), SgqUtils.BUHUO_TYPE, params, new HttpInterface() {
                @Override
                public void onSuccess(Gson gson, Object result) {
                    JSONObject jb = (JSONObject) result;
                    try {
                        alarmStock = jb.getInt("alarmStock");
                         machineEntity = gson.fromJson(jb.getJSONObject("machine").toString(), MachineEntity.class);
                        machineAddr.setText(machineEntity.getAddress() + machineEntity.getDetailedinstalladdress());


                        AMap aMap = mMapView.getMap();
                        LatLng latLng = new LatLng(machineEntity.getLatitude(), machineEntity.getLongitude());
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
                                ActionSheelUtil.showMenu(BuhuoMessageInfoActivity.this, "选择导航",
                                        new String[]{"百度地图"}, false, new JFActionSheetMenu.OnActionSheetItemClickListener() {
                                            @Override
                                            public void onItemClick(View view, int itemPosition) {
                                                switch (itemPosition) {
                                                    case 0:
                                                        APPUtils.jumpBaiduNav(BuhuoMessageInfoActivity.this, latLng);
                                                        break;
                                                }
                                            }

                                            @Override
                                            public void onCanceClick(View view) {

                                            }
                                        });
                            }
                        });
                        JSONArray ja = jb.getJSONArray("list");
                        for (int i = 0; i < ja.length(); i++) {
                            BuhuoInfoEntity buhuoInfoEntity = gson.fromJson(ja.get(i).toString(), BuhuoInfoEntity.class);
                            buhuoInfoEntities.add(buhuoInfoEntity);
                        }
//                        sortInfo();
                        if (buhuoMessageInfoAdapter == null) {
                            sortInfo();
                            buhuoMessageInfoAdapter = new BuhuoMessageInfoAdapter(alarmStock, buhuoInfoEntities, BuhuoMessageInfoActivity.this, new RVItemClickListener() {
                                @Override
                                public void onItemClick(int position) {

                                }
                            });
                            rv.setAdapter(buhuoMessageInfoAdapter);
                        } else {
                            buhuoMessageInfoAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        findViewById(R.id.buhuodone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestParams params = new RequestParams();
                params.put("machineNo", machineEntity.getMachinenumber());
                params.put("machineID", machineEntity.getId());
                params.put("isrepair", "1");
                params.put("itemNo", "");
                httpUtils.doPost(Urls.setBuhuoMessageRead(), SgqUtils.BUHUO_TYPE, params, new HttpInterface() {
                    @Override
                    public void onSuccess(Gson gson, Object result) {
                        BuhuoMessageActivity.handler.sendEmptyMessage(1);
                    }
                });
            }
        });
    }

    private void sortInfo() {
        Comparator<BuhuoInfoEntity> itemComparator = new Comparator<BuhuoInfoEntity>() {
            public int compare(BuhuoInfoEntity info1, BuhuoInfoEntity info2) {
                return info1.getLatticenumbers() - info2.getLatticenumbers();
            }
        };
        Collections.sort(buhuoInfoEntities, itemComparator);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


}
