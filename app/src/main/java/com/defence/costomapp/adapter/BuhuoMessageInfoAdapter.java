package com.defence.costomapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.defence.costomapp.R;
import com.defence.costomapp.bean.BuhuoInfoEntity;
import com.defence.costomapp.myinterface.RVItemClickListener;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by fangfafengfu on 2017/12/6.
 */

public class BuhuoMessageInfoAdapter extends RecyclerView.Adapter<BuhuoMessageInfoAdapter.BuhuoInfoViewholder> {
    private List<BuhuoInfoEntity> buhuoInfoEntities;
    private Context context;
    private RVItemClickListener rvItemClickListener;
    private int alarmStock;

    public BuhuoMessageInfoAdapter(int alarmStock, List<BuhuoInfoEntity> buhuoInfoEntities, Context context, RVItemClickListener rvItemClickListener) {
        this.buhuoInfoEntities = buhuoInfoEntities;
        this.context = context;
        this.rvItemClickListener = rvItemClickListener;
        this.alarmStock = alarmStock;
    }

    @Override
    public BuhuoInfoViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_buhuo_info, parent, false);
        return new BuhuoInfoViewholder(v);
    }


    @Override
    public void onBindViewHolder(BuhuoInfoViewholder holder, final int position) {
        BuhuoInfoEntity buhuoInfoEntity = buhuoInfoEntities.get(position);

//        Collections.sort(buhuoInfoEntities, new order());
        holder.huodao.setText(buhuoInfoEntity.getLatticenumbers() + "-" + buhuoInfoEntity.getDescVal() + "-" + buhuoInfoEntity.getShowName());

        if (buhuoInfoEntity.getStocknumber() <= alarmStock) {
            holder.cunliang.setTextColor(Color.RED);
        } else {
            holder.cunliang.setTextColor(Color.argb(255, 51, 51, 51));
        }
        holder.cunliang.setText(buhuoInfoEntity.getStocknumber() + " / "+buhuoInfoEntity.getSavenum()+"");

        holder.buqi.setText(buhuoInfoEntity.getICount() + " / "+buhuoInfoEntity.getKu_cun());

        holder.infoLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvItemClickListener.onItemClick(position);
            }
        });
    }


    public class order implements Comparator<BuhuoInfoEntity> {

        @Override
        public int compare(BuhuoInfoEntity lhs, BuhuoInfoEntity rhs) {
            // TODO Auto-generated method stub
            return rhs.getLatticenumbers() - lhs.getLatticenumbers();
        }

    }

    @Override
    public int getItemCount() {
        return buhuoInfoEntities == null ? 0 : buhuoInfoEntities.size();
    }

    public class BuhuoInfoViewholder extends RecyclerView.ViewHolder {
        private TextView huodao, cunliang, buqi;
        private LinearLayout infoLL;

        public BuhuoInfoViewholder(View itemView) {
            super(itemView);
            huodao = itemView.findViewById(R.id.huodao);
            cunliang = itemView.findViewById(R.id.buhuoinfokucun);
            buqi = itemView.findViewById(R.id.buhuoinfobuqi);
            infoLL = itemView.findViewById(R.id.buhuoinfoll);
        }
    }
}
