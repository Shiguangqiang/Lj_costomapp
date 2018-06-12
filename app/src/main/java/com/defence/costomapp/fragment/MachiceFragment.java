package com.defence.costomapp.fragment;


import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.defence.costomapp.R;
import com.defence.costomapp.activity.statistics.MachineDetailActivity;
import com.defence.costomapp.adapter.MachineAdapter;
import com.defence.costomapp.base.BaseFragment;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.TongjiBean;
import com.defence.costomapp.myinterface.RVItemClickListener;
import com.defence.costomapp.utils.MyEvent;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Created by author Sgq
 * on 2018/3/7.
 * 商品
 */


public class MachiceFragment extends BaseFragment {

    private List<TongjiBean.MachineListBean> machine_list;
    private String leftdate = SgqUtils.getNowDate();
    private String rightdate = SgqUtils.getNowDate();
    private String tvAdd;

    private String groupid;

    private String device;
    private String status;
    private MachineAdapter machineAdapter;
    private ListView listMachine;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_machice, null);
        EventBus.getDefault().register(this);
        listMachine = view.findViewById(R.id.list_machine);
        initdata();
        return view;
    }

    private void initdata() {

        leftdate = getActivity().getIntent().getStringExtra("leftdate");
        rightdate = getActivity().getIntent().getStringExtra("rightdate");
        device = getActivity().getIntent().getStringExtra("device");
        status = getActivity().getIntent().getStringExtra("status");
        getData();
    }

    private void getData() {
        groupid = SharePerenceUtil.getStringValueFromSp("groupid");
        if (TextUtils.isEmpty(leftdate)) {
            leftdate = SgqUtils.getNowDate();
        }
        if (TextUtils.isEmpty(rightdate)) {
            rightdate = SgqUtils.getNowDate();
        }
        RequestParams params = new RequestParams();
        params.put("adminGroupID", groupid);
        params.put("date1", leftdate);
        params.put("date2", rightdate);
        params.put("addr1", "0");
        params.put("addr2", "0");
        params.put("addr3", "0");
        params.put("devices", device);
        params.put("status", status);


        /*if (TextUtils.isEmpty(device) && TextUtils.isEmpty(status)) {
            params.put("adminGroupID", groupid);
            params.put("addr1", "0");
            params.put("addr2", "0");
            params.put("addr3", "0");
            params.put("date1", SgqUtils.getNowDate());
            leftdate = SgqUtils.getNowDate();
            params.put("date2", SgqUtils.getNowDate());
            rightdate = SgqUtils.getNowDate();
        } else {
            params.put("adminGroupID", groupid);
            params.put("date1", leftdate);
            params.put("date2", rightdate);
            params.put("addr1", "0");
            params.put("addr2", "0");
            params.put("addr3", "0");
            params.put("devices", device);
            params.put("status", status);
        }*/


        httpUtils.doPost(Urls.tjserach(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result, String message) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                TongjiBean tongjiBean = gson.fromJson(jsonObject.toString(), TongjiBean.class);
                machine_list = tongjiBean.getMachine_list();

                machineAdapter = new MachineAdapter(getActivity(), machine_list, new RVItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(getActivity(), MachineDetailActivity.class);
                        intent.putExtra("machineID", machine_list.get(position).getMachine_id() + "");
                        intent.putExtra("machineNo", machine_list.get(position).getMachinenumber());
                        intent.putExtra("date1", leftdate);
                        intent.putExtra("date2", rightdate);
                        intent.putExtra("device", device);
                        intent.putExtra("status", status);
                        startActivity(intent);

                    }
                });
                listMachine.setAdapter(machineAdapter);

            }
        });

    }

    /**
     * 这里用到的了EventBus框架
     *
     * @param data
     */
    @Subscribe
    public void onEvent(MyEvent data) {
        if (66 == data.getEventType()) {
            leftdate = data.getDatebefore();
            rightdate = data.getDateafter();

            device = data.getDevice();
            status = data.getStatus();
            getData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);//取消注册
    }
}
