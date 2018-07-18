package com.defence.costomapp.utils.view;

import com.blankj.utilcode.util.SPUtils;
import com.defence.costomapp.net.Constant;
import com.github.mikephil.chartline.charts.BarLineChartBase;
import com.github.mikephil.chartline.components.AxisBase;
import com.github.mikephil.chartline.formatter.IAxisValueFormatter;

/**
 * Created by philipp on 02/06/16.
 */
public class DayAxisValueFormatter implements IAxisValueFormatter {


    private BarLineChartBase<?> chart;


    public DayAxisValueFormatter(BarLineChartBase<?> chart) {
        this.chart = chart;

    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        int time = (int) value;
        if ("1".equals(SPUtils.getInstance(Constant.SHARED_NAME).getString(Constant.SDATE))) {
            return (time + 1) + "时";
        } else {
            return (time + 1) + "日";
        }

    }
}

