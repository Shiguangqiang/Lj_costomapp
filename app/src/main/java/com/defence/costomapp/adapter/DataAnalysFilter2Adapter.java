package com.defence.costomapp.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.defence.costomapp.R;
import com.defence.costomapp.bean.DataAnMachineFilterBean;
import com.defence.costomapp.bean.DataAnalysisFilterBean;

import java.util.List;


/**
 * Created by lw on 2018/1/19.
 */

public class DataAnalysFilter2Adapter extends BaseQuickAdapter<DataAnMachineFilterBean.MachineListBean, BaseViewHolder> {

    public DataAnalysFilter2Adapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, DataAnMachineFilterBean.MachineListBean item) {
        helper.setText(R.id.tv_show, item.getMachinename());
    }
}
