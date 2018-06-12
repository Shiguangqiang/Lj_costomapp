package com.defence.costomapp.activity.statistics;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.defence.costomapp.bean.DingdDetailBean;
import com.defence.costomapp.utils.AmountUtils;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DingdanDetailActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_anum)
    TextView tvAnum;
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.tv_payval)
    TextView tvPayval;
    @BindView(R.id.tv_anum2)
    TextView tvAnum2;
    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_ignore)
    TextView tvIgnore;
    @BindView(R.id.tv_tuikuan)
    TextView tvTuikuan;
    @BindView(R.id.liear_daichuhuo)
    LinearLayout liearDaichuhuo;
    @BindView(R.id.tv_timen)
    TextView tvTimen;
    @BindView(R.id.liear_tuikuansucc)
    LinearLayout liearTuikuansucc;
    @BindView(R.id.liear_left)
    LinearLayout liearLeft;
    @BindView(R.id.liear_right)
    LinearLayout liearRight;
    private String whoID;
    private String wxOpenID;
    private String numberID;
    private String groupid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan_detail);
        ButterKnife.bind(this);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        map.onCreate(savedInstanceState);
        initdata();
    }

    private void initdata() {
        numberID = getIntent().getStringExtra("numberID");
        String dis = getIntent().getStringExtra("dis");
        groupid = SharePerenceUtil.getStringValueFromSp("groupid");
        middleTitle.setText(numberID);
        middleTitle.setTextSize(14);
        if (TextUtils.isEmpty(dis)) {
            rightIcon.setImageResource(R.mipmap.all);
        }


        RequestParams params = new RequestParams();
        params.put("numberID", numberID);
        params.put("adminGroupID", groupid);
        httpUtils.doPost(Urls.dingdandetail(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result, String message) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                final DingdDetailBean.DataBean dingdDetailBean = gson.fromJson(jsonObject.toString(), DingdDetailBean.DataBean.class);
                tvAddress.setText(dingdDetailBean.getMachine_data().getDetailedinstalladdress());
                tvAnum.setText(dingdDetailBean.getMachineID());
                tvPayval.setText(AmountUtils.changeF2Y(dingdDetailBean.getPayVal() + ""));
                tvAnum2.setText(dingdDetailBean.getMachineID());
                tvShow.setText(dingdDetailBean.getDescVal());
                tvTimen.setText(dingdDetailBean.getOrderTimeline());
                whoID = dingdDetailBean.getOrderUID() + "";
                wxOpenID = dingdDetailBean.getWxOpenID();


                switch (dingdDetailBean.getStatus()) {
                    case 3:
                        tvState.setText("待出货");
                        tvState.setTextColor(Color.rgb(255, 51, 0));
                        liearDaichuhuo.setVisibility(View.VISIBLE);
                        liearTuikuansucc.setVisibility(View.GONE);
                        break;
                    case 4:
                        tvState.setText("交易成功");
                        tvState.setTextColor(Color.rgb(26, 233, 50));
                        liearDaichuhuo.setVisibility(View.VISIBLE);
                        liearTuikuansucc.setVisibility(View.GONE);
                        break;
                    case 5:
                        tvState.setText("退款成功");
                        tvState.setTextColor(Color.rgb(255, 204, 0));
                        liearDaichuhuo.setVisibility(View.GONE);
                        liearTuikuansucc.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        tvState.setText("退款成功");
                        tvState.setTextColor(Color.rgb(255, 204, 0));
                        liearDaichuhuo.setVisibility(View.GONE);
                        liearTuikuansucc.setVisibility(View.VISIBLE);
                        break;
                }
                tvTime.setText(dingdDetailBean.getOrderTimeline());

                tvTuikuan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ShowDialog(AmountUtils.changeF2Y(dingdDetailBean.getPayVal() + ""));
                    }
                });

                AMap aMap = map.getMap();
                LatLng latLng = new LatLng(dingdDetailBean.getMachine_data().getLatitude(), dingdDetailBean.getMachine_data().getLongitude());
                UiSettings uiSettings = aMap.getUiSettings();
                // 通过UISettings.setZoomControlsEnabled(boolean)来设置缩放按钮是否能显示
                uiSettings.setZoomControlsEnabled(true);
                uiSettings.setScrollGesturesEnabled(true);
                uiSettings.setZoomGesturesEnabled(true);
                //可视化区域，将指定位置指定到屏幕中心位置
                CameraUpdate cameraUpdate = CameraUpdateFactory
                        .newCameraPosition(new CameraPosition(latLng, 19, 0, 30));
                aMap.moveCamera(cameraUpdate);
                aMap.addMarker(new MarkerOptions().position(latLng).title(dingdDetailBean.getMachine_data().getDetailedinstalladdress()));

              /*  aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(final LatLng latLng) {
                        ActionSheelUtil.showMenu(DingdanDetailActivity.this, "选择导航",
                                new String[]{"百度地图"}, false, new JFActionSheetMenu.OnActionSheetItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int itemPosition) {
                                        switch (itemPosition) {
                                            case 0:
                                                APPUtils.jumpBaiduNav(DingdanDetailActivity.this, latLng);
                                                break;
                                        }
                                    }

                                    @Override
                                    public void onCanceClick(View view) {

                                    }
                                });
                    }
                });*/
            }
        });
    }


    @OnClick({R.id.liear_left, R.id.tv_ignore, R.id.right_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.liear_left:
                finish();
                break;
            case R.id.tv_ignore:
                finish();
                break;
            case R.id.right_icon:
                Intent intenthis = new Intent(DingdanDetailActivity.this, DingdHistoryActivity.class);
                intenthis.putExtra("whoID", whoID);
                intenthis.putExtra("wxOpenID", wxOpenID);
                startActivity(intenthis);
                break;
        }
    }

    /***
     * 单选Dialog
     */
    private int singleSelectedId; // 单选ＩＤ
    private String[] items = {"出货失败,手动退款", "付款超时,手动退款", "协商退款"};


    private void ShowDialog(String sprice) {
        TextView title = new TextView(this);
        title.setText(sprice + "元");
        title.setPadding(20, 20, 20, 20);
        title.setGravity(Gravity.RIGHT);
        title.setTextColor(getResources().getColor(R.color.black));
        title.setTextSize(23);

        singleSelectedId = -1;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setIcon(R.drawable.ic_launcher);
        builder.setCustomTitle(title);
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                singleSelectedId = which;
//                Toast.makeText(, "你选择的ID为："+which, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("退款", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated meth od stub

                if (singleSelectedId > 0) {

                    tuikuan(items[singleSelectedId].toString());
                } else {
                    tuikuan(items[0].toString());
                    // 业务逻辑
                }
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        builder.create().show();
    }


    //退款
    private void tuikuan(final String reason) {

        RequestParams params = new RequestParams();
        params.put("numberID", numberID);
        params.put("adminGroupID", groupid);
        params.put("reason", reason);
        httpUtils.doPost(Urls.dingdantuikuan(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result, String message) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                liearDaichuhuo.setVisibility(View.GONE);
                tvState.setText("退款成功");


            }
        });
    }
}
