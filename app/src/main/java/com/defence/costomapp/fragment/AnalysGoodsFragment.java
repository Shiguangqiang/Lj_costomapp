package com.defence.costomapp.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.defence.costomapp.R;
import com.defence.costomapp.activity.statistics.GrowthRateActivity;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisContract;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisPresenter;
import com.defence.costomapp.adapter.DAFilterGoodsAdapter;
import com.defence.costomapp.adapter.DataAnalysFilter2Adapter;
import com.defence.costomapp.adapter.DataAnalysFilter3Adapter;
import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.base.BaseNewFragment;
import com.defence.costomapp.bean.DataAnGoodsFilterBean;
import com.defence.costomapp.bean.DataAnMachineFilterBean;
import com.defence.costomapp.bean.DataAnalysisFilterBean;
import com.defence.costomapp.net.Constant;
import com.defence.costomapp.net.RxBus;
import com.defence.costomapp.utils.DividerItemDecoration;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SpUtil;
import com.defence.costomapp.utils.event.EventUtils;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;

/**
 * Created by Sgq
 * Create Date 1018/7/4 and 17:15
 * Used  筛选商品fragment
 */

@Route(path = "/fragment/AnalysGoodsFragment")
public class AnalysGoodsFragment extends BaseNewFragment<DataAnalysisPresenter> implements DataAnalysisContract.View, DataAnalysFilter3Adapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, DataAnalysFilter3Adapter.RequestLoadMoreListener {

    @Autowired
    public String machineNumber;
    @BindView(R.id.tv_skip)
    TextView tvSkip;
    Unbinder unbinder;
    @BindView(R.id.rv_fund)
    RecyclerView rvFund;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Inject
    DataAnalysFilter3Adapter mDataAnalysFilterAdapter;
    private List<DataAnGoodsFilterBean.ShangpinListBean> newlist;
    private int begin = 0;
    private String mVerticalaxis;


    public static void start(String machineNumber) {
        ARouter.getInstance().build("/fragment/AnalysGoodsFragment").withString("machineNumber", machineNumber).navigation();
    }


    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View view) {

        /**设置RecyclerView*/
        rvFund.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFund.setAdapter(mDataAnalysFilterAdapter);
        mDataAnalysFilterAdapter.setOnItemClickListener(this);
        mDataAnalysFilterAdapter.setOnLoadMoreListener(this);
        swipeRefreshLayout.setOnRefreshListener(this);

        machineNumber = getActivity().getIntent().getStringExtra("machineNumber");
        mPresenter.getFilterGoodsData(String.valueOf(SgqUtils.TONGJI_TYPE), (begin * 10) + "", "10", machineNumber);
        mVerticalaxis = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.VERTICALAXIS);
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mVerticalaxis.equals("left")) {
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDS, "0");
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAME, "");

                } else if (mVerticalaxis.equals("right")) {

                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDSRIGHT, "0");
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAMERIGHT, "");
                } else if (mVerticalaxis.equals("lefts")) {

                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDSS, "0");
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAMES, "");

                } else if (mVerticalaxis.equals("rights")) {
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDSRIGHTS, "0");
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAMERIGHTS, "");
                }
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
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setFilterMachineData(DataAnMachineFilterBean dataAnMachineFilterBean, int loadType) {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setFilterGoodsGroupData(DataAnGoodsFilterBean dataAnGoodsFilterBean) {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setFilterGoodsData(DataAnGoodsFilterBean dataAnGoodsFilterBean, int loadType) {
        swipeRefreshLayout.setRefreshing(false);
        newlist = dataAnGoodsFilterBean.getShangpinList();
        setLoadDataResult(mDataAnalysFilterAdapter, swipeRefreshLayout, newlist, loadType);
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        if (mVerticalaxis.equals("left")) {
            SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDS, mDataAnalysFilterAdapter.getItem(position).getCommodityspecificationsid() + "");
            SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAME, mDataAnalysFilterAdapter.getItem(position).getShang_pin_full_name());

        } else if (mVerticalaxis.equals("right")) {

            SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDSRIGHT, mDataAnalysFilterAdapter.getItem(position).getCommodityspecificationsid() + "");
            SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAMERIGHT, mDataAnalysFilterAdapter.getItem(position).getShang_pin_full_name());
        } else if (mVerticalaxis.equals("lefts")) {

            SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDSS, mDataAnalysFilterAdapter.getItem(position).getCommodityspecificationsid() + "");
            SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAMES, mDataAnalysFilterAdapter.getItem(position).getShang_pin_full_name());
        } else if (mVerticalaxis.equals("rights")) {

            SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDSRIGHTS, mDataAnalysFilterAdapter.getItem(position).getCommodityspecificationsid() + "");
            SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAMERIGHTS, mDataAnalysFilterAdapter.getItem(position).getShang_pin_full_name());
        }
        GrowthRateActivity.start();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMore();
    }
}
