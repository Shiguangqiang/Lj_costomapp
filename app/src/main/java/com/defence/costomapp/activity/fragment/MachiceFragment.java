package com.defence.costomapp.activity.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.activity.MachineDetailActivity;
import com.defence.costomapp.activity.MachineTjActivity;
import com.defence.costomapp.base.BaseFragment;
import com.defence.costomapp.bean.MachineEntity;
import com.defence.costomapp.bean.TongjiBean;
import com.defence.costomapp.myinterface.RVItemClickListener;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by author Sgq
 * on 2018/3/7.
 * 商品
 */


public class MachiceFragment extends BaseFragment {

    @BindView(R.id.list_machine)
    ListView listMachine;
    Unbinder unbinder;
    private List<TongjiBean.MachineListBean> machine_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_machice, null);
        unbinder = ButterKnife.bind(this, view);
        initdata();
        return view;
    }

    private void initdata() {
        String stj = SharePerenceUtil.getStringValueFromSp("stj");
        if (!TextUtils.isEmpty(stj)) {
            TongjiBean tongjiBean = new Gson().fromJson(stj, TongjiBean.class);
            machine_list = tongjiBean.getMachine_list();
            listMachine.setAdapter(new MachineAdapter(getActivity(), machine_list, new RVItemClickListener() {
                @Override
                public void onItemClick(int position) {

                    Intent intent = new Intent(getActivity(), MachineDetailActivity.class);

                    intent.putExtra("machineID", machine_list.get(position).getMachine_id()+"");
                    intent.putExtra("machineNo", machine_list.get(position).getMachinenumber());

                    intent.putExtra("date1", getActivity().getIntent().getStringExtra("leftdate"));
                    intent.putExtra("date2", getActivity().getIntent().getStringExtra("rightdate"));

                    String addr1 = getActivity().getIntent().getStringExtra("addr1");
                    String addr2 = getActivity().getIntent().getStringExtra("addr2");
                    String addr3 = getActivity().getIntent().getStringExtra("addr3");

                    if(TextUtils.isEmpty(addr1)){
                        addr1="0";
                        addr2="0";
                        addr3="0";
                    }
                    intent.putExtra("addr1", addr1);
                    intent.putExtra("addr2", addr2);
                    intent.putExtra("addr3", addr3);
                    startActivity(intent);

                }
            }));
        }

    }


    private class MachineAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        List<TongjiBean.MachineListBean> machine_list;
        private RVItemClickListener rvItemClickListener;

        public MachineAdapter(Context context, List<TongjiBean.MachineListBean> machine_list,RVItemClickListener rvItemClickListener) {
            super();
            this.context = context;
            inflater = LayoutInflater.from(context);
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
                view = inflater.inflate(R.layout.item_tjmachine, null);
            }
            TextView tv_name = view.findViewById(R.id.tv_name);
            TextView tv_showshop = view.findViewById(R.id.tv_showshop);
            TextView tv_num = view.findViewById(R.id.tv_num);
            tv_name.setText(machine_list.get(position).getMachinenumber());
            tv_showshop.setText(String.valueOf(machine_list.get(position).getDetailedinstalladdress()));
            tv_num.setText(machine_list.get(position).getSumJinE() + "元");
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
