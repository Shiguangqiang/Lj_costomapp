package com.defence.costomapp.activity.statistics

import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.DatePicker
import android.widget.RadioGroup
import com.defence.costomapp.R
import com.defence.costomapp.utils.SgqUtils
import kotlinx.android.synthetic.main.activity_vip_screen.*
import kotlinx.android.synthetic.main.include_title_img.*
import java.util.*

/**
 * 会员筛选查询
 */
class VipScreenActivity : AppCompatActivity() {

    private var huiyuantype: String? = "0"
    private var iskaitong: String? = "1"
    private var leftdate: String? = SgqUtils.getNowDate()
    private var rightdate: String? = SgqUtils.getNowDate()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vip_screen)
        init()
    }

    private fun init() {
        back.setImageResource(R.mipmap.back)
        liear_left.setOnClickListener {
            finish()
        }

        middle_title.text = "会员筛选查询"
        tv_leftdate.text = SgqUtils.getNowDate()
        tv_rightdate.text = SgqUtils.getNowDate()

        tv_leftdate.setOnClickListener {
            //TODO 调用时间选择器
            DatePickerDialog(this@VipScreenActivity, R.style.MyDatePickerDialogTheme, onDateSetListener, mYear, mMonth, mDay).show()
        }
        tv_rightdate.setOnClickListener {
            //TODO 调用时间选择器
            DatePickerDialog(this@VipScreenActivity, R.style.MyDatePickerDialogTheme, onDateSetListenerright, mYear, mMonth, mDay).show()
        }

        // 今天   最近7天   最近30天
        rg_to730day.setOnCheckedChangeListener { radioGroup, i ->

            when (radioGroup.checkedRadioButtonId) {
                R.id.tv_rbtoday -> {
                    tv_leftdate.text = SgqUtils.getNowDate()
                    leftdate = SgqUtils.getNowDate()
//                    rightdate = SgqUtils.getNowDate()
                }
                R.id.tv_rb7day -> {
                    tv_leftdate.text = SgqUtils.getReduceDateStr(SgqUtils.getNowDate(), 7)
//                    leftdate = SgqUtils.getReduceDateStr(SgqUtils.getNowDate(), 7)
                    rightdate = SgqUtils.getNowDate()
                }
                R.id.tv_rb30day -> {
                    tv_leftdate.text = SgqUtils.getReduceDateStr(SgqUtils.getNowDate(), 30)
//                    leftdate = SgqUtils.getReduceDateStr(SgqUtils.getNowDate(), 30)
                    rightdate = SgqUtils.getNowDate()
                }
            }
        }

        //0 全部   2 年会员  3 月会员  4 季度会员   5 半年会员
        rg_date.setOnCheckedChangeListener { radioGroup, i ->
            when (radioGroup.checkedRadioButtonId) {
                R.id.rb_all -> huiyuantype = "0"
                R.id.rb_mouth -> huiyuantype = "3"
                R.id.rb_year -> huiyuantype = "2"
                R.id.rb_quarterly -> huiyuantype = "4"
                R.id.rb_halfOfAYear -> huiyuantype = "5"

            }
        }

        //1 时间范围内开通    0时间范围内 终止
        rg_timerange.setOnCheckedChangeListener { radioGroup, i ->
            when (radioGroup.checkedRadioButtonId) {
                R.id.rb_ktvip -> iskaitong = "1"
                R.id.tb_zzvip -> iskaitong = "0"
            }
        }

        btn_startserach.setOnClickListener {
            //   筛选天数
            //   checkedRadioButtonId  未选择默认是-1
            if (rg_to730day.checkedRadioButtonId == -1) {
                leftdate = tv_leftdate.text.toString()
                rightdate = tv_rightdate.text.toString()
            }
            val intent = Intent()
            intent.putExtra("leftdate", tv_leftdate.text.toString())
            intent.putExtra("rightdate", tv_rightdate.text.toString())
            intent.putExtra("huiyuantype", huiyuantype)
            intent.putExtra("iskaitong", iskaitong)
            setResult(234, intent)
            finish()
        }
    }


    internal var ca = Calendar.getInstance()
    internal var mYear = ca.get(Calendar.YEAR)
    internal var mMonth = ca.get(Calendar.MONTH)
    internal var mDay = ca.get(Calendar.DAY_OF_MONTH)
    /**
     * 日期选择器对话框监听
     */
    private val onDateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        mYear = year
        mMonth = monthOfYear
        mDay = dayOfMonth
        val days: String
        if (mMonth + 1 < 10) {
            if (mDay < 10) {

                days = StringBuffer().append(mYear).append("-").append("0").append(mMonth + 1).append("-").append("0").append(mDay).toString()
            } else {
                days = StringBuffer().append(mYear).append("-").append("0").append(mMonth + 1).append("-").append(mDay).toString()
            }

        } else {
            if (mDay < 10) {
                days = StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").append("0").append(mDay).toString()
            } else {
                days = StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").append(mDay).toString()
            }
        }
        tv_leftdate.text = days
    }


    private val onDateSetListenerright = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        mYear = year
        mMonth = monthOfYear
        mDay = dayOfMonth
        val days: String
        if (mMonth + 1 < 10) {
            if (mDay < 10) {
                days = StringBuffer().append(mYear).append("-").append("0").append(mMonth + 1).append("-").append("0").append(mDay).toString()
            } else {
                days = StringBuffer().append(mYear).append("-").append("0").append(mMonth + 1).append("-").append(mDay).toString()
            }
        } else {
            if (mDay < 10) {
                days = StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").append("0").append(mDay).toString()
            } else {
                days = StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").append(mDay).toString()
            }
        }
        tv_rightdate.text = days
    }
}
