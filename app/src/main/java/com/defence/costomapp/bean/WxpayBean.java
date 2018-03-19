package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/15.
 */

public class WxpayBean {

    /**
     * pingtaiNum : 346
     * list : [{"wx":"W9HYbr7eIY","wxOpenID":"oAyIL1Zta7UH2qFMHJW9HYbr7eIY","pv":18600,"orderUID":0},{"wx":"z5B9ES3ORo","wxOpenID":"oAyIL1XnKex4uWumP5z5B9ES3ORo","pv":12930,"orderUID":0},{"wx":"2muSGfmClM","wxOpenID":"oAyIL1cTMuKiNhlP2y2muSGfmClM","pv":8950,"orderUID":0},{"wx":"AD04xKM-aU","wxOpenID":"oAyIL1Z4974kOQx2sCAD04xKM-aU","pv":8910,"orderUID":0},{"wx":"VqClckc9wg","wxOpenID":"oAyIL1denh0wGuE_Q2VqClckc9wg","pv":6770,"orderUID":0},{"wx":"P5IrbgsZ3o","wxOpenID":"oAyIL1aWpyWD1pBJPgP5IrbgsZ3o","pv":6260,"orderUID":0},{"wx":"Nu2pxlW7Mk","wxOpenID":"oAyIL1f7oFEgTwEkpwNu2pxlW7Mk","pv":5360,"orderUID":0},{"wx":"jVazQOq79Q","wxOpenID":"oAyIL1WF6PZNWCYAjTjVazQOq79Q","pv":5300,"orderUID":0},{"wx":"wFvbHrjAgM","wxOpenID":"oAyIL1eGhV2VoMsOc0wFvbHrjAgM","pv":4740,"orderUID":0},{"wx":"gQkJs4csh4","wxOpenID":"oAyIL1XhDKHBiX8LcegQkJs4csh4","pv":4410,"orderUID":0}]
     * weixinNum : 1092
     * reg_user : 186
     */

    private int pingtaiNum;
    private int weixinNum;
    private int reg_user;
    private List<ListBean> list;

    public int getPingtaiNum() {
        return pingtaiNum;
    }

    public void setPingtaiNum(int pingtaiNum) {
        this.pingtaiNum = pingtaiNum;
    }

    public int getWeixinNum() {
        return weixinNum;
    }

    public void setWeixinNum(int weixinNum) {
        this.weixinNum = weixinNum;
    }

    public int getReg_user() {
        return reg_user;
    }

    public void setReg_user(int reg_user) {
        this.reg_user = reg_user;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * wx : W9HYbr7eIY
         * wxOpenID : oAyIL1Zta7UH2qFMHJW9HYbr7eIY
         * pv : 18600
         * orderUID : 0
         */

        private String wx;
        private String wxOpenID;
        private int pv;
        private int orderUID;

        public String getWx() {
            return wx;
        }

        public void setWx(String wx) {
            this.wx = wx;
        }

        public String getWxOpenID() {
            return wxOpenID;
        }

        public void setWxOpenID(String wxOpenID) {
            this.wxOpenID = wxOpenID;
        }

        public int getPv() {
            return pv;
        }

        public void setPv(int pv) {
            this.pv = pv;
        }

        public int getOrderUID() {
            return orderUID;
        }

        public void setOrderUID(int orderUID) {
            this.orderUID = orderUID;
        }
    }
}
