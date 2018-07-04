package com.defence.costomapp.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.Html;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.defence.costomapp.R;
import com.defence.costomapp.bean.ShouWangVipBean;


import java.util.List;

import javax.inject.Inject;

/**
 * Created by lw on 2018/1/19.
 */

public class Vip_SW2NewAdapter extends BaseQuickAdapter<ShouWangVipBean.XfkListBean, BaseViewHolder> {

    @Inject
    public Vip_SW2NewAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ShouWangVipBean.XfkListBean item) {
        helper.setText(R.id.tv_cardnum, item.getHaoma());
        helper.setText(R.id.tv_cardyue, item.getJieshuhuiyuanshijian());
        helper.setText(R.id.tv_cardtime, Html.fromHtml(item.getCt()));

    }
}
