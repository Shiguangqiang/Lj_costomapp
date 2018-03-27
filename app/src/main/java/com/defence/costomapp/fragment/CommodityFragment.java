package com.defence.costomapp.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.defence.costomapp.base.BaseFragment;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.TongjiBean;
import com.defence.costomapp.myinterface.RVItemClickListener;
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
import butterknife.Unbinder;


/**
 * Created by author Sgq
 * on 2018/3/7.
 * 商品
 */


public class CommodityFragment extends BaseFragment {

    @BindView(R.id.list_shop)
    ListView listShop;
    Unbinder unbinder;
    private List<TongjiBean.GoodsListBean> goods_list;
    private String leftdate;
    private String rightdate;
    private String groupid;
    private String device;
    private String status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, null);
        unbinder = ButterKnife.bind(this, view);

        initdata();
        return view;
    }


    private void initdata() {
        getData();
    }

    private void getData() {

        groupid = SharePerenceUtil.getStringValueFromSp("groupid");
        leftdate = getActivity().getIntent().getStringExtra("leftdate");
        rightdate = getActivity().getIntent().getStringExtra("rightdate");
        device = getActivity().getIntent().getStringExtra("device");
        status = getActivity().getIntent().getStringExtra("status");
        RequestParams params = new RequestParams();

        if (TextUtils.isEmpty(device) && TextUtils.isEmpty(status)) {
            params.put("adminGroupID", groupid);
            params.put("addr1", "0");
            params.put("addr2", "0");
            params.put("addr3", "0");
            params.put("date1", "2018-01-01");
            params.put("date2", SgqUtils.getNowDate());
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
        httpUtils.doPost(Urls.tjserach(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                TongjiBean tongjiBean = gson.fromJson(jsonObject.toString(), TongjiBean.class);
                goods_list = tongjiBean.getGoods_list();

                listShop.setAdapter(new ShopAdapter(getActivity(), goods_list, new RVItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), ShopDetailActivity.class);
                        intent.putExtra("formatID", goods_list.get(position).getFormatID() + "");
                        intent.putExtra("formatText", goods_list.get(position).getDescVal());
                        intent.putExtra("date1", leftdate);
                        intent.putExtra("date2", rightdate);
                        startActivity(intent);

                    }
                }));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class ShopAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        List<TongjiBean.GoodsListBean> goods_list;
        private RVItemClickListener rvItemClickListener;

        public ShopAdapter(Context context, List<TongjiBean.GoodsListBean> goods_list, RVItemClickListener rvItemClickListener) {
            super();
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.goods_list = goods_list;
            this.rvItemClickListener = rvItemClickListener;

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if (goods_list != null && goods_list.size() > 0) {
                return goods_list.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return goods_list.get(arg0);
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
                view = inflater.inflate(R.layout.item_shop, null);
            }
            TextView tv_name = view.findViewById(R.id.tv_name);
            TextView tv_showshop = view.findViewById(R.id.tv_showshop);
            TextView tv_num = view.findViewById(R.id.tv_num);
            tv_name.setText(goods_list.get(position).getDescVal());
            tv_showshop.setText(String.valueOf(goods_list.get(position).getFormatID()));
            tv_num.setText(goods_list.get(position).getSaleCount() + "个");

            LinearLayout item_liearcom = view.findViewById(R.id.item_liearcom);

            item_liearcom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rvItemClickListener.onItemClick(position);
                }
            });


            return view;

        }


    }


}
