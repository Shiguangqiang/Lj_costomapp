package com.defence.costomapp.activity.statistics

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.defence.costomapp.R
import com.defence.costomapp.base.BaseActivity
import kotlinx.android.synthetic.main.include_title_img.*

/**
 * Created by author Sgq
 * on 2018/5/3.
 */
class VipStatistDetail2Activity : BaseActivity() {

    private var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vipdetail2_statist)
        context = this.applicationContext
        back!!.setOnClickListener {
            finish()
        }
        middle_title.text = "会员"
        right_icon!!.setImageResource(R.mipmap.shaixuan)
        right_icon!!.setOnClickListener {
            startActivity(Intent(this, VipScreenActivity::class.java))
        }

    }
}