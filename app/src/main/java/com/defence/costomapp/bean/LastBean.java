package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/4/8.
 */

public class LastBean {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * shangchuantime : 2018-04-08 13:23:53
         * orderTimeline : 2018-04-08 13:04:47
         * descVal : 国丰山楂无添加-蔬果山楂条
         * detailedinstalladdress : 政馨园三区
         * payVal : 250
         * machineID : lj-010-04-006-001
         */

        private String shangchuantime;
        private String orderTimeline;
        private String descVal;
        private String detailedinstalladdress;
        private int payVal;
        private String machineID;

        public String getShangchuantime() {
            return shangchuantime;
        }

        public void setShangchuantime(String shangchuantime) {
            this.shangchuantime = shangchuantime;
        }

        public String getOrderTimeline() {
            return orderTimeline;
        }

        public void setOrderTimeline(String orderTimeline) {
            this.orderTimeline = orderTimeline;
        }

        public String getDescVal() {
            return descVal;
        }

        public void setDescVal(String descVal) {
            this.descVal = descVal;
        }

        public String getDetailedinstalladdress() {
            return detailedinstalladdress;
        }

        public void setDetailedinstalladdress(String detailedinstalladdress) {
            this.detailedinstalladdress = detailedinstalladdress;
        }

        public int getPayVal() {
            return payVal;
        }

        public void setPayVal(int payVal) {
            this.payVal = payVal;
        }

        public String getMachineID() {
            return machineID;
        }

        public void setMachineID(String machineID) {
            this.machineID = machineID;
        }
    }
}
