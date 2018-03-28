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
         * numberID : 40201803191607069681293800047265
         * status : 4
         * payVal : 100
         * orderUID : 0
         * groupNID : 41201803191607069684518700057467
         * adminGroupID : 1
         * machineID : dev-001
         * wxTransactionID : 4200000066201803191628219521
         * orderTimeline : 2018-03-19 16:07:06
         * tui_val : 0
         * machine_data : {"machinename":"DEV===001","address":"北京市北京市丰台区","detailedinstalladdress":"33333","longitude":116.357042,"latitude":39.885905}
         * wxOpenID : odCaB1dDSN1mGE7o1EbDxB0OR9Jk
         * descVal : 诺贝达半面包-蛋羹乳酪
         * backTimeline :
         * payTimeline : 2018-03-19 16:08:53
         */

        private String numberID;
        private int status;
        private int payVal;
        private int orderUID;
        private String groupNID;
        private int adminGroupID;
        private String machineID;
        private String wxTransactionID;
        private String orderTimeline;
        private int tui_val;
        private MachineDataBean machine_data;
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

        public String getMachineID() {
            return machineID;
        }

        public void setMachineID(String machineID) {
            this.machineID = machineID;
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

        public MachineDataBean getMachine_data() {
            return machine_data;
        }

        public void setMachine_data(MachineDataBean machine_data) {
            this.machine_data = machine_data;
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

        public static class MachineDataBean {
            /**
             * machinename : DEV===001
             * address : 北京市北京市丰台区
             * detailedinstalladdress : 33333
             * longitude : 116.357042
             * latitude : 39.885905
             */

            private String machinename;
            private String address;
            private String detailedinstalladdress;
            private double longitude;
            private double latitude;

            public String getMachinename() {
                return machinename;
            }

            public void setMachinename(String machinename) {
                this.machinename = machinename;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getDetailedinstalladdress() {
                return detailedinstalladdress;
            }

            public void setDetailedinstalladdress(String detailedinstalladdress) {
                this.detailedinstalladdress = detailedinstalladdress;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }
        }
    }
}
