package com.defence.costomapp.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.defence.costomapp.R;
import com.defence.costomapp.utils.httputils.HttpUtils;

/**
 * Created by author Sgq
 * on 2018/3/7.
 */

public class BaseFragment extends Fragment {


    public HttpUtils httpUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpUtils = new HttpUtils(getActivity());

    }


}
