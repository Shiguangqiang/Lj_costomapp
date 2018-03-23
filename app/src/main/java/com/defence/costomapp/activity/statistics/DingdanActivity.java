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
import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.DingdanBean;
import com.defence.costomapp.bean.UserInfo;
import com.defence.costomapp.bean.WxpayBean;
import com.defence.costomapp.myinterface.RVItemClickListener;
import com.defence.costomapp.utils.AmountUtils;
import com.defence.costomapp.utils.HttpUtils;
import com.defence.costomapp.utils.RefreshUtils.RefreshLayout;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DingdanActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.list_dingdan)
    ListView listDingdan;
    @BindView(R.id.srl)
    RefreshLayout srl;
    private PopupWindow pop;
    private String groupid;

    int iiCount = 0;
    String state = "";
    private DingdanAdapter dingdanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        middleTitle.setText("全部订单");
        rightIcon.setImageResource(R.mipmap.all);

        //        // 设置下拉刷新监听器
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (iiCount >= 10) {
                    iiCount -= 10;
                    getdata(iiCount, state);
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
                getdata(iiCount, state);
                dingdanAdapter.notifyDataSetChanged();

            }
        });

//        getNewData(iiCount, state);
        getdata(iiCount, state);

        initpopdialog();


    }

    /*数据*/
    private void getdata(int iiCount, String sstate) {
        groupid = SharePerenceUtil.getStringValueFromSp("groupid");
        RequestParams params = new RequestParams();
        params.put("iCount", iiCount + "");
        params.put("adminGroupID", groupid);
        params.put("status", sstate);
        httpUtils.doPost(Urls.dingdan(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {
                srl.setRefreshing(false);
                srl.setLoading(false);
                JSONObject jsonObject = new JSONObject(result.toString());
                final DingdanBean dingdanBean = gson.fromJson(jsonObject.toString(), DingdanBean.class);

                dingdanAdapter = new DingdanAdapter(DingdanActivity.this, dingdanBean.getList(), new RVItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(DingdanActivity.this, DingdanDetailActivity.class);
                        intent.putExtra("numberID", dingdanBean.getList().get(position).getNumberID());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });

                listDingdan.setAdapter(dingdanAdapter);
            }
        });
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
                getdata(iiCount, state);
                dingdanAdapter.notifyDataSetChanged();
                pop.dismiss();

            }
        });

        tv_tuikuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iiCount = 0;
                state = "5";
                getdata(iiCount, state);
                dingdanAdapter.notifyDataSetChanged();
                pop.dismiss();
            }
        });
        tv_jiaoyisucc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iiCount = 0;
                state = "4";
                getdata(iiCount, state);
                dingdanAdapter.notifyDataSetChanged();
                pop.dismiss();
            }
        });
        tv_alllist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iiCount = 0;
                state = "";
                getdata(iiCount, state);
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
        //设置背景透明才能显示
        pop.setBackgroundDrawable(new ColorDrawable(-00000));

        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });

        pop.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
//        pop.setBackgroundDrawable(new ColorDrawable(Color.argb(136, 0, 0, 0)));// 设置背景透明，点击back退出pop

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

    private class DingdanAdapter extends BaseAdapter {

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
