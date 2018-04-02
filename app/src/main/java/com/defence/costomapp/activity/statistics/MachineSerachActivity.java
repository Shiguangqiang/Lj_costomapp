package com.defence.costomapp.activity.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.TongjiBean;
import com.defence.costomapp.fragment.CommodityFragment;
import com.defence.costomapp.fragment.MachiceFragment;
import com.defence.costomapp.utils.AmountUtils;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MachineSerachActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.sumJinE)
    TextView sumJinE;
    @BindView(R.id.paysalecount)
    TextView paysalecount;
    @BindView(R.id.freesalecount)
    TextView freesalecount;
    @BindView(R.id.freecost)
    TextView freecost;
    @BindView(R.id.lirun)
    TextView lirun;
    @BindView(R.id.manger_tablayout)
    TabLayout mangerTablayout;
    @BindView(R.id.manger_viewpager)
    ViewPager mangerViewpager;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.liear_left)
    LinearLayout liearLeft;
    @BindView(R.id.liear_right)
    LinearLayout liearRight;
    private String leftdate;
    private String rightdate;


    private String groupid;

    private String[] titles = new String[]{"商品", "机器"};
    private List<Fragment> list;
    private MyAdapter adapter;
    private CommodityFragment commodityFragment;
    private String device;
    private String status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tj_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        leftdate = getIntent().getStringExtra("leftdate");
        rightdate = getIntent().getStringExtra("rightdate");


        device = getIntent().getStringExtra("device");
        status = getIntent().getStringExtra("status");


        middleTitle.setTextSize(16);
        rightIcon.setImageResource(R.mipmap.all);


        //展示数据
        getData();
        commodityFragment = new CommodityFragment();
        //页面，数据源
        list = new ArrayList<>();
        list.add(commodityFragment);
        list.add(new MachiceFragment());
//        list.add(BlankFragment.newInstance("dd"));
        //ViewPager的适配器
        adapter = new MyAdapter(getSupportFragmentManager());
        mangerViewpager.setAdapter(adapter);
        //绑定
        mangerTablayout.setupWithViewPager(mangerViewpager);

        SgqUtils.setIndicator(mangerTablayout, 50, 50);


    }

    private void getData() {
        groupid = SharePerenceUtil.getStringValueFromSp("groupid");
//        if (!TextUtils.isEmpty(tvAdd)) {
//            if (tvAdd.length() == 3) {
//                addr1 = "0";
//                addr2 = "0";
//                addr3 = "0";
//            } else {
//                addr1 = intent_addr1;
//                addr2 = intent_addr2;
//                addr3 = intent_addr3;
//            }
//        } else {
//            addr1 = "0";
//            addr2 = "0";
//            addr3 = "0";
//        }
//
        RequestParams params = new RequestParams();
        if (TextUtils.isEmpty(device) && TextUtils.isEmpty(status)) {
            params.put("adminGroupID", groupid);
            params.put("addr1", "0");
            params.put("addr2", "0");
            params.put("addr3", "0");
            params.put("date1",  SgqUtils.getNowDate());
            leftdate = SgqUtils.getNowDate();
            params.put("date2", SgqUtils.getNowDate());
            rightdate = SgqUtils.getNowDate();
        } else {
            params.put("adminGroupID", groupid);
            params.put("date1", leftdate);
            params.put("date2", rightdate);
            params.put("addr1", "0");
            params.put("addr2", "0");
            params.put("addr3", "0");
            params.put("devices", device);
            params.put("status", status);
        }

        middleTitle.setText(leftdate + " 至 " + rightdate);


        httpUtils.doPost(Urls.tjserach(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                TongjiBean tongjiBean = gson.fromJson(jsonObject.toString(), TongjiBean.class);

                if (TextUtils.isEmpty(status)) {
                    sumJinE.setText("金额:" + AmountUtils.changeF2Y(tongjiBean.getMap_data().getSumJinE() + "") + "元");
                    paysalecount.setText("付款商品:" + tongjiBean.getMap_data().getPaySaleCount() + "个");
                    freesalecount.setText("赠送商品:" + tongjiBean.getMap_data().getFreeSaleCount() + "个");
                    lirun.setText("净利润:" + AmountUtils.changeF2Y(tongjiBean.getMap_data().getLiRun1() + "") + "元");
                } else {
                    if (status.equals("4")) {
                        sumJinE.setText("成功金额:" + AmountUtils.changeF2Y(tongjiBean.getMap_data().getSumJinE() + "") + "元");
                        paysalecount.setText("付款商品:" + tongjiBean.getMap_data().getPaySaleCount() + "个");
                        freesalecount.setText("赠送商品:" + tongjiBean.getMap_data().getFreeSaleCount() + "个");
                        lirun.setText("净利润:" + AmountUtils.changeF2Y(tongjiBean.getMap_data().getLiRun1() + "") + "元");
                    } else {
                        sumJinE.setText("失败金额:" + AmountUtils.changeF2Y(tongjiBean.getMap_data().getSumJinE() + "") + "元");
                        paysalecount.setText("退款商品:" + tongjiBean.getMap_data().getPaySaleCount() + "个");
                        freesalecount.setText("赠送商品:" + tongjiBean.getMap_data().getFreeSaleCount() + "个");
                        lirun.setText("总成本:" + AmountUtils.changeF2Y(tongjiBean.getMap_data().getPayCost() + "") + "元");
                    }
                }


                freecost.setText("赠送成本:" + AmountUtils.changeF2Y(tongjiBean.getMap_data().getFreeCost() + "") + "元");


                List<TongjiBean.GoodsListBean> goods_list = tongjiBean.getGoods_list();
                List<TongjiBean.MachineListBean> machine_list = tongjiBean.getMachine_list();

                String stj = jsonObject.toString();
                SharePerenceUtil.putStringValuetoSp("stj", stj);

            }
        });
    }

    @OnClick({R.id.liear_left, R.id.liear_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.liear_left:
                finish();
                break;
            case R.id.liear_right:
                startActivity(new Intent(MachineSerachActivity.this, MachineTjActivity.class));
                finish();
                break;
        }
    }


    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        //重写这个方法，将设置每个Tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }
}
