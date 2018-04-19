package com.defence.costomapp.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.activity.statistics.ShopDetailActivity;
import com.defence.costomapp.adapter.ShopAdapter;
import com.defence.costomapp.base.BaseFragment;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.TongjiBean;
import com.defence.costomapp.myinterface.RVItemClickListener;
import com.defence.costomapp.utils.MyEvent;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by author Sgq
 * on 2018/3/7.
 * 商品
 */


public class CommodityFragment extends BaseFragment {

    private List<TongjiBean.GoodsListBean> goods_list;
    private String leftdate = SgqUtils.getNowDate();
    private String rightdate = SgqUtils.getNowDate();
    private String groupid;
    private String device;
    private String status;
    private ShopAdapter shopAdapter;
    private ListView listShop;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, null);
        EventBus.getDefault().register(this);
        listShop = view.findViewById(R.id.list_shop);
        initdata();
        return view;
    }

    private void initdata() {
        leftdate = getActivity().getIntent().getStringExtra("leftdate");
        rightdate = getActivity().getIntent().getStringExtra("rightdate");
        device = getActivity().getIntent().getStringExtra("device");
        status = getActivity().getIntent().getStringExtra("status");
        getData();
    }

    private void getData() {
        groupid = SharePerenceUtil.getStringValueFromSp("groupid");
        RequestParams params = new RequestParams();
        params.put("adminGroupID", groupid);
        params.put("date1", leftdate);
        params.put("date2", rightdate);
        params.put("addr1", "0");
        params.put("addr2", "0");
        params.put("addr3", "0");
        params.put("devices", device);
        params.put("status", status);

//        if (TextUtils.isEmpty(device) && TextUtils.isEmpty(status)) {
//            params.put("adminGroupID", groupid);
//            params.put("addr1", "0");
//            params.put("addr2", "0");
//            params.put("addr3", "0");
//            params.put("date1", SgqUtils.getNowDate());
//            leftdate = SgqUtils.getNowDate();
//            params.put("date2", SgqUtils.getNowDate());
//            rightdate = SgqUtils.getNowDate();
//        } else {
//            params.put("adminGroupID", groupid);
//            params.put("date1", leftdate);
//            params.put("date2", rightdate);
//            params.put("addr1", "0");
//            params.put("addr2", "0");
//            params.put("addr3", "0");
//            params.put("devices", device);
//            params.put("status", status);
//
//        }
        httpUtils.doPost(Urls.tjserach(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                TongjiBean tongjiBean = gson.fromJson(jsonObject.toString(), TongjiBean.class);
                goods_list = tongjiBean.getGoods_list();


                shopAdapter = new ShopAdapter(getActivity(), goods_list, new RVItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), ShopDetailActivity.class);
                        intent.putExtra("formatID", goods_list.get(position).getFormatID() + "");
                        intent.putExtra("formatText", goods_list.get(position).getDescVal());
                        intent.putExtra("date1", leftdate);
                        intent.putExtra("date2", rightdate);
                        intent.putExtra("device", device);
                        intent.putExtra("status", status);
                        startActivity(intent);

                    }
                });
                listShop.setAdapter(shopAdapter);
            }
        });
    }


    /**
     * 这里用到的了EventBus框架
     *
     * @param data
     */
    @Subscribe
    public void onEvent(MyEvent data) {
        if (66 == data.getEventType()) {
            leftdate = data.getDatebefore();
            rightdate = data.getDateafter();
            device = data.getDevice();
            status = data.getStatus();
            getData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);//取消注册
    }


}
