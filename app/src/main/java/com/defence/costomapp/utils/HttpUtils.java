package com.defence.costomapp.utils;

import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.bean.UserInfo;
import com.loopj.android.http.RequestParams;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtils {
    private static OkHttpClient client = null;
    private MyApplication myApplication;


    private HttpUtils() {
    }

    public static OkHttpClient getInstance() {
        if (client == null) {
            synchronized (HttpUtils.class) {
                if (client == null)
                    client = new OkHttpClient();
            }
        }
        return client;
    }

    /**
     * Get请求
     *
     * @param url
     * @param callback
     */
    public static void doGet(String url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }

    /**
     * Post请求发送键值对数据
     *
     * @param url
     * @param mapParams
     * @param callback
     */
    public static void doPost(String url, int funcType, Map<String, String> mapParams, Callback callback) {
        addBaseParams(mapParams, funcType);
        FormBody.Builder builder = new FormBody.Builder();
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        for (String key : mapParams.keySet()) {
            builder.add(key, mapParams.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }

    /**
     * 添加基本的Params
     */
    public static Map<String, String> addBaseParams(Map<String, String> mapParams, int funcType) {
        //判断如果是Welcome则不显示pd;
        UserInfo user = MyApplication.getApp().getUserInfo();
        mapParams.put("_t", System.currentTimeMillis() + "");
        if (user != null) {
            mapParams.put("uniqueCode", user.getAuthorizationKey());
            mapParams.put("phoneAID", user.getId() + "");
            mapParams.put("funcType", funcType + "");
            mapParams.put("_t", Math.random() + "");
            // TODO: 16/9/14 尚未添加登录和未登录 目前视为未登录,修改后把上面两行代码删除即可
        } else {
            mapParams.put("uniqueCode", "009900");
            mapParams.put("phoneAID", "0");
            mapParams.put("funcType", funcType + "");
            mapParams.put("_t", Math.random() + "");
        }
        return mapParams;
    }


}
