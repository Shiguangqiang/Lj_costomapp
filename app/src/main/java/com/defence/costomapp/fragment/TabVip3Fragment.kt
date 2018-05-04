package com.defence.costomapp.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.defence.costomapp.R
import com.defence.costomapp.base.BaseFragment
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.utils.ValueFormatter

import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder

/**
 * Created by author Sgq
 * on 2018/4/28.
 */

class TabVip3Fragment : BaseFragment() {

//    var pieChart: PieChart? = null
//
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tabvip3, null)
        var pieChart = view.findViewById<PieChart>(R.id.pie_chart)

        val mPieData = getPieData(2, 100f, 4, 5)
        showChart(pieChart, mPieData)
        return view
    }

    /**
     * @param count       分成几部分
     * @param range
     * @param zeroStarNum
     * @param oneStarNum
     */
    private fun getPieData(count: Int, range: Float, zeroStarNum: Int, oneStarNum: Int): PieData {

        val xValues = ArrayList<String>()  //xVals用来表示每个饼块上的内容


        xValues.add("储值卡会员")  //饼块上显示成Quarterly1, Quarterly2, Quarterly3, Quarterly4
        xValues.add("储值卡普通用户")


        val yValues = ArrayList<Entry>()  //yVals用来表示封装每个饼块的实际数据

        // 饼图数据
        /**
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38
         * 所以 14代表的百分比就是14%
         */

        yValues.add(Entry(zeroStarNum.toFloat(), 0))
        yValues.add(Entry(oneStarNum.toFloat(), 1))


        //y轴的集合
        val pieDataSet = PieDataSet(yValues, ""/*显示在比例图上*/)
        pieDataSet.sliceSpace = 0f //设置个饼状图之间的距离
        val colors = ArrayList<Int>()

        // 饼图颜色
        colors.add(Color.rgb(50, 139, 241))
        colors.add(Color.rgb(252, 252, 79))
        pieDataSet.colors = colors

        val metrics = resources.displayMetrics
        val px = 5 * (metrics.densityDpi / 160f)
        pieDataSet.selectionShift = px // 选中态多出的长度


        val pieData = PieData(xValues, pieDataSet)
        pieData.setValueFormatter { v ->
            if (v == 0f) {
                v.toString()
            } else {
                v.toString() + "%"
            }
        }

        return pieData
    }


    private fun showChart(pieChart: PieChart?, pieData: PieData) {
        pieChart!!.setHoleColorTransparent(true)

        pieChart.holeRadius = 60f  //半径
        pieChart.transparentCircleRadius = 4f // 半透明圈
        //pieChart.setHoleRadius(0)  //实心圆

        pieChart.setDescription("饼状图")

        // mChart.setDrawYValues(true);
        pieChart.setDrawCenterText(true)  //饼状图中间可以添加文字

        pieChart.isDrawHoleEnabled = true

        pieChart.rotationAngle = 90f // 初始旋转角度

        // draws the corresponding description value into the slice
        // mChart.setDrawXValues(true);

        // enable rotation of the chart by touch
        pieChart.isRotationEnabled = false // 可以手动旋转

        // display percentage values
        pieChart.setUsePercentValues(true)  //显示成百分比
        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        //      mChart.setOnChartValueSelectedListener(this);
        // mChart.setTouchEnabled(false);

        //      mChart.setOnAnimationListener(this);

        pieChart.centerText = ""  //饼状图中间的文字

        //设置数据
        pieChart.data = pieData

        // undo all highlights
        //      pieChart.highlightValues(null);
        //      pieChart.invalidate();

        val mLegend = pieChart.legend  //设置比例图
        mLegend.position = Legend.LegendPosition.RIGHT_OF_CHART  //最右边显示
        //      mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形
        mLegend.xEntrySpace = 7f
        mLegend.yEntrySpace = 5f

        pieChart.animateXY(1000, 1000)  //设置动画
        // mChart.spin(2000, 0, 360);
    }

}
