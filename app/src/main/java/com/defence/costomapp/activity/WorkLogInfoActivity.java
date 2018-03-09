package com.defence.costomapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.adapter.WorkLogDetailAdapter;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.WorkLogDateBean;
import com.defence.costomapp.bean.WorkLogInfoBean;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.defence.costomapp.utils.spinerpopwindow.AbstractSpinerAdapter;
import com.defence.costomapp.utils.spinerpopwindow.SpinerPopWindow;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by author Sgq
 * on 2018/3/8.
 * <p>
 * 工作日志详情
 */

public class WorkLogInfoActivity extends BaseActivity implements AbstractSpinerAdapter.IOnItemSelectListener {
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.buhuomessage_item_icon)
    ImageView buhuomessageItemIcon;
    @BindView(R.id.worklog_name)
    TextView worklogName;
    @BindView(R.id.worklog_phone)
    TextView worklogPhone;
    @BindView(R.id.buhuoitemll)
    LinearLayout buhuoitemll;
    @BindView(R.id.liear_call)
    LinearLayout liearCall;
    @BindView(R.id.exlist)
    ExpandableListView exlist;
    private String whoID;
    String groupid;

    //筛选框
    SpinerPopWindow mSpinerPopWindow;
    private List<String> listname = new ArrayList<>();
    private WorkLogDateBean workLogDateBean;
    private WorkLogDetailAdapter workLogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workloginfo);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        back.setText("返回");
        middleTitle.setText("工作日志");
        rightTitle.setText(SgqUtils.getNowDate());
        rightTitle.setTextColor(Color.rgb(25, 29, 249));
        groupid = SharePerenceUtil.getStringValueFromSp("groupid");
        whoID = getIntent().getStringExtra("whoID");

        getWorkDate();
        mSpinerPopWindow = new SpinerPopWindow(this);
        mSpinerPopWindow.setItemListener(this);
        rightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(rightTitle);
            }
        });
        getWorkInfo(SgqUtils.getNowDate());

    }

    /*工作日期*/
    private void getWorkDate() {
        RequestParams params = new RequestParams();
        params.put("userid", groupid);
        httpUtils.doPost(Urls.worklogdate(), SgqUtils.MANAGER_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                workLogDateBean = gson.fromJson(jsonObject.toString(), WorkLogDateBean.class);
                for (int i = 0; i < workLogDateBean.getList().size(); i++) {
                    listname.add(workLogDateBean.getList().get(i).getShedate());
                }
                mSpinerPopWindow.refreshData(listname, 0);

            }
        });
    }

    /**
     * 工作信息详情
     *
     * @param date
     */
    private void getWorkInfo(String date) {

        RequestParams params = new RequestParams();
        params.put("whoID", whoID);
        params.put("date", date);
        params.put("adminGroupID", groupid);
        httpUtils.doPost(Urls.workloginfo(), SgqUtils.MANAGER_TYPE, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result) {
                try {
                    JSONObject jsonObject = new JSONObject(result.toString());
                    WorkLogInfoBean.ResultBean workUserInfoBean = gson.fromJson(jsonObject.toString(), WorkLogInfoBean.ResultBean.class);
                    worklogName.setText(workUserInfoBean.getWork_user().getRealname());
                    worklogPhone.setText(workUserInfoBean.getWork_user().getTelphone());

                    workLogAdapter = new WorkLogDetailAdapter(WorkLogInfoActivity.this, workUserInfoBean.getData_list());
                    exlist.setAdapter(workLogAdapter);

                    //设置默认展开
                    int groupCount = exlist.getCount();
                    for (int i = 0; i < groupCount; i++) {
                        exlist.expandGroup(i);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @OnClick({R.id.back, R.id.liear_call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.liear_call:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + worklogPhone.getText().toString());
                intent.setData(data);
                startActivity(intent);
                break;
        }
    }

    //选择日期展示
    private void showPopupMenu(TextView rightTitle) {
        mSpinerPopWindow.setWidth(rightTitle.getWidth());
        mSpinerPopWindow.showAsDropDown(rightTitle);
    }


    //切换日期
    @Override
    public void onItemClick(int pos) {
        if (pos >= 0 && pos <= listname.size()) {

            rightTitle.setText(this.listname.get(pos));
            getWorkInfo(this.listname.get(pos));
            workLogAdapter.notifyDataSetChanged();
        }


    }
}

