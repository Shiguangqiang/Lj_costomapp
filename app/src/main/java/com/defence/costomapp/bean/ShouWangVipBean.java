package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/5/23.
 */

public class ShouWangVipBean {


    /**
     * xfkList : [{"haoma":"18330072989","id":19,"jieshuhuiyuanshijian":"2018-06-21 23:59:59","goumaitypename":"月卡","goumaishijian":"2018-05-08 15:59:01","kaishihuiyuanshijian":"2018-05-08 17:55:15","ct":29},{"haoma":"13264415615","id":77,"jieshuhuiyuanshijian":"2018-06-08 23:59:59","goumaitypename":"周卡","goumaishijian":"2018-05-14 16:34:23","kaishihuiyuanshijian":"2018-05-18 13:43:58","ct":16},{"haoma":"15944237542","id":8,"jieshuhuiyuanshijian":"2018-05-29 23:59:59","goumaitypename":"月卡","goumaishijian":"2018-05-08 15:06:19","kaishihuiyuanshijian":"2018-05-08 14:17:12","ct":6},{"haoma":"13683293908","id":81,"jieshuhuiyuanshijian":"2018-05-17 23:59:59","goumaitypename":"周卡","goumaishijian":"2018-05-15 10:28:20","kaishihuiyuanshijian":"2018-05-17 11:45:31","ct":-6},{"haoma":"20180503123924148659","id":39,"jieshuhuiyuanshijian":"","goumaitypename":"周卡","goumaishijian":"2018-05-09 10:24:37","kaishihuiyuanshijian":"","ct":""},{"haoma":"20180514135927467645","id":107,"jieshuhuiyuanshijian":"","goumaitypename":"周卡","goumaishijian":"2018-05-18 11:22:14","kaishihuiyuanshijian":"","ct":""},{"haoma":"20180514135927731140","id":75,"jieshuhuiyuanshijian":"","goumaitypename":"周卡","goumaishijian":"2018-05-14 15:05:16","kaishihuiyuanshijian":"","ct":""},{"haoma":"20180514135929909699","id":87,"jieshuhuiyuanshijian":"","goumaitypename":"周卡","goumaishijian":"","kaishihuiyuanshijian":"","ct":""},{"haoma":"20180514135928841322","id":98,"jieshuhuiyuanshijian":"","goumaitypename":"周卡","goumaishijian":"","kaishihuiyuanshijian":"","ct":""},{"haoma":"20180514135924319700","id":101,"jieshuhuiyuanshijian":"","goumaitypename":"周卡","goumaishijian":"","kaishihuiyuanshijian":"","ct":""}]
     * huiyuanzongshu : 3
     */

    private int huiyuanzongshu;
    private List<XfkListBean> xfkList;

    public int getHuiyuanzongshu() {
        return huiyuanzongshu;
    }

    public void setHuiyuanzongshu(int huiyuanzongshu) {
        this.huiyuanzongshu = huiyuanzongshu;
    }

    public List<XfkListBean> getXfkList() {
        return xfkList;
    }

    public void setXfkList(List<XfkListBean> xfkList) {
        this.xfkList = xfkList;
    }

    public static class XfkListBean {
        /**
         * haoma : 18330072989
         * id : 19
         * jieshuhuiyuanshijian : 2018-06-21 23:59:59
         * goumaitypename : 月卡
         * goumaishijian : 2018-05-08 15:59:01
         * kaishihuiyuanshijian : 2018-05-08 17:55:15
         * ct : 29
         */

        private String haoma;
        private int id;
        private String jieshuhuiyuanshijian;
        private String goumaitypename;
        private String goumaishijian;
        private String kaishihuiyuanshijian;
        private String ct;
        private String cccctype;


        public String getCccctype() {
            return cccctype;
        }

        public void setCccctype(String cccctype) {
            this.cccctype = cccctype;
        }


        public String getHaoma() {
            return haoma;
        }

        public void setHaoma(String haoma) {
            this.haoma = haoma;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getJieshuhuiyuanshijian() {
            return jieshuhuiyuanshijian;
        }

        public void setJieshuhuiyuanshijian(String jieshuhuiyuanshijian) {
            this.jieshuhuiyuanshijian = jieshuhuiyuanshijian;
        }

        public String getGoumaitypename() {
            return goumaitypename;
        }

        public void setGoumaitypename(String goumaitypename) {
            this.goumaitypename = goumaitypename;
        }

        public String getGoumaishijian() {
            return goumaishijian;
        }

        public void setGoumaishijian(String goumaishijian) {
            this.goumaishijian = goumaishijian;
        }

        public String getKaishihuiyuanshijian() {
            return kaishihuiyuanshijian;
        }

        public void setKaishihuiyuanshijian(String kaishihuiyuanshijian) {
            this.kaishihuiyuanshijian = kaishihuiyuanshijian;
        }

        public String getCt() {
            return ct;
        }

        public void setCt(String ct) {
            this.ct = ct;
        }
    }
}
