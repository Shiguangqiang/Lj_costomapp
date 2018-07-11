package com.defence.costomapp.utils.view;

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

        int days = (int) value;
        return (days + 1) + "月";
    }
}

