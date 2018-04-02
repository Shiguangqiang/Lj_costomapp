package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/14.
 */

public class ShopHistoryDetailBean {

    /**
     * list : [{"test_shang_pin":0,"numberID":"40201803140514088491723901291233","orderBy":2,"payVal":0,"orderUID":571,"adminGroupID":1,"machineID":"lj-010-04-002-001","wxTransactionID":"","kuaiDiNumber":"","liRun":0,"orderTimeline":"2018-03-14 05:14:08","kuaiDiName":"","kuaiDiTimeline":"2018-03-14 05:15:04","shouHuoTimeline":"","shouHuoRen":"","shouHuoRenIDCard":"","payTimeline":"2018-03-14 05:14:48","totalVal":0,"voucherMoneyID":0,"shouHuoAddr":"","itemNo":23,"returnAdminID":0,"status":4,"howMuch":0,"orderMessage":"","groupNID":"41201803140514088494237901251161","shouHuoPhone":"","kuaiDiAdminID":0,"jsonData":"<===>","discount":90,"tui_val":0,"wxOpenID":"","descVal":"薄荷-观音茶-3g（0元购）","cancelTimeline":"","payType":1,"kuaiDiFee":0},{"test_shang_pin":0,"numberID":"40201803071324254352671801187279","orderBy":2,"payVal":225,"orderUID":571,"adminGroupID":1,"machineID":"lj-010-04-002-001","wxTransactionID":"","kuaiDiNumber":"","liRun":125,"orderTimeline":"2018-03-07 13:24:25","kuaiDiName":"","kuaiDiTimeline":"2018-03-07 13:24:50","shouHuoTimeline":"","shouHuoRen":"","shouHuoRenIDCard":"","payTimeline":"2018-03-07 13:24:32","totalVal":250,"voucherMoneyID":0,"shouHuoAddr":"","itemNo":58,"returnAdminID":0,"status":4,"howMuch":0,"orderMessage":"","groupNID":"41201803071324254352797001134735","shouHuoPhone":"","kuaiDiAdminID":0,"jsonData":"<===>","discount":90,"tui_val":0,"wxOpenID":"","descVal":"二环内汽水-水蜜桃味450ml","cancelTimeline":"","payType":1,"kuaiDiFee":0}]
     * payval : 225
     */

    private int payval;
    private List<ListBean> list;

    public int getPayval() {
        return payval;
    }

    public void setPayval(int payval) {
        this.payval = payval;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {


        /**
         * test_shang_pin : 0
         * numberID : 40201803140514088491723901291233
         * orderBy : 2
         * payVal : 0
         * orderUID : 571
         * adminGroupID : 1
         * machineID : lj-010-04-002-001
         * wxTransactionID :
         * kuaiDiNumber :
         * liRun : 0
         * orderTimeline : 2018-03-14 05:14:08
         * kuaiDiName :
         * kuaiDiTimeline : 2018-03-14 05:15:04
         * shouHuoTimeline :
         * shouHuoRen :
         * shouHuoRenIDCard :
         * payTimeline : 2018-03-14 05:14:48
         * totalVal : 0
         * voucherMoneyID : 0
         * shouHuoAddr :
         * itemNo : 23
         * returnAdminID : 0
         * status : 4
         * howMuch : 0
         * orderMessage :
         * groupNID : 41201803140514088494237901251161
         * shouHuoPhone :
         * kuaiDiAdminID : 0
         * jsonData : <===>
         * discount : 90
         * tui_val : 0
         * wxOpenID :
         * descVal : 薄荷-观音茶-3g（0元购）
         * cancelTimeline :
         * payType : 1
         * kuaiDiFee : 0
         */


        private int type;
        private String moneyy = "0";
        private String newdate;

        private int test_shang_pin;
        private String numberID;
        private int orderBy;
        private int payVal;
        private int orderUID;
        private int adminGroupID;
        private String machineID;
        private String wxTransactionID;
        private String kuaiDiNumber;
        private int liRun;
        private String orderTimeline;
        private String kuaiDiName;
        private String kuaiDiTimeline;
        private String shouHuoTimeline;
        private String shouHuoRen;
        private String shouHuoRenIDCard;
        private String payTimeline;
        private int totalVal;
        private int voucherMoneyID;
        private String shouHuoAddr;
        private int itemNo;
        private int returnAdminID;
        private int status;
        private int howMuch;
        private String orderMessage;
        private String groupNID;
        private String shouHuoPhone;
        private int kuaiDiAdminID;
        private String jsonData;
        private int discount;
        private int tui_val;
        private String wxOpenID;
        private String descVal;
        private String cancelTimeline;
        private int payType;
        private int kuaiDiFee;

        public String getNewdate() {
            return newdate;
        }

        public void setNewdate(String newdate) {
            this.newdate = newdate;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getMoneyy() {
            return moneyy;
        }

        public void setMoneyy(String moneyy) {
            this.moneyy = moneyy;
        }

        public int getTest_shang_pin() {
            return test_shang_pin;
        }

        public void setTest_shang_pin(int test_shang_pin) {
            this.test_shang_pin = test_shang_pin;
        }

        public String getNumberID() {
            return numberID;
        }

        public void setNumberID(String numberID) {
            this.numberID = numberID;
        }

        public int getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(int orderBy) {
            this.orderBy = orderBy;
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

        public String getKuaiDiNumber() {
            return kuaiDiNumber;
        }

        public void setKuaiDiNumber(String kuaiDiNumber) {
            this.kuaiDiNumber = kuaiDiNumber;
        }

        public int getLiRun() {
            return liRun;
        }

        public void setLiRun(int liRun) {
            this.liRun = liRun;
        }

        public String getOrderTimeline() {
            return orderTimeline;
        }

        public void setOrderTimeline(String orderTimeline) {
            this.orderTimeline = orderTimeline;
        }

        public String getKuaiDiName() {
            return kuaiDiName;
        }

        public void setKuaiDiName(String kuaiDiName) {
            this.kuaiDiName = kuaiDiName;
        }

        public String getKuaiDiTimeline() {
            return kuaiDiTimeline;
        }

        public void setKuaiDiTimeline(String kuaiDiTimeline) {
            this.kuaiDiTimeline = kuaiDiTimeline;
        }

        public String getShouHuoTimeline() {
            return shouHuoTimeline;
        }

        public void setShouHuoTimeline(String shouHuoTimeline) {
            this.shouHuoTimeline = shouHuoTimeline;
        }

        public String getShouHuoRen() {
            return shouHuoRen;
        }

        public void setShouHuoRen(String shouHuoRen) {
            this.shouHuoRen = shouHuoRen;
        }

        public String getShouHuoRenIDCard() {
            return shouHuoRenIDCard;
        }

        public void setShouHuoRenIDCard(String shouHuoRenIDCard) {
            this.shouHuoRenIDCard = shouHuoRenIDCard;
        }

        public String getPayTimeline() {
            return payTimeline;
        }

        public void setPayTimeline(String payTimeline) {
            this.payTimeline = payTimeline;
        }

        public int getTotalVal() {
            return totalVal;
        }

        public void setTotalVal(int totalVal) {
            this.totalVal = totalVal;
        }

        public int getVoucherMoneyID() {
            return voucherMoneyID;
        }

        public void setVoucherMoneyID(int voucherMoneyID) {
            this.voucherMoneyID = voucherMoneyID;
        }

        public String getShouHuoAddr() {
            return shouHuoAddr;
        }

        public void setShouHuoAddr(String shouHuoAddr) {
            this.shouHuoAddr = shouHuoAddr;
        }

        public int getItemNo() {
            return itemNo;
        }

        public void setItemNo(int itemNo) {
            this.itemNo = itemNo;
        }

        public int getReturnAdminID() {
            return returnAdminID;
        }

        public void setReturnAdminID(int returnAdminID) {
            this.returnAdminID = returnAdminID;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getHowMuch() {
            return howMuch;
        }

        public void setHowMuch(int howMuch) {
            this.howMuch = howMuch;
        }

        public String getOrderMessage() {
            return orderMessage;
        }

        public void setOrderMessage(String orderMessage) {
            this.orderMessage = orderMessage;
        }

        public String getGroupNID() {
            return groupNID;
        }

        public void setGroupNID(String groupNID) {
            this.groupNID = groupNID;
        }

        public String getShouHuoPhone() {
            return shouHuoPhone;
        }

        public void setShouHuoPhone(String shouHuoPhone) {
            this.shouHuoPhone = shouHuoPhone;
        }

        public int getKuaiDiAdminID() {
            return kuaiDiAdminID;
        }

        public void setKuaiDiAdminID(int kuaiDiAdminID) {
            this.kuaiDiAdminID = kuaiDiAdminID;
        }

        public String getJsonData() {
            return jsonData;
        }

        public void setJsonData(String jsonData) {
            this.jsonData = jsonData;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
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

        public String getCancelTimeline() {
            return cancelTimeline;
        }

        public void setCancelTimeline(String cancelTimeline) {
            this.cancelTimeline = cancelTimeline;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public int getKuaiDiFee() {
            return kuaiDiFee;
        }

        public void setKuaiDiFee(int kuaiDiFee) {
            this.kuaiDiFee = kuaiDiFee;
        }
    }
}
