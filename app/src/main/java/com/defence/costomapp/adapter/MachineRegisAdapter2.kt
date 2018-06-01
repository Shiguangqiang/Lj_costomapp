package com.defence.costomapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dyhdyh.adapters.AbstractListAdapter

import com.defence.costomapp.R
import com.defence.costomapp.bean.MachineStaticsBean
import kotlinx.android.synthetic.main.item_simple2.view.*
import java.util.ArrayList

//class MachineRegisAdapter2(var mList: List<MachineStaticsBean>, var context: Context) : AbstractListAdapter<Int, MachineRegisAdapter2.ItemViewHolder>() {
//
//
//    override fun onBindViewHolder(holder: ItemViewHolder, position: Int, item: Int) {
//        with(holder?.itemView!!) {
//            //
//            tv_communityName.text = mList.get(position).xiaoquname + " ( " + mList.get(position).taishu + "台 ) "
//            tv_watchUsers.text = "守 望 用 户 : " + mList.get(position).appusernum.toString() + "人"
//            tv_watchMembers.text = "守 望 会 员 : " + mList.get(position).appuserhuiyuannum.toString() + "人"
//            tv_storeCardUsers.text = "储值卡用户 : " + mList.get(position).xfkusernum.toString() + "人"
//            tv_prepaidCardMembers.text = "储值卡会员 : " + mList.get(position).xfkuserhuiyuannum.toString() + "人"
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
//        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_simple2, parent, false))
//    }
//
//    class ItemViewHolder(itemView: View) : AbstractListAdapter.ViewHolder(itemView)
//
//}
