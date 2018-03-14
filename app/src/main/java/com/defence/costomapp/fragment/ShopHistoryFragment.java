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
import com.defence.costomapp.myinterface.RVItemClickListener;
import com.defence.costomapp.utils.RefreshUtils.RefreshLayout;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

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
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_xiaofei)
    TextView tvXiaofei;
    @BindView(R.id.srl)
    RefreshLayout srl;

    private String uid;
    String Sdate = SgqUtils.getNowYmDate();
    private ShopHistoryAdapter shopHistoryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shophistory, null);
        unbinder = ButterKnife.bind(this, view);
        //        // 设置下拉刷新监听器
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                String dateStr = SgqUtils.getDateStr(Sdate, 28);
                initdata(dateStr);
                shopHistoryAdapter.notifyDataSetChanged();


            }
        });
//        上拉加载
        srl.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {

                String dateStr = SgqUtils.getAddDateStr(Sdate, 30);
                initdata(dateStr);
                shopHistoryAdapter.notifyDataSetChanged();


            }
        });

        initdata(Sdate);
        return view;
    }

    private void initdata(final String date) {
        uid = getActivity().getIntent().getStringExtra("uid");
        RequestParams params = new RequestParams();
        params.put("orderUID", uid);
        params.put("sdate", date);
//        params.put("sdate", "2018-02");
        params.put("orderBy", "2");

        httpUtils.doPost(Urls.shophistory(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {
                Sdate=date;
                srl.setRefreshing(false);
                srl.setLoading(false);

                JSONObject jsonObject = new JSONObject(result.toString());

                ShopHistoryDetailBean shopHistoryDetailBean = gson.fromJson(jsonObject.toString(), ShopHistoryDetailBean.class);

                //字符串替换
                String newdate = date.replace("-", "年");
                tvDate.setText(newdate + "月");
                tvXiaofei.setText("本月消费:" + shopHistoryDetailBean.getPayval() + "元");

                shopHistoryAdapter = new ShopHistoryAdapter(getActivity(), shopHistoryDetailBean.getList(), new RVItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }
                });
                listShophistory.setAdapter(shopHistoryAdapter);


            }
        });
    }

    private class ShopHistoryAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        private List<ShopHistoryDetailBean.ListBean> list;
        private RVItemClickListener rvItemClickListener;

        public ShopHistoryAdapter(Context context, List<ShopHistoryDetailBean.ListBean> list, RVItemClickListener rvItemClickListener) {
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
                view = inflater.inflate(R.layout.item_shophistory, null);
            }
            TextView tv_show = view.findViewById(R.id.tv_show);
            TextView tv_date = view.findViewById(R.id.tv_date);
            TextView tv_pricee = view.findViewById(R.id.tv_pricee);


            tv_show.setText(list.get(position).getDescVal());
            tv_date.setText(list.get(position).getOrderTimeline());
            tv_pricee.setText(list.get(position).getPayVal() + "");

//
//            buhuoItemll.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    rvItemClickListener.onItemClick(position);
//                }
//            });

            return view;

        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
