package com.defence.costomapp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.defence.costomapp.R;
import com.defence.costomapp.activity.statistics.AnalysisFilter3Activity;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisContract;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisPresenter;
import com.defence.costomapp.adapter.DataAnalysFilter2Adapter;
import com.defence.costomapp.adapter.DataAnalysFilter3Adapter;
import com.defence.costomapp.base.BaseNewFragment;
import com.defence.costomapp.bean.DataAnGoodsFilterBean;
import com.defence.costomapp.bean.DataAnMachineFilterBean;
import com.defence.costomapp.bean.DataAnalysisFilterBean;
import com.defence.costomapp.utils.SgqUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Sgq
 * Create Date 2018/7/4 and 17:15
 * Used  筛选商品fragment
 */

@Route(path = "/fragment/AnalysGoodsFragment")
public class AnalysGoodsFragment extends BaseNewFragment<DataAnalysisPresenter> implements DataAnalysisContract.View {

    @Autowired
    public String machineNumber;

    @BindView(R.id.rv_fund)
    RecyclerView rvFund;
    private List<DataAnGoodsFilterBean.ShangpinListBean> mShangpin;

    public static void start(String machineNumber) {
        ARouter.getInstance().build("/fragment/AnalysGoodsFragment").withString("machineNumber", machineNumber).navigation();
    }

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {

        ARouter.getInstance().inject(this);
        machineNumber = getActivity().getIntent().getStringExtra("machineNumber");

        /**设置RecyclerView*/
        rvFund.setLayoutManager(new LinearLayoutManager(getContext()));

        mPresenter.getFilterGoodsData(String.valueOf(SgqUtils.TONGJI_TYPE), "0", "10", this.machineNumber);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fund;
    }

    @Override
    public void setFilterData(DataAnalysisFilterBean swVipData) {
    }

    @Override
    public void setFilterMachineData(DataAnMachineFilterBean dataAnMachineFilterBean) {
    }

    @Override
    public void setFilterGoodsData(DataAnGoodsFilterBean data) {
        mShangpin = data.getShangpinList();
        DataAnalysFilter3Adapter dataAnalysFilterAdapter = new DataAnalysFilter3Adapter(R.layout.llitem_ddfilter2, mShangpin);
        rvFund.setAdapter(dataAnalysFilterAdapter);

    }
}
