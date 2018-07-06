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
         * id : 507
         * showName : 冰露矿泉水
         * descVal : 550ml
         * shangPinID : 391
         */
        /**
         * commodityspecificationsid : 559
         * shang_pin_full_name : 甜酥夹心饼干-榛子巧克力味80+16g
         * machinenumbers : lj-010-04-001-001
         */

        private int id;
        private String showName;
        private String descVal;
        private int shangPinID;

        private int commodityspecificationsid;
        private String shang_pin_full_name;
        private String machinenumbers;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getShowName() {
            return showName == null ? "" : showName;
        }

        public void setShowName(String showName) {
            this.showName = showName;
        }

        public String getDescVal() {
            return descVal == null ? "" : descVal;
        }

        public void setDescVal(String descVal) {
            this.descVal = descVal;
        }

        public int getShangPinID() {
            return shangPinID;
        }

        public void setShangPinID(int shangPinID) {
            this.shangPinID = shangPinID;
        }

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
