package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/5/22.
 */

public class ChiZhiListBean {


    /**
     * xfkList : [{"id":3,"chongzhikahao":"20180509155112759471","yuemoney":11433,"xiaofeikahao":"0064752279","ct":1},{"id":15,"chongzhikahao":"20180514135928256276","yuemoney":4370,"xiaofeikahao":"0064232183","ct":""},{"id":9,"chongzhikahao":"20180514135927467645","yuemoney":3920,"xiaofeikahao":"0064279879","ct":10},{"id":10,"chongzhikahao":"20180514135927731140","yuemoney":2485,"xiaofeikahao":"0064620103","ct":6},{"id":24,"chongzhikahao":"20180514135929171083","yuemoney":0,"xiaofeikahao":"","ct":""},{"id":8,"chongzhikahao":"20180514135927735847","yuemoney":0,"xiaofeikahao":"","ct":""},{"id":6,"chongzhikahao":"20180514135927819818","yuemoney":0,"xiaofeikahao":"0064525127","ct":""},{"id":11,"chongzhikahao":"20180514135927583281","yuemoney":0,"xiaofeikahao":"","ct":""},{"id":12,"chongzhikahao":"20180514135928714056","yuemoney":0,"xiaofeikahao":"","ct":""},{"id":13,"chongzhikahao":"20180514135928820658","yuemoney":0,"xiaofeikahao":"","ct":""}]
     * yuezongshu : 22208
     */

    private int yuezongshu;
    private List<XfkListBean> xfkList;

    public int getYuezongshu() {
        return yuezongshu;
    }

    public void setYuezongshu(int yuezongshu) {
        this.yuezongshu = yuezongshu;
    }

    public List<XfkListBean> getXfkList() {
        return xfkList;
    }

    public void setXfkList(List<XfkListBean> xfkList) {
        this.xfkList = xfkList;
    }

    public static class XfkListBean {
        /**
         * id : 3
         * chongzhikahao : 20180509155112759471
         * yuemoney : 11433
         * xiaofeikahao : 0064752279
         * ct : 1
         */

        private int id;
        private String chongzhikahao;
        private int yuemoney;
        private String xiaofeikahao;
        private String ct;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getChongzhikahao() {
            return chongzhikahao;
        }

        public void setChongzhikahao(String chongzhikahao) {
            this.chongzhikahao = chongzhikahao;
        }

        public int getYuemoney() {
            return yuemoney;
        }

        public void setYuemoney(int yuemoney) {
            this.yuemoney = yuemoney;
        }

        public String getXiaofeikahao() {
            return xiaofeikahao;
        }

        public void setXiaofeikahao(String xiaofeikahao) {
            this.xiaofeikahao = xiaofeikahao;
        }

        public String getCt() {
            return ct;
        }

        public void setCt(String ct) {
            this.ct = ct;
        }
    }
}
