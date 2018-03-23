package com.defence.costomapp.activity.statistics;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.TuikuanListBean;
import com.defence.costomapp.myinterface.RVItemClickListener;
import com.defence.costomapp.utils.AmountUtils;
import com.defence.costomapp.utils.RefreshUtils.RefreshLayout;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TuikuanListActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.liear_left)
    LinearLayout liearLeft;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.liear_right)
    LinearLayout liearRight;
    @BindView(R.id.tuikuanSum)
    TextView tuikuanSum;
    @BindView(R.id.tuikuandingdanshu)
    TextView tuikuandingdanshu;
    @BindView(R.id.tuikuanchengben)
    TextView tuikuanchengben;
    @BindView(R.id.daichuhuoSum)
    TextView daichuhuoSum;
    @BindView(R.id.daichuhuodingdanshu)
    TextView daichuhuodingdanshu;
    @BindView(R.id.daichuhuochengben)
    TextView daichuhuochengben;

    int length = 0;
    String sdate;
    String edate;
    @BindView(R.id.list_tuikuan)
    ListView listTuikuan;
    @BindView(R.id.srl)
    RefreshLayout srl;
    @BindView(R.id.liear_tk1)
    LinearLayout liearTk1;
    @BindView(R.id.liear_tk2)
    LinearLayout liearTk2;
    private DingdanAdapter dingdanAdapter;
    private TuikuanListBean tuikuanListBean;
    //    private String device;
    private String formatid;
    private String machineNumber;
    private String groupMachineNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuikuan_list);
        ButterKnife.bind(this);
        sdate = getIntent().getStringExtra("sdate");
        edate = getIntent().getStringExtra("edate");
//        device = getIntent().getStringExtra("device");
        formatid = getIntent().getStringExtra("formatid");
        machineNumber = getIntent().getStringExtra("machineNumber");
        groupMachineNumber = getIntent().getStringExtra("groupMachineNumber");


        middleTitle.setText("退款查询");
        //        // 设置下拉刷新监听器
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                length = 0;
                list.clear();
                initdata(length);

            }
        });
//        上拉加载
        srl.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                length++;
                initdata(length);


            }
        });
        initdata(length);


    }

    private List list;

    private void initdata(int length) {
        rightIcon.setImageResource(R.mipmap.all);


        if (TextUtils.isEmpty(formatid) && TextUtils.isEmpty(machineNumber) && TextUtils.isEmpty(groupMachineNumber)) {
            RequestParams params = new RequestParams();
            params.put("length", (length*10)+ "");
            params.put("orderBy", "2");
            params.put("endpag", "10");
            //未选择展示全部
            httpUtils.doPost(Urls.allisttuikuan(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {

                @Override
                public void onSuccess(Gson gson, Object result) throws JSONException {
                    srl.setRefreshing(false);
                    srl.setLoading(false);

                    JSONObject jsonObject = new JSONObject(result.toString());
                    tuikuanListBean = gson.fromJson(jsonObject.toString(), TuikuanListBean.class);

                    tuikuanSum.setText("退款总额:" + AmountUtils.changeF2Y(String.valueOf(tuikuanListBean.getTuikuanSum())));
                    tuikuandingdanshu.setText("退款订单数:" + tuikuanListBean.getTuikuandingdanshu() + "");
                    tuikuanchengben.setText("退款成本:" + AmountUtils.changeF2Y(String.valueOf(tuikuanListBean.getTuikuanchengben())));

                    daichuhuoSum.setText("待出货总额:" + AmountUtils.changeF2Y(String.valueOf(tuikuanListBean.getDaichuhuoSum())));
                    daichuhuodingdanshu.setText("待出货订单数:" + tuikuanListBean.getDaichuhuodingdanshu() + "");
                    daichuhuochengben.setText("待出货成本:" + AmountUtils.changeF2Y(String.valueOf(tuikuanListBean.getDaichuhuochengben())));

                    if (list == null)
                        list = new ArrayList();
                    list.addAll(tuikuanListBean.getList());

                    if (dingdanAdapter == null) {
                        dingdanAdapter = new DingdanAdapter(TuikuanListActivity.this, list, new RVItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Intent intent = new Intent(TuikuanListActivity.this, DingdanDetailActivity.class);
                                intent.putExtra("numberID", tuikuanListBean.getList().get(position).getNumberID());
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        });
                        listTuikuan.setAdapter(dingdanAdapter);
                    } else {
                        dingdanAdapter.notifyDataSetChanged();
                    }


                }
            });

        } else {
            String groupid = SharePerenceUtil.getStringValueFromSp("groupid");
            RequestParams params = new RequestParams();
            params.put("length", length + "");
            params.put("orderBy", "2");
            params.put("endpag", "10");
            params.put("adminGroupID", groupid);
            params.put("sdate", sdate + " 00:00:00");
            params.put("edate", edate + " 23:59:59");
            params.put("formatid", formatid);
            params.put("machineNumber", machineNumber);
            params.put("groupMachineNumber", groupMachineNumber);

            //展示机器
            httpUtils.doPost(Urls.allisttuikuan(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {

                @Override
                public void onSuccess(Gson gson, Object result) throws JSONException {
                    srl.setRefreshing(false);
                    srl.setLoading(false);
                    liearTk1.setVisibility(View.GONE);
                    liearTk2.setVisibility(View.GONE);
//
//                    tuikuanSum.setText("退款总额:" + AmountUtils.changeF2Y(String.valueOf(tuikuanListBean.getTuikuanSum())));
//                    tuikuandingdanshu.setText("退款订单数:" + tuikuanListBean.getTuikuandingdanshu() + "");
//                    tuikuanchengben.setText("退款成本:" + AmountUtils.changeF2Y(String.valueOf(tuikuanListBean.getDaichuhuochengben())));
//                    daichuhuoSum.setText("待出货总额:" + AmountUtils.changeF2Y(String.valueOf(tuikuanListBean.getDaichuhuoSum())));
//                    daichuhuodingdanshu.setText("待出货订单数:" + tuikuanListBean.getDaichuhuodingdanshu() + "");
//                    daichuhuochengben.setText("待出货成本:" + AmountUtils.changeF2Y(String.valueOf(tuikuanListBean.getTuikuanchengben())));
                    JSONObject jsonObject = new JSONObject(result.toString());
                    tuikuanListBean = gson.fromJson(jsonObject.toString(), TuikuanListBean.class);
                    dingdanAdapter = new DingdanAdapter(TuikuanListActivity.this, tuikuanListBean.getList(), new RVItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent = new Intent(TuikuanListActivity.this, DingdanDetailActivity.class);
                            intent.putExtra("numberID", tuikuanListBean.getList().get(position).getNumberID());
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    });

                    if (tuikuanListBean.getList() != null) {
                        listTuikuan.setAdapter(dingdanAdapter);
                    }


                }
            });
        }
    }


    @OnClick({R.id.liear_left, R.id.liear_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.liear_left:
                finish();
                break;
            case R.id.liear_right:
                startActivity(new Intent(TuikuanListActivity.this, TuiKuanSerachActivity.class));
                finish();
                break;
        }
    }

    public class DingdanAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        private List<TuikuanListBean.ListBean> list;
        private RVItemClickListener rvItemClickListener;

        public DingdanAdapter(Context context, List<TuikuanListBean.ListBean> list, RVItemClickListener rvItemClickListener) {
            super();
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.list = list;
            this.rvItemClickListener = rvItemClickListener;
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
                view = inflater.inflate(R.layout.item_dingdan, null);
            }

            TextView tv_show = view.findViewById(R.id.tv_show);
            TextView tv_state = view.findViewById(R.id.tv_state);
            TextView tv_time = view.findViewById(R.id.tv_time);
            TextView tv_money = view.findViewById(R.id.tv_money);
            TextView tv_dannum = view.findViewById(R.id.tv_dannum);
            LinearLayout liearitemll = view.findViewById(R.id.liearitemll);

            tv_show.setText(list.get(position).getDescVal());
            tv_state.setText("退款成功");
            tv_state.setTextColor(Color.rgb(255, 204, 0));
//            switch (list.get(position).getStatus()) {
//                case 3:
//                    tv_state.setText("待出货");
//                    tv_state.setTextColor(Color.rgb(255, 51, 0));
//                    break;
//                case 4:
//                    tv_state.setText("交易成功");
//                    tv_state.setTextColor(Color.rgb(26, 233, 50));
//                    break;
//                case 5:
//                    tv_state.setText("退款成功");
//                    tv_state.setTextColor(Color.rgb(255, 204, 0));
//                    break;
//                case 6:
//                    tv_state.setText("退款成功");
//                    tv_state.setTextColor(Color.rgb(255, 204, 0));
//                    break;
//            }

            tv_time.setText(list.get(position).getBackTimeline());
            tv_money.setText(AmountUtils.changeF2Y(list.get(position).getPayVal() + ""));
            tv_dannum.setText(list.get(position).getNumberID());

//
            liearitemll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rvItemClickListener.onItemClick(position);
                }
            });

            return view;

        }
    }
}
