package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/13.
 */

public class TjshopDetailBean {


    /**
     * map_data : {"sumCostStr":17.64,"sumLiRun":738,"sumCost":1764,"sumJinE":2502,"sumLiRunStr":7.38,"sumJinEStr":25.02,"saleCount":2}
     * date2 : 2018-03-13
     * list : [{"sumJinE":2502,"address":"北京市北京市丰台区","saleCount":2,"machinenumber":"LJ-010-04-001-001"}]
     * pMap : {"addr1":0,"addr2":0,"formatID":509,"addr3":0,"columns":"","time_end":"2018-03-13 23:59:59","ugTypes":" 0, 1, 3 ","time_start":"2018-03-13 00:00:00"}
     * date1 : 2018-03-13
     */

    private MapDataBean map_data;
    private String date2;
    private PMapBean pMap;
    private String date1;
    private List<ListBean> list;

    public MapDataBean getMap_data() {
        return map_data;
    }

    public void setMap_data(MapDataBean map_data) {
        this.map_data = map_data;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public PMapBean getPMap() {
        return pMap;
    }

    public void setPMap(PMapBean pMap) {
        this.pMap = pMap;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class MapDataBean {
        /**
         * sumCostStr : 17.64
         * sumLiRun : 738
         * sumCost : 1764
         * sumJinE : 2502
         * sumLiRunStr : 7.38
         * sumJinEStr : 25.02
         * saleCount : 2
         */

        private double sumCostStr;
        private int sumLiRun;
        private int sumCost;
        private int sumJinE;
        private double sumLiRunStr;
        private double sumJinEStr;
        private int saleCount;

        public double getSumCostStr() {
            return sumCostStr;
        }

        public void setSumCostStr(double sumCostStr) {
            this.sumCostStr = sumCostStr;
        }

        public int getSumLiRun() {
            return sumLiRun;
        }

        public void setSumLiRun(int sumLiRun) {
            this.sumLiRun = sumLiRun;
        }

        public int getSumCost() {
            return sumCost;
        }

        public void setSumCost(int sumCost) {
            this.sumCost = sumCost;
        }

        public int getSumJinE() {
            return sumJinE;
        }

        public void setSumJinE(int sumJinE) {
            this.sumJinE = sumJinE;
        }

        public double getSumLiRunStr() {
            return sumLiRunStr;
        }

        public void setSumLiRunStr(double sumLiRunStr) {
            this.sumLiRunStr = sumLiRunStr;
        }

        public double getSumJinEStr() {
            return sumJinEStr;
        }

        public void setSumJinEStr(double sumJinEStr) {
            this.sumJinEStr = sumJinEStr;
        }

        public int getSaleCount() {
            return saleCount;
        }

        public void setSaleCount(int saleCount) {
            this.saleCount = saleCount;
        }
    }

    public static class PMapBean {
        /**
         * addr1 : 0
         * addr2 : 0
         * formatID : 509
         * addr3 : 0
         * columns :
         * time_end : 2018-03-13 23:59:59
         * ugTypes :  0, 1, 3
         * time_start : 2018-03-13 00:00:00
         */

        private int addr1;
        private int addr2;
        private int formatID;
        private int addr3;
        private String columns;
        private String time_end;
        private String ugTypes;
        private String time_start;

        public int getAddr1() {
            return addr1;
        }

        public void setAddr1(int addr1) {
            this.addr1 = addr1;
        }

        public int getAddr2() {
            return addr2;
        }

        public void setAddr2(int addr2) {
            this.addr2 = addr2;
        }

        public int getFormatID() {
            return formatID;
        }

        public void setFormatID(int formatID) {
            this.formatID = formatID;
        }

        public int getAddr3() {
            return addr3;
        }

        public void setAddr3(int addr3) {
            this.addr3 = addr3;
        }

        public String getColumns() {
            return columns;
        }

        public void setColumns(String columns) {
            this.columns = columns;
        }

        public String getTime_end() {
            return time_end;
        }

        public void setTime_end(String time_end) {
            this.time_end = time_end;
        }

        public String getUgTypes() {
            return ugTypes;
        }

        public void setUgTypes(String ugTypes) {
            this.ugTypes = ugTypes;
        }

        public String getTime_start() {
            return time_start;
        }

        public void setTime_start(String time_start) {
            this.time_start = time_start;
        }
    }

    public static class ListBean {
        /**
         * sumJinE : 2502
         * address : 北京市北京市丰台区
         * saleCount : 2
         * machinenumber : LJ-010-04-001-001
         */

        private int sumJinE;
        private String address;
        private int saleCount;
        private String machinenumber;

        public int getSumJinE() {
            return sumJinE;
        }

        public void setSumJinE(int sumJinE) {
            this.sumJinE = sumJinE;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getSaleCount() {
            return saleCount;
        }

        public void setSaleCount(int saleCount) {
            this.saleCount = saleCount;
        }

        public String getMachinenumber() {
            return machinenumber;
        }

        public void setMachinenumber(String machinenumber) {
            this.machinenumber = machinenumber;
        }
    }
}
