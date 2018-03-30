package com.defence.costomapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.bean.TuikuanMachineBean;
import com.defence.costomapp.utils.SpUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/30.
 */

public class SelectGroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<TuikuanMachineBean.ListBean> listgroups;
    ArrayList<String> listnames;
    ArrayList<String> listgroupids;
    Context contexts;

    private boolean mIsSelectable = false;

    private List<Integer> positions = new ArrayList<>();

    private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();

    public SelectGroupAdapter(Context context, List<TuikuanMachineBean.ListBean> listgroup, ArrayList<String> listgroupname, ArrayList<String> listgroupid) {
        contexts = context;
        listgroups = listgroup;
        listnames = listgroupname;
        listgroupids = listgroupid;

    }

    //更新adpter的数据和选择状态
    public void updateDataSet(List<TuikuanMachineBean.ListBean> listgroup) {
        listgroups = listgroup;
        mSelectedPositions = new SparseBooleanArray();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ListItemViewHolder(itemView);
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

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int i) {


        List<Serializable> checkgroup = SpUtil.getList(MyApplication.getApp(), "checkgroupString");
        if (checkgroup != null && checkgroup.size() > 0) {
            for (int j = 0; j < checkgroup.size(); j++) {
                if (checkgroup.get(j).equals(listnames.get(i))) {
                    setItemChecked(i, true);
                }
            }
        }


        ((ListItemViewHolder) holder).mainTitle.setText(listnames.get(i));

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
        if (listnames != null && listnames.size() > 0) {
            return listnames.size();
        }
        return 0;
    }

    //获得选中条目的结果
    public ArrayList<String> getSelectedItem() {
        ArrayList<String> selectList = new ArrayList<>();

        if (listgroups != null && listgroups.size() > 0) {
            for (int i = 0; i < listgroups.size(); i++) {
                for (int j = 0; j < listgroupids.size(); j++) {
                    if (isItemChecked(j)) {
                        if (listgroupids.get(j).equals(listgroups.get(i).getPrentid())) {
                            selectList.add(listgroups.get(i).getMachinenumber());
                        }
                    }
                }
            }
        }

        ArrayList<String> selectgroupList = new ArrayList<>();
        for (int z = 0; z < listnames.size(); z++) {
            if (isItemChecked(z)) {
                selectgroupList.add(listnames.get(z));
            }
        }
        SpUtil.putList(MyApplication.getApp(), "checkgroupString", selectgroupList);

        return selectList;
    }

    public List<Integer> getPositions() {
        return positions;
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
