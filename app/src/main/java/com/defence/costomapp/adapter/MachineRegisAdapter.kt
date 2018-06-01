package com.defence.costomapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


import com.defence.costomapp.R
import com.defence.costomapp.bean.MachineStaticsBean
import java.util.ArrayList

class MachineRegisAdapter(var mList: List<MachineStaticsBean>?, var context: Context) : BaseAdapter() {


    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {

        var holder: MyViewHolder
        //重用view
        var v: View
        if (p1 == null) {
            holder = MyViewHolder()
            v = LayoutInflater.from(context).inflate(R.layout.item_machineregis, p2, false)
            holder.tv_communityName = v.findViewById<TextView>(R.id.tv_communityName)
            holder.tv_watchUsers = v.findViewById<TextView>(R.id.tv_watchUsers)
            holder.tv_watchMembers = v.findViewById<TextView>(R.id.tv_watchMembers)
            holder.tv_storeCardUsers = v.findViewById<TextView>(R.id.tv_storeCardUsers)
            holder.tv_prepaidCardMembers = v.findViewById<TextView>(R.id.tv_prepaidCardMembers)

            //设置tag
            v.tag = holder
        } else {
            v = p1
            //获取tag并强转
            holder = v.tag as MyViewHolder
        }

        holder.tv_communityName.text = mList!!.get(p0).xiaoquname + " ( " + mList!!.get(p0).taishu + "台 ) "
        holder.tv_watchUsers.text = "守 望 用 户 : " + mList!!.get(p0).appusernum.toString() + "人"
        holder.tv_watchMembers.text = "守 望 会 员 : " + mList!!.get(p0).appuserhuiyuannum.toString() + "人"
        holder.tv_storeCardUsers.text = "储值卡用户 : " + mList!!.get(p0).xfkusernum.toString() + "人"
        holder.tv_prepaidCardMembers.text = "储值卡会员 : " + mList!!.get(p0).xfkuserhuiyuannum.toString() + "人"


        return v

    }


    override fun getItem(p0: Int): Any {
        return mList!![p0]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mList!!.size
    }

    class MyViewHolder {

        lateinit var tv_communityName: TextView
        lateinit var tv_watchUsers: TextView
        lateinit var tv_watchMembers: TextView
        lateinit var tv_storeCardUsers: TextView
        lateinit var tv_prepaidCardMembers: TextView

    }


}
