package com.defence.costomapp.activity.viewPresenter;

import com.defence.costomapp.base.BasePresenter;
import com.defence.costomapp.bean.DailyCostBean;
import com.defence.costomapp.bean.DataResponse;
import com.defence.costomapp.bean.LineChartBean;
import com.defence.costomapp.net.ApiService;
import com.defence.costomapp.net.RetrofitManager;
import com.defence.costomapp.net.RxSchedulers;


import io.reactivex.functions.Consumer;

/**
 * Created by lw on 2018/1/24.
 */

public class StaticstjPresenter extends BasePresenter<StaticstjContract.View> implements StaticstjContract.Presenter {
    public StaticstjPresenter() {
    }

    @Override
    public void getLineChartData(String adminGroupID, String funcType) {

        RetrofitManager.create(ApiService.class)
                .getSearchArticles(adminGroupID, funcType)
                .compose(RxSchedulers.<DataResponse<LineChartBean>>applySchedulers())
                .compose(mView.<DataResponse<LineChartBean>>bindToLife())
                .subscribe(new Consumer<DataResponse<LineChartBean>>() {
                    @Override
                    public void accept(DataResponse<LineChartBean> response) throws Exception {
                        if (response.getSign() == 1) {
                            mView.setLineChartData(response.getResult());
                        } else {
                            mView.showFaild(String.valueOf(response.getMessage()));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFaild(throwable.getMessage());
                    }
                });
    }

    @Override
    public void getGailycost(String adminGroupID, String funcType, String date) {

        RetrofitManager.create(ApiService.class)
                .getGailycost(adminGroupID, funcType, date)
                .compose(RxSchedulers.<DataResponse<DailyCostBean.ResultBean>>applySchedulers())
                .compose(mView.<DataResponse<DailyCostBean.ResultBean>>bindToLife())
                .subscribe(response -> {
                    if (response.getSign() == 1) {
                        mView.setGailycost(response.getResult());
                    } else {
                        mView.showFaild(String.valueOf(response.getMessage()));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showFaild(throwable.getMessage());
                    }
                });

    }
}
