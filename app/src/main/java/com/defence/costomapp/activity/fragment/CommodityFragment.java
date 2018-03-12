package com.defence.costomapp.activity.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseFragment;
import com.defence.costomapp.bean.TongjiBean;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.google.gson.Gson;

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
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, null);

        unbinder = ButterKnife.bind(this, view);
        initdata();
        return view;
    }


    private void initdata() {
        if (getArguments() != null) {
            List<TongjiBean.GoodsListBean> goods_list = (List<TongjiBean.GoodsListBean>) getArguments().getSerializable("goods");
        }

        String stj = SharePerenceUtil.getStringValueFromSp("stj");
        TongjiBean tongjiBean = new Gson().fromJson(stj, TongjiBean.class);
        List<TongjiBean.GoodsListBean> goods_list = tongjiBean.getGoods_list();

        listShop.setAdapter(new ShopAdapter(getActivity(),goods_list));


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private  class ShopAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        List<TongjiBean.GoodsListBean> goods_list;

        public ShopAdapter(Context context, List<TongjiBean.GoodsListBean> goods_list) {
            super();
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.goods_list = goods_list;


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
            TextView tv_show = view.findViewById(R.id.tv_show);
            TextView tv_num = view.findViewById(R.id.tv_num);
            tv_name.setText(goods_list.get(position).getDescVal());
//            tv_show.setText(goods_list.get(position).getFormatID());
            tv_num.setText(goods_list.get(position).getSaleCount()+"个");


            return view;

        }


    }


}
