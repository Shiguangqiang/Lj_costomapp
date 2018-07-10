package com.defence.costomapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.defence.costomapp.R;
import com.defence.costomapp.activity.statistics.AnalysisFilter3Activity;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisContract;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisPresenter;
import com.defence.costomapp.adapter.DAFilterMachineAdapter;
import com.defence.costomapp.adapter.DataAnalysFilter2Adapter;
import com.defence.costomapp.base.BaseNewFragment;
import com.defence.costomapp.bean.DataAnGoodsFilterBean;
import com.defence.costomapp.bean.DataAnMachineFilterBean;
import com.defence.costomapp.bean.DataAnalysisFilterBean;
import com.defence.costomapp.net.Constant;
import com.defence.costomapp.utils.SgqUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Sgq
 * Create Date 2018/7/4 and 17:15
 * Used  筛选机器fragment
 */
public class AnalysmachineFragment extends BaseNewFragment<DataAnalysisPresenter> implements DataAnalysisContract.View, DataAnalysFilter2Adapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, DataAnalysFilter2Adapter.RequestLoadMoreListener {

    @BindView(R.id.rv_fund)
    RecyclerView rvFund;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.btn_keep)
    Button btnKeep;
    @Inject
    DataAnalysFilter2Adapter mDataAnalysFilterAdapter;
    private int begin = 0;
    private List<DataAnMachineFilterBean.MachineListBean> mMachineList;
    private String mVerticalaxis;
    //    private DAFilterMachineAdapter mMDataAnalysFilterAdapter;

    @Override
    public void setFilterData(DataAnalysisFilterBean swVipData) {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setFilterMachineData(DataAnMachineFilterBean dataAnMachineFilterBean, int loadType) {
        swipeRefreshLayout.setRefreshing(false);
        mMachineList = dataAnMachineFilterBean.getMachineList();
        setLoadDataResult(mDataAnalysFilterAdapter, swipeRefreshLayout, mMachineList, loadType);

//       mMDataAnalysFilterAdapter = new DAFilterMachineAdapter(mMachineList, "machine");
//       rvFund.setAdapter(mMDataAnalysFilterAdapter);

    }


    @Override
    public void setFilterGoodsData(DataAnGoodsFilterBean dataAnGoodsFilterBean) {
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(getActivity(), AnalysisFilter3Activity.class);
        intent.putExtra("machineNumber", mDataAnalysFilterAdapter.getItem(position).getMachinenumber() + "");
        startActivity(intent);

        mVerticalaxis = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.VERTICALAXIS);

        if (mVerticalaxis.equals("left")) {
            SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.DATA_MACHINENAME, mDataAnalysFilterAdapter.getItem(position).getMachinename());
            SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.MACHINE_NUMBERS, "'" + mDataAnalysFilterAdapter.getItem(position).getMachinenumber() + "'");
        } else if (mVerticalaxis.equals("right")) {
            SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.MACHINENAMERIGHT, mDataAnalysFilterAdapter.getItem(position).getMachinename());
            SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.MACHINENUMBERSRIGHT, "'" + mDataAnalysFilterAdapter.getItem(position).getMachinenumber() + "'");
        }

    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showFaild(String errorMsg) {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        /**设置RecyclerView*/
        rvFund.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFund.setAdapter(mDataAnalysFilterAdapter);

        mDataAnalysFilterAdapter.setOnItemClickListener(this);
        mDataAnalysFilterAdapter.setOnLoadMoreListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);

        mPresenter.getFilterMachineData(String.valueOf(SgqUtils.TONGJI_TYPE), (begin * 10) + "", "10");


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fund2;
    }

    //上拉加载
    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMore();
    }

    // 下拉刷新
    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

}
