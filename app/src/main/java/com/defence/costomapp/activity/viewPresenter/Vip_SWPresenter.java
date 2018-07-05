package com.defence.costomapp.activity.viewPresenter;

import com.defence.costomapp.base.BasePresenter;
import com.defence.costomapp.bean.DataResponse;
import com.defence.costomapp.bean.LineChartBean;
import com.defence.costomapp.bean.ShouWangVipBean;
import com.defence.costomapp.net.ApiService;
import com.defence.costomapp.net.RetrofitManager;
import com.defence.costomapp.net.RxSchedulers;


import io.reactivex.functions.Consumer;
import retrofit2.http.Field;

/**
 * Created by Sgq
 * Create Date 2018/7/3 and 11:02
 * Used  vip统计  守望会员
 */
public class Vip_SWPresenter extends BasePresenter<Vip_SWContract.View> implements Vip_SWContract.Presenter {

    public Vip_SWPresenter() {

    }


    @Override
    public void getSWVipData(String funcType, String listNum, String pags, String kaishitime, String jieshutime, String iskaitong, String huiyuantype, String paixu, String dora) {
        RetrofitManager.create(ApiService.class)
                .getSWVipData(funcType, listNum, pags, kaishitime, jieshutime, iskaitong, huiyuantype, paixu, dora)
                .compose(RxSchedulers.<DataResponse<ShouWangVipBean>>applySchedulers())
                .compose(mView.<DataResponse<ShouWangVipBean>>bindToLife())
                .subscribe(new Consumer<DataResponse<ShouWangVipBean>>() {
                    @Override
                    public void accept(DataResponse<ShouWangVipBean> response) throws Exception {
                        if (response.getSign() == 1) {
                            mView.setSWVipData(response.getResult());
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

}
