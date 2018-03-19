package com.defence.costomapp.activity.statistics;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.TjshopDetailBean;
import com.defence.costomapp.utils.AmountUtils;
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
 * 商品接口详情
 */
public class ShopDetailActivity extends BaseActivity {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.sumJinE)
    TextView sumJinE;
    @BindView(R.id.sumLiRun)
    TextView sumLiRun;
    @BindView(R.id.saleCount)
    TextView saleCount;
    @BindView(R.id.list_shopdetail)
    ListView listShopdetail;
    private String formatID;
    private String formatText;
    private String date1;
    private String date2;
    private String addr1;
    private String addr2;
    private String addr3;
    private String groupid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);
        initdata();

    }

    private void initdata() {

        formatID = getIntent().getStringExtra("formatID");
        formatText = getIntent().getStringExtra("formatText");
        date1 = getIntent().getStringExtra("date1");
        date2 = getIntent().getStringExtra("date2");

        addr1 = getIntent().getStringExtra("addr1");
        addr2 = getIntent().getStringExtra("addr2");
        addr3 = getIntent().getStringExtra("addr3");

        middleTitle.setText(formatText + "销售占比");
        getData();
    }

    //商品详情
    private void getData() {

        groupid = SharePerenceUtil.getStringValueFromSp("groupid");
        RequestParams params = new RequestParams();
        params.put("adminGroupID", groupid);
        params.put("formatID", formatID);
        params.put("formatText", formatText);
        params.put("date1", date1);
        params.put("date2", date2);
        params.put("addr1", addr1);
        params.put("addr2", addr2);
        params.put("addr3", addr3);
        httpUtils.doPost(Urls.tjserach_shop(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                TjshopDetailBean tjshopDetailBean = gson.fromJson(jsonObject.toString(), TjshopDetailBean.class);
                sumJinE.setText("总额:"+ AmountUtils.changeF2Y(tjshopDetailBean.getMap_data().getSumJinE()+"")+"元");
                sumLiRun.setText("总利润:"+ AmountUtils.changeF2Y(tjshopDetailBean.getMap_data().getSumLiRun()+"")+"元");
                saleCount.setText("总件:"+tjshopDetailBean.getMap_data().getSaleCount()+"个");
                listShopdetail.setAdapter(new ShopDetailAdapter(ShopDetailActivity.this,tjshopDetailBean.getList()));


            }
        });
    }

    private class ShopDetailAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        List<TjshopDetailBean.ListBean> listBeanList;

        public ShopDetailAdapter(Context context, List<TjshopDetailBean.ListBean> listBeanList) {
            super();
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.listBeanList = listBeanList;


        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if (listBeanList != null && listBeanList.size() > 0) {
                return listBeanList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return listBeanList.get(arg0);
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
                view = inflater.inflate(R.layout.item_tjmachine, null);
            }
            TextView tv_name = view.findViewById(R.id.tv_name);
            TextView tv_showshop = view.findViewById(R.id.tv_showshop);
            TextView tv_num = view.findViewById(R.id.tv_num);
            tv_name.setText(listBeanList.get(position).getDetailedinstalladdress());
            tv_showshop.setText(listBeanList.get(position).getMachinenumber());
            tv_num.setText(listBeanList.get(position).getSaleCount() + "个");


            return view;

        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

}
