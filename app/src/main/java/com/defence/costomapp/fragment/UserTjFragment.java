package com.defence.costomapp.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.defence.costomapp.activity.statistics.ChongzhiDetailActivity;
import com.defence.costomapp.activity.statistics.UserTjDetailActivity;
import com.defence.costomapp.activity.statistics.UserTjNewActivity;
import com.defence.costomapp.adapter.UserTjAdapter;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.UserTjBean;
import com.defence.costomapp.myinterface.RVItemClickListener;
import com.defence.costomapp.utils.DividerItemDecoration;
import com.defence.costomapp.R;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.httputils.HttpInterface;
import com.defence.costomapp.utils.httputils.HttpUtils;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserTjFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserTjFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int type;


    public UserTjFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param type Parameter 1.
     * @return A new instance of fragment UserTjFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserTjFragment newInstance(int type) {
        UserTjFragment fragment = new UserTjFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, type);
        fragment.setArguments(args);
        return fragment;
    }

    private HttpUtils httpUtils;

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
        return inflater.inflate(R.layout.fragment_user_tj, container, false);
    }

    private PullLoadMoreRecyclerView pullLoadMoreRecyclerView;

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

    private String url;
    private int length = 0;

    private void initData() {
        doPost();

    }

    private UserTjAdapter userTjAdapter;
    private  List<UserTjBean.ListBean> list;

    private void doPost() {

        RequestParams params = setUrlRequestParams();
        httpUtils.doPost(url, SgqUtils.TONGJI_TYPE, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result) {
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                try {
                    JSONObject jsonObject = new JSONObject(result.toString());
                    final UserTjBean userTjBean = gson.fromJson(jsonObject.toString(), UserTjBean.class);
                    Message msg = ((UserTjNewActivity) getActivity()).getHandler().obtainMessage(1);
                    msg.obj = userTjBean;
                    msg.sendToTarget();

                    if (list == null)
                        list = new ArrayList();



                    UserTjFragment.this.list.addAll(userTjBean.getList());

                    if (userTjAdapter == null) {
                        userTjAdapter = new UserTjAdapter(getContext(), UserTjFragment.this.list, new RVItemClickListener() {
                            @Override
                            public void onItemClick(int position) {

                                if (type == 5) {
                                    Intent intent = new Intent(getActivity(), ChongzhiDetailActivity.class);
                                    intent.putExtra("userid", list.get(position).getUserID() + "");
                                    intent.putExtra("phone", list.get(position).getMphone() + "");
                                    startActivity(intent);
                                } else {
                                    Intent intent = new Intent(getActivity(), UserTjDetailActivity.class);
                                    intent.putExtra("uid", (list.get(position).getId()) + "");
                                    intent.putExtra("uname", list.get(position).getName());
                                    intent.putExtra("phone", list.get(position).getMphone() + "");
                                    intent.putExtra("ttype", type + "");
                                    intent.putExtra("wxid", list.get(position).getWxOpenID() + "");
                                    startActivity(intent);
                                }

                            }

                        }, type);

                        pullLoadMoreRecyclerView.setAdapter(userTjAdapter);
                    } else {
                        userTjAdapter.notifyDataSetChanged();
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

                try {
                    pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    ((UserTjNewActivity) getActivity()).getHandler().obtainMessage(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onSpecial(Context context, String sign, String message) {
                super.onSpecial(context, sign, message);
                pullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                ((UserTjNewActivity) getActivity()).getHandler().obtainMessage(2);
            }
        });

    }

    @NonNull
    private RequestParams setUrlRequestParams() {
        RequestParams params = new RequestParams();
        switch (type) {
            case 1:
                url = Urls.userTj();
                params.put("length", (length * 10) + "");
                params.put("sortOrderBy", "timeline");
                params.put("order", "desc");
                params.put("orderBy", "2");
                params.put("endpag", "10");
                break;
            case 2:
                url = Urls.userTj();
                params.put("length", (length * 10) + "");
                params.put("sortOrderBy", "bankNo");
                params.put("order", "desc");
                params.put("orderBy", "2");
                params.put("endpag", "10");
                break;
            case 3:
                url = Urls.userTj();
                params.put("length", (length * 10) + "");
                params.put("sortOrderBy", "reg_time");
                params.put("order", "desc");
                params.put("orderBy", "2");
                params.put("endpag", "10");
                break;
            case 4:
                url = Urls.wxpay();
                params.put("length", (length * 10) + "");
                params.put("orderUID", "0");
                params.put("orderBy", "2");
                params.put("endpag", "10");
                break;
            case 5:
                url = Urls.chongzhi();
                params.put("begin", (length * 10) + "");
                params.put("end", "10");
                params.put("orderBy", "2");
                break;
        }
        return params;
    }
}
