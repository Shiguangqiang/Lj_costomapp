package com.defence.costomapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.defence.costomapp.R
import com.defence.costomapp.bean.CardSaleBean
import com.defence.costomapp.utils.httputils.HttpInterface

/**
 * Created by author Sgq
 * on 2018/5/2.
 */
class VipCZStatistAdapter(var mList: MutableList<CardSaleBean.XiaoshouListBean>, var context: Context) : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: MyViewHolder
        //重用view
        var v: View
        if (convertView == null) {
            holder = MyViewHolder()
            v = LayoutInflater.from(context).inflate(R.layout.listvip_item, parent, false)
            holder.tv_name = v.findViewById<TextView>(R.id.tv_name)
            holder.tv_faka = v.findViewById<TextView>(R.id.tv_faka)
            holder.tv_xiaoshou = v.findViewById<TextView>(R.id.tv_xiaoshou)
            holder.tv_shengyu = v.findViewById<TextView>(R.id.tv_shengyu)
            //设置tag
            v.tag = holder
        } else {
            v = convertView
            //获取tag并强转
            holder = v.tag as MyViewHolder
        }

        //为TextView设置内容
        holder.tv_name.text = mList[position].name
        holder.tv_faka.text = mList[position].fakaliang.toString()
        holder.tv_xiaoshou.text = mList[position].xiaoshouliang.toString()
        holder.tv_shengyu.text = mList[position].shengyuliang.toString()

        return v
    }

    override fun getItem(position: Int): Any {
        return mList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mList.size
    }

    class MyViewHolder {

        lateinit var tv_name: TextView
        lateinit var tv_faka: TextView
        lateinit var tv_xiaoshou: TextView
        lateinit var tv_shengyu: TextView

    }

}