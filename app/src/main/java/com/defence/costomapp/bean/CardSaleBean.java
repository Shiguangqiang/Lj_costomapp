package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/5/21.
 */

public class CardSaleBean {


    private List<XiaoshouListBean> xiaoshouList;

    public List<XiaoshouListBean> getXiaoshouList() {
        return xiaoshouList;
    }

    public void setXiaoshouList(List<XiaoshouListBean> xiaoshouList) {
        this.xiaoshouList = xiaoshouList;
    }

    public static class XiaoshouListBean {
        /**
         * xiaoshouliang : 1
         * fakaliang : 3
         * shengyuliang : 2
         * name : 于妍
         */

        private int xiaoshouliang;
        private int fakaliang;
        private int shengyuliang;
        private String name;

        public int getXiaoshouliang() {
            return xiaoshouliang;
        }

        public void setXiaoshouliang(int xiaoshouliang) {
            this.xiaoshouliang = xiaoshouliang;
        }

        public int getFakaliang() {
            return fakaliang;
        }

        public void setFakaliang(int fakaliang) {
            this.fakaliang = fakaliang;
        }

        public int getShengyuliang() {
            return shengyuliang;
        }

        public void setShengyuliang(int shengyuliang) {
            this.shengyuliang = shengyuliang;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
