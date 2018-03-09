package com.defence.costomapp.utils;


import com.defence.costomapp.app.MyApplication;

public class SharePerenceUtil {
    public static void addJsonObjToSp(String spName, String key, Object obj) {
        String json = "";
        MyApplication.getApp().getSharedPreferences(spName, 0).edit().putString(key, json).apply();
    }

    public static String getObjFromSp(String spName, String key) {
        String json = MyApplication.getApp().getSharedPreferences(spName, 0).getString(key, "");
        return json;
    }

    public static void putIntValuetoSp(String key, int value) {
        MyApplication.getApp().getSharedPreferences("value", 0).edit().putInt(key, value).apply();
    }

    public static int getIntValueFromSP(String key) {
        return MyApplication.getApp().getSharedPreferences("value", 0).getInt(key, 0);
    }

    public static void putStringValuetoSp(String key, String value) {
        MyApplication.getApp().getSharedPreferences("value", 0).edit().putString(key, value).apply();
    }

    public static String getStringValueFromSp(String key) {
        return MyApplication.getApp().getSharedPreferences("value", 0).getString(key, "");
    }

    public static void putBooleanValuetoSp(String key, boolean value) {
        MyApplication.getApp().getSharedPreferences("value", 0).edit().putBoolean(key, value).apply();
    }

    public static boolean getBooleanValueFromSp(String key) {
        return MyApplication.getApp().getSharedPreferences("value", 0).getBoolean(key, false);
    }

    public static boolean getBooleanValueFromSp(String key, boolean bool) {
        return MyApplication.getApp().getSharedPreferences("value", 0).getBoolean(key, bool);
    }

    public static void removeValueFromSp(String name) {
        MyApplication.getApp().getSharedPreferences(name, 0).edit().clear().apply();
    }

}
