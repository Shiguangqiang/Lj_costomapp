package com.defence.costomapp.activity.statistics

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.widget.BaseAdapter
import android.widget.CompoundButton
import com.defence.costomapp.R
import com.defence.costomapp.adapter.VipShouWangAdapter
import com.defence.costomapp.base.BaseActivity
import com.defence.costomapp.base.Urls
import com.defence.costomapp.bean.ShouWangVipBean
import com.defence.costomapp.myinterface.RVItemClickListener
import com.defence.costomapp.utils.SgqUtils
import com.defence.costomapp.utils.httputils.HttpInterface
import com.google.gson.Gson
import com.loopj.android.http.RequestParams
import kotlinx.android.synthetic.main.activity_vipdetail2_statist.*
import kotlinx.android.synthetic.main.include_title_img.*
import org.json.JSONException
import org.json.JSONObject
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by author Sgq
 * on 2018/5/3.
 */
class VipStatistDetail2Activity : BaseActivity() {

    private var context: Context? = null
    private var iskaitong: String? = "1"
    private var openString: String? = ""
    private var sortingRecords: Int? = 0
    private var huiyuantype: String? = "0"
    private var leftdate: String? = SgqUtils.getReduceDateStr(SgqUtils.getNowDate(), 30)
    private var rightdate: String? = SgqUtils.getNowDate()
    private var sequence: String? = "2"
    private var dora: String? = "desc"

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
        //  会员开始筛选
        tv_memberStarts.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
                length = 0
                if (swviplist != null) {
                    swviplist!!.clear()
                }
                tv_memberStarts.setTextSize(TypedValue.COMPLEX_UNIT_PX, 50F); //22像素
                tv_memberExpires.setTextSize(TypedValue.COMPLEX_UNIT_PX, 40F);


                sequence = "1"
                if (isChecked) {
//                    sortingRecords=1
//                    sortOrderList(swviplist!!)
//                    swvipAdapter!!.notifyDataSetChanged()
                    dora = "asc"
                    getScreenData()
                } else {
//                    sortingRecords=2
//                    invertOrderList(swviplist!!)
//                    swvipAdapter!!.notifyDataSetChanged()
                    dora = "desc"
                    getScreenData()
                }
            }
        })
        //会员到期筛选
        tv_memberExpires.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
                length = 0
                if (swviplist != null) {
                    swviplist!!.clear()
                }
                tv_memberStarts.setTextSize(TypedValue.COMPLEX_UNIT_PX, 40F); //22像素
                tv_memberExpires.setTextSize(TypedValue.COMPLEX_UNIT_PX, 50F);
                sequence = "2"
                if (isChecked) {
//                    sortingRecords=3
//                    sortInfo()
//                    swvipAdapter!!.notifyDataSetChanged()
                    dora = "asc"
                    getScreenData()
                } else {
//                    sortingRecords=4
//                    reverseInfo()
//                    swvipAdapter!!.notifyDataSetChanged()
                    dora = "desc"
                    getScreenData()
                }
            }
        })


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
//                val dropleft = leftdate!!.drop(5)
//                val dropright = rightdate!!.drop(5)
//                if (iskaitong.equals("1")) {
//                    openString = "开通会员"
//                } else {
//                    openString = "终止会员"
//                }
//                tv_filterConditions.text = dropleft + "至" + dropright + openString
            }
        }
    }

    //将List按照时间倒序排列
    @SuppressLint("SimpleDateFormat")
    private fun invertOrderList(L: MutableList<ShouWangVipBean.XfkListBean>): List<ShouWangVipBean.XfkListBean> {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var d1: Date
        var d2: Date
        var temp_r = ShouWangVipBean.XfkListBean()
        //做一个冒泡排序，大的在数组的前列
        for (i in 0 until L.size - 1) {
            for (j in i + 1 until L.size) {
                val pos1 = ParsePosition(0)
                val pos2 = ParsePosition(0)
                d1 = sdf.parse(L[i].kaishihuiyuanshijian, pos1)
                d2 = sdf.parse(L[j].kaishihuiyuanshijian, pos2)
                if (d1.before(d2)) {//如果队前日期靠前，调换顺序
                    temp_r = L[i]
                    L[i] = L[j]
                    L[j] = temp_r
                }
            }
        }
        return L
    }


    //将List按照时间倒序排列
    @SuppressLint("SimpleDateFormat")
    private fun sortOrderList(L: MutableList<ShouWangVipBean.XfkListBean>): List<ShouWangVipBean.XfkListBean> {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var d1: Date
        var d2: Date
        var temp_r = ShouWangVipBean.XfkListBean()
        //做一个冒泡排序，大的在数组的前列
        for (i in 0 until L.size - 1) {
            for (j in i + 1 until L.size) {
                val pos1 = ParsePosition(0)
                val pos2 = ParsePosition(0)
                d1 = sdf.parse(L[i].kaishihuiyuanshijian, pos1)
                d2 = sdf.parse(L[j].kaishihuiyuanshijian, pos2)
                if (d1.after(d2)) {//如果队前日期靠前，调换顺序
                    temp_r = L[i]
                    L[i] = L[j]
                    L[j] = temp_r
                }
            }
        }
        return L
    }

    //    到期升序
    private fun sortInfo() {
        val itemComparator = Comparator<ShouWangVipBean.XfkListBean> { info1, info2 -> info1.ct.toInt() - info2.ct.toInt() }
        Collections.sort(swviplist, itemComparator)
    }

    //    到期降序
    private fun reverseInfo() {
        val itemComparator2 = Comparator<ShouWangVipBean.XfkListBean> { info1, info2 -> info1.ct.toInt() - info2.ct.toInt() }
        Collections.reverse(swviplist)
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
        params.add("paixu", sequence)
        params.add("dora", dora)

        httpUtils.doPost(Urls.shouwang_vip(), SgqUtils.TONGJI_TYPE, params, object : HttpInterface() {

            @Throws(JSONException::class)
            override fun onSuccess(gson: Gson, result: Any, message: String) {
                srl.isRefreshing = false
                srl.setLoading(false)

                val dropleft = leftdate!!.drop(5)
                val dropright = rightdate!!.drop(5)
                if (iskaitong.equals("1")) {
                    openString = "开通会员"
                } else {
                    openString = "终止会员"
                }
                tv_filterConditions.text = dropleft + "至" + dropright + openString

                val jsonObject = JSONObject(result.toString())
                var shouWangVipBean = gson.fromJson(jsonObject.toString(), ShouWangVipBean::class.java)

                if ("0".equals(iskaitong)) {
//                    tv_vipnum.text = "终止会员人数:" + shouWangVipBean.huiyuanzongshu + "人"
                    tv_vipnum.text = "会员人数:" + shouWangVipBean.huiyuanzongshu + "人"
                } else if ("1".equals(iskaitong)) {
                    tv_vipnum.text = "会员人数:" + shouWangVipBean.huiyuanzongshu + "人"
                }


                if (swviplist == null)
                    swviplist = ArrayList()

                swviplist!!.addAll(shouWangVipBean.xfkList)

                if (swvipAdapter == null) {
                    swvipAdapter = VipShouWangAdapter(swviplist!!, this@VipStatistDetail2Activity, RVItemClickListener {
                        val intent = Intent(this@VipStatistDetail2Activity, UserTjDetailActivity::class.java)
                        val id = swviplist!!.get(it).uid!!
                        val haoma = swviplist!!.get(it).haoma!!
                        intent.putExtra("phone", haoma)
                        intent.putExtra("uid", id.toString())
                        intent.putExtra("ttype", "1")
                        intent.putExtra("payType", "1")
                        startActivity(intent)
                    })
                    lv_czvipdetail.adapter = swvipAdapter
                } else {
//                  when(sortingRecords){
//                      1 ->  sortOrderList(swviplist!!)
//                      2 ->   invertOrderList(swviplist!!)
//                      3 ->  sortInfo()
//                      4 ->  reverseInfo()
//                  }
                    swvipAdapter!!.notifyDataSetChanged()
                }
            }
        })
    }
}