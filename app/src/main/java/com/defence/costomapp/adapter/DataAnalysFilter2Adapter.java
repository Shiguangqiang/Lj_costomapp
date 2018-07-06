package com.defence.costomapp.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.defence.costomapp.R;
import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.bean.DataAnMachineFilterBean;
import com.defence.costomapp.bean.DataAnalysisFilterBean;
import com.defence.costomapp.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by lw on 2018/1/19.
 */

public class DataAnalysFilter2Adapter extends BaseQuickAdapter<DataAnMachineFilterBean.MachineListBean, BaseViewHolder> {

    @Inject
    public DataAnalysFilter2Adapter() {
        super(R.layout.llitem_ddfilter2, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, DataAnMachineFilterBean.MachineListBean item) {
        helper.setText(R.id.tv_show, item.getMachinename());
    }
}
