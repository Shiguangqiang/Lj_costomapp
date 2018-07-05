package com.defence.costomapp.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.defence.costomapp.R;
import com.defence.costomapp.bean.DataAnGoodsFilterBean;
import com.defence.costomapp.bean.DataAnMachineFilterBean;

import java.util.List;


/**
 * Created by lw on 2018/1/19.
 */

public class DataAnalysFilter3Adapter extends BaseQuickAdapter<DataAnGoodsFilterBean.ShangpinListBean, BaseViewHolder> {

    public DataAnalysFilter3Adapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, DataAnGoodsFilterBean.ShangpinListBean item) {
        helper.setText(R.id.tv_show, item.getShang_pin_full_name());
    }
}
