package com.defence.costomapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.bean.TongjiBean;
import com.defence.costomapp.myinterface.RVItemClickListener;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/4/17.
 */

public class ShopAdapter extends BaseAdapter {

    List<TongjiBean.GoodsListBean> goods_list;
    private Context context;
    private RVItemClickListener rvItemClickListener;

    public ShopAdapter(Context context, List<TongjiBean.GoodsListBean> goods_list, RVItemClickListener rvItemClickListener) {
        super();
        this.context = context;
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
            view = View.inflate(context, R.layout.item_shop, null);
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