package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/19.
 */

public class DingdanBean {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * numberID : 40201803191345583763699601211783
         * status : 4
         * payVal : 250
         * orderUID : 0
         * groupNID : 41201803191345583769042401229970
         * adminGroupID : 1
         * wxTransactionID : 4200000063201803191600395323
         * orderTimeline : 2018-03-19 13:45:58
         * tui_val : 0
         * wxOpenID : oAyIL1aPQhsiy4UQjjapZTqgv7rY
         * descVal : 二环内汽水-水蜜桃味450ml
         * backTimeline :
         * payTimeline : 2018-03-19 13:46:22
         */

        private String numberID;
        private int status;
        private int payVal;
        private int orderUID;
        private String groupNID;
        private int adminGroupID;
        private String wxTransactionID;
        private String orderTimeline;
        private int tui_val;
        private String wxOpenID;
        private String descVal;
        private String backTimeline;
        private String payTimeline;

        public String getNumberID() {
            return numberID;
        }

        public void setNumberID(String numberID) {
            this.numberID = numberID;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPayVal() {
            return payVal;
        }

        public void setPayVal(int payVal) {
            this.payVal = payVal;
        }

        public int getOrderUID() {
            return orderUID;
        }

        public void setOrderUID(int orderUID) {
            this.orderUID = orderUID;
        }

        public String getGroupNID() {
            return groupNID;
        }

        public void setGroupNID(String groupNID) {
            this.groupNID = groupNID;
        }

        public int getAdminGroupID() {
            return adminGroupID;
        }

        public void setAdminGroupID(int adminGroupID) {
            this.adminGroupID = adminGroupID;
        }

        public String getWxTransactionID() {
            return wxTransactionID;
        }

        public void setWxTransactionID(String wxTransactionID) {
            this.wxTransactionID = wxTransactionID;
        }

        public String getOrderTimeline() {
            return orderTimeline;
        }

        public void setOrderTimeline(String orderTimeline) {
            this.orderTimeline = orderTimeline;
        }

        public int getTui_val() {
            return tui_val;
        }

        public void setTui_val(int tui_val) {
            this.tui_val = tui_val;
        }

        public String getWxOpenID() {
            return wxOpenID;
        }

        public void setWxOpenID(String wxOpenID) {
            this.wxOpenID = wxOpenID;
        }

        public String getDescVal() {
            return descVal;
        }

        public void setDescVal(String descVal) {
            this.descVal = descVal;
        }

        public String getBackTimeline() {
            return backTimeline;
        }

        public void setBackTimeline(String backTimeline) {
            this.backTimeline = backTimeline;
        }

        public String getPayTimeline() {
            return payTimeline;
        }

        public void setPayTimeline(String payTimeline) {
            this.payTimeline = payTimeline;
        }
    }
}
