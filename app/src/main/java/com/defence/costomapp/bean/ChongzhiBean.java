package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/16.
 */

public class ChongzhiBean {


    /**
     * bankNo : 56588
     * pingtaiNum : 351
     * list : [{"userID":273,"bankNo":55,"timeline":"2018-03-13 11:39:17","mphone":"18610527597","hm":25300},{"userID":271,"bankNo":3934,"timeline":"2018-03-09 19:23:39","mphone":"13910997076","hm":10000},{"userID":257,"bankNo":3842,"timeline":"2018-03-14 09:36:38","mphone":"13264415615","hm":6200},{"userID":265,"bankNo":4244,"timeline":"2018-03-16 02:36:34","mphone":"13373068170","hm":5900},{"userID":263,"bankNo":6010,"timeline":"2018-02-09 18:24:21","mphone":"13488686332","hm":5100},{"userID":259,"bankNo":5000,"timeline":"2018-02-09 21:21:46","mphone":"13683293908","hm":5010},{"userID":255,"bankNo":4244,"timeline":"2018-02-09 18:05:10","mphone":"18901309182","hm":5000},{"userID":297,"bankNo":5000,"timeline":"2018-02-13 20:18:52","mphone":"18710038806","hm":5000},{"userID":261,"bankNo":5000,"timeline":"2018-02-09 18:25:09","mphone":"13651077452","hm":5000},{"userID":267,"bankNo":4433,"timeline":"2018-02-09 18:07:19","mphone":"13910757065","hm":5000}]
     * chongzhinum : 117653
     * regnum : 190
     * weixinNum : 1130
     */

    private int bankNo;
    private int pingtaiNum;
    private int chongzhinum;
    private int regnum;
    private int weixinNum;
    private List<ListBean> list;

    public int getBankNo() {
        return bankNo;
    }

    public void setBankNo(int bankNo) {
        this.bankNo = bankNo;
    }

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

    public int getRegnum() {
        return regnum;
    }

    public void setRegnum(int regnum) {
        this.regnum = regnum;
    }

    public int getWeixinNum() {
        return weixinNum;
    }

    public void setWeixinNum(int weixinNum) {
        this.weixinNum = weixinNum;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * userID : 273
         * bankNo : 55
         * timeline : 2018-03-13 11:39:17
         * mphone : 18610527597
         * hm : 25300
         */

        private int userID;
        private int bankNo;
        private String timeline;
        private String mphone;
        private int hm;

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public int getBankNo() {
            return bankNo;
        }

        public void setBankNo(int bankNo) {
            this.bankNo = bankNo;
        }

        public String getTimeline() {
            return timeline;
        }

        public void setTimeline(String timeline) {
            this.timeline = timeline;
        }

        public String getMphone() {
            return mphone;
        }

        public void setMphone(String mphone) {
            this.mphone = mphone;
        }

        public int getHm() {
            return hm;
        }

        public void setHm(int hm) {
            this.hm = hm;
        }
    }
}
