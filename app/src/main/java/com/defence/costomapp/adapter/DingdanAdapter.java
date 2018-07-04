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
import com.defence.costomapp.bean.DingdanBean;
import com.defence.costomapp.myinterface.RVItemClickListener;
import com.defence.costomapp.utils.AmountUtils;
import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/23.
 */

public class DingdanAdapter extends RecyclerView.Adapter<DingdanAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<DingdanBean.ListBean> list;
    private RVItemClickListener rvItemClickListener;
    private int type;
    private String payType;
    private String ccccustome;

    public DingdanAdapter(Context context, List<DingdanBean.ListBean> list, RVItemClickListener rvItemClickListener, int type) {
        super();
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_dingdan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        DingdanBean.ListBean entity = list.get(position);
        switch (getType()) {
            case 4:
                holder.tv_show.setText(list.get(position).getDescVal());
                holder.tv_time.setText(list.get(position).getOrderTimeline());
                holder.tv_money.setText(AmountUtils.changeF2Y(list.get(position).getPayVal() + ""));
                holder.tv_dannum.setText(list.get(position).getWxTransactionID());
                holder.tv_state.setText("交易成功");
                holder.tv_address.setText(list.get(position).getMachine_data().getDetailedinstalladdress() + "-" + list.get(position).getItemNo());
                holder.tv_state.setTextColor(Color.rgb(26, 233, 50));
                holder.tv_orderFormstate.setTextColor(Color.rgb(26, 233, 50));
                break;
            case 3:
                holder.tv_show.setText(list.get(position).getDescVal());
                holder.tv_time.setText(list.get(position).getOrderTimeline());
                holder.tv_money.setText(AmountUtils.changeF2Y(list.get(position).getPayVal() + ""));
                holder.tv_dannum.setText(list.get(position).getWxTransactionID());
                holder.tv_state.setText("待出货");
                holder.tv_address.setText(list.get(position).getMachine_data().getDetailedinstalladdress() + "-" + list.get(position).getItemNo());
                holder.tv_state.setTextColor(Color.rgb(255, 51, 0));
                holder.tv_orderFormstate.setTextColor(Color.rgb(255, 51, 0));
                break;
            case 5:
                holder.tv_show.setText(list.get(position).getDescVal());
                holder.tv_time.setText(list.get(position).getOrderTimeline());
                holder.tv_money.setText(AmountUtils.changeF2Y(list.get(position).getPayVal() + ""));
                holder.tv_dannum.setText(list.get(position).getWxTransactionID());

                if (list.get(position).getTui_val() == 1) {
                    holder.tv_state.setText("手动退款(待出货)");
                    holder.tv_state.setTextColor(Color.rgb(0, 204, 255));
                    holder.tv_orderFormstate.setTextColor(Color.rgb(0, 204, 255));
                } else if (list.get(position).getTui_val() == 2) {
                    holder.tv_state.setText("手动退款(已出货)");
                    holder.tv_state.setTextColor(Color.rgb(0, 204, 255));
                    holder.tv_orderFormstate.setTextColor(Color.rgb(0, 204, 255));
                } else {
                    holder.tv_state.setText("退款成功");
                    holder.tv_state.setTextColor(Color.rgb(255, 204, 0));
                    holder.tv_orderFormstate.setTextColor(Color.rgb(255, 204, 0));
                }
                holder.tv_address.setText(list.get(position).getMachine_data().getDetailedinstalladdress() + "-" + list.get(position).getItemNo());


                break;
            case 0:
                holder.tv_show.setText(list.get(position).getDescVal());
                holder.tv_time.setText(list.get(position).getOrderTimeline());
                holder.tv_money.setText(AmountUtils.changeF2Y(list.get(position).getPayVal() + ""));
                holder.tv_address.setText(list.get(position).getMachine_data().getDetailedinstalladdress() + "-" + list.get(position).getItemNo());
                holder.tv_dannum.setText(list.get(position).getWxTransactionID());

                switch (list.get(position).getStatus()) {
                    case 3:
                        holder.tv_state.setText("待出货");
                        holder.tv_state.setTextColor(Color.rgb(255, 51, 0));
                        holder.tv_orderFormstate.setTextColor(Color.rgb(255, 51, 0));
                        break;
                    case 4:
                        holder.tv_state.setText("交易成功");
                        holder.tv_state.setTextColor(Color.rgb(26, 233, 50));
                        holder.tv_orderFormstate.setTextColor(Color.rgb(26, 233, 50));
                        break;
                    case 5:
                        if (list.get(position).getTui_val() == 1) {
                            holder.tv_state.setText("手动退款(待出货)");
                            holder.tv_state.setTextColor(Color.rgb(0, 204, 255));
                            holder.tv_orderFormstate.setTextColor(Color.rgb(0, 204, 255));
                        } else if (list.get(position).getTui_val() == 2) {
                            holder.tv_state.setText("手动退款(已出货)");
                            holder.tv_state.setTextColor(Color.rgb(0, 204, 255));
                            holder.tv_orderFormstate.setTextColor(Color.rgb(0, 204, 255));
                        } else {
                            holder.tv_state.setText("退款成功");
                            holder.tv_state.setTextColor(Color.rgb(255, 204, 0));
                            holder.tv_orderFormstate.setTextColor(Color.rgb(255, 204, 0));
                        }
//                        holder.tv_state.setText("退款成功");
//                        holder.tv_state.setTextColor(Color.rgb(255, 204, 0));
                        break;
                    case 6:
                        if (list.get(position).getTui_val() == 1) {
                            holder.tv_state.setText("手动退款(待出货)");
                            holder.tv_state.setTextColor(Color.rgb(0, 204, 255));
                            holder.tv_orderFormstate.setTextColor(Color.rgb(0, 204, 255));

                        } else if (list.get(position).getTui_val() == 2) {
                            holder.tv_state.setText("手动退款(已出货)");
                            holder.tv_state.setTextColor(Color.rgb(0, 204, 255));
                            holder.tv_orderFormstate.setTextColor(Color.rgb(0, 204, 255));
                        } else {
                            holder.tv_state.setText("退款成功");
                            holder.tv_state.setTextColor(Color.rgb(255, 204, 0));
                            holder.tv_orderFormstate.setTextColor(Color.rgb(255, 204, 0));
                        }
//                        holder.tv_state.setText("退款成功");
//                        holder.tv_state.setTextColor(Color.rgb(255, 204, 0));
                        break;
//                 订单已取消
                    case 7:
                        holder.tv_state.setText("Cancel");
                        holder.tv_state.setTextColor(Color.rgb(255, 51, 0));
                        holder.tv_orderFormstate.setTextColor(Color.rgb(255, 51, 0));
                        break;
                }
                break;

        }
        if (list.get(position).getCcccustome().equals("1")) {
            ccccustome = "会员订单";
        } else {
            ccccustome = "普通订单";
        }

        switch (list.get(position).getPayType()) {
            case "1":
                payType = "平台";
                break;
            case "2":
                payType = "微信";
                break;
            case "3":
                payType = "储值卡";
                break;
            default:
                break;

        }
        holder.tv_orderFormstate.setText(payType + ccccustome);

        switch (holder.tv_orderFormstate.getText().toString()) {
            case "平台普通订单":
                holder.liearitemll.setBackgroundColor(Color.rgb(222, 234, 255));
                break;
            case "微信普通订单":
                holder.liearitemll.setBackgroundColor(Color.rgb(220, 255, 218));
                break;
            case "储值卡普通订单":
                holder.liearitemll.setBackgroundColor(Color.rgb(255, 251, 195));
                break;

            case "平台会员订单":
                holder.liearitemll.setBackgroundColor(Color.rgb(255, 228, 228));
                break;
            case "微信会员订单":
                holder.liearitemll.setBackgroundColor(Color.rgb(255, 228, 228));
                break;
            case "储值卡会员订单":
                holder.liearitemll.setBackgroundColor(Color.rgb(255, 228, 228));
                break;
            default:
                break;
        }

        holder.liearitemll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_show;
        private TextView tv_state;
        private TextView tv_time;
        private TextView tv_money;
        private TextView tv_dannum;
        private TextView tv_address;
        private TextView tv_orderFormstate;
        private LinearLayout liearitemll;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_show = itemView.findViewById(R.id.tv_show);
            tv_state = itemView.findViewById(R.id.tv_state);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_money = itemView.findViewById(R.id.tv_money);
            tv_dannum = itemView.findViewById(R.id.tv_dannum);
            liearitemll = itemView.findViewById(R.id.liearitemll);
            tv_address = itemView.findViewById(R.id.tv_ddaddress);
            tv_orderFormstate = itemView.findViewById(R.id.tv_orderFormstate);

        }
    }
}


