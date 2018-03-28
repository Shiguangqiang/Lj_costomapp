package com.defence.costomapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.bean.UserTjBean;
import com.defence.costomapp.myinterface.RVItemClickListener;
import com.defence.costomapp.utils.AmountUtils;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/21.
 */

public class UserTjAdapter extends RecyclerView.Adapter<UserTjAdapter.ViewHolder> {
    private Context context;
    private List<UserTjBean.ListBean> userList;
    private RVItemClickListener rvItemClickListener;
    private int type;

    public UserTjAdapter(Context context, List<UserTjBean.ListBean> userList, RVItemClickListener rvItemClickListener, int type) {
        this.context = context;
        this.userList = userList;
        this.rvItemClickListener = rvItemClickListener;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_usertj, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        UserTjBean.ListBean entity = userList.get(position);
        switch (getType()) {
            case 1:
                holder.name.setText(entity.getMphone() + "");
                holder.content.setText("账户余额:" + AmountUtils.changeF2Y(entity.getBankNo() + "") + "元");
                holder.time.setText("注册时间:" + entity.getTimeline() + "");
                break;
            case 2:
                holder.name.setText(entity.getMphone() + "");
                holder.content.setText("账户余额:" + AmountUtils.changeF2Y(entity.getBankNo() + "") + "元");
                holder.time.setText("注册时间:" + entity.getTimeline() + "");
                break;
            case 3:
                holder.name.setText(entity.getMphone() + "");
                holder.content.setText("账户余额:" + AmountUtils.changeF2Y(entity.getBankNo() + "") + "元");
                holder.time.setText("注册时间:" + entity.getReg_time() + "");
                break;
            case 4:
                holder.name.setText(entity.getWx() + "");
                // holder.content.setText(AmountUtils.changeF2Y(entity.getBankNo() + "") + "元");
                holder.time.setText("总消费:" + AmountUtils.changeF2Y(entity.getPv() + "") + "元");
                break;
            case 5:
                holder.name.setText(entity.getMphone() + "");
                holder.cz.setVisibility(View.VISIBLE);
                holder.cz.setText("+" + AmountUtils.changeF2Y(entity.getHm() + ""));
                holder.content.setText("账户余额" + AmountUtils.changeF2Y(entity.getBankNo() + "") + "元");
                holder.time.setText(entity.getTimeline() + "");
                break;
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvItemClickListener.onItemClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, content, time, cz;
        private LinearLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            content = itemView.findViewById(R.id.content);
            time = itemView.findViewById(R.id.time);
            cz = itemView.findViewById(R.id.usertj_cz);
            layout = itemView.findViewById(R.id.liearitemll);
        }
    }
}