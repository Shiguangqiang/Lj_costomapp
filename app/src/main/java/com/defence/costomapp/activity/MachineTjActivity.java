package com.defence.costomapp.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;
import com.defence.costomapp.bean.JsonBean;
import com.defence.costomapp.utils.GetJsonDataUtil;
import com.defence.costomapp.utils.MyNumberPicker;
import com.defence.costomapp.utils.SgqUtils;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
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
    @BindView(R.id.btn_startserach)
    Button btnStartserach;


    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private String addr1;
    private String addr2;
    private String addr3;
    private String xianICStr;
    private String shiICStr;
    private String shengIcStr;

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
//        //解析
//        initJsonData();
    }

//    private void initJsonData() {//解析数据
//
//        /**
//         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
//         * 关键逻辑在于循环体
//         *
//         * */
//        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据
//
//        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体
//
//        /**
//         * 添加省份数据
//         *
//         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
//         * PickerView会通过getPickerViewText方法获取字符串显示出来。
//         */
//        options1Items = jsonBean;
//
//        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
//            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
//            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
//
//            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
//                String CityName = jsonBean.get(i).getCityList().get(c).getName();
//                CityList.add(CityName);//添加城市
//
//                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
//
//                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
//                if (jsonBean.get(i).getCityList().get(c).getArea() == null
//                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
//                    City_AreaList.add("");
//                } else {
//
//                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
//                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);
//
//                        City_AreaList.add(AreaName);//添加该城市所有地区数据
//                    }
//                }
//                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
//            }
//
//            /**
//             * 添加城市数据
//             */
//            options2Items.add(CityList);
//
//            /**
//             * 添加地区数据
//             */
//            options3Items.add(Province_AreaList);
//        }
//
//    }
//
//    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
//        ArrayList<JsonBean> detail = new ArrayList<>();
//        try {
//            JSONArray data = new JSONArray(result);
//            Gson gson = new Gson();
//            for (int i = 0; i < data.length(); i++) {
//                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
//                detail.add(entity);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return detail;
//    }

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


//    private void ShowPickerView() {// 弹出选择器
//
//        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText() +
//                        options2Items.get(options1).get(options2) +
//                        options3Items.get(options1).get(options2).get(options3);
//
//                addr1 = options1Items.get(options1).getPickerViewText();
//                addr2 = options2Items.get(options1).get(options2);
//                addr3 = options3Items.get(options1).get(options2).get(options3);
//
//                tvAdd.setText(tx);
//            }
//        })
//
//                .setTitleText("城市选择")
//                .setDividerColor(Color.BLACK)
//                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
//                .setContentTextSize(20)
//                .build();
//
//        /*pvOptions.setPicker(options1Items);//一级选择器
//        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
//        pvOptions.show();
//    }


    @OnClick({R.id.back, R.id.tv_leftdate, R.id.tv_rightdate, R.id.tv_add, R.id.btn_startserach})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
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
                Intent intent = new Intent(MachineTjActivity.this, TjDetailActivity.class);
                intent.putExtra("leftdate", tvLeftdate.getText().toString());
                intent.putExtra("rightdate", tvRightdate.getText().toString());
                intent.putExtra("tvAdd", tvAdd.getText().toString());
                intent.putExtra("addr1", shengIcStr);
                intent.putExtra("addr2", shiICStr);
                intent.putExtra("addr3", xianICStr);
                startActivity(intent);
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
        sheng = (MyNumberPicker) ad_view.findViewById(R.id.sheng);
        shi = (MyNumberPicker) ad_view.findViewById(R.id.shi);
        xian = (MyNumberPicker) ad_view.findViewById(R.id.xian);
        sheng.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        shi.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        xian.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        xian.setMinValue(0);
        xian.setValue(0);

        location_ok = (Button) ad_view.findViewById(R.id.location_ok);
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
