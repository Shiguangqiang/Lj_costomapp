package com.defence.costomapp.activity.statistics;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.defence.costomapp.R;
import com.defence.costomapp.activity.viewPresenter.Vip_SWContract;
import com.defence.costomapp.activity.viewPresenter.Vip_SWPresenter;
import com.defence.costomapp.adapter.VipShouWangAdapter;
import com.defence.costomapp.adapter.Vip_SW2NewAdapter;
import com.defence.costomapp.base.BaseNewActivity;
import com.defence.costomapp.bean.ShouWangVipBean;
import com.defence.costomapp.utils.SgqUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.linjiang.pandora.Pandora;


@Route(path = "/statistics/VipStatic2NewActivity")
public class VipStatic2NewActivity extends BaseNewActivity<Vip_SWPresenter> implements Vip_SWContract.View{


    @BindView(R.id.rv_vipstatic2)
    RecyclerView rvVipstatic2;
    @BindView(R.id.vip_static2srl)
    SwipeRefreshLayout vipStatic2srl;
    Vip_SW2NewAdapter mArticleAdapter;
    private String iskaitong = "1";
    private String openString = "";
    private int sortingRecords = 0;
    private String huiyuantype = "0";
    private String leftdate = SgqUtils.getReduceDateStr(SgqUtils.getNowDate(), 30);
    private String rightdate = SgqUtils.getNowDate();
    private String sequence = "2";
    private String dora = "desc";
    private int length = 0;

    public static void start() {
        ARouter.getInstance().build("/statistics/VipStatic2NewActivity").navigation();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_vip_static2_new;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {
        Pandora.get().open();
        try {
            mPresenter.getSWVipData("10110", length + "", "10", URLEncoder.encode(leftdate + " 00:00:00", "UTF-8"), URLEncoder.encode(rightdate + " 23:59:59", "UTF-8"), iskaitong, huiyuantype, sequence, dora);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**设置RecyclerView*/
        rvVipstatic2.setLayoutManager(new LinearLayoutManager(this));
    }



    @Override
    public void setSWVipData(ShouWangVipBean swVipData) {
        vipStatic2srl.setRefreshing(false);
        mArticleAdapter = new Vip_SW2NewAdapter(R.layout.listvipshouwang_item, swVipData.getXfkList());
        rvVipstatic2.setAdapter(mArticleAdapter);

    }
}
