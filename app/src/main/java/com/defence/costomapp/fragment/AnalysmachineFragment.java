package com.defence.costomapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.defence.costomapp.R;
import com.defence.costomapp.activity.statistics.AnalysisFilter3Activity;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisContract;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisPresenter;
import com.defence.costomapp.adapter.DataAnalysFilter2Adapter;
import com.defence.costomapp.base.BaseNewFragment;
import com.defence.costomapp.bean.DataAnGoodsFilterBean;
import com.defence.costomapp.bean.DataAnMachineFilterBean;
import com.defence.costomapp.bean.DataAnalysisFilterBean;
import com.defence.costomapp.utils.SgqUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Sgq
 * Create Date 2018/7/4 and 17:15
 * Used  筛选机器fragment
 */
public class AnalysmachineFragment extends BaseNewFragment<DataAnalysisPresenter> implements DataAnalysisContract.View, DataAnalysFilter2Adapter.OnItemClickListener {

    @BindView(R.id.rv_fund)
    RecyclerView rvFund;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    Unbinder unbinder;
    private List<DataAnMachineFilterBean.MachineListBean> mMachineList;

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        /**设置RecyclerView*/
        rvFund.setLayoutManager(new LinearLayoutManager(getContext()));
        mPresenter.getFilterMachineData(String.valueOf(SgqUtils.TONGJI_TYPE), "0", "10");




        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // do something, such as re-request from server or other


            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fund;
    }

    @Override
    public void setFilterData(DataAnalysisFilterBean swVipData) {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setFilterMachineData(DataAnMachineFilterBean dataAnMachineFilterBean) {
        swipeRefreshLayout.setRefreshing(false);

        mMachineList = dataAnMachineFilterBean.getMachineList();
        DataAnalysFilter2Adapter dataAnalysFilterAdapter = new DataAnalysFilter2Adapter(R.layout.llitem_ddfilter2, dataAnMachineFilterBean.getMachineList());
        rvFund.setAdapter(dataAnalysFilterAdapter);
        dataAnalysFilterAdapter.setOnItemClickListener(this);
    }

    @Override
    public void setFilterGoodsData(DataAnGoodsFilterBean dataAnGoodsFilterBean) {
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(getActivity(), AnalysisFilter3Activity.class);
        intent.putExtra("machineNumber", mMachineList.get(position).getMachinenumber() + "");
        startActivity(intent);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
