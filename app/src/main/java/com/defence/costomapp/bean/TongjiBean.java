package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/12.
 */

public class TongjiBean {


    /**
     * machine_list : [{"sumJinE":3170,"address":"北京市北京市丰台区","detailedinstalladdress":"芳城园二区门口","machine_id":"70201802081416404953601001243158","machinenumber":"LJ-010-04-002-001"},{"sumJinE":470,"address":"北京市北京市丰台区","detailedinstalladdress":"富卓苑小区2号林内壁炉门东","machine_id":"70201802081414333623557101250857","machinenumber":"LJ-010-04-001-002"}]
     * map_data : {"liRun1":1293,"liRun2":1293,"freeSaleCount":1,"sumJinE":3640,"payCost":2347,"freeCost":0,"paySaleCount":11}
     * goods_list : [{"descVal":"冰露矿泉水-550ml","formatID":507,"saleCount":4},{"descVal":"好丽友好友趣-加勒比烤翅味45g","formatID":593,"saleCount":2},{"descVal":"牛轧奶芙-蔓越莓味120g","formatID":509,"saleCount":1},{"descVal":"加多宝-310ml","formatID":553,"saleCount":1},{"descVal":"甜酥夹心饼干-榛子巧克力味80+16g","formatID":559,"saleCount":1},{"descVal":"尝香棒火鸡面-超辣味","formatID":569,"saleCount":1},{"descVal":"可口可乐-600ml","formatID":601,"saleCount":1},{"descVal":"薄荷-观音茶-3g（0元购）","formatID":611,"saleCount":1}]
     * data_map1 : {"sumCost":0,"sumJinE":0,"saleCount":1}
     * date2 : 2018-03-12
     * data_map0 : {"sumCost":2347,"sumJinE":3640,"saleCount":11}
     * date1 : 2018-03-12
     */

    private MapDataBean map_data;
    private DataMap1Bean data_map1;
    private String date2;
    private DataMap0Bean data_map0;
    private String date1;
    private List<MachineListBean> machine_list;
    private List<GoodsListBean> goods_list;

    public MapDataBean getMap_data() {
        return map_data;
    }

    public void setMap_data(MapDataBean map_data) {
        this.map_data = map_data;
    }

    public DataMap1Bean getData_map1() {
        return data_map1;
    }

    public void setData_map1(DataMap1Bean data_map1) {
        this.data_map1 = data_map1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public DataMap0Bean getData_map0() {
        return data_map0;
    }

    public void setData_map0(DataMap0Bean data_map0) {
        this.data_map0 = data_map0;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public List<MachineListBean> getMachine_list() {
        return machine_list;
    }

    public void setMachine_list(List<MachineListBean> machine_list) {
        this.machine_list = machine_list;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class MapDataBean {
        /**
         * liRun1 : 1293
         * liRun2 : 1293
         * freeSaleCount : 1
         * sumJinE : 3640
         * payCost : 2347
         * freeCost : 0
         * paySaleCount : 11
         */

        private int liRun1;
        private int liRun2;
        private int freeSaleCount;
        private int sumJinE;
        private int payCost;
        private int freeCost;
        private int paySaleCount;

        public int getLiRun1() {
            return liRun1;
        }

        public void setLiRun1(int liRun1) {
            this.liRun1 = liRun1;
        }

        public int getLiRun2() {
            return liRun2;
        }

        public void setLiRun2(int liRun2) {
            this.liRun2 = liRun2;
        }

        public int getFreeSaleCount() {
            return freeSaleCount;
        }

        public void setFreeSaleCount(int freeSaleCount) {
            this.freeSaleCount = freeSaleCount;
        }

        public int getSumJinE() {
            return sumJinE;
        }

        public void setSumJinE(int sumJinE) {
            this.sumJinE = sumJinE;
        }

        public int getPayCost() {
            return payCost;
        }

        public void setPayCost(int payCost) {
            this.payCost = payCost;
        }

        public int getFreeCost() {
            return freeCost;
        }

        public void setFreeCost(int freeCost) {
            this.freeCost = freeCost;
        }

        public int getPaySaleCount() {
            return paySaleCount;
        }

        public void setPaySaleCount(int paySaleCount) {
            this.paySaleCount = paySaleCount;
        }
    }

    public static class DataMap1Bean {
        /**
         * sumCost : 0
         * sumJinE : 0
         * saleCount : 1
         */

        private int sumCost;
        private int sumJinE;
        private int saleCount;

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
    }

    public static class DataMap0Bean {
        /**
         * sumCost : 2347
         * sumJinE : 3640
         * saleCount : 11
         */

        private int sumCost;
        private int sumJinE;
        private int saleCount;

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
    }

    public static class MachineListBean {
        /**
         * sumJinE : 3170
         * address : 北京市北京市丰台区
         * detailedinstalladdress : 芳城园二区门口
         * machine_id : 70201802081416404953601001243158
         * machinenumber : LJ-010-04-002-001
         */

        private int sumJinE;
        private String address;
        private String detailedinstalladdress;
        private String machine_id;
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

        public String getDetailedinstalladdress() {
            return detailedinstalladdress;
        }

        public void setDetailedinstalladdress(String detailedinstalladdress) {
            this.detailedinstalladdress = detailedinstalladdress;
        }

        public String getMachine_id() {
            return machine_id;
        }

        public void setMachine_id(String machine_id) {
            this.machine_id = machine_id;
        }

        public String getMachinenumber() {
            return machinenumber;
        }

        public void setMachinenumber(String machinenumber) {
            this.machinenumber = machinenumber;
        }
    }

    public static class GoodsListBean {
        /**
         * descVal : 冰露矿泉水-550ml
         * formatID : 507
         * saleCount : 4
         */

        private String descVal;
        private int formatID;
        private int saleCount;

        public String getDescVal() {
            return descVal;
        }

        public void setDescVal(String descVal) {
            this.descVal = descVal;
        }

        public int getFormatID() {
            return formatID;
        }

        public void setFormatID(int formatID) {
            this.formatID = formatID;
        }

        public int getSaleCount() {
            return saleCount;
        }

        public void setSaleCount(int saleCount) {
            this.saleCount = saleCount;
        }
    }
}
