package com.defence.costomapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/19.
 */

public class DailyCostBean implements Serializable {


    /**
     * sign : 1
     * message : ---
     * result : {"date":"2018-03-19","map":{"sumSaleCount":3,"sumLiRun":170,"sumFanXian":0,"sumCost":445,"sumJinE":615},"list":[{"returnratio":0,"sumCost":98,"sumJinE":135,"saleCount":1,"machineID":"lj-010-04-001-001"},{"returnratio":0,"sumCost":347,"sumJinE":480,"saleCount":2,"machineID":"lj-010-04-002-001"}]}
     * timelineN : 2018-03-19 10:40:45
     */

    private int sign;
    private String message;
    private ResultBean result;
    private String timelineN;

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getTimelineN() {
        return timelineN;
    }

    public void setTimelineN(String timelineN) {
        this.timelineN = timelineN;
    }

    public static class ResultBean {
        /**
         * date : 2018-03-19
         * map : {"sumSaleCount":3,"sumLiRun":170,"sumFanXian":0,"sumCost":445,"sumJinE":615}
         * list : [{"returnratio":0,"sumCost":98,"sumJinE":135,"saleCount":1,"machineID":"lj-010-04-001-001"},{"returnratio":0,"sumCost":347,"sumJinE":480,"saleCount":2,"machineID":"lj-010-04-002-001"}]
         */

        private String date;
        private MapBean map;
        private List<ListBean> list;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public MapBean getMap() {
            return map;
        }

        public void setMap(MapBean map) {
            this.map = map;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class MapBean {
            /**
             * sumSaleCount : 3
             * sumLiRun : 170
             * sumFanXian : 0
             * sumCost : 445
             * sumJinE : 615
             */

            private int sumSaleCount;
            private int sumLiRun;
            private int sumFanXian;
            private int sumCost;
            private int sumJinE;

            public int getSumSaleCount() {
                return sumSaleCount;
            }

            public void setSumSaleCount(int sumSaleCount) {
                this.sumSaleCount = sumSaleCount;
            }

            public int getSumLiRun() {
                return sumLiRun;
            }

            public void setSumLiRun(int sumLiRun) {
                this.sumLiRun = sumLiRun;
            }

            public int getSumFanXian() {
                return sumFanXian;
            }

            public void setSumFanXian(int sumFanXian) {
                this.sumFanXian = sumFanXian;
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
        }

        public static class ListBean {
            /**
             * returnratio : 0
             * sumCost : 98
             * sumJinE : 135
             * saleCount : 1
             * machineID : lj-010-04-001-001
             */

            private int returnratio;
            private int sumCost;
            private int sumJinE;
            private int saleCount;
            private String machineID;

            public int getReturnratio() {
                return returnratio;
            }

            public void setReturnratio(int returnratio) {
                this.returnratio = returnratio;
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

            public int getSaleCount() {
                return saleCount;
            }

            public void setSaleCount(int saleCount) {
                this.saleCount = saleCount;
            }

            public String getMachineID() {
                return machineID;
            }

            public void setMachineID(String machineID) {
                this.machineID = machineID;
            }
        }
    }
}
