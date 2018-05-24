package com.defence.costomapp.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

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
import com.defence.costomapp.activity.statistics.VipScreenActivity
import com.defence.costomapp.activity.statistics.VipStatistDetail2Activity
import com.defence.costomapp.activity.statistics.VipStatistDetail3Activity
import com.defence.costomapp.base.Urls
import com.defence.costomapp.bean.VipCardBean
import com.defence.costomapp.utils.SgqUtils
import com.defence.costomapp.utils.httputils.HttpInterface
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.Highlight
import com.google.gson.Gson
import com.loopj.android.http.RequestParams
import kotlinx.android.synthetic.main.fragment_tabvip3.*
import org.json.JSONException
import org.json.JSONObject
import java.text.NumberFormat

/**
 * Created by author Sgq
 * on 2018/4/28.
 */

class TabVip3Fragment : BaseFragment() {

    var pieChart: PieChart? = null
    var ll_tab3: LinearLayout? = null
    //
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tabvip3, null)
        pieChart = view.findViewById<PieChart>(R.id.pie_chart)
        ll_tab3 = view.findViewById<LinearLayout>(R.id.ll_tab3)
        initdata()
        return view
    }

    private fun initdata() {
        ll_tab3!!.setOnClickListener {
            startActivity(Intent(activity, VipStatistDetail3Activity::class.java))

        }
        pieChart!!.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {

            override fun onValueSelected(entry: Entry, i: Int, highlight: Highlight) {
                //第一次点击执行的方法在这个方法内我们不做处理
            }

            override fun onNothingSelected() {
                //第二次点击统计图或者其他位置，统计图的状态恢复就会执行该方法，也就是我们想要的点击事件
                startActivity(Intent(activity, VipStatistDetail3Activity::class.java))
            }
        })

        // 创建一个数值格式化对象
        val numberFormat = NumberFormat.getInstance()
        // 设置精确到小数点后2位
        numberFormat.maximumFractionDigits = 2

        val params = RequestParams()
        httpUtils.doPost(Urls.iaofeika_huiyuan(), SgqUtils.TONGJI_TYPE, params, object : HttpInterface() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Throws(JSONException::class)
            override fun onSuccess(gson: Gson, result: Any?) {

                try {
                    if (result != null) {
                        val jsonObject = JSONObject(result.toString())
                        val vipCardBean = gson.fromJson(jsonObject.toString(), VipCardBean::class.java)

                        tv_zongshu.text = "(总量" + vipCardBean.zongshu + "人)"
                        tv_cardvip.text = vipCardBean.huiyuanshu.toString() + "人(" + numberFormat.format((vipCardBean.huiyuanshu.toFloat() / vipCardBean.zongshu.toFloat() * 100).toDouble()) + "%)"
                        tv_cardnovip.text = vipCardBean.feihuiyuan.toString() + "人(" + numberFormat.format((vipCardBean.feihuiyuan.toFloat() / vipCardBean.zongshu.toFloat() * 100).toDouble()) + "%)"

                        val mPieData = getPieData(2, 100f, vipCardBean.huiyuanshu, vipCardBean.feihuiyuan)
                        showChart(pieChart, mPieData)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        })

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
