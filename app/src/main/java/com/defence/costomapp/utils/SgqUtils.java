package com.defence.costomapp.utils;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.defence.costomapp.app.MyApplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/7.
 */

public class SgqUtils {


    //补货
    public static final int BUHUO_TYPE = 0;
    //管理
    public static final int MANAGER_TYPE = 10100;
    //统计
    public static final int TONGJI_TYPE = 10110;


    //将list转换为带有 ， 的字符串
    public static String listToString(List<String> list) {

        StringBuilder sb = new StringBuilder();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append("'" + list.get(i) + "',");
                } else {
                    sb.append("'" + list.get(i) + "'");
                }
            }
        }
        return sb.toString();
    }

    //List<String>转换为List<int>
    public static List<Integer> parseIntegersList(List<String> StringList) {
        List<Integer> IntegerList = new ArrayList<Integer>();
        for (String x : StringList) {
            Integer z = Integer.parseInt(x);
            IntegerList.add(z);
        }
        return IntegerList;
    }

    //List<String>转换为List<int>  除以100
    public static List<Float> parseFloatListcy100(List<String> StringList) {
        List<Float> FloatList = new ArrayList<Float>();
        for (String x : StringList) {
            Float z = Float.parseFloat(AmountUtils.changeF2Y(x));
            FloatList.add(z);
        }
        return FloatList;
    }

    //List<String>转换为List<int>
    public static List<Float> parseFloatList(List<String> StringList) {
        List<Float> FloatList = new ArrayList<Float>();
        for (String x : StringList) {
            Float z = Float.parseFloat(x);
            FloatList.add(z);
        }
        return FloatList;
    }

    /**
     * 获取当前本地apk的版本
     *
     * @param mContext
     * @return
     */
    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    //将list转换为带有 ， 的字符串
    public static String listToStringSerializable(List<Serializable> list) {

        StringBuilder sb = new StringBuilder();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append("'" + list.get(i) + "',");
                } else {
                    sb.append("'" + list.get(i) + "'");
                }
            }
        }
        return sb.toString();
    }

    /**
     * 日期转星期
     *
     * @param datetime
     * @return
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


    public static String getAssetsFile(Context context) {
        try {
//Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open("location.txt");
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            String text = new String(buffer, "UTF-8");
            return text;
            // Finally stick the string into the text view.
        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }

    }

    //Day:日期字符串例如 2015-3-10  Num:需要减少的天数例如 7
    public static String getReduceDateStr(String day, int Num) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = null;
        try {
            nowDate = df.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //如果需要向后计算日期 -改为+
        Date newDate2 = new Date(nowDate.getTime() - (long) Num * 24 * 60 * 60 * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOk = simpleDateFormat.format(newDate2);
        return dateOk;
    }

    //Day:日期字符串例如 2015-3-10  Num:需要增加的天数例如 7
    public static String getAddDateStr(String day, int Num) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = null;
        try {
            nowDate = df.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //如果需要向后计算日期 -改为+
        Date newDate2 = new Date(nowDate.getTime() + (long) Num * 24 * 60 * 60 * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOk = simpleDateFormat.format(newDate2);
        return dateOk;
    }

    //获取当前时间
    public static String getNowHmsDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    //获取当前时间
    public static String getNowDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    //获取当前时间
    public static String getNowYmDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }


    //设置TabLayout下方下划线的宽度
    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }

    }
}