package com.defence.costomapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.defence.costomapp.R;
import com.defence.costomapp.activity.statistics.CreatGoodsActivity;
import com.defence.costomapp.activity.statistics.GrowthRateActivity;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisContract;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisPresenter;
import com.defence.costomapp.adapter.HeaderBottomAdapter;
import com.defence.costomapp.base.BaseNewFragment;
import com.defence.costomapp.bean.DataAnGoodsFilterBean;
import com.defence.costomapp.bean.DataAnMachineFilterBean;
import com.defence.costomapp.bean.DataAnalysisFilterBean;
import com.defence.costomapp.db.HistoryModel;
import com.defence.costomapp.net.Constant;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SpUtil;
import com.google.gson.JsonObject;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tech.linjiang.pandora.Pandora;

/**
 * Created by Sgq
 * Create Date 2018/7/4 and 17:15
 * Used  筛选商品组fragment
 */
public class AnalysGoodsGroupFragment extends BaseNewFragment<DataAnalysisPresenter> implements DataAnalysisContract.View {


    private HeaderBottomAdapter adapter;
    private LinearLayoutManager layoutManager;
    private RecyclerView mRvFundGood;
    private TextView mTvSkip;
    private List<String> mTexts;
    private List<HistoryModel> mHistoryModels;
    private String mVerticalaxis;

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected void initView(View view) {

        mRvFundGood = view.findViewById(R.id.rv_fund_good);
        mTvSkip = view.findViewById(R.id.tv_skip);
        //初始数据
        mTexts = new ArrayList<String>();
        // 查询所有记录
        mHistoryModels = SQLite.select().from(HistoryModel.class).queryList();
        for (int i = 0; i < mHistoryModels.size(); i++) {
            mTexts.add(mHistoryModels.get(i).getGoodName());
        }
        adapter = new HeaderBottomAdapter(getActivity(), mTexts);
        //线性布局
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvFundGood.setLayoutManager(layoutManager);
        mRvFundGood.setAdapter(adapter);

        initcustomize();
        mVerticalaxis = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.VERTICALAXIS);
        mTvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mVerticalaxis.equals("left")) {
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDS, "0");
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAME, "");
                } else if (mVerticalaxis.equals("right")) {
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDSRIGHT, "0");
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAMERIGHT, "");
                } else if (mVerticalaxis.equals("lefts")) {
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDSS, "0");
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAMES, "");

                } else if (mVerticalaxis.equals("rights")) {

                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDSRIGHTS, "0");
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAMERIGHTS, "");
                }
                GrowthRateActivity.start();
            }
        });

    }

    private void initcustomize() {

        ArrayList<String> listid = new ArrayList<>();
        ArrayList<String> listshowName = new ArrayList<>();
        adapter.setOnItemClickListener(new HeaderBottomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == mTexts.size()) {
                    startActivityForResult(new Intent(getActivity(), CreatGoodsActivity.class), 311);
                } else {
                    for (int i = 0; i < mHistoryModels.size(); i++) {
                        if (mTexts.get(position).equals(mHistoryModels.get(i).getGoodName())) {
                            try {
                                JSONArray jsonArray = new JSONArray(mHistoryModels.get(i).getJsonGoods());
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    listid.add(String.valueOf(jsonArray.getJSONObject(j).get("id")));
                                    listshowName.add(String.valueOf(jsonArray.getJSONObject(j).get("showName")));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    String sss = SgqUtils.listToString(listid);
                    String sff = SgqUtils.listToString(listshowName);


                    if (mVerticalaxis.equals("left")) {
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDS, sss);
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAME, sff);
                    } else if (mVerticalaxis.equals("right")) {
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDSRIGHT, sss);
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAMERIGHT, sff);
                    } else if (mVerticalaxis.equals("lefts")) {
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDSS, sss);
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAMES, sff);
                    } else if (mVerticalaxis.equals("rights")) {
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GUIGEIDSRIGHTS, sss);
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.GOODSNAMERIGHTS, sff);
                    }
                    GrowthRateActivity.start();
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                for (int i = 0; i < mHistoryModels.size(); i++) {
                    if (mTexts.get(position).equals(mHistoryModels.get(i).getGoodName())) {
                        Toast.makeText(getActivity(), mHistoryModels.get(i).getJsonGoods(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goodsgroup;
    }

    //    重写onActivityResult方法，用来接收B回传的数据。
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case 311:
                mTexts.clear();
                mHistoryModels = SQLite.select().from(HistoryModel.class).queryList();
                for (int i = 0; i < mHistoryModels.size(); i++) {
                    mTexts.add(mHistoryModels.get(i).getGoodName());
                }
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    @Override
    public void setFilterData(DataAnalysisFilterBean swVipData) {
    }

    @Override
    public void setFilterMachineData(DataAnMachineFilterBean dataAnMachineFilterBean, int loadType) {
    }

    @Override
    public void setFilterGoodsData(DataAnGoodsFilterBean dataAnGoodsFilterBean) {
    }

}
