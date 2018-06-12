package com.defence.costomapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.defence.costomapp.R;
import com.defence.costomapp.adapter.MachineRegisAdapter;
import com.defence.costomapp.base.BaseFragment;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.MachineStaticsBean;
import com.defence.costomapp.utils.RefreshUtils.RefreshLayout;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by author Sgq
 * on 2018/5/30.
 */
public class TabVip4NewFragment extends BaseFragment {

    private ListView lv_machineRegistration;
    private RefreshLayout srl;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_vip4, null);

        lv_machineRegistration = view.findViewById(R.id.lv_machineRegistration);
        srl = view.findViewById(R.id.srl);


        srl.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {

            }
        });

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        //注册机器统计
        init();

        return view;
    }

    private void init() {
        RequestParams params = new RequestParams();
        params.add("listNum", "0");
        params.add("pags", "5");
        httpUtils.doPost(Urls.machineStatics(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result, String message) {
                try {
                    if (result != null) {

                        List<Object> listBean = gson.fromJson(result.toString(), List.class);
                        List<MachineStaticsBean> listBeansum = new ArrayList<>();

                        for (Object obj : listBean) {
                            MachineStaticsBean allMachineBean = gson.fromJson(obj.toString(), MachineStaticsBean.class);
                            listBeansum.add(allMachineBean);
                        }

                        MachineRegisAdapter machineRegisAdapter = new MachineRegisAdapter(listBeansum, getActivity());
                        lv_machineRegistration.setAdapter(machineRegisAdapter);

//                        val machineRegisAdapter = MachineRegisAdapter2(machineBean)
//                        lv_machineRegistration.adapter = machineRegisAdapter

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
