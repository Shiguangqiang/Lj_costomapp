package com.defence.costomapp.activity.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.defence.costomapp.R;
import com.defence.costomapp.activity.LoginActivity;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisContract;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisPresenter;
import com.defence.costomapp.adapter.DAFilterGoodGroupAdapter;
import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.base.BaseNewActivity;
import com.defence.costomapp.bean.DataAnGoodsFilterBean;
import com.defence.costomapp.bean.DataAnMachineFilterBean;
import com.defence.costomapp.bean.DataAnalysisFilterBean;
import com.defence.costomapp.db.HistoryModel;
import com.defence.costomapp.utils.DividerItemDecoration;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SpUtil;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


@Route(path = "/statistics/CreatGoodsActivity")
public class CreatGoodsActivity extends BaseNewActivity<DataAnalysisPresenter> implements DataAnalysisContract.View, View.OnClickListener {

    private static final String TAG = "CreatGoodsActivity";
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.rv_fund_good)

    PullLoadMoreRecyclerView rvFund;
    @BindView(R.id.et_customize)
    EditText etCustomize;
    private List<DataAnGoodsFilterBean.ShangpinListBean> newlist;
    private DAFilterGoodGroupAdapter mDataAnalysFilterAdapter;
    private int begin = 0;
    private JSONArray mJsonarray;
    private List<HistoryModel> mHistoryModels;


    public static void start() {
        ARouter.getInstance().build("/statistics/CreatGoodsActivity").navigation();
    }

    @Override
    public void setFilterData(DataAnalysisFilterBean swVipData) {

    }

    @Override
    public void setFilterMachineData(DataAnMachineFilterBean dataAnMachineFilterBean, int loadType) {

    }

    @Override
    public void setFilterGoodsGroupData(DataAnGoodsFilterBean dataAnGoodsFilterBean) {
        rvFund.setPullLoadMoreCompleted();
        if (newlist == null) {
            newlist = new ArrayList();
        }
        newlist.addAll(dataAnGoodsFilterBean.getShangpinList());

        if (mDataAnalysFilterAdapter == null) {
            mDataAnalysFilterAdapter = new DAFilterGoodGroupAdapter(newlist, "shopgg");
            rvFund.setAdapter(mDataAnalysFilterAdapter);
        } else {
            mDataAnalysFilterAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void setFilterGoodsData(DataAnGoodsFilterBean dataAnGoodsFilterBean, int loadType) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
//                如果删除整个表，可以这样：
//                Delete.table(HistoryModel.class);
                finish();
                break;
            case R.id.right_title:
                for (int i = 0; i < mHistoryModels.size(); i++) {
                    if (mHistoryModels.get(i).getGoodName().equals(etCustomize.getText().toString())) {
                        Toast.makeText(this, "新增商品组名字重复", Toast.LENGTH_SHORT).show();
                    }
                }
                if (TextUtils.isEmpty(etCustomize.getText().toString())) {
                    Toast.makeText(this, "自定义商品组名字不能为空", Toast.LENGTH_SHORT).show();
                } else if (mDataAnalysFilterAdapter != null && mDataAnalysFilterAdapter.getSelectedItem().size() > 0) {
                    ArrayList<String> selectedItem = mDataAnalysFilterAdapter.getSelectedItem();
                    List<String> filtershopstring = SpUtil.getList(MyApplication.getApp(), "filtershopstringgg");
                    try {
                        mJsonarray = new JSONArray();
                        for (int i = 0; i < selectedItem.size(); i++) {
                            JSONObject mJsonobject = new JSONObject();
                            mJsonobject.put("id", selectedItem.get(i));
                            mJsonobject.put("showName", filtershopstring.get(i));
                            mJsonarray.put(mJsonobject);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    HistoryModel historyModel = new HistoryModel();
                    historyModel.insertData(etCustomize.getText().toString(), mJsonarray.toString());
                    historyModel.save();

                    Intent intent = new Intent();
                    setResult(311, intent);
                    finish();
                }
                break;
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_creat_goods;
    }

    @Override
    protected void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    protected void initView() {

//        Pandora.get().open();
        back.setText("返回");
        rightTitle.setText("保存");
        rightTitle.setTextColor(getResources().getColor(R.color.bule_light));
        back.setTextColor(getResources().getColor(R.color.bule_light));
        back.setOnClickListener(this);
        rightTitle.setOnClickListener(this);

        // 查询所有记录
        mHistoryModels = SQLite.select().from(HistoryModel.class).queryList();

        /**设置RecyclerView*/
        rvFund.setLinearLayout();
        rvFund.setFooterViewText("Loading...");
        rvFund.getRecyclerView().addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL_LIST));
        rvFund.setFooterViewTextColor(R.color.black);
        rvFund.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                begin = 0;
                if (newlist != null) {
                    newlist.clear();
                }
                mPresenter.getFilterGoodsGroupData(String.valueOf(SgqUtils.TONGJI_TYPE), (begin * 20) + "", "20", "nl");
            }

            @Override
            public void onLoadMore() {
                begin++;
                mPresenter.getFilterGoodsGroupData(String.valueOf(SgqUtils.TONGJI_TYPE), (begin * 20) + "", "20", "nl");
            }
        });

        mPresenter.getFilterGoodsGroupData(String.valueOf(SgqUtils.TONGJI_TYPE), (begin * 20) + "", "20", "nl");
    }
}
