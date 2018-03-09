package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/8.
 */

public class LogBean {


    /**
     * list_size : 4
     * list : [{"shelvesdate":"2018-03-07 16:07:28","username":"cjf","unreadCount":438,"userphoto":"","realname":"曹井夫","shelvesuserid":1,"telphone":"18901309182"},{"shelvesdate":"2018-02-02 10:30:27","username":"ljz","unreadCount":7,"userphoto":"","realname":"刘佳振","shelvesuserid":3,"telphone":"13264415615"},{"shelvesdate":"2018-03-07 09:45:55","username":"lxy","unreadCount":209,"userphoto":"","realname":"李新原","shelvesuserid":7,"telphone":"13801202242"},{"shelvesdate":"2018-03-07 23:44:11","username":"fn911","unreadCount":44,"userphoto":"","realname":"方楠","shelvesuserid":9,"telphone":"18610527597"}]
     */

    private int list_size;
    private List<ListBean> list;

    public int getList_size() {
        return list_size;
    }

    public void setList_size(int list_size) {
        this.list_size = list_size;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * shelvesdate : 2018-03-07 16:07:28
         * username : cjf
         * unreadCount : 438
         * userphoto :
         * realname : 曹井夫
         * shelvesuserid : 1
         * telphone : 18901309182
         */

        private String shelvesdate;
        private String username;
        private int unreadCount;
        private String userphoto;
        private String realname;
        private int shelvesuserid;
        private String telphone;

        public String getShelvesdate() {
            return shelvesdate;
        }

        public void setShelvesdate(String shelvesdate) {
            this.shelvesdate = shelvesdate;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getUnreadCount() {
            return unreadCount;
        }

        public void setUnreadCount(int unreadCount) {
            this.unreadCount = unreadCount;
        }

        public String getUserphoto() {
            return userphoto;
        }

        public void setUserphoto(String userphoto) {
            this.userphoto = userphoto;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public int getShelvesuserid() {
            return shelvesuserid;
        }

        public void setShelvesuserid(int shelvesuserid) {
            this.shelvesuserid = shelvesuserid;
        }

        public String getTelphone() {
            return telphone;
        }

        public void setTelphone(String telphone) {
            this.telphone = telphone;
        }
    }
}
