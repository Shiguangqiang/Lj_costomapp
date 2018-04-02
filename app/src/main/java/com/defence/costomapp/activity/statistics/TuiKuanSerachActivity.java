package com.defence.costomapp.activity.statistics;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.defence.costomapp.R;
import com.defence.costomapp.activity.LoginActivity;
import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SpUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TuiKuanSerachActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.liear_left)
    LinearLayout liearLeft;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.liear_right)
    LinearLayout liearRight;
    @BindView(R.id.tv_leftdate)
    TextView tvLeftdate;
    @BindView(R.id.tv_rightdate)
    TextView tvRightdate;
    @BindView(R.id.liear_machicelist)
    LinearLayout liearMachicelist;
    @BindView(R.id.liear_grouplist)
    LinearLayout liearGrouplist;
    @BindView(R.id.tv_startserach)
    TextView tvStartserach;
    @BindView(R.id.liear_shoplist)
    LinearLayout liearShoplist;
    @BindView(R.id.tv_ssshop)
    TextView tvSsshop;
    @BindView(R.id.tv_ssmacchine)
    TextView tvSsmacchine;
    @BindView(R.id.tv_ssgroup)
    TextView tvSsgroup;
    private String device;
    private String devicegroup;
    private String deviceshop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tui_kuan_serach);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        middleTitle.setText("退款查询");
        tvLeftdate.setText(SgqUtils.getNowDate());
        tvRightdate.setText(SgqUtils.getNowDate());

        try {
            List<Serializable> checkhisstring = SpUtil.getList(MyApplication.getApp(), "checkhisstring");
            if (checkhisstring != null && checkhisstring.size() > 0) {
                tvSsmacchine.setVisibility(View.VISIBLE);
                if (checkhisstring.size() == 1) {
                    tvSsmacchine.setText(checkhisstring.get(0) + "");
                } else if (checkhisstring.size() > 1) {
                    tvSsmacchine.setText(checkhisstring.get(0) + "等" + checkhisstring.size() + "个机器");
                } else {
                    tvSsmacchine.setText("");
                }
            }
            List<Serializable> checkgroupString = SpUtil.getList(MyApplication.getApp(), "checkgroupString");
            if (checkgroupString != null && checkgroupString.size() > 0) {
                tvSsgroup.setVisibility(View.VISIBLE);
                if (checkhisstring.size() == 1) {
                    tvSsgroup.setText(checkgroupString.get(0) + "");
                } else if (checkhisstring.size() > 1) {
                    tvSsgroup.setText(checkgroupString.get(0) + "等" + checkgroupString.size() + "个机器组");
                } else {
                    tvSsgroup.setText("");
                }
            }
            List<Serializable> intentcheckshop = SpUtil.getList(MyApplication.getApp(), "checkshopstring");
            if (intentcheckshop != null && intentcheckshop.size() > 0) {
                tvSsshop.setVisibility(View.VISIBLE);
                if (intentcheckshop.size() == 1) {
                    tvSsshop.setText(checkgroupString.get(0) + "");
                } else if (intentcheckshop.size() > 1) {
                    tvSsshop.setText(intentcheckshop.get(0) + "等" + intentcheckshop.size() + "个商品");
                } else {
                    tvSsshop.setText("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    Calendar ca = Calendar.getInstance();
    int mYear = ca.get(Calendar.YEAR);
    int mMonth = ca.get(Calendar.MONTH);
    int mDay = ca.get(Calendar.DAY_OF_MONTH);
    /**
     * 日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).toString();
                }
            }
            tvLeftdate.setText(days);

        }
    };
   

    private DatePickerDialog.OnDateSetListener onDateSetListenerright = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).toString();
                }
            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).toString();
                }
            }
            tvRightdate.setText(days);

        }
    };

    //    重写onActivityResult方法，用来接收B回传的数据。
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case 0:
                if (data != null) {
                    device = data.getStringExtra("device");
                    ArrayList<String> intentcheckhisstring = data.getStringArrayListExtra("intentcheckhisstring");
                    if (intentcheckhisstring != null && intentcheckhisstring.size() > 0) {
                        tvSsmacchine.setVisibility(View.VISIBLE);
                        if (intentcheckhisstring.size() == 1) {
                            tvSsmacchine.setText(intentcheckhisstring.get(0) + "");
                        } else if (intentcheckhisstring.size() > 1) {
                            tvSsmacchine.setText(intentcheckhisstring.get(0) + "等" + intentcheckhisstring.size() + "个机器");
                        } else {
                            tvSsmacchine.setText("");
                        }
                    }
                }

                break;
            case 1:
                if (data != null) {
                    devicegroup = data.getStringExtra("devicegroup");
                    ArrayList<String> intentcheckgroupString = data.getStringArrayListExtra("intentcheckgroupString");
                    if (intentcheckgroupString != null && intentcheckgroupString.size() > 0) {
                        tvSsgroup.setVisibility(View.VISIBLE);
                        if (intentcheckgroupString.size() == 1) {
                            tvSsgroup.setText(intentcheckgroupString.get(0) + "");
                        } else if (intentcheckgroupString.size() > 1) {
                            tvSsgroup.setText(intentcheckgroupString.get(0) + "等" + intentcheckgroupString.size() + "个机器组");
                        } else {
                            tvSsgroup.setText("");
                        }
                    }
                }
            case 2:
                if (data != null) {
                    deviceshop = data.getStringExtra("deviceshop");
                    ArrayList<String> intentcheckshop = data.getStringArrayListExtra("intentcheckshop");
                    if (intentcheckshop != null && intentcheckshop.size() > 0) {
                        tvSsshop.setVisibility(View.VISIBLE);
                        if (intentcheckshop.size() == 1) {
                            tvSsshop.setText(intentcheckshop.get(0) + "");
                        } else if (intentcheckshop.size() > 1) {
                            tvSsshop.setText(intentcheckshop.get(0) + "等" + intentcheckshop.size() + "个商品");
                        } else {
                            tvSsshop.setText("");
                        }
                    }


                }
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.liear_shoplist, R.id.tv_leftdate, R.id.tv_rightdate, R.id.liear_left, R.id.liear_right, R.id.liear_machicelist, R.id.liear_grouplist, R.id.tv_startserach})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.liear_left:
                finish();
                break;
            case R.id.tv_leftdate:
                //TODO 调用时间选择器
                new DatePickerDialog(TuiKuanSerachActivity.this, R.style.MyDatePickerDialogTheme, onDateSetListener, mYear, mMonth, mDay).show();
                break;
            case R.id.tv_rightdate:
                //TODO 调用时间选择器
                new DatePickerDialog(TuiKuanSerachActivity.this, R.style.MyDatePickerDialogTheme, onDateSetListenerright, mYear, mMonth, mDay).show();
                break;
            case R.id.liear_machicelist:
                if (!TextUtils.isEmpty(devicegroup)) {
                    Toast.makeText(TuiKuanSerachActivity.this, "机器和机器组只能选择一种!", Toast.LENGTH_SHORT).show();
                } else {
                    startActivityForResult(new Intent(TuiKuanSerachActivity.this, TkMachinListActivity.class), 144);
                }
                break;
            case R.id.liear_grouplist:
                if (!TextUtils.isEmpty(device)) {
                    Toast.makeText(TuiKuanSerachActivity.this, "机器组和机器只能选择一种!", Toast.LENGTH_SHORT).show();
                } else {
                    startActivityForResult(new Intent(TuiKuanSerachActivity.this, TkGroupActivity.class), 166);
                }

                break;
            case R.id.liear_shoplist:
                startActivityForResult(new Intent(TuiKuanSerachActivity.this, TkShopActivity.class), 144);
                break;
            case R.id.tv_startserach:

                if (TextUtils.isEmpty(device) && TextUtils.isEmpty(devicegroup) && TextUtils.isEmpty(deviceshop)) {
                    new AlertDialog.Builder(this)
                            .setMessage("请至少选择一种查询方式")
                            .setNegativeButton("好", null)
                            .show();
                } else {
                    Intent intentss = new Intent(TuiKuanSerachActivity.this, TuikuanListActivity.class);
                    intentss.putExtra("sdate", tvLeftdate.getText().toString());
                    intentss.putExtra("edate", tvRightdate.getText().toString());
                    intentss.putExtra("machineNumber", device);
                    intentss.putExtra("formatid", deviceshop);
                    intentss.putExtra("groupMachineNumber", devicegroup);
                    startActivity(intentss);
                    finish();
                }
                break;
        }
    }

    @OnClick()
    public void onViewClicked() {
    }
}
