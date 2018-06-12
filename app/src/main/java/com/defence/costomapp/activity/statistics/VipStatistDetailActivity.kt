package com.defence.costomapp.activity.statistics

import android.content.Context
import android.os.Bundle
import android.widget.BaseAdapter
import com.defence.costomapp.R
import com.defence.costomapp.adapter.VipCZStatistAdapter
import com.defence.costomapp.adapter.VipCZlistAdapter
import com.defence.costomapp.base.BaseActivity
import com.defence.costomapp.base.Urls
import com.defence.costomapp.bean.CardSaleBean
import com.defence.costomapp.bean.ChiZhiListBean
import com.defence.costomapp.utils.AmountUtils
import com.defence.costomapp.utils.SgqUtils
import com.defence.costomapp.utils.httputils.HttpInterface
import com.google.gson.Gson
import com.loopj.android.http.RequestParams
import kotlinx.android.synthetic.main.activity_vipdetail_statist.*
import kotlinx.android.synthetic.main.include_title_img.*
import org.json.JSONException
import org.json.JSONObject

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

        liear_left!!.setOnClickListener {
            finish()
        }
        middle_title.text = "储蓄卡"
        init()
    }

    private fun init() {
        //销售人员
        getSaleCount()

        // 设置下拉刷新监听器
        srl.setOnRefreshListener {
            length = 0
            if (xfkList != null) {
                xfkList!!.clear()
            }
            getChuzhiList()
        }
        //上拉加载
        srl.setOnLoadListener {
            length++
            getChuzhiList()
        }

        //储值情况
        getChuzhiList()
    }

    private var length = 0
    private var czAdapter: BaseAdapter? = null
    private var xfkList: MutableList<ChiZhiListBean.XfkListBean>? = null


    private fun getChuzhiList() {
        val paramscz = RequestParams()
        paramscz.add("listNum", ((length * 10).toString()))
        paramscz.add("pags", "10")
        httpUtils.doPost(Urls.xiaofeika_xiangxi(), SgqUtils.TONGJI_TYPE, paramscz, object : HttpInterface() {
            @Throws(JSONException::class)
            override fun onSuccess(gson: Gson, result: Any, message: String) {

                srl.isRefreshing = false
                srl.setLoading(false)

                val czjsonObject = JSONObject(result.toString())
                var chiZhiListBean = gson.fromJson(czjsonObject.toString(), ChiZhiListBean::class.java)
                tv_vipyue.text = AmountUtils.changeF2Y(chiZhiListBean.yuezongshu.toString()) + "元(卡内总余额)"

                if (xfkList == null)
                    xfkList = ArrayList()

                xfkList!!.addAll(chiZhiListBean.xfkList)

                if (czAdapter == null) {
                    czAdapter = VipCZlistAdapter( xfkList!!,this@VipStatistDetailActivity)
                    lv_czvipdetail.adapter = czAdapter
                } else {
                    czAdapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(context: Context?) {
                super.onFailure(context)
                srl.isRefreshing = false
                srl.setLoading(false)
            }

            override fun onError(context: Context?, message: String?) {
                super.onError(context, message)
                srl.isRefreshing = false
                srl.setLoading(false)
            }

        })
    }

    private fun getSaleCount() {
        val params = RequestParams()
        httpUtils.doPost(Urls.xiaoshou_xiaofeika(), SgqUtils.TONGJI_TYPE, params, object : HttpInterface() {
            @Throws(JSONException::class)
            override fun onSuccess(gson: Gson, result: Any, message: String) {

                val jsonObject = JSONObject(result.toString())
                val cardSaleBean = gson.fromJson(jsonObject.toString(), CardSaleBean::class.java)

                val myAdapter = VipCZStatistAdapter(cardSaleBean.xiaoshouList, this@VipStatistDetailActivity)
                lv_xsvipdetail.adapter = myAdapter
            }
        })

    }

}