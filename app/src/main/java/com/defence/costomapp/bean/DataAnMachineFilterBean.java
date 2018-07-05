package com.defence.costomapp.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sgq
 * Create Date 2018/7/5 and 15:39
 * Used  数据分析  机器筛选
 */
public class DataAnMachineFilterBean {


    private List<MachineListBean> machineList;

    public List<MachineListBean> getMachineList() {
        if (machineList == null) {
            return new ArrayList<>();
        }
        return machineList;
    }

    public void setMachineList(List<MachineListBean> machineList) {
        this.machineList = machineList;
    }

    public static class MachineListBean {
        /**
         * suoshuxiaoqu : 富卓苑小区
         * machinename : 丰台富卓苑1号机器
         * address : 北京市北京市丰台区
         * detailedinstalladdress : 富卓苑小区1号西娱乐广场
         * machinenumber : LJ-010-04-001-001
         */

        private String suoshuxiaoqu;
        private String machinename;
        private String address;
        private String detailedinstalladdress;
        private String machinenumber;

        public String getSuoshuxiaoqu() {
            return suoshuxiaoqu == null ? "" : suoshuxiaoqu;
        }

        public void setSuoshuxiaoqu(String suoshuxiaoqu) {
            this.suoshuxiaoqu = suoshuxiaoqu;
        }

        public String getMachinename() {
            return machinename == null ? "" : machinename;
        }

        public void setMachinename(String machinename) {
            this.machinename = machinename;
        }

        public String getAddress() {
            return address == null ? "" : address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDetailedinstalladdress() {
            return detailedinstalladdress == null ? "" : detailedinstalladdress;
        }

        public void setDetailedinstalladdress(String detailedinstalladdress) {
            this.detailedinstalladdress = detailedinstalladdress;
        }

        public String getMachinenumber() {
            return machinenumber == null ? "" : machinenumber;
        }

        public void setMachinenumber(String machinenumber) {
            this.machinenumber = machinenumber;
        }
    }
}
