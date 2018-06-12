package com.defence.costomapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.defence.costomapp.R
import com.defence.costomapp.bean.ChiZhiListBean
import com.defence.costomapp.utils.AmountUtils

/**
 * Created by author Sgq
 * on 2018/5/21.
 */
class VipCZlistAdapter(var mList: List<ChiZhiListBean.XfkListBean>?, var context: Context) : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: MyViewHolder
        //重用view
        var v: View
        if (convertView == null) {
            holder = MyViewHolder()
            v = LayoutInflater.from(context).inflate(R.layout.listvipchuzhi_item, parent, false)
            holder.tv_cardnum = v.findViewById<TextView>(R.id.tv_cardnum)
            holder.tv_cardyue = v.findViewById<TextView>(R.id.tv_cardyue)
            holder.tv_cardtime = v.findViewById<TextView>(R.id.tv_cardtime)

            //设置tag
            v.tag = holder
        } else {
            v = convertView
            //获取tag并强转
            holder = v.tag as MyViewHolder
        }

        //为TextView设置内容
        holder.tv_cardnum.text = mList!!.get(position).chongzhikahao
        holder.tv_cardyue.text = AmountUtils.changeF2Y(mList!![position].yuemoney.toString())+"元"

        if (!mList!![position].ct.equals("")) {
            if (mList!![position].ct.toInt() < 1) {
                holder.tv_cardtime.text = " -- "
            } else {
                holder.tv_cardtime.text = "还有" + mList!![position].ct.toString() + "天到期"
            }
        }else{
            holder.tv_cardtime.text = " -- "
        }


        return v
    }

    override fun getItem(position: Int): Any {
        return mList!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mList!!.size
    }

    class MyViewHolder {

        lateinit var tv_cardnum: TextView
        lateinit var tv_cardyue: TextView
        lateinit var tv_cardtime: TextView
    }

}