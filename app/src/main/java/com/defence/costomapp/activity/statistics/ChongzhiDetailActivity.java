package com.defence.costomapp.activity.statistics;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.defence.costomapp.bean.ChongzhiDetailBean;
import com.defence.costomapp.utils.AmountUtils;
import com.defence.costomapp.utils.DateAndTimeUtil;
import com.defence.costomapp.utils.RefreshUtils.RefreshLayout;
import com.defence.costomapp.utils.SgqUtils;
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

public class ChongzhiDetailActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.tv_zxf)
    TextView tvZxf;
    @BindView(R.id.tv_zcz)
    TextView tvZcz;
    @BindView(R.id.tv_zxd)
    TextView tvZxd;
    @BindView(R.id.tv_zdk)
    TextView tvZdk;
    @BindView(R.id.tv_zdcb)
    TextView tvZdcb;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.list_czhidetail)
    ListView listCzhidetail;
    @BindView(R.id.srl)
    RefreshLayout srl;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_xiaofei)
    TextView tvXiaofei;
    @BindView(R.id.liear_left)
    LinearLayout liearLeft;
    @BindView(R.id.liear_right)
    LinearLayout liearRight;
    private String userid;
    String Sdate = SgqUtils.getNowYmDate();
    private ChongzhiAdapter chongzhiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chongzhi_detail);
        ButterKnife.bind(this);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               /* String dateStr = DateAndTimeUtil.dateFormat(Sdate);
                intdata(dateStr);
                chongzhiAdapter.notifyDataSetChanged();*/

                srl.setRefreshing(false);


            }
        });
////        上拉加载
        srl.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                String dateStr = DateAndTimeUtil.dateFormat(Sdate);
                intdata(dateStr);
               /* if (!dateStr.equals(DateAndTimeUtil.dateaddFormat(SgqUtils.getNowYmDate()))) {
                    intdata(dateStr);
                    chongzhiAdapter.notifyDataSetChanged();
                } else {
                    srl.setLoading(false);
                    Toast.makeText(ChongzhiDetailActivity.this, "已是最新月份数据", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        intdata(Sdate);
    }

    private List<ChongzhiDetailBean.ListBean> list;


    private void intdata(final String date) {
        userid = getIntent().getStringExtra("userid");
        String phone = getIntent().getStringExtra("phone");
        middleTitle.setText(phone);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        RequestParams params = new RequestParams();
        params.put("userid", userid);
        params.put("sdate", date);
        params.put("orderBy", "2");
        httpUtils.doPost(Urls.chongzhidetail(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result, String message) throws JSONException {

                Sdate = date;
                srl.setRefreshing(false);
                srl.setLoading(false);

                JSONObject jsonObject = new JSONObject(result.toString());
                ChongzhiDetailBean chongzhiDetailBean = gson.fromJson(jsonObject.toString(), ChongzhiDetailBean.class);

                tvYue.setText("当前余额:" + AmountUtils.changeF2Y(chongzhiDetailBean.getYueNum() + ""));
                tvZcz.setText("总充值:" + AmountUtils.changeF2Y(chongzhiDetailBean.getChongzhinum() + ""));
                tvZdcb.setText("总退款成本:" + AmountUtils.changeF2Y(chongzhiDetailBean.getTkcbNum() + ""));
                tvZdk.setText("总退款金额:" + AmountUtils.changeF2Y(chongzhiDetailBean.getTuikuanNum() + ""));
                tvZxd.setText("总下单数:" + chongzhiDetailBean.getPingtaiNum());
                tvZxf.setText("总消费金额:" + AmountUtils.changeF2Y(chongzhiDetailBean.getPayval() + ""));

                List<ChongzhiDetailBean.ListBean> l = chongzhiDetailBean.getList();

                ChongzhiDetailBean.ListBean bean = new ChongzhiDetailBean.ListBean();

                if (l.size() != 0) {
                    int nnum = 0;
                    for (int i = 0; i < l.size(); i++) {
                        nnum += l.get(i).getHowMuch();
                    }
                    bean.setMoneyy(nnum + "");
                }

                bean.setType(222);
                l.add(0, bean);

                for (int i = 0; i < l.size(); i++) {
                    if (l.get(i).getType() == 222) {
                        l.get(i).setNewdate(Sdate);
                    }
                }

                if (list == null)
                    list = new ArrayList();
                list.addAll(l);
                if (chongzhiAdapter == null) {
                    chongzhiAdapter = new ChongzhiAdapter(ChongzhiDetailActivity.this, list);
                    listCzhidetail.setAdapter(chongzhiAdapter);
                } else {
                    chongzhiAdapter.notifyDataSetChanged();
                }


//                //字符串替换
//                String newdate = date.replace("-", "年");
//                tvDate.setText(newdate + "月");
//
//                int nnum = 0;
//                for (int i = 0; i < chongzhiDetailBean.getList().size(); i++) {
//                    nnum += chongzhiDetailBean.getList().get(i).getHowMuch();
//                }
//                tvXiaofei.setText("本月:" + AmountUtils.changeF2Y(nnum + "") + "元");


//                chongzhiAdapter = new ChongzhiAdapter(ChongzhiDetailActivity.this, chongzhiDetailBean.getList());
//                listCzhidetail.setAdapter(chongzhiAdapter);
            }
        });
    }

    @OnClick(R.id.liear_left)
    public void onViewClicked() {
        finish();
    }

    private class ChongzhiAdapter extends BaseAdapter {

        public static final int TYPE_TITLE = 0;
        public static final int TYPE_COMPANY = 1;
        private Context context;
        private LayoutInflater inflater;
        private List<ChongzhiDetailBean.ListBean> list;

        public ChongzhiAdapter(Context context, List<ChongzhiDetailBean.ListBean> list) {
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
        public int getItemViewType(int position) {

            if (list.get(position).getType() == 222) {
                return TYPE_TITLE;
            } else {
                return TYPE_COMPANY;
            }
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup arg2) {
            // TODO Auto-generated method stub


            ViewHolder1 viewHolder1 = null;
            ViewHolder2 viewHolder2 = null;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

      /*      if (view == null) {
                view = inflater.inflate(R.layout.item_tjmachine, null);
            }
            TextView tv_name = view.findViewById(R.id.tv_name);
            TextView tv_showshop = view.findViewById(R.id.tv_showshop);
            TextView tv_num = view.findViewById(R.id.tv_num);

            tv_name.setText("充值");
            tv_showshop.setText(list.get(position).getTimeline());
            tv_num.setText("+" + AmountUtils.changeF2Y(list.get(position).getHowMuch() + ""));
            tv_num.setTextSize(20);
            tv_num.setTextColor(Color.rgb(0, 0, 0));

            return view;

        }*/
            switch (getItemViewType(position)) {
                case TYPE_TITLE:
                    if (convertView == null) {
                        viewHolder1 = new ViewHolder1();
                        convertView = inflater.inflate(R.layout.layout_header, null);
                        viewHolder1.tv_dateyy = convertView.findViewById(R.id.tv_dateyy);
                        viewHolder1.tv_xiaofei = convertView.findViewById(R.id.tv_xiaofei);
                        convertView.setTag(viewHolder1);
                    } else {
                        viewHolder1 = (ViewHolder1) convertView.getTag();
                    }

                    viewHolder1.tv_dateyy.setText(list.get(position).getNewdate());
                    viewHolder1.tv_xiaofei.setText("本月消费:" + AmountUtils.changeF2Y(list.get(position).getMoneyy()) + "元");
                    break;

                case TYPE_COMPANY:
                    if (convertView == null) {
                        viewHolder2 = new ViewHolder2();
                        convertView = inflater.inflate(R.layout.item_shophistory, null);
                        viewHolder2.tv_show = convertView.findViewById(R.id.tv_show);
                        viewHolder2.tv_date = convertView.findViewById(R.id.tv_date);
                        viewHolder2.tv_pricee = convertView.findViewById(R.id.tv_pricee);
                        convertView.setTag(viewHolder2);
                    } else {
                        viewHolder2 = (ViewHolder2) convertView.getTag();
                    }
                    viewHolder2.tv_show.setText("充值");
                    viewHolder2.tv_date.setText(list.get(position).getTimeline());
                    viewHolder2.tv_pricee.setText("+" + AmountUtils.changeF2Y(list.get(position).getHowMuch() + ""));
                    viewHolder2.tv_pricee.setTextSize(20);
                    viewHolder2.tv_pricee.setTextColor(Color.rgb(0, 0, 0));

                  /*  viewHolder2.tv_show.setText(list.get(position).getDescVal());
                    viewHolder2.tv_date.setText(list.get(position).getPayTimeline());
                    viewHolder2.tv_pricee.setText("-" + AmountUtils.changeF2Y(list.get(position).getPayVal() + "") + "元");*/
                    break;
                default:
                    break;
            }

            return convertView;
        }

        class ViewHolder1 {
            TextView tv_dateyy;
            TextView tv_xiaofei;
        }

        class ViewHolder2 {
            TextView tv_show;
            TextView tv_date;
            TextView tv_pricee;

        }
    }
}
