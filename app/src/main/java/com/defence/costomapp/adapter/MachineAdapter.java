package com.defence.costomapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.bean.TongjiBean;
import com.defence.costomapp.myinterface.RVItemClickListener;
import com.defence.costomapp.utils.AmountUtils;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/4/17.
 */

public class MachineAdapter extends BaseAdapter {

    List<TongjiBean.MachineListBean> machine_list;
    private Context context;
    private RVItemClickListener rvItemClickListener;

    public MachineAdapter(Context contextn, List<TongjiBean.MachineListBean> machine_list, RVItemClickListener rvItemClickListener) {
        super();
        this.context = contextn;
        this.machine_list = machine_list;
        this.rvItemClickListener = rvItemClickListener;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (machine_list != null && machine_list.size() > 0) {
            return machine_list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return machine_list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup arg2) {
        // TODO Auto-generated method stub
        if (view == null) {
            view = View.inflate(context, R.layout.item_tjmachine, null);
        }
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_showshop = view.findViewById(R.id.tv_showshop);
        TextView tv_num = view.findViewById(R.id.tv_num);
        tv_name.setText(machine_list.get(position).getMachinenumber());
        tv_showshop.setText(String.valueOf(machine_list.get(position).getDetailedinstalladdress()));
        tv_num.setText(AmountUtils.changeF2Y(machine_list.get(position).getSumJinE() + "") + "元");
        LinearLayout liear_tjmachine = view.findViewById(R.id.liear_tjmachine);

        liear_tjmachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvItemClickListener.onItemClick(position);
            }
        });


        return view;

    }

}