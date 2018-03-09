package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/8.
 */

public class MachineBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 70201802011215355280629001227157
         * machinename : 公司内部测试1号机器
         * lastReportTime : 2018-02-22 16:02:52
         * notRepairCount : 1352
         * machinenumber : test-001
         */

        private String id;
        private String machinename;
        private String lastReportTime;
        private int notRepairCount;
        private String machinenumber;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMachinename() {
            return machinename;
        }

        public void setMachinename(String machinename) {
            this.machinename = machinename;
        }

        public String getLastReportTime() {
            return lastReportTime;
        }

        public void setLastReportTime(String lastReportTime) {
            this.lastReportTime = lastReportTime;
        }

        public int getNotRepairCount() {
            return notRepairCount;
        }

        public void setNotRepairCount(int notRepairCount) {
            this.notRepairCount = notRepairCount;
        }

        public String getMachinenumber() {
            return machinenumber;
        }

        public void setMachinenumber(String machinenumber) {
            this.machinenumber = machinenumber;
        }
    }
}
