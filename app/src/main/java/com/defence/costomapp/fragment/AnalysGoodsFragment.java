package com.defence.costomapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.defence.costomapp.R;
import com.defence.costomapp.activity.statistics.GrowthRateActivity;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisContract;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisPresenter;
import com.defence.costomapp.adapter.DAFilterGoodsAdapter;
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
public class AnalysGoodsFragment extends BaseNewFragment<DataAnalysisPresenter> implements DataAnalysisContract.View {

    @Autowired
    public String machineNumber;
    @BindView(R.id.tv_skip)
    TextView tvSkip;
    Unbinder unbinder;
    private PullLoadMoreRecyclerView rvFund;
    private List<DataAnGoodsFilterBean.ShangpinListBean> newlist;
    private DAFilterGoodsAdapter mDataAnalysFilterAdapter;
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

        ARouter.getInstance().inject(this);
        machineNumber = getActivity().getIntent().getStringExtra("machineNumber");
        /**设置RecyclerView*/
        rvFund = view.findViewById(R.id.rv_fund_good);
        rvFund.setLinearLayout();
        rvFund.setFooterViewText("Loading...");
        rvFund.getRecyclerView().addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST));
        rvFund.setFooterViewTextColor(R.color.black);
        rvFund.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                begin = 0;
                if (newlist != null) {
                    newlist.clear();
                }
                mPresenter.getFilterGoodsData(String.valueOf(SgqUtils.TONGJI_TYPE), (begin * 10) + "", "10", machineNumber);
            }

            @Override
            public void onLoadMore() {
                begin++;
                mPresenter.getFilterGoodsData(String.valueOf(SgqUtils.TONGJI_TYPE), (begin * 10) + "", "10", machineNumber);
            }
        });
        mPresenter.getFilterGoodsData(String.valueOf(SgqUtils.TONGJI_TYPE), (begin * 10) + "", "10", this.machineNumber);

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

        /**保存商品*/
        RxBus.getInstance().toFlowable(EventUtils.class)
                .subscribe(new Consumer<EventUtils>() {
                    @Override
                    public void accept(EventUtils event) throws Exception {
                        if (mDataAnalysFilterAdapter != null && mDataAnalysFilterAdapter.getSelectedItem().size() > 0) {
                            ArrayList<String> selectedItem = mDataAnalysFilterAdapter.getSelectedItem();
                            List<String> filtershopstring = SpUtil.getList(MyApplication.getApp(), "filtershopstring");
                            String sss = SgqUtils.listToString(selectedItem);
                            String sff = SgqUtils.listToString(filtershopstring);


                            if (mVerticalaxis.equals("left")) {

                                SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDS, sss);
                                SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAME, sff);

                            } else if (mVerticalaxis.equals("right")) {

                                SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDSRIGHT, sss);
                                SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAMERIGHT, sff);
                            } else if (mVerticalaxis.equals("lefts")) {

                                SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDSS, sss);
                                SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAMES, sff);

                            } else if (mVerticalaxis.equals("rights")) {

                                SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDSRIGHTS, sss);
                                SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAMERIGHTS, sff);
                            }
                        }
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
        rvFund.setPullLoadMoreCompleted();
        if (newlist == null) {
            newlist = new ArrayList();
        }
        newlist.addAll(data.getShangpinList());
        if (mDataAnalysFilterAdapter == null) {
            mDataAnalysFilterAdapter = new DAFilterGoodsAdapter(newlist, "shop");
            rvFund.setAdapter(mDataAnalysFilterAdapter);
        } else {
            mDataAnalysFilterAdapter.notifyDataSetChanged();
        }
    }
}
