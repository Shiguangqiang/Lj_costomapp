package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/22.
 */

public class TuikuanListBean {


    /**
     * list : [{"wxTransactionID":"4200000061201802078557532474","numberID":"40201802071144588096817901285731","detailedinstalladdress":"马家堡西路15号时代风帆大厦2-1009","formatID":529,"descVal":"测康师傅矿泉水-测康水250毫升","payVal":100,"backTimeline":"2018-02-07 11:45:23","payType":2,"orderUID":0,"machineID":"test-001"},{"wxTransactionID":"4200000068201802078563639950","numberID":"40201802071046146762907701235495","detailedinstalladdress":"马家堡西路15号时代风帆大厦2-1009","formatID":527,"descVal":"测可乐-测可乐250毫升","payVal":200,"backTimeline":"2018-02-07 11:17:37","payType":2,"orderUID":0,"machineID":"test-001"},{"wxTransactionID":"4200000062201802025667351911","numberID":"40201802021611419649027701209484","detailedinstalladdress":"马家堡西路15号时代风帆大厦2-1009","formatID":529,"descVal":"测康师傅矿泉水-测康水250毫升","payVal":100,"backTimeline":"2018-02-02 16:12:16","payType":2,"orderUID":0,"machineID":"test-001"},{"wxTransactionID":"4200000062201802025667351911","numberID":"40201802021611419592311601236167","detailedinstalladdress":"马家堡西路15号时代风帆大厦2-1009","formatID":529,"descVal":"测康师傅矿泉水-测康水250毫升","payVal":100,"backTimeline":"2018-02-02 16:12:07","payType":2,"orderUID":0,"machineID":"test-001"},{"wxTransactionID":"13264415615","numberID":"40201802021105484426439601136614","detailedinstalladdress":"马家堡西路15号时代风帆大厦2-1009","formatID":531,"descVal":"康师傅矿泉水-康师傅矿泉水250毫升（0元）","payVal":0,"backTimeline":"2018-02-02 11:49:48","payType":1,"orderUID":257,"machineID":"test-001"},{"wxTransactionID":"4200000070201802025304094112","numberID":"40201802021128330526836301186124","detailedinstalladdress":"马家堡西路15号时代风帆大厦2-1009","formatID":527,"descVal":"可口可乐-可口可乐250毫升","payVal":200,"backTimeline":"2018-02-02 11:49:31","payType":2,"orderUID":0,"machineID":"test-001"},{"wxTransactionID":"4200000070201802025420581061","numberID":"40201802021129281889021801126756","detailedinstalladdress":"马家堡西路15号时代风帆大厦2-1009","formatID":527,"descVal":"可口可乐-可口可乐250毫升","payVal":200,"backTimeline":"2018-02-02 11:49:23","payType":2,"orderUID":0,"machineID":"test-001"},{"wxTransactionID":"4200000070201802025304094112","numberID":"40201802021128330482587001161638","detailedinstalladdress":"马家堡西路15号时代风帆大厦2-1009","formatID":527,"descVal":"可口可乐-可口可乐250毫升","payVal":200,"backTimeline":"2018-02-02 11:29:08","payType":2,"orderUID":0,"machineID":"test-001"},{"wxTransactionID":"4200000071201802025444649095","numberID":"40201802021028344022479201229259","detailedinstalladdress":"马家堡西路15号时代风帆大厦2-1009","formatID":527,"descVal":"可口可乐-可口可乐250毫升","payVal":200,"backTimeline":"2018-02-02 11:02:57","payType":2,"orderUID":0,"machineID":"test-001"},{"wxTransactionID":"4200000068201802025442855367","numberID":"40201802021054436361151201107921","detailedinstalladdress":"马家堡西路15号时代风帆大厦2-1009","formatID":527,"descVal":"可口可乐-可口可乐250毫升","payVal":200,"backTimeline":"2018-02-02 11:02:35","payType":2,"orderUID":0,"machineID":"test-001"}]
     * tuikuandingdanshu : 62
     * daichuhuochengben : 235
     * daichuhuoSum : 100
     * tuikuanchengben : 11833
     * daichuhuodingdanshu : 4
     * tuikuanSum : 13502
     */

    private int tuikuandingdanshu;
    private int daichuhuochengben;
    private int daichuhuoSum;
    private int tuikuanchengben;
    private int daichuhuodingdanshu;
    private int tuikuanSum;
    private List<ListBean> list;

    public int getTuikuandingdanshu() {
        return tuikuandingdanshu;
    }

    public void setTuikuandingdanshu(int tuikuandingdanshu) {
        this.tuikuandingdanshu = tuikuandingdanshu;
    }

    public int getDaichuhuochengben() {
        return daichuhuochengben;
    }

    public void setDaichuhuochengben(int daichuhuochengben) {
        this.daichuhuochengben = daichuhuochengben;
    }

    public int getDaichuhuoSum() {
        return daichuhuoSum;
    }

    public void setDaichuhuoSum(int daichuhuoSum) {
        this.daichuhuoSum = daichuhuoSum;
    }

    public int getTuikuanchengben() {
        return tuikuanchengben;
    }

    public void setTuikuanchengben(int tuikuanchengben) {
        this.tuikuanchengben = tuikuanchengben;
    }

    public int getDaichuhuodingdanshu() {
        return daichuhuodingdanshu;
    }

    public void setDaichuhuodingdanshu(int daichuhuodingdanshu) {
        this.daichuhuodingdanshu = daichuhuodingdanshu;
    }

    public int getTuikuanSum() {
        return tuikuanSum;
    }

    public void setTuikuanSum(int tuikuanSum) {
        this.tuikuanSum = tuikuanSum;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * wxTransactionID : 4200000061201802078557532474
         * numberID : 40201802071144588096817901285731
         * detailedinstalladdress : 马家堡西路15号时代风帆大厦2-1009
         * formatID : 529
         * descVal : 测康师傅矿泉水-测康水250毫升
         * payVal : 100
         * backTimeline : 2018-02-07 11:45:23
         * payType : 2
         * orderUID : 0
         * machineID : test-001
         */

        private String wxTransactionID;
        private String numberID;
        private String detailedinstalladdress;
        private int formatID;
        private String descVal;
        private int payVal;
        private String backTimeline;
        private int payType;
        private int orderUID;
        private String machineID;

        public String getWxTransactionID() {
            return wxTransactionID;
        }

        public void setWxTransactionID(String wxTransactionID) {
            this.wxTransactionID = wxTransactionID;
        }

        public String getNumberID() {
            return numberID;
        }

        public void setNumberID(String numberID) {
            this.numberID = numberID;
        }

        public String getDetailedinstalladdress() {
            return detailedinstalladdress;
        }

        public void setDetailedinstalladdress(String detailedinstalladdress) {
            this.detailedinstalladdress = detailedinstalladdress;
        }

        public int getFormatID() {
            return formatID;
        }

        public void setFormatID(int formatID) {
            this.formatID = formatID;
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

        public String getBackTimeline() {
            return backTimeline;
        }

        public void setBackTimeline(String backTimeline) {
            this.backTimeline = backTimeline;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
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
    }
}
