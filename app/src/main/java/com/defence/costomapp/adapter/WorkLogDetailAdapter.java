package com.defence.costomapp.adapter;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.activity.WorkLogInfoActivity;
import com.defence.costomapp.bean.WorkLogInfoBean;
import com.defence.costomapp.utils.httputils.HttpInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by author Sgq
 * on 2018/3/9.
 */

public class WorkLogDetailAdapter extends BaseExpandableListAdapter {
    //总list
    private List<WorkLogInfoBean.ResultBean.DataListBean> dataListBeans;

    private Context context;

    public WorkLogDetailAdapter(Context context, List<WorkLogInfoBean.ResultBean.DataListBean> dataListBeans) {
        this.context = context;
        this.dataListBeans = dataListBeans;
    }

    @Override
    public int getGroupCount() {
        return dataListBeans.size();
    }

    /**
     * 这个返回的一定要是对应外层的item里面的List集合的size
     *
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {

        return dataListBeans.get(groupPosition).getWork_log().size();
    }

    @Override
    public WorkLogInfoBean.ResultBean.DataListBean.MachineBean getGroup(int groupPosition) {
        return dataListBeans.get(groupPosition).getMachine();
    }

    @Override
    public WorkLogInfoBean.ResultBean.DataListBean.WorkLogBean getChild(int groupPosition, int childPosition) {
        return dataListBeans.get(groupPosition).getWork_log().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.item_logaddress, null);


        TextView tv_address = (TextView) convertView.findViewById(R.id.tv_address);
        TextView tv_huodao = (TextView) convertView.findViewById(R.id.tv_huodao);
        TextView tv_numberlog = (TextView) convertView.findViewById(R.id.tv_numberlog);
        tv_huodao.setText("货道号及商品名称");
        tv_numberlog.setText("当日补货总量");
        tv_address.setText(dataListBeans.get(groupPosition).getMachine().getDetailedinstalladdress());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.item_loglist, null);
        TextView tv_huodao = (TextView) view.findViewById(R.id.tv_huodao);
        TextView tv_numbuhuo = (TextView) view.findViewById(R.id.tv_numbuhuo);


        if (childPosition == 0) {
            tv_huodao.setText("货道号及商品名称");
            tv_numbuhuo.setText("当日补货总量");
        } else {
            if (childPosition > 0) {
                tv_huodao.setText(childPosition + "." + dataListBeans.get(groupPosition).getWork_log().get(childPosition - 1).getGoods_map().getShowName());
                tv_numbuhuo.setText(dataListBeans.get(groupPosition).getWork_log().get(childPosition - 1).getShelvesnum()+"");
            }
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}


