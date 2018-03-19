package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/15.
 */

public class MaciChuNumBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * ad : 北京市北京市丰台区富卓苑小区2号林内壁炉门东
         * ct : 4
         * machinenumber : LJ-010-04-001-002
         */

        private String ad;
        private int ct;
        private String machinenumber;

        public String getAd() {
            return ad;
        }

        public void setAd(String ad) {
            this.ad = ad;
        }

        public int getCt() {
            return ct;
        }

        public void setCt(int ct) {
            this.ct = ct;
        }

        public String getMachinenumber() {
            return machinenumber;
        }

        public void setMachinenumber(String machinenumber) {
            this.machinenumber = machinenumber;
        }
    }
}
