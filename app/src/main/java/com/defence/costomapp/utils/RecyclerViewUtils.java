package com.defence.costomapp.utils;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by fangfafengfu on 2017/12/6.
 */

public class RecyclerViewUtils {
    public static void setReRecyclerView(Context context, RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(
                context, DividerItemDecoration.VERTICAL_LIST));

    }
}
