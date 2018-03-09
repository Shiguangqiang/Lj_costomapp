package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/9.
 */

public class WorkLogDateBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id :
         * shelvestype :
         * machineNo : lj-010-04-001-001
         * itemNo :
         * commodityid :
         * shelvesdate :
         * shelvesuserid :
         * shelvesnum :
         * machineID :
         * readAID :
         * readDT :
         * shedate : 2018-03-07
         */

        private String id;
        private String shelvestype;
        private String machineNo;
        private String itemNo;
        private String commodityid;
        private String shelvesdate;
        private String shelvesuserid;
        private String shelvesnum;
        private String machineID;
        private String readAID;
        private String readDT;
        private String shedate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShelvestype() {
            return shelvestype;
        }

        public void setShelvestype(String shelvestype) {
            this.shelvestype = shelvestype;
        }

        public String getMachineNo() {
            return machineNo;
        }

        public void setMachineNo(String machineNo) {
            this.machineNo = machineNo;
        }

        public String getItemNo() {
            return itemNo;
        }

        public void setItemNo(String itemNo) {
            this.itemNo = itemNo;
        }

        public String getCommodityid() {
            return commodityid;
        }

        public void setCommodityid(String commodityid) {
            this.commodityid = commodityid;
        }

        public String getShelvesdate() {
            return shelvesdate;
        }

        public void setShelvesdate(String shelvesdate) {
            this.shelvesdate = shelvesdate;
        }

        public String getShelvesuserid() {
            return shelvesuserid;
        }

        public void setShelvesuserid(String shelvesuserid) {
            this.shelvesuserid = shelvesuserid;
        }

        public String getShelvesnum() {
            return shelvesnum;
        }

        public void setShelvesnum(String shelvesnum) {
            this.shelvesnum = shelvesnum;
        }

        public String getMachineID() {
            return machineID;
        }

        public void setMachineID(String machineID) {
            this.machineID = machineID;
        }

        public String getReadAID() {
            return readAID;
        }

        public void setReadAID(String readAID) {
            this.readAID = readAID;
        }

        public String getReadDT() {
            return readDT;
        }

        public void setReadDT(String readDT) {
            this.readDT = readDT;
        }

        public String getShedate() {
            return shedate;
        }

        public void setShedate(String shedate) {
            this.shedate = shedate;
        }
    }
}
