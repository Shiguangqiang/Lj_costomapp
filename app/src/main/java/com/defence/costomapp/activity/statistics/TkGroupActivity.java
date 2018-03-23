package com.defence.costomapp.activity.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.adapter.TkSelectAdapter;
import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.TuikuanMachineBean;
import com.defence.costomapp.bean.UserInfo;
import com.defence.costomapp.utils.DividerItemDecoration;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TkGroupActivity extends BaseActivity {

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
        setContentView(R.layout.activity_tk_group);
        ButterKnife.bind(this);

        back.setText("返回");
        middleTitle.setText("订单机器组");
        rightTitle.setText("保存");
        //实例化
        getdata();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(TkGroupActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        setItemDecoration();


    }

    //退款
    private void getdata() {

        UserInfo userInfo = MyApplication.getApp().getUserInfo();
        RequestParams params = new RequestParams();
        params.put("userid",userInfo.getId()+"");
        httpUtils.doPost(Urls.listtkgroup(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                TuikuanMachineBean tuikuanMachineBean = gson.fromJson(jsonObject.toString(), TuikuanMachineBean.class);
                mAdapter = new TkSelectAdapter(tuikuanMachineBean.getList(), "group");
                recyclerView.setAdapter(mAdapter);

            }
        });
    }

    //设置分割线
    private void setItemDecoration() {
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(TkGroupActivity.this, DividerItemDecoration.VERTICAL_LIST);
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
                Intent intent = new Intent();
                intent.putExtra("devicegroup", s);
                setResult(1, intent);
                finish();
                break;
        }
    }
}
