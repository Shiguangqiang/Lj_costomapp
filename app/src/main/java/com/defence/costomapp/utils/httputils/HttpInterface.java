package com.defence.costomapp.utils.httputils;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;

public abstract class HttpInterface {

    public abstract void onSuccess(Gson gson, Object result) throws JSONException;

    public void onError(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void onFailure(Context context) {
        Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();

    }

    public void onSpecial(Context context, String sign, String message) {

    }

}
