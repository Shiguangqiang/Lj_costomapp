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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.base.Urls;
import com.defence.costomapp.bean.UserTjBean;
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

/**
 * 用户统计
 */
public class UserTjActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.regusernum)
    TextView regusernum;
    @BindView(R.id.saomausernum)
    TextView saomausernum;
    @BindView(R.id.pingtai_pay)
    TextView pingtaiPay;
    @BindView(R.id.weixinpay)
    TextView weixinpay;
    @BindView(R.id.list_usertj)
    ListView listUsertj;
    @BindView(R.id.srl)
    RefreshLayout srl;


    private PopupWindow pop;

    int length = 0;
    String sortOrderBy = "timeline";
    private UserTjAdapter userTjAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tj);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        middleTitle.setText("用户统计 - 登录时间");
        // 设置下拉刷新时的颜色值,颜色值需要定义在xml中
//        srl
//                .setColorScheme(R.color.umeng_comm_text_topic_light_color,
//                        R.color.umeng_comm_yellow, R.color.umeng_comm_green,
//                        R.color.umeng_comm_linked_text);
//        // 设置下拉刷新监听器
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (length > 0) {
                    length--;
                    getData(length, sortOrderBy);
                    userTjAdapter.notifyDataSetChanged();
                } else {
                    srl.setRefreshing(false);
                }


            }
        });
//        上拉加载
        srl.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {

                length++;
                getData(length, sortOrderBy);
                userTjAdapter.notifyDataSetChanged();

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getData(length, sortOrderBy);

        initpopdialog();


    }

    /*初始化从底部弹出*/
    private void initpopdialog() {

        final View view = getLayoutInflater().inflate(R.layout.pop_dialog, null);
        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv_logintime = view.findViewById(R.id.tv_logintime);
        TextView tv_yue = view.findViewById(R.id.tv_yue);
        TextView tv_reg_time = view.findViewById(R.id.tv_reg_time);
        TextView tv_weixinpay = view.findViewById(R.id.tv_weixinpay);
        TextView tv_shouwang_history = view.findViewById(R.id.tv_shouwang_history);

        tv_logintime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                middleTitle.setText("用户统计 - 登录时间");
                sortOrderBy = "timeline";
                getData(length, sortOrderBy);
                if (pop.isShowing()) {
                    pop.dismiss();
                }
            }
        });
        tv_yue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                middleTitle.setText("用户统计 - 账户余额");
                sortOrderBy = "bankNo";
                getData(length, sortOrderBy);
                if (pop.isShowing()) {
                    pop.dismiss();
                }

            }
        });
        tv_reg_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                middleTitle.setText("用户统计 - 注册时间");
                sortOrderBy = "reg_time";
                getData(length, sortOrderBy);
                if (pop.isShowing()) {
                    pop.dismiss();
                }

            }
        });
        tv_weixinpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                middleTitle.setText("用户统计 - 微信支付");
                getData(length, sortOrderBy);
                if (pop.isShowing()) {
                    pop.dismiss();
                }
            }
        });
        tv_shouwang_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                middleTitle.setText("用户统计 - 充值记录");
                getData(length, sortOrderBy);
                if (pop.isShowing()) {
                    pop.dismiss();
                }
            }
        });


        pop.setOutsideTouchable(true);
        pop.setFocusable(true);// 点击back退出pop
        pop.setAnimationStyle(R.style.add_new_style);
        pop.setBackgroundDrawable(new ColorDrawable(Color.argb(136, 0, 0, 0)));// 设置背景透明，点击back退出pop
        rightIcon.setImageResource(R.mipmap.all);


        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pop.isShowing()) {
                    pop.dismiss();
                } else {
                    pop.showAtLocation(view, Gravity.BOTTOM, 0, -560);//在父控件下方出来
                    pop.showAsDropDown(view);
                }
            }
        });

    }

    /*得到数据*/
    private void getData(int length, String sortOrderBy) {

        RequestParams params = new RequestParams();
        params.put("length", length + "");
        params.put("sortOrderBy", sortOrderBy);
        params.put("order", "desc");
        params.put("orderBy", "2");
        params.put("endpag", "10");

        httpUtils.doPost(Urls.userTj(), SgqUtils.TONGJI_TYPE, params, new HttpInterface() {

            @Override
            public void onSuccess(Gson gson, Object result) throws JSONException {
                srl.setRefreshing(false);
                srl.setLoading(false);
                JSONObject jsonObject = new JSONObject(result.toString());
                final UserTjBean userTjBean = gson.fromJson(jsonObject.toString(), UserTjBean.class);
                regusernum.setText("注册人数:" + userTjBean.getReg_user());
                pingtaiPay.setText("平台付款次数:" + userTjBean.getPingtaiNum());
                weixinpay.setText("微信付款次数" + userTjBean.getWeixinNum());

                userTjAdapter = new UserTjAdapter(UserTjActivity.this, userTjBean.getUserList(), new RVItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(UserTjActivity.this, UserTjDetailActivity.class);
                        intent.putExtra("uid", (userTjBean.getUserList().get(position).getId())+"");
                        intent.putExtra("uname", userTjBean.getUserList().get(position).getName());
                        startActivity(intent);
                    }
                });

                listUsertj.setAdapter(userTjAdapter);
            }
        });

    }


    private class UserTjAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        private List<UserTjBean.UserListBean> userList;
        private RVItemClickListener rvItemClickListener;

        public UserTjAdapter(Context context, List<UserTjBean.UserListBean> userList, RVItemClickListener rvItemClickListener) {
            super();
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.userList = userList;
            this.rvItemClickListener = rvItemClickListener;

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            if (userList != null && userList.size() > 0) {
                return userList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return userList.get(arg0);
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
                view = inflater.inflate(R.layout.item_usertj, null);
            }
            TextView reg_phone = view.findViewById(R.id.reg_phone);
            TextView tv_yue = view.findViewById(R.id.tv_yue);
            TextView tv_nearlogintime = view.findViewById(R.id.tv_nearlogintime);
            LinearLayout liearitemll = view.findViewById(R.id.liearitemll);

            reg_phone.setText(userList.get(position).getMphone());
            tv_yue.setText("账户余额:" + userList.get(position).getBankNo() + "元");
            tv_nearlogintime.setText("最近登录时间:" + userList.get(position).getTimeline());

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
