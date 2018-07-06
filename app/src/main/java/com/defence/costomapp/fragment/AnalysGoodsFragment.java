package com.defence.costomapp.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.defence.costomapp.R;
import com.defence.costomapp.activity.statistics.GrowthRateActivity;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisContract;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisPresenter;
import com.defence.costomapp.adapter.DAFilterGoodsAdapter;
import com.defence.costomapp.base.BaseNewFragment;
import com.defence.costomapp.bean.DataAnGoodsFilterBean;
import com.defence.costomapp.bean.DataAnMachineFilterBean;
import com.defence.costomapp.bean.DataAnalysisFilterBean;
import com.defence.costomapp.net.Constant;
import com.defence.costomapp.utils.SgqUtils;

import java.util.ArrayList;
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
    @BindView(R.id.btn_keep)
    Button btnKeep;

    private List<DataAnGoodsFilterBean.ShangpinListBean> mShangpin;
    private DAFilterGoodsAdapter mDataAnalysFilterAdapter;

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


        btnKeep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> selectedItem = mDataAnalysFilterAdapter.getSelectedItem();
                String s = SgqUtils.listToString(selectedItem);
                SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDS, s);
                GrowthRateActivity.start();
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fund3;
    }

    @Override
    public void setFilterData(DataAnalysisFilterBean swVipData) {
    }

    @Override
    public void setFilterMachineData(DataAnMachineFilterBean dataAnMachineFilterBean, int loadType) {

    }

    @Override
    public void setFilterGoodsData(DataAnGoodsFilterBean data) {
        mShangpin = data.getShangpinList();
        mDataAnalysFilterAdapter = new DAFilterGoodsAdapter(mShangpin, "shop");
        rvFund.setAdapter(mDataAnalysFilterAdapter);

    }


}
