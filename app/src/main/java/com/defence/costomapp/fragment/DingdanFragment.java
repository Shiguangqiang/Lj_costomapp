package com.defence.costomapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.defence.costomapp.R;
import com.defence.costomapp.activity.statistics.DingdanDetailActivity;
import com.defence.costomapp.activity.statistics.UserTjNewActivity;
import com.defence.costomapp.adapter.DingdanAdapter;
import com.defence.costomapp.base.BaseFragment;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.DingdanBean;
import com.defence.costomapp.myinterface.RVItemClickListener;
import com.defence.costomapp.utils.DividerItemDecoration;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SharePerenceUtil;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.defence.costomapp.utils.httputils.HttpUtils;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DingdanFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private int type;
    private DingdanAdapter dingdanAdapter;
    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;
    private String url;
    private int length = 0;
    private List<DingdanBean.ListBean> list;

    public DingdanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DingdanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DingdanFragment newInstance(int type) {
        DingdanFragment fragment = new DingdanFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(ARG_PARAM1);
        }
        httpUtils = new HttpUtils(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_dingdan, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pullLoadMoreRecyclerView = view.findViewById(R.id.srl);
        pullLoadMoreRecyclerView.setLinearLayout();
        pullLoadMoreRecyclerView.setFooterViewText("loading");
        pullLoadMoreRecyclerView.getRecyclerView().addItemDecoration(new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL_LIST));
        pullLoadMoreRecyclerView.setFooterViewTextColor(R.color.blue);
        pullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                length = 0;
                list.clear();
                initData();
            }

            @Override
            public void onLoadMore() {
                length++;
                initData();
            }
        });
        initData();
    }

    private void initData() {
        doPost();

    }

    @NonNull
    private RequestParams setUrlRequestParams() {
        String groupid = SharePerenceUtil.getStringValueFromSp("groupid");
        RequestParams params = new RequestParams();
        switch (type) {
//            交易成功订单: 下单时间排序
//                    status=4
            case 4:
                url = Urls.dingdan();
                params.put("iCount", (length * 10) + "");
                params.put("adminGroupID", groupid);
                params.put("status", "4");
                break;
//            待出货订单: 付款时间排序
//                    status=3
            case 3:
                url = Urls.dingdan();
                params.put("iCount", (length * 10) + "");
                params.put("adminGroupID", groupid);
                params.put("status", "3");
                break;
//            退款成功订单: 退款时间排序
//                    status=5,6
            case 5:
                url = Urls.dingdan();
                params.put("iCount", (length * 10) + "");
                params.put("adminGroupID", groupid);
                params.put("status", "5,6");
                break;
            //            退款成功订单: 退款时间排序
//                    status=5,6
//          全部订单
//          status=   传空值
            case 0:
                url = Urls.dingdan();
                params.put("iCount", (length * 10) + "");
                params.put("adminGroupID", groupid);
                params.put("status", "");
                break;
        }
        return params;
    }

    private void doPost() {

        RequestParams params = setUrlRequestParams();
        httpUtils.doPost(url, SgqUtils.TONGJI_TYPE, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result, String message) {
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                try {
                    JSONObject jsonObject = new JSONObject(result.toString());
                    final DingdanBean userTjBean = gson.fromJson(jsonObject.toString(), DingdanBean.class);

                    if (list == null)
                        list = new ArrayList();

                    list.addAll(userTjBean.getList());

                    if (dingdanAdapter == null) {
                        dingdanAdapter = new DingdanAdapter(getContext(), list, new RVItemClickListener() {

                            @Override
                            public void onItemClick(int position) {
                                Intent intent = new Intent(getActivity(), DingdanDetailActivity.class);
                                intent.putExtra("numberID", list.get(position).getNumberID());
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);


                            }
                        }, type);

                        pullLoadMoreRecyclerView.setAdapter(dingdanAdapter);
                    } else {
                        dingdanAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Context context, String message) {
                super.onError(context, message);
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                ((UserTjNewActivity) getActivity()).getHandler().obtainMessage(2);

            }

            @Override
            public void onFailure(Context context) {
                super.onFailure(context);
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                ((UserTjNewActivity) getActivity()).getHandler().obtainMessage(2);
            }

            @Override
            public void onSpecial(Context context, String sign, String message) {
                super.onSpecial(context, sign, message);
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                ((UserTjNewActivity) getActivity()).getHandler().obtainMessage(2);
            }
        });
    }


}
