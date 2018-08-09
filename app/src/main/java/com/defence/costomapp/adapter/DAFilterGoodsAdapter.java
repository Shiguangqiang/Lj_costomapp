package com.defence.costomapp.adapter;


import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.defence.costomapp.R;
import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.bean.DataAnGoodsFilterBean;
import com.defence.costomapp.utils.SpUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/21.
 */


public class DAFilterGoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    String string;
    private List<DataAnGoodsFilterBean.ShangpinListBean> mList;
    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();
    private boolean mIsSelectable = false;


    public DAFilterGoodsAdapter(List<DataAnGoodsFilterBean.ShangpinListBean> list, String type) {
        if (list == null) {
            throw new IllegalArgumentException("model Data must not be null");
        }
        mList = list;
        string = type;
    }

    //更新adpter的数据和选择状态
    public void updateDataSet(List<DataAnGoodsFilterBean.ShangpinListBean> list) {
        this.mList = list;
        mSelectedPositions = new SparseBooleanArray();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
        return new DAFilterGoodsAdapter.ListItemViewHolder(itemView);
    }

    //绑定界面，设置监听
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int i) {

        List<Serializable> checkshop = SpUtil.getList(MyApplication.getApp(), "checkshop");

        if (string.equals("shop")) {
            ((ListItemViewHolder) holder).mainTitle.setText(mList.get(i).getShang_pin_full_name());
            if (checkshop != null && checkshop.size() > 0) {
                for (int j = 0; j < checkshop.size(); j++) {
                    if (checkshop.get(j).equals(mList.get(i).getShang_pin_full_name())) {
                        setItemChecked(i, true);
                    }
                }
            }
        }

        ((ListItemViewHolder) holder).checkBox.setChecked(isItemChecked(i));
        //checkBox的监听
        ((ListItemViewHolder) holder).checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkBox的监听
                if (isItemChecked(i)) {
                    setItemChecked(i, false);
                } else {
                    if (getSelectedItem().size() > 0) {
                        Toast.makeText(MyApplication.getAppContext(), "商品最多只能选择一种!", Toast.LENGTH_SHORT).show();
                    } else {
                        setItemChecked(i, true);
                    }
                }
                notifyItemChanged(i);
            }
        });

        //条目view的监听
        ((ListItemViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkBox的监听
                if (isItemChecked(i)) {
                    setItemChecked(i, false);
                } else {
                    if (getSelectedItem().size() > 0) {
                        Toast.makeText(MyApplication.getAppContext(), "商品最多只能选择一种!", Toast.LENGTH_SHORT).show();
                    } else {
                        setItemChecked(i, true);
                    }
                }
                notifyItemChanged(i);
            }
        });
    }

    //设置给定位置条目的选择状态

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private void setItemChecked(int position, boolean isChecked) {
        mSelectedPositions.put(position, isChecked);
    }

    //根据位置判断条目是否选中
    private boolean isItemChecked(int position) {
        return mSelectedPositions.get(position);
    }

    //获得选中条目的结果
    public ArrayList<String> getSelectedItem() {
        ArrayList<String> selectList = new ArrayList<>();
        ArrayList<String> selectListstring = new ArrayList<>();
        ArrayList<String> selectshopsting = new ArrayList<>();

        if (string.equals("shop")) {

            for (int i = 0; i < mList.size(); i++) {
                if (isItemChecked(i)) {
                    selectList.add(mList.get(i).getCommodityspecificationsid() + "");
                    selectshopsting.add(mList.get(i).getShang_pin_full_name());
                }
            }
            SpUtil.putList(MyApplication.getApp(), "checkshop", selectList);
            SpUtil.putList(MyApplication.getApp(), "filtershopstring", selectshopsting);

        }
        return selectList;
    }

    //根据位置判断条目是否可选
    private boolean isSelectable() {
        return mIsSelectable;
    }

    //设置给定位置条目的可选与否的状态
    private void setSelectable(boolean selectable) {
        mIsSelectable = selectable;
    }


    public class ListItemViewHolder extends RecyclerView.ViewHolder {
        //ViewHolder
        CheckBox checkBox;
        TextView mainTitle;

        ListItemViewHolder(View view) {
            super(view);
            this.mainTitle = view.findViewById(R.id.text);
            this.checkBox = view.findViewById(R.id.select_checkbox);

        }
    }
}