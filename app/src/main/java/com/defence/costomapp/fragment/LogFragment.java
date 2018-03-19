package com.defence.costomapp.fragment;


import android.content.Context;
import android.content.Intent;
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
import com.defence.costomapp.activity.manage.WorkLogInfoActivity;
import com.defence.costomapp.base.BaseFragment;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.LogBean;
import com.defence.costomapp.myinterface.RVItemClickListener;
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
import butterknife.Unbinder;


/**
 * Created by author Sgq
 * on 2018/3/7.
 */


public class LogFragment extends BaseFragment {

    String groupid;
    @BindView(R.id.list_log)
    ListView listLog;
    Unbinder unbinder;
    @BindView(R.id.nomessageLL)
    LinearLayout nomessageLL;
    private SwipeRefreshLayout srl;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, null);
        srl = (SwipeRefreshLayout) view.findViewById(R.id.srl);
        initdata();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initdata() {

//        groupid= getActivity().getIntent().getStringExtra("groupidmm");
        groupid = SharePerenceUtil.getStringValueFromSp("groupid");
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getWorkLog();
            }
        });
        //获取工作日志
        getWorkLog();
    }

    private void getWorkLog() {

        RequestParams params = new RequestParams();
        params.put("adminGroupID", groupid);
        httpUtils.doPost(Urls.worklog(), SgqUtils.MANAGER_TYPE, params, new HttpInterface() {
            @Override
            public void onSuccess(Gson gson, Object result) {
                srl.setRefreshing(false);
                try {
                    JSONObject jsonObject = new JSONObject(result.toString());

                    LogBean logBean = gson.fromJson(jsonObject.toString(), LogBean.class);
                    final List<LogBean.ListBean> list = logBean.getList();
                    if (list.size() == 0) {
                        nomessageLL.setVisibility(View.VISIBLE);
                    } else {
                        nomessageLL.setVisibility(View.GONE);
                        LogAdapter logAdapter = new LogAdapter(getContext(), list, new RVItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                Intent intent = new Intent(getActivity(), WorkLogInfoActivity.class);
                                intent.putExtra("whoID", list.get(position).getShelvesuserid() + "");
                                startActivity(intent);
                            }
                        });

                        listLog.setAdapter(logAdapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Context context) {
                super.onFailure(context);
                srl.setRefreshing(false);
            }

            @Override
            public void onError(Context context, String message) {
                super.onError(context, message);
                srl.setRefreshing(false);
            }

            @Override
            public void onSpecial(Context context, String sign, String message) {
                super.onSpecial(context, sign, message);
                srl.setRefreshing(false);
            }
        });
    }

    private class LogAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        private List<LogBean.ListBean> list;
        private RVItemClickListener rvItemClickListener;

        public LogAdapter(Context context, List<LogBean.ListBean> list, RVItemClickListener rvItemClickListener) {
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
                view = inflater.inflate(R.layout.item_manager, null);
            }
            TextView name = view.findViewById(R.id.buhuomessage_item_name);
            TextView time = view.findViewById(R.id.buhuomessage_item_time);
            ImageView icon = view.findViewById(R.id.buhuomessage_item_icon);
            ImageView circle = view.findViewById(R.id.buhuomessage_item_newmessage);
            LinearLayout buhuoItemll = view.findViewById(R.id.buhuoitemll);

            circle.setVisibility(View.GONE);
            name.setText("维护人“" + list.get(position).getRealname() + "”工作日志");
            time.setText(list.get(position).getShelvesdate());

            buhuoItemll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rvItemClickListener.onItemClick(position);
                }
            });

            return view;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
