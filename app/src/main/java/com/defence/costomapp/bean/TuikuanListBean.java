package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/22.
 */

public class TuikuanListBean {


    /**
     * list : [{"wxTransactionID":"4200000071201803233954572001","numberID":"40201803231134299358513600075665","itemNo":26,"detailedinstalladdress":"33333","formatID":581,"descVal":"诺贝达半面包-豆奶流沙味","payVal":100,"backTimeline":"2018-03-23 11:34:55","payType":2,"orderUID":0,"machineID":"dev-001"},{"wxTransactionID":"4200000052201803233840735645","numberID":"40201803231133364586015600054338","itemNo":26,"detailedinstalladdress":"33333","formatID":581,"descVal":"诺贝达半面包-豆奶流沙味","payVal":100,"backTimeline":"2018-03-23 11:34:09","payType":2,"orderUID":0,"machineID":"dev-001"},{"wxTransactionID":"4200000054201803213032318657","numberID":"40201803212102233440723300087795","itemNo":26,"detailedinstalladdress":"33333","formatID":581,"descVal":"诺贝达半面包-豆奶流沙味","payVal":100,"backTimeline":"2018-03-21 21:02:56","payType":2,"orderUID":0,"machineID":"dev-001"},{"wxTransactionID":"4200000057201803148548933420","numberID":"40201803141631507628721700098349","itemNo":26,"detailedinstalladdress":"33333","formatID":581,"descVal":"诺贝达半面包-豆奶流沙味","payVal":1,"backTimeline":"2018-03-14 16:45:35","payType":2,"orderUID":0,"machineID":"DEV-001"},{"wxTransactionID":"13716218304","numberID":"40201803141634045190560200034429","itemNo":26,"detailedinstalladdress":"33333","formatID":581,"descVal":"诺贝达半面包-豆奶流沙味","payVal":1,"backTimeline":"2018-03-14 16:43:06","payType":1,"orderUID":449,"machineID":"DEV-001"},{"wxTransactionID":"4200000051201803085035958845","numberID":"40201803081806135072455900048599","itemNo":27,"detailedinstalladdress":"33333","formatID":557,"descVal":"3+2苏打夹心饼干-香浓奶油味125g","payVal":225,"backTimeline":"2018-03-14 13:59:29","payType":2,"orderUID":0,"machineID":"DEV-001"},{"wxTransactionID":"4200000080201803106109291520","numberID":"40201803101532558240494000076062","itemNo":11,"detailedinstalladdress":"33333","formatID":591,"descVal":"好丽友好友趣-韩国泡菜味45g","payVal":175,"backTimeline":"2018-03-14 13:52:44","payType":2,"orderUID":0,"machineID":"DEV-001"},{"wxTransactionID":"4200000060201803148424665650","numberID":"40201803141138007694521200097622","itemNo":26,"detailedinstalladdress":"33333","formatID":581,"descVal":"诺贝达半面包-豆奶流沙味","payVal":100,"backTimeline":"2018-03-14 11:46:58","payType":2,"orderUID":0,"machineID":"DEV-001"},{"wxTransactionID":"4200000080201803148423220731","numberID":"40201803141105387755643600020125","itemNo":26,"detailedinstalladdress":"33333","formatID":581,"descVal":"诺贝达半面包-豆奶流沙味","payVal":100,"backTimeline":"2018-03-14 11:07:42","payType":2,"orderUID":0,"machineID":"DEV-001"},{"wxTransactionID":"4200000059201803085059189158","numberID":"40201803081804588264483200077272","itemNo":11,"detailedinstalladdress":"33333","formatID":591,"descVal":"好丽友好友趣-韩国泡菜味45g","payVal":175,"backTimeline":"2018-03-08 18:05:25","payType":2,"orderUID":0,"machineID":"DEV-001"}]
     * tuikuandingdanshu : 64
     * daichuhuochengben : 235
     * daichuhuoSum : 100
     * tuikuanchengben : 12103
     * daichuhuodingdanshu : 4
     * tuikuanSum : 13702
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
         * wxTransactionID : 4200000071201803233954572001
         * numberID : 40201803231134299358513600075665
         * itemNo : 26
         * detailedinstalladdress : 33333
         * formatID : 581
         * descVal : 诺贝达半面包-豆奶流沙味
         * payVal : 100
         * backTimeline : 2018-03-23 11:34:55
         * payType : 2
         *
         * orderUID : 0
         * machineID : dev-001
         */

        private String wxTransactionID;
        private String numberID;
        private int itemNo;
        private String detailedinstalladdress;
        private int formatID;
        private String descVal;
        private int payVal;
        private String backTimeline;
        private int payType;
        private int orderUID;
        private String machineID;
        private int tui_val;

        public int getTui_val() {
            return tui_val;
        }

        public void setTui_val(int tui_val) {
            this.tui_val = tui_val;
        }

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

        public int getItemNo() {
            return itemNo;
        }

        public void setItemNo(int itemNo) {
            this.itemNo = itemNo;
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
