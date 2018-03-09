package com.defence.costomapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.defence.costomapp.R;
import com.defence.costomapp.bean.BuhuoMessageEntity;
import com.defence.costomapp.myinterface.RVItemClickListener;

import java.util.List;

/**
 * Created by fangfafengfu on 2017/12/6.
 */

public class BuhuoMessageAdapter extends RecyclerView.Adapter<BuhuoMessageAdapter.BuhuoViewholder> {
    private List<BuhuoMessageEntity> buhuoMessageEntityList;
    private Context context;
    private RVItemClickListener rvItemClickListener;

    public BuhuoMessageAdapter(List<BuhuoMessageEntity> buhuoMessageEntityList, Context context, RVItemClickListener rvItemClickListener) {
        this.buhuoMessageEntityList = buhuoMessageEntityList;
        this.context = context;
        this.rvItemClickListener = rvItemClickListener;
    }

    @Override
    public BuhuoViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_buhuo_message, parent, false);
        return new BuhuoViewholder(v);
    }

    @Override
    public void onBindViewHolder(BuhuoViewholder holder, final int position) {
        BuhuoMessageEntity buhuoMessageEntity = buhuoMessageEntityList.get(position);


        holder.name.setText(buhuoMessageEntity.getMachinename() + "缺货补货通知       ");


        // Glide.with(context).load(buhuoMessageEntity.).
        if (buhuoMessageEntity.getNotRepairCount() != 0) {

            holder.circle.setVisibility(View.VISIBLE);
        } else {
            holder.circle.setVisibility(View.INVISIBLE);
        }
        holder.buhuoItemll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return buhuoMessageEntityList == null ? 0 : buhuoMessageEntityList.size();
    }

    public class BuhuoViewholder extends RecyclerView.ViewHolder {
        private TextView name, time;
        private ImageView icon, circle;
        private LinearLayout buhuoItemll;

        public BuhuoViewholder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.buhuomessage_item_name);
            time = itemView.findViewById(R.id.buhuomessage_item_time);
            icon = itemView.findViewById(R.id.buhuomessage_item_icon);
            circle = itemView.findViewById(R.id.buhuomessage_item_newmessage);
            buhuoItemll = itemView.findViewById(R.id.buhuoitemll);
        }
    }
}
