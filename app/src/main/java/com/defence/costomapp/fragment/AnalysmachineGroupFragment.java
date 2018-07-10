package com.defence.costomapp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.defence.costomapp.R;
import com.defence.costomapp.activity.statistics.AnalysisFilter2Activity;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisContract;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisPresenter;
import com.defence.costomapp.adapter.DataAnalysFilter2Adapter;
import com.defence.costomapp.adapter.DataAnalysFilterAdapter;
import com.defence.costomapp.base.BaseNewFragment;
import com.defence.costomapp.bean.DataAnGoodsFilterBean;
import com.defence.costomapp.bean.DataAnMachineFilterBean;
import com.defence.costomapp.bean.DataAnalysisFilterBean;
import com.defence.costomapp.utils.SgqUtils;

import butterknife.BindView;

/**
 * Created by Sgq
 * Create Date 2018/7/4 and 17:15
 * Used  筛选机器组fragment
 */
public class AnalysmachineGroupFragment extends BaseNewFragment<DataAnalysisPresenter> implements DataAnalysisContract.View {

    @BindView(R.id.rv_fund)
    RecyclerView rvFund;

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        /**设置RecyclerView*/
        rvFund.setLayoutManager(new LinearLayoutManager(getContext()));
//        mPresenter.getFilterMachineData(String.valueOf(SgqUtils.TONGJI_TYPE), "0", "20");


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fund2;
    }

    @Override
    public void setFilterData(DataAnalysisFilterBean swVipData) {


    }

    @Override
    public void setFilterMachineData(DataAnMachineFilterBean dataAnMachineFilterBean, int loadType) {
//        DataAnalysFilter2Adapter dataAnalysFilterAdapter = new DataAnalysFilter2Adapter(R.layout.llitem_ddfilter2, dataAnMachineFilterBean.getMachineList());
//        rvFund.setAdapter(dataAnalysFilterAdapter);
    }


    @Override
    public void setFilterGoodsData(DataAnGoodsFilterBean dataAnGoodsFilterBean) {

    }

}
