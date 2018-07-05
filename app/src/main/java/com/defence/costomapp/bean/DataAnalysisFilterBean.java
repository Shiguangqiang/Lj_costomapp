package com.defence.costomapp.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sgq
 * Create Date 2018/7/4 and 14:15
 * Used 数据分析
 */
public class DataAnalysisFilterBean {


    private List<TjListBean> tjList;

    public List<TjListBean> getTjList() {
        if (tjList == null) {
            return new ArrayList<>();
        }
        return tjList;
    }

    public void setTjList(List<TjListBean> tjList) {
        this.tjList = tjList;
    }

    public static class TjListBean {
        /**
         * typeid : 1
         * name : 总销售金额
         * ctype : 0
         * beiyong1 :
         * beiyong2 :
         * beiyong3 :
         */

        private int typeid;
        private String name;
        private int ctype;
        private String beiyong1;
        private String beiyong2;
        private String beiyong3;

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCtype() {
            return ctype;
        }

        public void setCtype(int ctype) {
            this.ctype = ctype;
        }

        public String getBeiyong1() {
            return beiyong1 == null ? "" : beiyong1;
        }

        public void setBeiyong1(String beiyong1) {
            this.beiyong1 = beiyong1;
        }

        public String getBeiyong2() {
            return beiyong2 == null ? "" : beiyong2;
        }

        public void setBeiyong2(String beiyong2) {
            this.beiyong2 = beiyong2;
        }

        public String getBeiyong3() {
            return beiyong3 == null ? "" : beiyong3;
        }

        public void setBeiyong3(String beiyong3) {
            this.beiyong3 = beiyong3;
        }
    }
}
