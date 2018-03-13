package com.defence.costomapp.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.activity.fragment.CommodityFragment;
import com.defence.costomapp.activity.fragment.MachiceFragment;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.TongjiBean;
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

public class TjDetailActivity extends BaseActivity {

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
    private String leftdate;
    private String rightdate;
    private String tvAdd;
    String addr1, addr2, addr3;
    private String intent_addr1;
    private String intent_addr2;
    private String intent_addr3;
    private String groupid;

    private String[] titles = new String[]{"商品", "机器"};
    private List<Fragment> list;
    private MyAdapter adapter;
    private CommodityFragment commodityFragment;


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
        tvAdd = getIntent().getStringExtra("tvAdd");
        intent_addr1 = getIntent().getStringExtra("addr1");
        intent_addr2 = getIntent().getStringExtra("addr2");
        intent_addr3 = getIntent().getStringExtra("addr3");
        middleTitle.setText(leftdate + " 至 " + rightdate);
        middleTitle.setTextSize(16);


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



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void getData() {
        groupid = SharePerenceUtil.getStringValueFromSp("groupid");

        if (tvAdd.length() == 3) {
            addr1 = "0";
            addr2 = "0";
            addr3 = "0";
        } else {
            addr1 = intent_addr1;
            addr2 = intent_addr2;
            addr3 = intent_addr3;
        }

        RequestParams params = new RequestParams();
        params.put("adminGroupID", groupid);
        params.put("date1", leftdate);
        params.put("date2", rightdate);
        params.put("addr1", addr1);
        params.put("addr2", addr2);
        params.put("addr3", addr3);
        httpUtils.doPost(Urls.tjserach(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                TongjiBean tongjiBean = gson.fromJson(jsonObject.toString(), TongjiBean.class);


                sumJinE.setText("金额:" + tongjiBean.getMap_data().getSumJinE() + "元");
                paysalecount.setText("付款商品:" + tongjiBean.getMap_data().getPaySaleCount() + "个");
                freesalecount.setText("赠送商品:" + tongjiBean.getMap_data().getFreeSaleCount() + "个");
                freecost.setText("赠送成本:" + tongjiBean.getMap_data().getFreeCost() + "元");
                lirun.setText("净利润:" + tongjiBean.getMap_data().getLiRun1() + "元");

                List<TongjiBean.GoodsListBean> goods_list = tongjiBean.getGoods_list();
                List<TongjiBean.MachineListBean> machine_list = tongjiBean.getMachine_list();

                String stj = jsonObject.toString();
                SharePerenceUtil.putStringValuetoSp("stj", stj);


//                Bundle bundle = new Bundle();
//                bundle.putSerializable("goods", (Serializable) goods_list);
//                commodityFragment.setArguments(bundle);


            }
        });
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
