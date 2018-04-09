package com.defence.costomapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.bean.LastBean;
import com.defence.costomapp.utils.AmountUtils;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/4/8.
 */

public class LastAdapter extends RecyclerView.Adapter<LastAdapter.LastViewholder> {
    private Context context;
    private List<LastBean.ListBean> list;

    public LastAdapter(Context context, List<LastBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public LastAdapter.LastViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_last_info, parent, false);
        return new LastAdapter.LastViewholder(v);
    }

    @Override
    public void onBindViewHolder(LastViewholder holder, int position) {
        holder.tv_lastaddress.setText(list.get(position).getDetailedinstalladdress());
        holder.tv_lasttime.setText(list.get(position).getOrderTimeline());
        holder.tv_price.setTextColor(Color.rgb(249, 6, 6));
        holder.tv_price.setText("￥" + AmountUtils.changeF2Y(list.get(position).getPayVal() + ""));
        holder.tv_show.setText(list.get(position).getDescVal());
        holder.tv_lastmachineconnect.setText(list.get(position).getShangchuantime());

    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class LastViewholder extends RecyclerView.ViewHolder {
        private TextView tv_lastaddress, tv_lasttime, tv_price, tv_show, tv_lastmachineconnect;

        public LastViewholder(View itemView) {
            super(itemView);
            tv_lastaddress = itemView.findViewById(R.id.tv_lastaddress);
            tv_lasttime = itemView.findViewById(R.id.tv_lasttime);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_show = itemView.findViewById(R.id.tv_show);
            tv_lastmachineconnect = itemView.findViewById(R.id.tv_lastmachineconnect);

        }
    }
}
