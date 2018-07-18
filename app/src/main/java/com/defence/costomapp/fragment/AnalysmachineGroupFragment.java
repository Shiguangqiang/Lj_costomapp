package com.defence.costomapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.defence.costomapp.R;
import com.defence.costomapp.activity.statistics.AnalysisFilter3Activity;
import com.defence.costomapp.activity.statistics.CreatGoodsActivity;
import com.defence.costomapp.activity.statistics.CreatMachineActivity;
import com.defence.costomapp.activity.statistics.GrowthRateActivity;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisContract;
import com.defence.costomapp.activity.viewPresenter.DataAnalysisPresenter;
import com.defence.costomapp.adapter.HeaderBottomAdapter;
import com.defence.costomapp.adapter.HeaderBottomMachineAdapter;
import com.defence.costomapp.base.BaseNewFragment;
import com.defence.costomapp.bean.DataAnGoodsFilterBean;
import com.defence.costomapp.bean.DataAnMachineFilterBean;
import com.defence.costomapp.bean.DataAnalysisFilterBean;
import com.defence.costomapp.db.HistoryModel;
import com.defence.costomapp.db.MachineGroupModel;
import com.defence.costomapp.net.Constant;
import com.defence.costomapp.utils.SgqUtils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Sgq
 * Create Date 2018/7/4 and 17:15
 * Used  筛选机器组fragment
 */
public class AnalysmachineGroupFragment extends BaseNewFragment<DataAnalysisPresenter> implements DataAnalysisContract.View {

    @BindView(R.id.rv_fund)
    RecyclerView rvFund;
    @BindView(R.id.tv_skip)
    TextView tvSkip;
    Unbinder unbinder;
    private HeaderBottomMachineAdapter adapter;
    private LinearLayoutManager layoutManager;
    private List<String> mTexts;
    private List<MachineGroupModel> machinegroupmodel;
    private String mVerticalaxis;

    @Override
    protected void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initView(View view) {
        //初始数据
        mTexts = new ArrayList<String>();
        // 查询所有记录
        machinegroupmodel = SQLite.select().from(MachineGroupModel.class).queryList();
        for (int i = 0; i < machinegroupmodel.size(); i++) {
            mTexts.add(machinegroupmodel.get(i).getGoodName());
        }
        adapter = new HeaderBottomMachineAdapter(getActivity(), mTexts);
        //线性布局
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvFund.setLayoutManager(layoutManager);
        rvFund.setAdapter(adapter);

        initcustomize();
        mVerticalaxis = SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.VERTICALAXIS);
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mVerticalaxis.equals("left")) {
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.DATA_MACHINENAME, "");
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.MACHINE_NUMBERS, "'0'");
                } else if (mVerticalaxis.equals("right")) {
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.MACHINENAMERIGHT, "");
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.MACHINENUMBERSRIGHT, "'0'");
                } else if (mVerticalaxis.equals("lefts")) {
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.DATA_MACHINENAMES, "");
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.MACHINE_NUMBERSS, "'0'");
                } else if (mVerticalaxis.equals("rights")) {
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.MACHINENAMERIGHTS, "");
                    SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.MACHINENUMBERSRIGHTS, "'0'");
                }
                Intent intent = new Intent(getActivity(), AnalysisFilter3Activity.class);
                startActivity(intent);
            }
        });
    }

    private void initcustomize() {

        ArrayList<String> listid = new ArrayList<>();
        ArrayList<String> listshowName = new ArrayList<>();
        adapter.setOnItemClickListener(new HeaderBottomMachineAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == mTexts.size()) {
                    startActivityForResult(new Intent(getActivity(), CreatMachineActivity.class), 311);
                } else {
                    for (int i = 0; i < machinegroupmodel.size(); i++) {
                        if (mTexts.get(position).equals(machinegroupmodel.get(i).getGoodName())) {
                            try {
                                JSONArray jsonArray = new JSONArray(machinegroupmodel.get(i).getJsonGoods());
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
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.DATA_MACHINENAME, sff);
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.MACHINE_NUMBERS, sss);
                    } else if (mVerticalaxis.equals("right")) {
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.DATA_MACHINENAME, sff);
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.MACHINE_NUMBERS, sss);
                    } else if (mVerticalaxis.equals("lefts")) {
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.DATA_MACHINENAME, sff);
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.MACHINE_NUMBERS, sss);
                    } else if (mVerticalaxis.equals("rights")) {
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.DATA_MACHINENAME, sff);
                        SPUtils.getInstance(Constant.SHARED_NAME).put(Constant.MACHINE_NUMBERS, sss);
                    }
                    Intent intent = new Intent(getActivity(), AnalysisFilter3Activity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                for (int i = 0; i < machinegroupmodel.size(); i++) {
                    if (mTexts.get(position).equals(machinegroupmodel.get(i).getGoodName())) {
                        Toast.makeText(getActivity(), machinegroupmodel.get(i).getJsonGoods(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_machinegroup;
    }


    //    重写onActivityResult方法，用来接收B回传的数据。
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case 322:
                mTexts.clear();
                machinegroupmodel = SQLite.select().from(MachineGroupModel.class).queryList();
                for (int i = 0; i < machinegroupmodel.size(); i++) {
                    mTexts.add(machinegroupmodel.get(i).getGoodName());
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
