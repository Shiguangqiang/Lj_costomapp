package com.defence.costomapp.activity.statistics


import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.defence.costomapp.R
import com.defence.costomapp.base.BaseActivity
import com.defence.costomapp.fragment.*
import kotlinx.android.synthetic.main.activity_vip_statist.*
import kotlinx.android.synthetic.main.include_title_img.*


/**
 * 会员统计  储值卡与会员卡
 */
class VipStatistActivity : BaseActivity() {
    private var list: MutableList<Fragment>? = null
    private var adapter: MyAdapter? = null
    private val titles = arrayOf("", "", "")
    private var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vip_statist)
        context = this.applicationContext

        back!!.setOnClickListener {
            finish()
        }
        middle_title.text = "储蓄卡和会员"
        init()

    }

    private fun init() {
        list = ArrayList()
        list!!.add(TabVip1Fragment())
        list!!.add(TabVip2Fragment())
        list!!.add(TabVip3Fragment())
        list!!.add(TabVip4Fragment())

//        TabVip4Fragment().setUserVisibleHint()
        adapter = MyAdapter(supportFragmentManager, this.context!!)
        viewpager_vip!!.adapter = adapter

        //绑定
        tablayout_vip!!.setupWithViewPager(viewpager_vip)
        //设置自定义视图
        for (i in 0 until tablayout_vip!!.tabCount) {
            val tab = tablayout_vip!!.getTabAt(i)
            tab!!.customView = adapter!!.getTabView(i)
        }

        tablayout_vip.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> middle_title.text = "储蓄卡和会员"
                    1 -> middle_title.text = "储蓄卡和会员"
//                    2 -> middle_title.text = "储蓄卡和会员"
//                    3 -> middle_title.text = "机器注册统计"
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }


    internal inner class MyAdapter(fm: FragmentManager, private val context: Context) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return list!![position]
        }

        override fun getCount(): Int {
            return list!!.size
        }

        /**
         * 自定义方法，提供自定义Tab
         *
         * @param position 位置
         * @return 返回Tab的View
         */
        fun getTabView(position: Int): View {
            val v = LayoutInflater.from(context).inflate(R.layout.tab_custom, null)

            val tv_title = v.findViewById<View>(R.id.tv_title) as TextView

            return v
        }

    }
}
