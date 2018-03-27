package com.defence.costomapp.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.defence.costomapp.R;

/**
 * Created by author Sgq
 * on 2018/3/27.
 */

public class MyDialogFragment extends DialogFragment {
    private int mNum;
    private OnDialogButtonClickListener buttonClickListner;

    static MyDialogFragment newInstance(int num) {
        MyDialogFragment f = new MyDialogFragment();

        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }

    //实现回调功能
    public interface OnDialogButtonClickListener {
        public void okButtonClick();
//        public void cancelButtonClick();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mNum = getArguments().getInt("num");

        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        switch (2) {
            case 1: style = DialogFragment.STYLE_NO_TITLE; break;
            case 2: style = DialogFragment.STYLE_NO_FRAME; break;
            case 3: style = DialogFragment.STYLE_NO_INPUT; break;
            case 4: style = DialogFragment.STYLE_NORMAL; break;
            case 5: style = DialogFragment.STYLE_NORMAL; break;
            case 6: style = DialogFragment.STYLE_NO_TITLE; break;
            case 7: style = DialogFragment.STYLE_NO_FRAME; break;
            case 8: style = DialogFragment.STYLE_NORMAL; break;
        }
        switch (5) {
            case 4: theme = android.R.style.Theme_Holo; break;
            case 5: theme = android.R.style.Theme_Holo_Light_Dialog; break;
            case 6: theme = android.R.style.Theme_Holo_Light; break;
            case 7: theme = android.R.style.Theme_Holo_Light_Panel; break;
            case 8: theme = android.R.style.Theme_Holo_Light; break;
        }
        setStyle(style, theme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        Button button = (Button)v.findViewById(R.id.btn_ok);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClickListner.okButtonClick();
                getDialog().dismiss();
            }
        });

//        v.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                buttonClickListner.cancelButtonClick();
//                getDialog().dismiss();
//            }
//        });

        return v;
    }

    public void setOnButtonClickListener(OnDialogButtonClickListener listener) {
        this.buttonClickListner = listener;
    }
}