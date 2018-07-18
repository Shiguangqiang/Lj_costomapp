package com.defence.costomapp.activity.statistics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.defence.costomapp.R;
import com.defence.costomapp.base.BaseActivity;

@Route(path = "/statistics/LineCutoverActivity")
public class LineCutoverActivity extends BaseActivity {

    public static void start() {
        ARouter.getInstance().build("/statistics/LineCutoverActivity").navigation();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_cutover);
    }
}
