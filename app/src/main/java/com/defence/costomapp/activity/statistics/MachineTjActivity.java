package com.defence.costomapp.activity.statistics;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.defence.costomapp.R;
import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.utils.MyNumberPicker;
import com.defence.costomapp.utils.SgqUtils;
import com.defence.costomapp.utils.SpUtil;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 自助机统计查询
 */
public class MachineTjActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.middle_title)
    TextView middleTitle;
    @BindView(R.id.right_title)
    TextView rightTitle;
    @BindView(R.id.tv_leftdate)
    TextView tvLeftdate;
    @BindView(R.id.tv_rightdate)
    TextView tvRightdate;
    @BindView(R.id.tv_add)
    TextView tvAdd;

    @BindView(R.id.liear_left)
    LinearLayout liearLeft;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.liear_right)
    LinearLayout liearRight;
    @BindView(R.id.liear_machicelist)
    LinearLayout liearMachicelist;
    @BindView(R.id.liear_grouplist)
    LinearLayout liearGrouplist;
    @BindView(R.id.btn_startserach)
    TextView btnStartserach;
    @BindView(R.id.chu_rb)
    RadioButton chuRb;
    @BindView(R.id.tuikuan_rb)
    RadioButton tuikuanRb;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.tv_machis)
    TextView tvMachis;
    @BindView(R.id.tv_macgroup)
    TextView tvMacgroup;
    private String device;
    private String devicegroup;

    private String xianICStr = "0";
    private String shiICStr = "0";
    private String shengIcStr = "0";
    private String status = "";
    private String checkhisstring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_tj);
        ButterKnife.bind(this);
        initData();
        init();
    }

    private void init() {
        middleTitle.setText("统计");
        tvLeftdate.setText(SgqUtils.getNowDate());
        tvRightdate.setText(SgqUtils.getNowDate());

        try {
            List<Serializable> checkhisstring = SpUtil.getList(MyApplication.getApp(), "checkhisstring");
            if (checkhisstring != null && checkhisstring.size() > 0) {
                tvMachis.setVisibility(View.VISIBLE);
                if (checkhisstring.size() == 1) {
                    tvMachis.setText(checkhisstring.get(0) + "");
                } else if (checkhisstring.size() > 1) {
                    tvMachis.setText(checkhisstring.get(0) + "等" + checkhisstring.size() + "个机器");
                } else {
                    tvMachis.setText("");
                }
            }
            List<Serializable> checkgroupString = SpUtil.getList(MyApplication.getApp(), "checkgroupString");
            if (checkgroupString != null && checkgroupString.size() > 0) {
                tvMacgroup.setVisibility(View.VISIBLE);
                if (checkhisstring.size() == 1) {
                    tvMacgroup.setText(checkgroupString.get(0) + "");
                } else if (checkhisstring.size() > 1) {
                    tvMacgroup.setText(checkgroupString.get(0) + "等" + checkgroupString.size() + "个机器组");
                } else {
                    tvMacgroup.setText("");
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
                        tvMachis.setVisibility(View.VISIBLE);
                        if (intentcheckhisstring.size() == 1) {
                            tvMachis.setText(intentcheckhisstring.get(0) + "");
                        } else if (intentcheckhisstring.size() > 1) {
                            tvMachis.setText(intentcheckhisstring.get(0) + "等" + intentcheckhisstring.size() + "个机器");
                        } else {
                            tvMachis.setText("");
                        }
                    }
                }
                break;
            case 1:
                if (data != null) {
                    devicegroup = data.getStringExtra("devicegroup");
                    ArrayList<String> intentcheckgroupString = data.getStringArrayListExtra("intentcheckgroupString");

                    if (intentcheckgroupString != null && intentcheckgroupString.size() > 0) {
                        tvMacgroup.setVisibility(View.VISIBLE);
                        if (intentcheckgroupString.size() == 1) {
                            tvMacgroup.setText(intentcheckgroupString.get(0) + "");
                        } else if (intentcheckgroupString.size() > 1) {
                            tvMacgroup.setText(intentcheckgroupString.get(0) + "等" + intentcheckgroupString.size() + "个机器组");
                        } else {
                            tvMacgroup.setText("");
                        }
                    }
                }

                break;
            default:
                break;
        }
    }

    @OnClick({R.id.liear_machicelist, R.id.liear_grouplist, R.id.chu_rb, R.id.tuikuan_rb, R.id.liear_left, R.id.tv_leftdate, R.id.tv_rightdate, R.id.tv_add, R.id.btn_startserach})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.liear_machicelist:
                startActivityForResult(new Intent(MachineTjActivity.this, TkMachinListActivity.class), 144);
                break;
            case R.id.liear_grouplist:
                startActivityForResult(new Intent(MachineTjActivity.this, TkGroupActivity.class), 166);
                break;
            case R.id.chu_rb:
                break;
            case R.id.tuikuan_rb:
                break;
            case R.id.liear_left:
                finish();
                break;
            case R.id.tv_leftdate:
                //TODO 调用时间选择器
                new DatePickerDialog(MachineTjActivity.this, R.style.MyDatePickerDialogTheme, onDateSetListener, mYear, mMonth, mDay).show();
                break;
            case R.id.tv_rightdate:
                //TODO 调用时间选择器
                new DatePickerDialog(MachineTjActivity.this, R.style.MyDatePickerDialogTheme, onDateSetListenerright, mYear, mMonth, mDay).show();
                break;
            case R.id.tv_add:
//                ShowPickerView();
                ShowNewPickerView();
                break;
            case R.id.btn_startserach:


                if (TextUtils.isEmpty(device) && TextUtils.isEmpty(devicegroup)) {


//                    new MyDialogFragment().setOnButtonClickListener(new MyDialogFragment.OnDialogButtonClickListener() {
//
//                        @Override
//                        public void okButtonClick() {
//                            Log.i("debug", "ok button click");
//                        }
//
//                    });

//
                    new AlertDialog.Builder(this)
                            .setMessage("请至少选择一种查询方式")
//                            .setNegativeButton("好", null)
                            .setPositiveButton("好", null)
//                            .setNeutralButton("好", null)
                            .show();
                } else {

                    if (chuRb.isChecked()) {
                        status = "4";
                    }
                    if (tuikuanRb.isChecked()) {
                        status = "5,6";
                    }
                    if (!TextUtils.isEmpty(devicegroup)) {
                        device = devicegroup;
                    }

                    Intent intent = new Intent(MachineTjActivity.this, MachineSerachActivity.class);
                    intent.putExtra("leftdate", tvLeftdate.getText().toString());
                    intent.putExtra("rightdate", tvRightdate.getText().toString());
                    intent.putExtra("tvAdd", tvAdd.getText().toString());
                    intent.putExtra("addr1", shengIcStr);
                    intent.putExtra("addr2", shiICStr);
                    intent.putExtra("addr3", xianICStr);
                    intent.putExtra("device", device);
                    intent.putExtra("status", status);
                    startActivity(intent);
                    finish();
                }

                break;
        }
    }


    public AlertDialog alertDialog;
    private View ad_view;
    private MyNumberPicker sheng, shi, xian;
    private String[] shengs, shis, xians;
    private final int checkCity = 1;
    private final int checkCountry = 2;
    private TreeMap<Integer, String> locationMap;
    private TreeMap<Integer, String> provinces;
    private ArrayList<String> temp;
    private int iVal = 100;
    private Button location_ok;

    private void initData() {
        locationMap = new TreeMap<>();
        provinces = new TreeMap<>();
        temp = new ArrayList<>();
        try {
            String text = SgqUtils.getAssetsFile(this);
            JSONArray array = new JSONArray(text);
            for (int i = 0; i < array.length(); i++) {
                locationMap.put(Integer.parseInt(array.getJSONObject(i).getString("ic")), array.getJSONObject(i).getString("txt"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        shengs = initProvinces();

    }


    private void ShowNewPickerView() {

        if (alertDialog == null) {
            ad_view = LayoutInflater.from(MachineTjActivity.this).inflate(R.layout.location_item, null);
            alertDialog = new AlertDialog.Builder(MachineTjActivity.this).setView(ad_view).create();
            initAD(ad_view);
        }
        setData();
        alertDialog.show();


    }

    private void initAD(View ad_view) {
        sheng = ad_view.findViewById(R.id.sheng);
        shi = ad_view.findViewById(R.id.shi);
        xian = ad_view.findViewById(R.id.xian);
        sheng.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        shi.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        xian.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        xian.setMinValue(0);
        xian.setValue(0);

        location_ok = ad_view.findViewById(R.id.location_ok);
        location_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                xianICStr = String.valueOf(ValueToKey(xians[xian.getValue()], 2));
                shiICStr = xianICStr.substring(0, 4);
                shengIcStr = xianICStr.substring(0, 2);

                tvAdd.setText(shengs[sheng.getValue()] + shis[shi.getValue()] + xians[xian.getValue()]);
                if (alertDialog != null) {
                    alertDialog.dismiss();
                }
            }
        });

    }

    private void setData() {
        sheng.setMinValue(0);
        sheng.setMaxValue(shengs.length - 1);
        sheng.setValue(0);
        sheng.setDisplayedValues(shengs);
        changeCity(0);
        sheng.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, final int newVal) {
                changeCity(newVal);
            }
        });
        shi.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                changeCountry(newVal);
            }
        });
    }

    //根据省,联动市数据
    private void changeCity(int newVal) {
        shis = getNames(ValueToKey(shengs[newVal], checkCity));
        try {
            shi.setMinValue(0);
            shi.setMaxValue(shis.length - 1);
            shi.setDisplayedValues(shis);//设置选择器数据、默认值
        } catch (Exception e) {
            try {
                shi.setDisplayedValues(shis);//设置选择器数据、默认值
                shi.setMinValue(0);
                shi.setMaxValue(shis.length - 1);
            } catch (Exception e1e) {
            }
        }
        shi.setValue(0);
        changeCountry(0);
    }

    //根据市,联动县数据
    private void changeCountry(int newVal) {
        xians = getNames(ValueToKey(shis[newVal], checkCountry));
        try {
            xian.setMinValue(0);
            xian.setMaxValue(xians.length - 1);
            xian.setDisplayedValues(xians);//设置选择器数据、默认值
        } catch (Exception e) {
            xian.setDisplayedValues(xians);//设置选择器数据、默认值
            xian.setMinValue(0);
            xian.setMaxValue(xians.length - 1);
        }
        xian.setValue(0);

    }

    private int ValueToKey(String value, int type) {
        Set<Integer> keys = locationMap.keySet();
        Iterator<Integer> iterator = keys.iterator();
        while (iterator.hasNext()) {
            int key = iterator.next();
            String temp = locationMap.get(key);
            if (value.equals(temp)) {
                if (type == checkCity) {
                    return key;
                } else if (type == checkCountry) {
                    if (key > 100) {
                        return key;
                    } else {
                        continue;
                    }

                }

            }
        }
        return -1;
    }

    //初始化省列表
    private String[] initProvinces() {
        int minVal = 0;
        int maxVal = 99;
        for (int i = minVal; i < maxVal; i++) {
            if (locationMap.containsKey(i)) {
                provinces.put(i, locationMap.get(i));
            }

        }
        Collection<String> values = provinces.values();
        return values.toArray(new String[values.size()]);
    }

    private String[] getNames(int ic) {
        temp.clear();
        int minVal = ic * iVal;
        int maxVal = minVal + iVal;
        for (int i = minVal; i < maxVal; i++) {
            if (locationMap.containsKey(i)) {
                temp.add(locationMap.get(i));
            }
        }
        return temp.toArray(new String[temp.size()]);

    }
}
