package com.defence.costomapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.bean.BuhuoInfoEntity;
import com.defence.costomapp.bean.CeGuiGoodsBean;
import com.defence.costomapp.myinterface.RVItemClickListener;

import java.util.Comparator;
import java.util.List;

/**
 * Created by fangfafengfu on 2017/12/6.
 */

public class BuhuoCeguiGoodsAdapter extends RecyclerView.Adapter<BuhuoCeguiGoodsAdapter.BuhuoInfoViewholder> {
    private List<CeGuiGoodsBean.ListBean> buhuoInfoEntities;
    private Context context;
    private RVItemClickListener rvItemClickListener;


    public BuhuoCeguiGoodsAdapter(List<CeGuiGoodsBean.ListBean> buhuoInfoEntities, Context context, RVItemClickListener rvItemClickListener) {
        this.buhuoInfoEntities = buhuoInfoEntities;
        this.context = context;
        this.rvItemClickListener = rvItemClickListener;

    }

    @Override
    public BuhuoInfoViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_buhuocegui_info, parent, false);
        return new BuhuoInfoViewholder(v);
    }


    @Override
    public void onBindViewHolder(BuhuoInfoViewholder holder, final int position) {
        CeGuiGoodsBean.ListBean buhuoInfoEntity = buhuoInfoEntities.get(position);

        holder.huodao.setText(buhuoInfoEntity.getShangpin_name());
        holder.cunliang.setText(buhuoInfoEntity.getKu_cun()+"");

        holder.infoLL.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                rvItemClickListener.onItemClick(position);
                return false;
            }
        });
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
            infoLL = itemView.findViewById(R.id.buhuoinfoll);
        }
    }
}
