package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/16.
 */

public class ChongzhiDetailBean {


    /**
     * pingtaiNum : 77
     * chongzhinum : 25300
     * tuikuanNum : 1
     * list : [{"orderNumber":"60201803021555034229501001283366","userID":273,"howMuch":5000,"chargeType":1,"chargeKey":1,"chargeData":"","moneyTo":273,"timeline":"2018-03-02 15:55:03","resultVal":4,"jsonData":""},{"orderNumber":"60201803101332050789121901121711","userID":273,"howMuch":100,"chargeType":1,"chargeKey":1,"chargeData":"","moneyTo":273,"timeline":"2018-03-10 13:32:05","resultVal":4,"jsonData":""},{"orderNumber":"60201803101332364168994101102439","userID":273,"howMuch":100,"chargeType":1,"chargeKey":1,"chargeData":"","moneyTo":273,"timeline":"2018-03-10 13:32:36","resultVal":4,"jsonData":""},{"orderNumber":"60201803120000510442381401297906","userID":273,"howMuch":100,"chargeType":1,"chargeKey":1,"chargeData":"","moneyTo":273,"timeline":"2018-03-12 00:00:51","resultVal":4,"jsonData":""},{"orderNumber":"60201803131139171738004301259802","userID":273,"howMuch":5000,"chargeType":1,"chargeKey":1,"chargeData":"","moneyTo":273,"timeline":"2018-03-13 11:39:17","resultVal":4,"jsonData":""}]
     * yueNum : 55
     * payval : 21177
     * tkcbNum : 882
     */

    private int pingtaiNum;
    private int chongzhinum;
    private int tuikuanNum;
    private int yueNum;
    private int payval;
    private int tkcbNum;
    private List<ListBean> list;

    public int getPingtaiNum() {
        return pingtaiNum;
    }

    public void setPingtaiNum(int pingtaiNum) {
        this.pingtaiNum = pingtaiNum;
    }

    public int getChongzhinum() {
        return chongzhinum;
    }

    public void setChongzhinum(int chongzhinum) {
        this.chongzhinum = chongzhinum;
    }

    public int getTuikuanNum() {
        return tuikuanNum;
    }

    public void setTuikuanNum(int tuikuanNum) {
        this.tuikuanNum = tuikuanNum;
    }

    public int getYueNum() {
        return yueNum;
    }

    public void setYueNum(int yueNum) {
        this.yueNum = yueNum;
    }

    public int getPayval() {
        return payval;
    }

    public void setPayval(int payval) {
        this.payval = payval;
    }

    public int getTkcbNum() {
        return tkcbNum;
    }

    public void setTkcbNum(int tkcbNum) {
        this.tkcbNum = tkcbNum;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * orderNumber : 60201803021555034229501001283366
         * userID : 273
         * howMuch : 5000
         * chargeType : 1
         * chargeKey : 1
         * chargeData :
         * moneyTo : 273
         * timeline : 2018-03-02 15:55:03
         * resultVal : 4
         * jsonData :
         */

        private String orderNumber;
        private int userID;
        private int howMuch;
        private int chargeType;
        private int chargeKey;
        private String chargeData;
        private int moneyTo;
        private String timeline;
        private int resultVal;
        private String jsonData;

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public int getHowMuch() {
            return howMuch;
        }

        public void setHowMuch(int howMuch) {
            this.howMuch = howMuch;
        }

        public int getChargeType() {
            return chargeType;
        }

        public void setChargeType(int chargeType) {
            this.chargeType = chargeType;
        }

        public int getChargeKey() {
            return chargeKey;
        }

        public void setChargeKey(int chargeKey) {
            this.chargeKey = chargeKey;
        }

        public String getChargeData() {
            return chargeData;
        }

        public void setChargeData(String chargeData) {
            this.chargeData = chargeData;
        }

        public int getMoneyTo() {
            return moneyTo;
        }

        public void setMoneyTo(int moneyTo) {
            this.moneyTo = moneyTo;
        }

        public String getTimeline() {
            return timeline;
        }

        public void setTimeline(String timeline) {
            this.timeline = timeline;
        }

        public int getResultVal() {
            return resultVal;
        }

        public void setResultVal(int resultVal) {
            this.resultVal = resultVal;
        }

        public String getJsonData() {
            return jsonData;
        }

        public void setJsonData(String jsonData) {
            this.jsonData = jsonData;
        }
    }
}
