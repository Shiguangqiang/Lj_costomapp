package com.defence.costomapp.activity.statistics

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.BaseAdapter
import com.defence.costomapp.R
import com.defence.costomapp.adapter.VipShouWangAdapter
import com.defence.costomapp.base.BaseActivity
import com.defence.costomapp.base.Urls
import com.defence.costomapp.bean.ChiZhiListBean
import com.defence.costomapp.bean.ShouWangVipBean
import com.defence.costomapp.utils.SgqUtils
import com.defence.costomapp.utils.httputils.HttpInterface
import com.google.gson.Gson
import com.loopj.android.http.RequestParams
import kotlinx.android.synthetic.main.activity_vipdetail2_statist.*
import kotlinx.android.synthetic.main.include_title_img.*
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by author Sgq
 * on 2018/5/3.
 */
class VipStatistDetail2Activity : BaseActivity() {

    private var context: Context? = null
    private var iskaitong: String? = "1"
    private var huiyuantype: String? = "0"
    private var leftdate: String? = SgqUtils.getReduceDateStr(SgqUtils.getNowDate(), 30)
    private var rightdate: String? = SgqUtils.getNowDate()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vipdetail2_statist)
        context = this.applicationContext
        liear_left!!.setOnClickListener {
            finish()
        }
        middle_title.text = "会员"
        right_icon!!.setImageResource(R.mipmap.shaixuan)
        right_icon!!.setOnClickListener {
            startActivityForResult(Intent(this, VipScreenActivity::class.java), 233)
        }


        // 设置下拉刷新监听器
        srl.setOnRefreshListener {
            length = 0
            if (swviplist != null) {
                swviplist!!.clear()
            }
            //守望会员卡
            getScreenData()
        }
        //上拉加载
        srl.setOnLoadListener {
            length++
            //守望会员卡
            getScreenData()
        }

        //守望会员卡
        getScreenData()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        when (resultCode) {
            234 -> if (data != null) {
                leftdate = data.getStringExtra("leftdate")
                rightdate = data.getStringExtra("rightdate")

                huiyuantype = data.getStringExtra("huiyuantype")
                iskaitong = data.getStringExtra("iskaitong")

                length = 0
                if (swviplist != null) {
                    swviplist!!.clear()
                }
                //守望会员卡
                getScreenData()
            }
        }
    }

    private var length = 0
    private var swvipAdapter: BaseAdapter? = null
    private var swviplist: MutableList<ShouWangVipBean.XfkListBean>? = null

    private fun getScreenData() {
        val params = RequestParams()
        params.add("listNum", ((length * 10).toString()))
        params.add("pags", "10")
        params.add("kaishitime", leftdate + " 00:00:00")
        params.add("jieshutime", rightdate + " 23:59:59")
        params.add("iskaitong", iskaitong)
        params.add("huiyuantype", huiyuantype)

        httpUtils.doPost(Urls.shouwang_vip(), SgqUtils.TONGJI_TYPE, params, object : HttpInterface() {
            @Throws(JSONException::class)
            override fun onSuccess(gson: Gson, result: Any) {
                srl.isRefreshing = false
                srl.setLoading(false)

                val jsonObject = JSONObject(result.toString())
                var shouWangVipBean = gson.fromJson(jsonObject.toString(), ShouWangVipBean::class.java)

                if("0".equals(iskaitong)){
                    tv_vipnum.text = "终止会员人数:" + shouWangVipBean.huiyuanzongshu + "人"
                }else if("1".equals(iskaitong)){
                    tv_vipnum.text = "会员人数:" + shouWangVipBean.huiyuanzongshu + "人"
                }


                if (swviplist == null)
                    swviplist = ArrayList()

                swviplist!!.addAll(shouWangVipBean.xfkList)

                if (swvipAdapter == null) {
                    swvipAdapter = VipShouWangAdapter(swviplist!!, this@VipStatistDetail2Activity)
                    lv_czvipdetail.adapter = swvipAdapter
                } else {
                    swvipAdapter!!.notifyDataSetChanged()
                }
            }
        })
    }
}