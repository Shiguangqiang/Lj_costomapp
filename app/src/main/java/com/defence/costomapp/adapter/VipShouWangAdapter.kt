package com.defence.costomapp.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.ColorSpace
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.defence.costomapp.R
import com.defence.costomapp.bean.ShouWangVipBean
import com.defence.costomapp.myinterface.RVItemClickListener

/**
 * Created by author Sgq
 * on 2018/5/21.
 */
class VipShouWangAdapter(var mList: List<ShouWangVipBean.XfkListBean>?, var context: Context, val rvItemClickListener: RVItemClickListener) : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: MyViewHolder
        //重用view
        var v: View
        if (convertView == null) {
            holder = MyViewHolder()
            v = LayoutInflater.from(context).inflate(R.layout.listvipshouwang_item, parent, false)
            holder.tv_cardnum = v.findViewById<TextView>(R.id.tv_cardnum)
            holder.tv_cardyue = v.findViewById<TextView>(R.id.tv_cardyue)
            holder.tv_cardtime = v.findViewById<TextView>(R.id.tv_cardtime)
            holder.ll_item_vip = v.findViewById(R.id.ll_item_vip)

            //设置tag
            v.tag = holder
        } else {
            v = convertView
            //获取tag并强转
            holder = v.tag as MyViewHolder
        }

        if (!TextUtils.isEmpty(mList!!.get(position).cccctype)) {
            if (mList!!.get(position).cccctype.equals("1")) {
                holder.tv_cardnum.setTextColor(Color.BLUE)
                holder.tv_cardyue.setTextColor(Color.BLUE)
                holder.tv_cardtime.setTextColor(Color.BLUE)
            } else {
                holder.tv_cardnum.setTextColor(Color.GRAY)
                holder.tv_cardyue.setTextColor(Color.GRAY)
                holder.tv_cardtime.setTextColor(Color.GRAY)
            }
        }


        //为TextView设置内容
        holder.tv_cardnum.text = mList!!.get(position).haoma

        if (!mList!![position].kaishihuiyuanshijian.equals("")) {
//            holder.tv_cardyue.text = mList!![position].kaishihuiyuanshijian.substring(0, 10) + "(" + mList!!.get(position).goumaitypename + ")"
            holder.tv_cardyue.text = mList!![position].kaishihuiyuanshijian.substring(0, 10)
        } else {
            holder.tv_cardyue.text = " -- "
        }
        if (!mList!![position].ct.equals("")) {
            if (mList!![position].ct.toInt() < 1) {
                holder.tv_cardtime.text = " -- "
            } else {
                holder.tv_cardtime.text = "还有" + mList!![position].ct.toString() + "天到期"
            }
        } else {
            holder.tv_cardtime.text = " -- "
        }

        holder.ll_item_vip.setOnClickListener {
            rvItemClickListener.onItemClick(position)
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
        lateinit var ll_item_vip: LinearLayout
    }

}