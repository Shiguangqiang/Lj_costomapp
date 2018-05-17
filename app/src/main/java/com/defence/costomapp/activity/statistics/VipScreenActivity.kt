package com.defence.costomapp.activity.statistics

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import com.defence.costomapp.R
import com.defence.costomapp.utils.SgqUtils
import kotlinx.android.synthetic.main.activity_vip_screen.*
import kotlinx.android.synthetic.main.include_title_img.*
import java.util.*

/**
 * 会员筛选查询
 */
class VipScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vip_screen)
        init()
    }

    private fun init() {
        back.setImageResource(R.mipmap.back)
        back.setOnClickListener {
            finish()
        }

        middle_title.text = "会员筛选查询"
        tv_leftdate.text = SgqUtils.getNowDate()
        tv_rightdate.text = SgqUtils.getNowDate()



        tv_leftdate.setOnClickListener{
            //TODO 调用时间选择器
            DatePickerDialog(this@VipScreenActivity, R.style.MyDatePickerDialogTheme, onDateSetListener, mYear, mMonth, mDay).show()
        }
        tv_rightdate.setOnClickListener {
            //TODO 调用时间选择器
            DatePickerDialog(this@VipScreenActivity, R.style.MyDatePickerDialogTheme, onDateSetListenerright, mYear, mMonth, mDay).show()
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
