package com.defence.costomapp.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.defence.costomapp.R;
import com.defence.costomapp.bean.DataAnGoodsFilterBean;
import com.defence.costomapp.bean.DataAnMachineFilterBean;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by Sgq on 2018/1/19.
 */

public class DataAnalysFilter3Adapter extends BaseQuickAdapter<DataAnGoodsFilterBean.ShangpinListBean, BaseViewHolder> {
    @Inject
    public DataAnalysFilter3Adapter() {
        super(R.layout.llitem_ddfilter2, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, DataAnGoodsFilterBean.ShangpinListBean item) {
        helper.setText(R.id.tv_show, item.getShang_pin_full_name());
    }
}
