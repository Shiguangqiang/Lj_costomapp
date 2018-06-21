package com.defence.costomapp.activity.statistics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.bean.UserTjBean;
import com.defence.costomapp.fragment.UserTjFragment;
import com.defence.costomapp.utils.AmountUtils;

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
    @BindView(R.id.liear_left)
    LinearLayout liearLeft;
    @BindView(R.id.liear_right)
    LinearLayout liearRight;
    @BindView(R.id.et_FuzzyQuery)
    EditText etFuzzyQuery;
    @BindView(R.id.tv_totalRetention)
    TextView tvTotalRetention;
    @BindView(R.id.tv_consumerCard)
    TextView tvConsumerCard;
    @BindView(R.id.ll_retain)
    LinearLayout llRetain;


    private PopupWindow pop;
    private Handler handler;
    private int type = 1;
    private String mphone = "";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tj_new);
        ButterKnife.bind(this);

        middleTitle.setText("用户统计 - 登录时间");
        if (fragmentManager == null)
            fragmentManager = UserTjNewActivity.this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.usertj_LL, UserTjFragment.newInstance(type, mphone)).commitAllowingStateLoss();
        initpopdialog();
        initHanlder();

        etFuzzyQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mphone = etFuzzyQuery.getText().toString();
                    FragmentTransaction tf = fragmentManager.beginTransaction();
                    replaceFragment(tf, type, mphone);

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                    etFuzzyQuery.setText("");


                }
                return false;
            }
        });

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
                            tvChongzhi.setText("守望注册人数:" + bean.getRegnum()+"人");
                            regusernum.setText("平台付款次数:" + bean.getPingtaiNum()+"次");
                            saomausernum.setText("微信付款次数:" + bean.getWeixinNum()+"次");
                            pingtaiPay.setText("充值金额:" + AmountUtils.changeF2Y(bean.getChongzhinum() + "")+"元");
                            weixinpay.setText("平台留存余额:" + AmountUtils.changeF2Y(bean.getBankNo() + "")+"元");
                            tvTotalRetention.setText("总留存金额:" + AmountUtils.changeF2Y(bean.getBankNo() + bean.getXfkyuemony() + "")+"元");
                            tvConsumerCard.setText("消费卡留存:" + AmountUtils.changeF2Y(bean.getXfkyuemony() + "")+"元");
                            tvChongzhi.setVisibility(View.VISIBLE);
                            saomausernum.setVisibility(View.VISIBLE);
                            llRetain.setVisibility(View.VISIBLE);
                        } else {
                            regusernum.setText("注册人数:" + bean.getReg_user()+"人");
                            pingtaiPay.setText("平台付款次数:" + bean.getPingtaiNum()+"次");
                            weixinpay.setText("微信付款次数" + bean.getWeixinNum()+"次");

                            tvChongzhi.setVisibility(View.GONE);
                            saomausernum.setVisibility(View.GONE);
                            llRetain.setVisibility(View.GONE);


                        }
                        break;

                    case 2:
                        //请求失败
                        if (type == 5) {
                            tvChongzhi.setText("守望注册人数:");
                            regusernum.setText("平台付款次数:");
                            saomausernum.setText("微信付款次数:");
                            pingtaiPay.setText("充值金额:");
                            weixinpay.setText("平台留存余额:");
                            tvTotalRetention.setText("总留存金额:");
                            tvConsumerCard.setText("消费卡留存:");
                            tvChongzhi.setVisibility(View.VISIBLE);
                            saomausernum.setVisibility(View.VISIBLE);
                            llRetain.setVisibility(View.VISIBLE);
                        } else {
                            regusernum.setText("注册人数:");
                            pingtaiPay.setText("平台付款次数:");
                            weixinpay.setText("微信付款次数");
                            tvChongzhi.setVisibility(View.GONE);
                            saomausernum.setVisibility(View.GONE);
                            tvTotalRetention.setVisibility(View.GONE);
                            llRetain.setVisibility(View.GONE);

                        }

                        break;
                }
            }
        };
    }

    private FragmentManager fragmentManager;

    @OnClick(R.id.liear_left)
    public void onViewClicked() {
        finish();
    }

    private class DialogOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            FragmentTransaction tf = fragmentManager.beginTransaction();
            switch (view.getId()) {
                case R.id.tv_logintime:
                    middleTitle.setText("用户统计 - 登录时间");
                    type = 1;
                    mphone = "";
                    replaceFragment(tf, type, mphone);
                    break;
                case R.id.tv_yue:
                    middleTitle.setText("用户统计 - 账户余额");
                    type = 2;
                    mphone = "";
                    replaceFragment(tf, type, mphone);
                    break;
                case R.id.tv_reg_time:
                    middleTitle.setText("用户统计 - 注册时间");
                    type = 3;
                    mphone = "";
                    replaceFragment(tf, type, mphone);
                    break;

                case R.id.tv_weixinpay:
                    middleTitle.setText("用户统计 - 微信支付");
                    type = 4;
                    mphone = "";
                    replaceFragment(tf, type, mphone);
                    break;
                case R.id.tv_shouwang_history:
                    middleTitle.setText("用户统计 - 充值记录");
                    type = 5;
                    mphone = "";
                    replaceFragment(tf, type, mphone);
                    break;
            }
            if (pop.isShowing()) {
                pop.dismiss();
            }
        }
    }

    private void replaceFragment(FragmentTransaction tf, int type, String mphone) {
        tf.replace(R.id.usertj_LL, UserTjFragment.newInstance(type, mphone));
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

        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
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
                    // 设置背景颜色变暗
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 0.7f;
                    getWindow().setAttributes(lp);
                }
            }
        });

    }

}
