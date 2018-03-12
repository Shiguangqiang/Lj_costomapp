package com.defence.costomapp.activity.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseFragment;


/**
 * Created by author Sgq
 * on 2018/3/7.
 * 商品
 */


public class MachiceFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_machice, null);
        initdata();
        return view;
    }

    private void initdata() {

    }


}
