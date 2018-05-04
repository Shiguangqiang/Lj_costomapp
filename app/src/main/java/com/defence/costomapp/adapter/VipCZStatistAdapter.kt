package com.defence.costomapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.defence.costomapp.R

/**
 * Created by author Sgq
 * on 2018/5/2.
 */
class VipCZStatistAdapter(var mList: List<String>, var context: Context) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: MyViewHolder
        //重用view
        var v: View
        if (convertView == null) {
            holder = MyViewHolder()
            v = LayoutInflater.from(context).inflate(R.layout.listvip_item, parent, false)
            holder.textView = v.findViewById<TextView>(R.id.tv_name)
            //设置tag
            v.tag = holder
        } else {
            v = convertView
            //获取tag并强转
            holder = v.tag as MyViewHolder
        }

        //为TextView设置内容
        holder.textView.text = mList[position]
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

        lateinit var textView: TextView

    }

}