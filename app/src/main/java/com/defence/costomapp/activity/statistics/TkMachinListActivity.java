package com.defence.costomapp.activity.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.adapter.TkSelectAdapter;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.TuikuanMachineBean;
import com.defence.costomapp.utils.DividerItemDecoration;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TkMachinListActivity extends BaseActivity {

    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    TkSelectAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tk_machin_list);
        ButterKnife.bind(this);

//        back.setBackgroundResource(R.mipmap.back);
        back.setText("返回");
        middleTitle.setText("机器列表");
        rightTitle.setVisibility(View.VISIBLE);
        rightTitle.setText("保存");
//       rightTitle.setTextColor(getResources().getColor(R.color.bule_light));

        //实例化
        getdata();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(TkMachinListActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        setItemDecoration();
    }

    //退款
    private void getdata() {
        RequestParams params = new RequestParams();

        httpUtils.doPost(Urls.dingdtuikuan(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                TuikuanMachineBean tuikuanMachineBean = gson.fromJson(jsonObject.toString(), TuikuanMachineBean.class);
                mAdapter = new TkSelectAdapter(tuikuanMachineBean.getList(), "machine");
                recyclerView.setAdapter(mAdapter);

            }
        });
    }

    //设置分割线
    private void setItemDecoration() {
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(TkMachinListActivity.this, DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);
    }

    @OnClick({R.id.back, R.id.right_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.right_title:
                ArrayList<String> selectedItem = mAdapter.getSelectedItem();
                String s = SgqUtils.listToString(selectedItem);
//                Intent intent = new Intent(TkMachinListActivity.this, TuiKuanSerachActivity.class);
                Intent intent = new Intent();
                intent.putExtra("device", s);
//                startActivity(intent);
                setResult(0, intent);
                finish();
                break;
        }
    }
}
