package com.defence.costomapp.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sgq
 * Create Date 2018/7/5 and 15:39
 * Used  数据分析  商品筛选
 */
public class DataAnGoodsFilterBean {


    private List<ShangpinListBean> shangpinList;

    public List<ShangpinListBean> getShangpinList() {
        if (shangpinList == null) {
            return new ArrayList<>();
        }
        return shangpinList;
    }

    public void setShangpinList(List<ShangpinListBean> shangpinList) {
        this.shangpinList = shangpinList;
    }

    public static class ShangpinListBean {
        /**
         * commodityspecificationsid : 521
         * shang_pin_full_name : 七度空间-优雅系列-5片
         * machinenumbers : LJ-010-04-002-001
         */

        private int commodityspecificationsid;
        private String shang_pin_full_name;
        private String machinenumbers;

        public int getCommodityspecificationsid() {
            return commodityspecificationsid;
        }

        public void setCommodityspecificationsid(int commodityspecificationsid) {
            this.commodityspecificationsid = commodityspecificationsid;
        }

        public String getShang_pin_full_name() {
            return shang_pin_full_name == null ? "" : shang_pin_full_name;
        }

        public void setShang_pin_full_name(String shang_pin_full_name) {
            this.shang_pin_full_name = shang_pin_full_name;
        }

        public String getMachinenumbers() {
            return machinenumbers == null ? "" : machinenumbers;
        }

        public void setMachinenumbers(String machinenumbers) {
            this.machinenumbers = machinenumbers;
        }
    }
}
