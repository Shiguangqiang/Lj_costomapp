package com.defence.costomapp.activity.statistics;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.bean.UserTjBean;
import com.defence.costomapp.fragment.UserTjFragment;
import com.defence.costomapp.utils.AmountUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserTjNewActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.tv_chongzhi)
    TextView tvChongzhi;
    @BindView(R.id.regusernum)
    TextView regusernum;
    @BindView(R.id.saomausernum)
    TextView saomausernum;
    @BindView(R.id.pingtai_pay)
    TextView pingtaiPay;
    @BindView(R.id.weixinpay)
    TextView weixinpay;
    @BindView(R.id.usertj_LL)
    LinearLayout ll;


    private PopupWindow pop;
    private Handler handler;
    private int type = 1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tj_new);
        ButterKnife.bind(this);


        middleTitle.setText("用户统计 - 登录时间");
        if (fragmentManager == null)
            fragmentManager = UserTjNewActivity.this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.usertj_LL, UserTjFragment.newInstance(type)).commitAllowingStateLoss();
        initpopdialog();
        initHanlder();

    }

    public Handler getHandler() {
        return handler;
    }

    @SuppressLint("HandlerLeak")

    private void initHanlder() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        //请求成功
                        UserTjBean bean = (UserTjBean) msg.obj;

                        if (type == 5) {
                            tvChongzhi.setText("守望注册人数:" + bean.getRegnum());
                            regusernum.setText("平台付款次数:" + bean.getPingtaiNum());
                            saomausernum.setText("微信付款次数:" + bean.getWeixinNum());
                            pingtaiPay.setText("充值金额:" + AmountUtils.changeF2Y(bean.getChongzhinum() + ""));
                            weixinpay.setText("平台留存余额:" + AmountUtils.changeF2Y(bean.getBankNo() + ""));
                            tvChongzhi.setVisibility(View.VISIBLE);
                            saomausernum.setVisibility(View.VISIBLE);
                        } else {
                            regusernum.setText("注册人数:" + bean.getReg_user());
                            pingtaiPay.setText("平台付款次数:" + bean.getPingtaiNum());
                            weixinpay.setText("微信付款次数" + bean.getWeixinNum());
                            tvChongzhi.setVisibility(View.GONE);
                            saomausernum.setVisibility(View.GONE);

                        }
                        break;

                    case 2:
                        //请求失败
                        if (type == 5) {
                            tvChongzhi.setText("守望注册人数:" );
                            regusernum.setText("平台付款次数:");
                            saomausernum.setText("微信付款次数:" );
                            pingtaiPay.setText("充值金额:");
                            weixinpay.setText("平台留存余额:");
                            tvChongzhi.setVisibility(View.VISIBLE);
                            saomausernum.setVisibility(View.VISIBLE);
                        } else {
                            regusernum.setText("注册人数:" );
                            pingtaiPay.setText("平台付款次数:");
                            weixinpay.setText("微信付款次数");
                            tvChongzhi.setVisibility(View.GONE);
                            saomausernum.setVisibility(View.GONE);

                        }

                        break;
                }


            }
        };
    }

    private FragmentManager fragmentManager;

    private class DialogOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            android.support.v4.app.FragmentTransaction tf = fragmentManager.beginTransaction();
            switch (view.getId()) {
                case R.id.tv_logintime:
                    middleTitle.setText("用户统计 - 登录时间");
                    type = 1;
                    replaceFragment(tf, type);
                    break;
                case R.id.tv_yue:
                    middleTitle.setText("用户统计 - 账户余额");
                    type = 2;
                    replaceFragment(tf, type);
                    break;
                case R.id.tv_reg_time:
                    middleTitle.setText("用户统计 - 注册时间");
                    type = 3;
                    replaceFragment(tf, type);
                    break;

                case R.id.tv_weixinpay:
                    middleTitle.setText("用户统计 - 微信支付");
                    type = 4;
                    replaceFragment(tf, type);
                    break;
                case R.id.tv_shouwang_history:
                    middleTitle.setText("用户统计 - 充值记录");
                    type = 5;
                    replaceFragment(tf, type);
                    break;
            }
            if (pop.isShowing()) {
                pop.dismiss();
            }
        }
    }

    private void replaceFragment(FragmentTransaction tf, int type) {
        tf.replace(R.id.usertj_LL, UserTjFragment.newInstance(type));
        tf.commitAllowingStateLoss();
    }

    private void initpopdialog() {
        DialogOnclickListener dialogOnclickListener = new DialogOnclickListener();
        final View view = getLayoutInflater().inflate(R.layout.pop_dialog, null);
        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        view.findViewById(R.id.tv_logintime).setOnClickListener(dialogOnclickListener);
        view.findViewById(R.id.tv_yue).setOnClickListener(dialogOnclickListener);
        view.findViewById(R.id.tv_reg_time).setOnClickListener(dialogOnclickListener);
        view.findViewById(R.id.tv_weixinpay).setOnClickListener(dialogOnclickListener);
        view.findViewById(R.id.tv_shouwang_history).setOnClickListener(dialogOnclickListener);


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

    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

        }
    }
}
