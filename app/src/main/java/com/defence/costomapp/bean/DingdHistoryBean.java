package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/20.
 */

public class DingdHistoryBean {


    /**
     * success_count : 89
     * wait_out_store : 0
     * refund_count : 1
     * list : [{"status":4,"ppp":24138,"ccc":89,"bbb":18776},{"status":6,"ppp":1251,"ccc":1,"bbb":882}]
     * refund_money : 1251
     * refund_cb : 882
     * success_money : 24138
     */

    private int success_count;
    private int wait_out_store;
    private int refund_count;
    private int refund_money;
    private int refund_cb;
    private int success_money;
    private List<ListBean> list;

    public int getSuccess_count() {
        return success_count;
    }

    public void setSuccess_count(int success_count) {
        this.success_count = success_count;
    }

    public int getWait_out_store() {
        return wait_out_store;
    }

    public void setWait_out_store(int wait_out_store) {
        this.wait_out_store = wait_out_store;
    }

    public int getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(int refund_count) {
        this.refund_count = refund_count;
    }

    public int getRefund_money() {
        return refund_money;
    }

    public void setRefund_money(int refund_money) {
        this.refund_money = refund_money;
    }

    public int getRefund_cb() {
        return refund_cb;
    }

    public void setRefund_cb(int refund_cb) {
        this.refund_cb = refund_cb;
    }

    public int getSuccess_money() {
        return success_money;
    }

    public void setSuccess_money(int success_money) {
        this.success_money = success_money;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * status : 4
         * ppp : 24138
         * ccc : 89
         * bbb : 18776
         */

        private int status;
        private int ppp;
        private int ccc;
        private int bbb;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getPpp() {
            return ppp;
        }

        public void setPpp(int ppp) {
            this.ppp = ppp;
        }

        public int getCcc() {
            return ccc;
        }

        public void setCcc(int ccc) {
            this.ccc = ccc;
        }

        public int getBbb() {
            return bbb;
        }

        public void setBbb(int bbb) {
            this.bbb = bbb;
        }
    }
}
