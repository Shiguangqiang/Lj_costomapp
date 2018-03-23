package com.defence.costomapp.activity.statistics;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.DingdHistoryBean;
import com.defence.costomapp.bean.DingdanBean;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DingdHistoryActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.success_count)
    TextView successCount;
    @BindView(R.id.waitout_count)
    TextView waitoutCount;
    @BindView(R.id.refound_count)
    TextView refoundCount;
    @BindView(R.id.success_money)
    TextView successMoney;
    @BindView(R.id.refound_money)
    TextView refoundMoney;
    @BindView(R.id.refound_cb)
    TextView refoundCb;
    @BindView(R.id.list_dingdanhis)
    ListView listDingdanhis;
    @BindView(R.id.srl)
    RefreshLayout srl;
    private String wxOpenID;
    private String whoID;
    private String groupid;
    private DingdanAdapter dingdanAdapter;
    int iiCount = 0;
    String state = "";
    private PopupWindow pop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingd_history);
        ButterKnife.bind(this);

        whoID = getIntent().getStringExtra("whoID");
        wxOpenID = getIntent().getStringExtra("wxOpenID");
        groupid = SharePerenceUtil.getStringValueFromSp("groupid");

        //        // 设置下拉刷新监听器
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (iiCount >= 10) {
                    iiCount -= 10;
                    getlistdata(iiCount, state);
                    dingdanAdapter.notifyDataSetChanged();
                } else {
                    srl.setRefreshing(false);
                }
            }
        });
//        上拉加载
        srl.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                iiCount += 10;
                getlistdata(iiCount, state);
                dingdanAdapter.notifyDataSetChanged();

            }
        });
        /*个人记录*/
        initdata();
        /*list 历史*/
        getlistdata(iiCount, state);

        initpopdialog();


    }

    private void initpopdialog() {
        /*初始化从底部弹出*/

        final View view = getLayoutInflater().inflate(R.layout.pop_dialog_dingd, null);
        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv_jiaoyisucc = view.findViewById(R.id.tv_jiaoyisucc);
        TextView tv_daichuhuo = view.findViewById(R.id.tv_daichuhuo);
        TextView tv_tuikuan = view.findViewById(R.id.tv_tuikuan);
        TextView tv_alllist = view.findViewById(R.id.tv_alllist);


        tv_daichuhuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iiCount = 0;
                state = "3";
                getlistdata(iiCount, state);
                dingdanAdapter.notifyDataSetChanged();
                pop.dismiss();
            }
        });

        tv_tuikuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iiCount = 0;
                state = "5";
                getlistdata(iiCount, state);
                dingdanAdapter.notifyDataSetChanged();
                pop.dismiss();
            }
        });
        tv_jiaoyisucc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iiCount = 0;
                state = "4";
                getlistdata(iiCount, state);
                dingdanAdapter.notifyDataSetChanged();
                pop.dismiss();
            }
        });
        tv_alllist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iiCount = 0;
                state = "";
                getlistdata(iiCount, state);
                dingdanAdapter.notifyDataSetChanged();
                pop.dismiss();
            }
        });

        TextView tv_cancle = view.findViewById(R.id.tv_cancle);

        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop.dismiss();
            }
        });

        pop.setOutsideTouchable(true);
        pop.setFocusable(true);// 点击back退出pop
        pop.setAnimationStyle(R.style.add_new_style);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pop.setBackgroundDrawable(new ColorDrawable(Color.argb(136, 0, 0, 0)));// 设置背景透明，点击back退出pop

    }

    private void getlistdata(int iiCount, String sstate) {

        rightIcon.setImageResource(R.mipmap.all);
        RequestParams params = new RequestParams();
        params.put("whoID", whoID);
        params.put("wxOpenID", wxOpenID);
        params.put("adminGroupID", groupid);
        params.put("iCount", iiCount + "");
        params.put("adminGroupID", groupid);
        params.put("status", sstate);
        httpUtils.doPost(Urls.dingdhistoryllist(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {
                srl.setRefreshing(false);
                srl.setLoading(false);
                JSONObject jsonObject = new JSONObject(result.toString());
                final DingdanBean dingdanBean = gson.fromJson(jsonObject.toString(), DingdanBean.class);

                dingdanAdapter = new DingdanAdapter(DingdHistoryActivity.this, dingdanBean.getList(), new RVItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(DingdHistoryActivity.this, DingdanDetailActivity.class);
                        intent.putExtra("numberID", dingdanBean.getList().get(position).getNumberID());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });

                if (dingdanBean.getList() != null) {
                    listDingdanhis.setAdapter(dingdanAdapter);
                }

            }
        });

    }

    private void initdata() {

        middleTitle.setText(whoID);
        RequestParams params = new RequestParams();
        params.put("whoID", whoID);
        params.put("wxOpenID", wxOpenID);
        params.put("adminGroupID", groupid);
        httpUtils.doPost(Urls.dingdhistoryl(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {


            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {
                JSONObject jsonObject = new JSONObject(result.toString());
                DingdHistoryBean dingdHistoryBean = gson.fromJson(jsonObject.toString(), DingdHistoryBean.class);
                successCount.setText("成功次数:" + dingdHistoryBean.getSuccess_count() + "");
                successMoney.setText("成功金额:" + AmountUtils.changeF2Y(dingdHistoryBean.getSuccess_money() + ""));
                waitoutCount.setText("待出货:" + dingdHistoryBean.getWait_out_store() + "");
                refoundMoney.setText("退款金额:" + AmountUtils.changeF2Y(dingdHistoryBean.getRefund_money() + ""));
                refoundCount.setText("退款次数:" + dingdHistoryBean.getRefund_count() + "");
                refoundCb.setText("退款成本:" + AmountUtils.changeF2Y(dingdHistoryBean.getRefund_cb() + ""));
            }
        });
    }

    @OnClick({R.id.back, R.id.right_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.right_icon:
                if (pop.isShowing()) {
                    pop.dismiss();
                } else {
                    pop.showAtLocation(view, Gravity.BOTTOM, 0, -560);//在父控件下方出来
                    pop.showAsDropDown(view);
                    // 设置背景颜色变暗
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 0.7f;
                    getWindow().setAttributes(lp);

                }
                break;
        }
    }

    public class DingdanAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        private List<DingdanBean.ListBean> list;
        private RVItemClickListener rvItemClickListener;

        public DingdanAdapter(Context context, List<DingdanBean.ListBean> list, RVItemClickListener rvItemClickListener) {
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
            switch (list.get(position).getStatus()) {
                case 3:
                    tv_state.setText("待出货");
                    tv_state.setTextColor(Color.rgb(255, 51, 0));
                    break;
                case 4:
                    tv_state.setText("交易成功");
                    tv_state.setTextColor(Color.rgb(26, 233, 50));
                    break;
                case 5:
                    tv_state.setText("退款成功");
                    tv_state.setTextColor(Color.rgb(255, 204, 0));
                    break;
                case 6:
                    tv_state.setText("退款成功");
                    tv_state.setTextColor(Color.rgb(255, 204, 0));
                    break;
            }

            tv_time.setText(list.get(position).getOrderTimeline());
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
