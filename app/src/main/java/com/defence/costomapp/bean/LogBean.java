package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/8.
 */

public class LogBean {


    /**
     * list_size : 7
     * list : [{"shelvesdate":"2018-06-19 16:01:48","username":"yuan","unreadCount":"","userphoto":"","realname":"袁泉","shelvesuserid":17,"telphone":"15944237542"},{"shelvesdate":"2018-06-18 21:15:48","username":"fn911","unreadCount":"","userphoto":"","realname":"方楠","shelvesuserid":9,"telphone":"18610527597"},{"shelvesdate":"2018-06-17 16:17:45","username":"cjf","unreadCount":"","userphoto":"","realname":"曹井夫","shelvesuserid":1,"telphone":"18901309182"},{"shelvesdate":"2018-06-15 17:47:45","username":"lxy","unreadCount":"","userphoto":"","realname":"李新原","shelvesuserid":7,"telphone":"13801202242"},{"shelvesdate":"2018-06-15 12:37:53","username":"ljz","unreadCount":"","userphoto":"","realname":"刘佳振","shelvesuserid":3,"telphone":"13264415615"},{"shelvesdate":"2018-06-13 10:11:02","username":"mhw","unreadCount":"","userphoto":"","realname":"米橫文","shelvesuserid":5,"telphone":"15010761108"},{"shelvesdate":"2018-06-12 10:31:29","username":"wz","unreadCount":"","userphoto":"","realname":"王增","shelvesuserid":15,"telphone":"13910757065"}]
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
         * shelvesdate : 2018-06-19 16:01:48
         * username : yuan
         * unreadCount :
         * userphoto :
         * realname : 袁泉
         * shelvesuserid : 17
         * telphone : 15944237542
         */

        private String shelvesdate;
        private String username;
        private String unreadCount;
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

        public String getUnreadCount() {
            return unreadCount;
        }

        public void setUnreadCount(String unreadCount) {
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
