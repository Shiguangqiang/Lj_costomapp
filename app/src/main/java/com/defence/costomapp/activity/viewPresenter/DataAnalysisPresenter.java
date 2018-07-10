package com.defence.costomapp.activity.viewPresenter;

import com.defence.costomapp.base.BasePresenter;
import com.defence.costomapp.bean.DataAnGoodsFilterBean;
import com.defence.costomapp.bean.DataAnMachineFilterBean;
import com.defence.costomapp.bean.DataAnalysisFilterBean;
import com.defence.costomapp.bean.DataResponse;
import com.defence.costomapp.net.ApiService;
import com.defence.costomapp.net.LoadType;
import com.defence.costomapp.net.RetrofitManager;
import com.defence.costomapp.net.RxSchedulers;


import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by Sgq
 * Create Date 2018/7/3 and 11:02
 * Used  vip统计  守望会员
 */
public class DataAnalysisPresenter extends BasePresenter<DataAnalysisContract.View> implements DataAnalysisContract.Presenter {


    private boolean mIsRefresh;
    private int begin;
    private String funcType;


    @Inject
    public DataAnalysisPresenter() {
        this.mIsRefresh = true;
    }

    @Override
    public void refresh() {
        begin = 0;
        mIsRefresh = true;
        getFilterMachineData(funcType, (begin *10) + "", "10");
    }

    @Override
    public void loadMore() {
        begin++;
        mIsRefresh = false;
        getFilterMachineData(funcType, (begin *10) + "", "10");
    }

    @Override
    public void getFilterData(String funcType, String ctype) {
        mView.showLoading();
        RetrofitManager.create(ApiService.class)
                .getFilterData(funcType, ctype)
                .compose(RxSchedulers.<DataResponse<DataAnalysisFilterBean>>applySchedulers())
                .compose(mView.<DataResponse<DataAnalysisFilterBean>>bindToLife())
                .subscribe(new Consumer<DataResponse<DataAnalysisFilterBean>>() {
                    @Override
                    public void accept(DataResponse<DataAnalysisFilterBean> response) throws Exception {
                        if (response.getSign() == 1) {
                            mView.setFilterData(response.getResult());
                            mView.hideLoading();
                        } else {
                            mView.showFaild(String.valueOf(response.getMessage()));
                            mView.hideLoading();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFaild(throwable.getMessage());
                        mView.hideLoading();
                    }
                });
    }


    @Override
    public void getFilterMachineData(String funcType, String begin, String end) {
        this.funcType = funcType;
        mView.showLoading();
        RetrofitManager.create(ApiService.class)
                .getFilterMachineData(funcType, begin, end)
                .compose(RxSchedulers.<DataResponse<DataAnMachineFilterBean>>applySchedulers())
                .compose(mView.<DataResponse<DataAnMachineFilterBean>>bindToLife())
                .subscribe(new Consumer<DataResponse<DataAnMachineFilterBean>>() {
                    @Override
                    public void accept(DataResponse<DataAnMachineFilterBean> response) throws Exception {
                        if (response.getSign() == 1) {
                            int loadType = mIsRefresh ? LoadType.TYPE_REFRESH_SUCCESS : LoadType.TYPE_LOAD_MORE_SUCCESS;
                            mView.setFilterMachineData(response.getResult(), loadType);
                            mView.hideLoading();

                        } else {

                            mView.showFaild(String.valueOf(response.getMessage()));
                            mView.hideLoading();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFaild(throwable.getMessage());
                        mView.hideLoading();
                    }
                });
    }


    @Override
    public void getFilterGoodsData(String funcType, String begin, String end, String machineNumber) {
        mView.showLoading();
        RetrofitManager.create(ApiService.class)
                .getFilterGoodsData(funcType, begin, end, machineNumber)
                .compose(RxSchedulers.<DataResponse<DataAnGoodsFilterBean>>applySchedulers())
                .compose(mView.<DataResponse<DataAnGoodsFilterBean>>bindToLife())
                .subscribe(new Consumer<DataResponse<DataAnGoodsFilterBean>>() {
                    @Override
                    public void accept(DataResponse<DataAnGoodsFilterBean> response) throws Exception {
                        if (response.getSign() == 1) {
                            mView.setFilterGoodsData(response.getResult());
                            mView.hideLoading();
                        } else {
                            mView.showFaild(String.valueOf(response.getMessage()));
                            mView.hideLoading();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFaild(throwable.getMessage());
                        mView.hideLoading();
                    }
                });
    }
}
