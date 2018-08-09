package com.defence.costomapp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.defence.costomapp.R;
import com.defence.costomapp.activity.statistics.AnalysisFilter2Activity;
import com.defence.costomapp.activity.statistics.AnalysisFilterXYActivity;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisContract;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisPresenter;
import com.defence.costomapp.adapter.DataAnalysFilterAdapter;
import com.defence.costomapp.base.BaseNewFragment;
import com.defence.costomapp.bean.DataAnGoodsFilterBean;
import com.defence.costomapp.bean.DataAnMachineFilterBean;
import com.defence.costomapp.bean.DataAnalysisFilterBean;
import com.defence.costomapp.net.Constant;
import com.defence.costomapp.utils.SgqUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Sgq
 * Create Date 2018/7/4 and 17:15
 * Used  筛选用户fragment
 */
public class AnalysUserFragment extends BaseNewFragment<DataAnalysisPresenter> implements DataAnalysisContract.View, DataAnalysFilterAdapter.OnItemClickListener {

    @BindView(R.id.rv_fund)
    RecyclerView rvFund;
    private List<DataAnalysisFilterBean.TjListBean> mTjList;
    private String mVerticalAxis;

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {
        /**设置RecyclerView*/
        rvFund.setLayoutManager(new LinearLayoutManager(getContext()));
        mVerticalAxis = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.VERTICALAXIS);
        mPresenter.getFilterData(String.valueOf(SgqUtils.TONGJI_TYPE), "1");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fund;
    }

    @Override
    public void setFilterData(DataAnalysisFilterBean swVipData) {
        mTjList = swVipData.getTjList();
        DataAnalysFilterAdapter dataAnalysFilterAdapter = new DataAnalysFilterAdapter(R.layout.llitem_ddfilter, mTjList);
        rvFund.setAdapter(dataAnalysFilterAdapter);
        dataAnalysFilterAdapter.setOnItemClickListener(this);
    }

    @Override
    public void setFilterMachineData(DataAnMachineFilterBean dataAnMachineFilterBean, int loadType) {

    }

    @Override
    public void setFilterGoodsGroupData(DataAnGoodsFilterBean dataAnGoodsFilterBean) {

    }

    @Override
    public void setFilterGoodsData(DataAnGoodsFilterBean dataAnGoodsFilterBean, int loadType) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        AnalysisFilterXYActivity.start();
        switch (mVerticalAxis) {
            case "left":
                SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.DATA_FILTERNAME, mTjList.get(position).getName() + "");
                SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.DATA_STYPEID, mTjList.get(position).getTypeid() + "");
                break;
            case "right":
                SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.DATA_FILTERNAMERIGHT, mTjList.get(position).getName() + "");
                SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.DATA_STYPEIDRIGHT, mTjList.get(position).getTypeid() + "");
                break;
        }
    }
}
