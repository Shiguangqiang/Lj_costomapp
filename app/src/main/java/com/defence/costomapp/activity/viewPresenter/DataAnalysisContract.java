package com.defence.costomapp.activity.viewPresenter;

import com.defence.costomapp.base.BaseContract;
import com.defence.costomapp.bean.DataAnGoodsFilterBean;
import com.defence.costomapp.bean.DataAnMachineFilterBean;
import com.defence.costomapp.bean.DataAnalysisFilterBean;
import com.defence.costomapp.net.LoadType;

/**
 * Created by Sgq
 * Create Date 2018/7/4 and 11:01
 * Used  数据分析
 */
public interface DataAnalysisContract {


    interface View extends BaseContract.BaseView {

        void setFilterData(DataAnalysisFilterBean swVipData);

        void setFilterMachineData(DataAnMachineFilterBean dataAnMachineFilterBean, @LoadType.checker int loadType);


        void setFilterGoodsGroupData(DataAnGoodsFilterBean dataAnGoodsFilterBean);

        void setFilterGoodsData(DataAnGoodsFilterBean dataAnGoodsFilterBean, @LoadType.checker int loadType);

    }

    interface Presenter extends BaseContract.BasePresenter<DataAnalysisContract.View> {

        void refresh();

        void loadMore();

        void getFilterData(String funcType, String ctype);

        void getFilterMachineData(String funcType, String begin, String end);

        void getFilterGoodsData(String funcType, String begin, String end, String machineNumber);

        void getFilterGoodsGroupData(String funcType, String begin, String end, String machineNumber);


    }
}
