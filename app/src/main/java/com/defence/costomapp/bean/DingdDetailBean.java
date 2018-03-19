package com.defence.costomapp.bean;

/**
 * Created by author Sgq
 * on 2018/3/19.
 */

public class DingdDetailBean {


    /**
     * sign : 1
     * message :
     * data : {"numberID":"40201803191519495790731401129074","orderTimeline":"2018-03-19 15:19:49","machine_data":{"machinename":"丰台芳城园二区1号机器","address":"北京市北京市丰台区","detailedinstalladdress":"芳城园二区门口","longitude":116.439897,"latitude":39.873032},"wxOpenID":"0","status":4,"descVal":"脉动-600ml","payVal":405,"orderUID":389,"machineID":"lj-010-04-002-001"}
     * timelineN : 2018-03-19 15:58:58
     */

    private int sign;
    private String message;
    private DataBean data;
    private String timelineN;

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getTimelineN() {
        return timelineN;
    }

    public void setTimelineN(String timelineN) {
        this.timelineN = timelineN;
    }

    public static class DataBean {
        /**
         * numberID : 40201803191519495790731401129074
         * orderTimeline : 2018-03-19 15:19:49
         * machine_data : {"machinename":"丰台芳城园二区1号机器","address":"北京市北京市丰台区","detailedinstalladdress":"芳城园二区门口","longitude":116.439897,"latitude":39.873032}
         * wxOpenID : 0
         * status : 4
         * descVal : 脉动-600ml
         * payVal : 405
         * orderUID : 389
         * machineID : lj-010-04-002-001
         */

        private String numberID;
        private String orderTimeline;
        private MachineDataBean machine_data;
        private String wxOpenID;
        private int status;
        private String descVal;
        private int payVal;
        private int orderUID;
        private String machineID;

        public String getNumberID() {
            return numberID;
        }

        public void setNumberID(String numberID) {
            this.numberID = numberID;
        }

        public String getOrderTimeline() {
            return orderTimeline;
        }

        public void setOrderTimeline(String orderTimeline) {
            this.orderTimeline = orderTimeline;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDescVal() {
            return descVal;
        }

        public void setDescVal(String descVal) {
            this.descVal = descVal;
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

        public String getMachineID() {
            return machineID;
        }

        public void setMachineID(String machineID) {
            this.machineID = machineID;
        }

        public static class MachineDataBean {
            /**
             * machinename : 丰台芳城园二区1号机器
             * address : 北京市北京市丰台区
             * detailedinstalladdress : 芳城园二区门口
             * longitude : 116.439897
             * latitude : 39.873032
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
