package com.defence.costomapp.activity.statistics

import android.content.Context
import android.os.Bundle
import com.defence.costomapp.R
import com.defence.costomapp.adapter.VipCZStatistAdapter
import com.defence.costomapp.base.BaseActivity
import kotlinx.android.synthetic.main.activity_vipdetail_statist.*
import kotlinx.android.synthetic.main.include_title_img.*

/**
 * Created by author Sgq
 * on 2018/5/2.
 */
class VipStatistDetailActivity : BaseActivity() {

    private var context: Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vipdetail_statist)
        context = this.applicationContext

        back!!.setOnClickListener {
            finish()
        }
        middle_title.text = "储蓄卡"
        init()
    }

    private fun init() {
        /**
         *创建数据结合
         */
        val arrayList = ArrayList<String>()
        var i: Int = 0
        var end: Int = 10
        do {
            arrayList.add("我是第 $i 条数据 ")
            i++
        } while (i < end)


        for(i in 1..20){
            arrayList.add("$i")
        }
        val myAdapter = VipCZStatistAdapter(arrayList,this)
        lv_xsvipdetail.adapter = myAdapter


    }

}