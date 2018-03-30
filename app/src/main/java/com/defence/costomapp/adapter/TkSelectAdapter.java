package com.defence.costomapp.adapter;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.bean.TuikuanMachineBean;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SpUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/21.
 */

public class TkSelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TuikuanMachineBean.ListBean> mList;

    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();

    private boolean mIsSelectable = false;


    String string;

    public TkSelectAdapter(List<TuikuanMachineBean.ListBean> list, String type) {
        if (list == null) {
            throw new IllegalArgumentException("model Data must not be null");
        }
        mList = list;
        string = type;
    }

    //更新adpter的数据和选择状态
    public void updateDataSet(List<TuikuanMachineBean.ListBean> list) {
        this.mList = list;
        mSelectedPositions = new SparseBooleanArray();

    }

    //获得选中条目的结果
    public ArrayList<String> getSelectedItem() {
        ArrayList<String> selectList = new ArrayList<>();
        ArrayList<String> selectListstring = new ArrayList<>();
        ArrayList<String> selectshopsting = new ArrayList<>();

        if (string.equals("shop")) {

            for (int i = 0; i < mList.size(); i++) {
                if (isItemChecked(i)) {
                    selectList.add(mList.get(i).getGuigeid());
                    selectshopsting.add(mList.get(i).getDs());
                }
            }
            SpUtil.putList(MyApplication.getApp(), "checkshop", selectList);
            SpUtil.putList(MyApplication.getApp(), "checkshopstring", selectshopsting);
        } else {
            for (int i = 0; i < mList.size(); i++) {
                if (isItemChecked(i)) {
                    selectList.add(mList.get(i).getMachinenumber());
                    selectListstring.add(mList.get(i).getDetailedinstalladdress());
                }
            }
            SpUtil.putList(MyApplication.getApp(), "checkhis", selectList);
            SpUtil.putList(MyApplication.getApp(), "checkhisstring", selectListstring);
        }

        return selectList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
        return new TkSelectAdapter.ListItemViewHolder(itemView);
    }

    //设置给定位置条目的选择状态

    private void setItemChecked(int position, boolean isChecked) {
        mSelectedPositions.put(position, isChecked);
    }

    //根据位置判断条目是否选中
    private boolean isItemChecked(int position) {
        return mSelectedPositions.get(position);
    }

    //根据位置判断条目是否可选
    private boolean isSelectable() {
        return mIsSelectable;
    }

    //设置给定位置条目的可选与否的状态
    private void setSelectable(boolean selectable) {
        mIsSelectable = selectable;
    }

    //绑定界面，设置监听
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int i) {


        List<Serializable> checkhis = SpUtil.getList(MyApplication.getApp(), "checkhis");
        List<Serializable> checkshop = SpUtil.getList(MyApplication.getApp(), "checkshop");



        //设置条目状态
        if (string.equals("machine")) {
            ((ListItemViewHolder) holder).mainTitle.setText(mList.get(i).getDetailedinstalladdress());
            if (checkhis != null && checkhis.size() > 0) {
                for (int j = 0; j < checkhis.size(); j++) {
                    if (checkhis.get(j).equals(mList.get(i).getMachinenumber())) {
                        setItemChecked(i, true);
                    }
                }
            }

        } else if (string.equals("shop")) {
            ((ListItemViewHolder) holder).mainTitle.setText(mList.get(i).getDs());
            if (checkshop != null && checkshop.size() > 0) {
                for (int j = 0; j < checkshop.size(); j++) {
                    if (checkshop.get(j).equals(mList.get(i).getGuigeid())) {
                        setItemChecked(i, true);
                    }
                }
            }
        }

//        } else if (string.equals("group")) {
//            List<TuikuanMachineBean.ListBean> l = new ArrayList();
//            if (mList != null && mList.size() > 0) {
//                for (int j = 0; j < mList.size(); j++) {
//                    if (mList.get(j).getPrentid() == 0) {
//                        l.add(mList.get(j));
//
//                    }
//                }
//            }
//            if (checkgroup != null && checkgroup.size() > 0) {
//                for (int m = 0; m < l.size(); m++) {
//                    for (int z = 0; z < checkgroup.size(); z++) {
//                        if (checkgroup.get(z).equals(l.get(m).getName())) {
//                            setItemChecked(i, true);
//                        }
//                    }
//
//                }
//            }

//        }

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