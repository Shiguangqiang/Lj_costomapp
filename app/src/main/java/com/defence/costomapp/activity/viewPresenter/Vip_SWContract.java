package com.defence.costomapp.activity.viewPresenter;

import com.defence.costomapp.base.BaseContract;
import com.defence.costomapp.bean.ShouWangVipBean;

/**
 * Created by Sgq
 * Create Date 2018/7/3 and 11:01
 * Used  vip统计  守望会员
 */
public interface Vip_SWContract {


    interface View extends BaseContract.BaseView {
        void setSWVipData(ShouWangVipBean swVipData);

    }

    interface Presenter extends BaseContract.BasePresenter<Vip_SWContract.View> {
        void getSWVipData(String funcType, String listNum, String pags, String kaishitime, String jieshutime, String iskaitong, String huiyuantype, String paixu, String dora);

    }
}
