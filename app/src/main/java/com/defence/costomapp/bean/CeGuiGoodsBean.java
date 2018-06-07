package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by Sgq
 * Create Date 2018/6/6 and 13:52
 * Used
 */
public class CeGuiGoodsBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * shangpin_name : 维达手帕纸-（0元购）
         * ku_cun : 6
         * guige_id : 541
         * machine_no : lj-010-04-007-001
         */

        private String shangpin_name;
        private int ku_cun;
        private int guige_id;
        private String machine_no;

        public String getShangpin_name() {
            return shangpin_name;
        }

        public void setShangpin_name(String shangpin_name) {
            this.shangpin_name = shangpin_name;
        }

        public int getKu_cun() {
            return ku_cun;
        }

        public void setKu_cun(int ku_cun) {
            this.ku_cun = ku_cun;
        }

        public int getGuige_id() {
            return guige_id;
        }

        public void setGuige_id(int guige_id) {
            this.guige_id = guige_id;
        }

        public String getMachine_no() {
            return machine_no;
        }

        public void setMachine_no(String machine_no) {
            this.machine_no = machine_no;
        }
    }
}
