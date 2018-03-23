package com.defence.costomapp.utils.httputils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


import com.defence.costomapp.app.MyApplication;
import com.defence.costomapp.bean.UserInfo;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


public class HttpUtils {

    private AsyncHttpClient client;
    private ProgressDialog pd;
    private Context context;
    private Gson gson;
    private MyApplication myApplication;
    private UserInfo user;

    public HttpUtils(Context context) {
        myApplication = (MyApplication) context.getApplicationContext();
        this.context = context;
        client = new AsyncHttpClient();

        client.setTimeout(20000); // 设置链接超时，如果不设置，默认为10s

        gson = new Gson();
        pd = new ProgressDialog(context);
        pd.setCanceledOnTouchOutside(false);
        pd.setMessage("正在加载....");
//

    }

    /**
     * 添加基本的Params
     */
    public RequestParams addBaseParams(RequestParams params, int funcType) {
        //判断如果是Welcome则不显示pd;
        user = MyApplication.getApp().getUserInfo();
        params.put("_t", System.currentTimeMillis() + "");
        if (user != null) {
            params.put("uniqueCode", user.getAuthorizationKey());
            params.put("phoneAID", user.getId() + "");
            params.put("funcType", funcType + "");
            // TODO: 16/9/14 尚未添加登录和未登录 目前视为未登录,修改后把上面两行代码删除即可
        } else {
            params.put("uniqueCode", "009900");
            params.put("phoneAID", "0");
            params.put("funcType", funcType + "");
        }
        return params;
    }

    public void doPost(String url, int funcType, RequestParams params, final HttpInterface integerface) {
        addBaseParams(params, funcType);
        Log.d("HttpUtils", url);
        Log.d("HttpUtils", params + "");
        if (isNetworkAvailable()) {

            client.post(url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onStart() {
                    try {
                        if (!pd.isShowing()) {
                            pd.show();
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    try {
                        if (pd != null) {
                            pd.dismiss();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d("HttpUtils", "statusCode:" + statusCode);
                    Log.d("HttpUtils", new String(responseBody));
                    try {
                        JSONObject jb = new JSONObject(new String(responseBody));
                        String sign = jb.getString("sign");
                        String message = jb.optString("message");
                        if ("1".equals(sign)) {

                            Object result = jb.opt("result");
                            Object data_list = jb.opt("data_list");
                            Object data = jb.opt("data");
                            if (result == null && data != null && data_list != null) {
                                integerface.onSuccess(gson, data_list);
                            } else if (result == null && data_list == null && data != null) {
                                integerface.onSuccess(gson, data);
                            } else {
                                integerface.onSuccess(gson, result);
                            }

                        } else if ("4".equals(sign)) {
                            integerface.onError(context, message);
                        } else if ("3".equals(sign)) {
                            integerface.onSpecial(context, sign, message);
                        } else if ("5".equals(sign)) {
                            integerface.onSpecial(context, sign, message);
                        } else {
                            integerface.onError(context, message);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        integerface.onFailure(context);
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    pd.dismiss();
                    Log.d("HttpUtils", "statusCode:" + statusCode);
                    integerface.onFailure(context);
                }
            });


        } else {
            integerface.onFailure(context);
        }


    }

    /**
     * 判断是否有网络可用
     */

    public boolean isNetworkAvailable() {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
