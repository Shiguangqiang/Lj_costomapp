package com.defence.costomapp.activity.statistics;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.TjMachineDetailBean;
import com.defence.costomapp.utils.AmountUtils;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MachineDetailActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.sumJinE)
    TextView sumJinE;
    @BindView(R.id.sumLiRun)
    TextView sumLiRun;
    @BindView(R.id.saleCount)
    TextView saleCount;
    @BindView(R.id.list_machinedetail)
    ListView listMachinedetail;

    private String machineID;
    private String machineNo;
    private String date1;
    private String date2;
    private String addr1;
    private String addr2;
    private String addr3;
    private String groupid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        machineID = getIntent().getStringExtra("machineID");
        machineNo = getIntent().getStringExtra("machineNo");
        date1 = getIntent().getStringExtra("date1");
        date2 = getIntent().getStringExtra("date2");

        middleTitle.setText(machineNo + "商品销售情况");
        getData();

    }

    private void getData() {
        groupid = SharePerenceUtil.getStringValueFromSp("groupid");
        RequestParams params = new RequestParams();
        params.put("adminGroupID", groupid);
        params.put("machineID", machineID);
        params.put("machineNo", machineNo);
        params.put("date1", date1);
        params.put("date2", date2);
        params.put("addr1", "0");
        params.put("addr2", "0");
        params.put("addr3", "0");
        httpUtils.doPost(Urls.tjserach_machine(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                TjMachineDetailBean tjMachineDetailBean = gson.fromJson(jsonObject.toString(), TjMachineDetailBean.class);
                sumJinE.setText("总额:" + AmountUtils.changeF2Y(tjMachineDetailBean.getMap_data().getSumJinE() + "") + "元");
                sumLiRun.setText("总利润:" + AmountUtils.changeF2Y(tjMachineDetailBean.getMap_data().getSumLiRun() + "") + "元");
                saleCount.setText("总件:" + tjMachineDetailBean.getMap_data().getSaleCount() + "个");
                listMachinedetail.setAdapter(new MachineDetailAdapter(MachineDetailActivity.this, tjMachineDetailBean.getList()));

            }
        });


    }

    private class MachineDetailAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        List<TjMachineDetailBean.ListBean> list;

        public MachineDetailAdapter(Context context, List<TjMachineDetailBean.ListBean> list) {
            super();
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.list = list;


        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if (list != null && list.size() > 0) {
                return list.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return list.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(final int position, View view, ViewGroup arg2) {
            // TODO Auto-generated method stub
            if (view == null) {
                view = inflater.inflate(R.layout.item_tjmachine, null);
            }
            TextView tv_name = view.findViewById(R.id.tv_name);
            TextView tv_showshop = view.findViewById(R.id.tv_showshop);
            TextView tv_num = view.findViewById(R.id.tv_num);
            tv_name.setText(list.get(position).getDescVal());
            tv_showshop.setVisibility(View.GONE);
            tv_num.setText(list.get(position).getSaleCount() + "个");


            return view;

        }
    }
}
