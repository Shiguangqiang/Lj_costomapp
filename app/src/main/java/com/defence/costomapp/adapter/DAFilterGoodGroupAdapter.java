package com.defence.costomapp.adapter;


import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

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


public class DAFilterGoodGroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    String string;
    private List<DataAnGoodsFilterBean.ShangpinListBean> mList;
    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();
    private boolean mIsSelectable = false;


    public DAFilterGoodGroupAdapter(List<DataAnGoodsFilterBean.ShangpinListBean> list, String type) {
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

    //获得选中条目的结果
    public ArrayList<String> getSelectedItem() {
        ArrayList<String> selectList = new ArrayList<>();
        ArrayList<String> selectListstring = new ArrayList<>();
        ArrayList<String> selectshopsting = new ArrayList<>();

        if (string.equals("shopgg")) {
            for (int i = 0; i < mList.size(); i++) {
                if (isItemChecked(i)) {
                    selectList.add(mList.get(i).getId() + "");
                    selectshopsting.add(mList.get(i).getShowName());
                }
            }
            SpUtil.putList(MyApplication.getApp(), "checkshopgg", selectList);
            SpUtil.putList(MyApplication.getApp(), "filtershopstringgg", selectshopsting);
        }

        return selectList;
    }

    //根据位置判断条目是否选中
    private boolean isItemChecked(int position) {
        return mSelectedPositions.get(position);
    }

    //设置给定位置条目的选择状态

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
        return new DAFilterGoodGroupAdapter.ListItemViewHolder(itemView);
    }

    //绑定界面，设置监听
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int i) {


        List<Serializable> checkshopgg = SpUtil.getList(MyApplication.getApp(), "checkshopgg");


        if (string.equals("shopgg")) {
            ((ListItemViewHolder) holder).mainTitle.setText(mList.get(i).getShowName() + mList.get(i).getDescVal());
            if (checkshopgg != null && checkshopgg.size() > 0) {
                for (int j = 0; j < checkshopgg.size(); j++) {
                    if (checkshopgg.get(j).equals(mList.get(i).getShowName())) {
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
                if (isItemChecked(i)) {
                    setItemChecked(i, false);
                } else {
                    setItemChecked(i, true);
                }


            }
        });

        //条目view的监听
        ((ListItemViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isItemChecked(i)) {
                    setItemChecked(i, false);
                } else {
                    setItemChecked(i, true);
                }
                notifyItemChanged(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private void setItemChecked(int position, boolean isChecked) {
        mSelectedPositions.put(position, isChecked);
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