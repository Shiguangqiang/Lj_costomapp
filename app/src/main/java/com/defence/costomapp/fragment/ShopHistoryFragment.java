package com.defence.costomapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseFragment;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.ShopHistoryDetailBean;
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
import butterknife.Unbinder;

/**
 * Created by author Sgq
 * on 2018/3/14.
 * <p>
 * 消费记录
 */

public class ShopHistoryFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.list_shophistory)
    ListView listShophistory;
    @BindView(R.id.srl)
    RefreshLayout srl;

    private String uid;
    String Sdate = SgqUtils.getNowYmDate();
    private ShopHistoryAdapter shopHistoryAdapter;
    private String wxid = "";
    private String newdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shophistory, null);
        unbinder = ButterKnife.bind(this, view);
        srl.setRefreshing(false);
        //        // 设置下拉刷新监听器
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(false);

            }
        });
////        上拉加载

        srl.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                String dateStr = DateAndTimeUtil.dateFormat(Sdate);
                initdata(dateStr);
            }
        });

        initdata(Sdate);
        return view;
    }


    private List<ShopHistoryDetailBean.ListBean> list;

    private void initdata(final String date) {
        uid = getActivity().getIntent().getStringExtra("uid");
        wxid = getActivity().getIntent().getStringExtra("wxid");

        RequestParams params = new RequestParams();
        params.put("orderUID", uid);
        params.put("sdate", date);
        params.put("wxopenID", wxid);
        params.put("orderBy", "2");

        httpUtils.doPost(Urls.shophistory(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {

                Sdate = date;
                srl.setRefreshing(false);
                srl.setLoading(false);
                JSONObject jsonObject = new JSONObject(result.toString());
                ShopHistoryDetailBean shopHistoryDetailBean = gson.fromJson(jsonObject.toString(), ShopHistoryDetailBean.class);
                //字符串替
//                newdate = date.replace("-", "年");


                List<ShopHistoryDetailBean.ListBean> l = shopHistoryDetailBean.getList();

                for (int i = 0; i < l.size(); i++) {
                    if (l.get(i).getType() == 222) {
                        l.get(i).setNewdate(Sdate);
                    }
                }


                ShopHistoryDetailBean.ListBean bean = new ShopHistoryDetailBean.ListBean();

                if (l.size() != 0) {
                    int nnum = 0;
                    for (int i = 0; i < l.size(); i++) {
                        nnum += l.get(i).getPayVal();
                    }
                    bean.setMoneyy(nnum + "");
                }


                bean.setType(222);
                l.add(0, bean);

                if (list == null)
                    list = new ArrayList();
                list.addAll(l);
                if (shopHistoryAdapter == null) {
                    shopHistoryAdapter = new ShopHistoryAdapter(getActivity(), list);
                    listShophistory.setAdapter(shopHistoryAdapter);
                } else {
                    shopHistoryAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    public class ShopHistoryAdapter extends BaseAdapter {

        public static final int TYPE_TITLE = 0;
        public static final int TYPE_COMPANY = 1;
        private Context context;
        private LayoutInflater inflater;
        private List<ShopHistoryDetailBean.ListBean> list;


        public ShopHistoryAdapter(Context context, List<ShopHistoryDetailBean.ListBean> list) {
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
                    viewHolder2.tv_show.setText(list.get(position).getDescVal());
                    viewHolder2.tv_date.setText(list.get(position).getPayTimeline());
                    viewHolder2.tv_pricee.setText("-" + AmountUtils.changeF2Y(list.get(position).getPayVal() + "") + "元");
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

