package com.defence.costomapp.activity.viewPresenter;


import com.defence.costomapp.base.BaseContract;
import com.defence.costomapp.bean.DailyCostBean;
import com.defence.costomapp.bean.LineChartBean;

/**
 * Created by lw on 2018/1/24.
 */

public interface StaticstjContract {
    interface View extends BaseContract.BaseView {

        void setLineChartData(LineChartBean lineChartData);
        void setGailycost(DailyCostBean.ResultBean resultBean);
    }

    interface Presenter extends BaseContract.BasePresenter<StaticstjContract.View> {
        void getLineChartData(String adminGroupID, String funcType);
        void getGailycost(String adminGroupID, String funcType, String date);

    }


}
