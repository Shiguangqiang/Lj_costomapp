package com.defence.costomapp.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.Html;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.defence.costomapp.R;
import com.defence.costomapp.bean.DataAnalysisFilterBean;
import com.defence.costomapp.bean.ShouWangVipBean;


import java.util.List;


/**
 * Created by lw on 2018/1/19.
 */

public class DataAnalysFilterAdapter extends BaseQuickAdapter<DataAnalysisFilterBean.TjListBean, BaseViewHolder> {

    public DataAnalysFilterAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, DataAnalysisFilterBean.TjListBean item) {
        helper.setText(R.id.tv_filterConditions, item.getName());
    }
}
