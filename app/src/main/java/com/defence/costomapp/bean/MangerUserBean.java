package com.defence.costomapp.bean;

/**
 * Created by author Sgq
 * on 2018/3/7.
 */

public class MangerUserBean {

    /**
     * message : 操作成功!
     * sign : 1
     * result : {"funcType":10100,"data_one":{"id":1,"login":"jiazhen","pwd":"","nickname":"管理员","timeline":"2018-03-07 17:39:50","authorizationKey":"rZl61G8n","groupID":1,"enableVal":1,"registrationid":"191e35f7e04fe2a7530","whichPhone":"Android"},"im_identifier":"IM_AM_1_1"}
     * timelineN : 2018-03-07 17:39:50
     */

    private String message;
    private int sign;
    private ResultBean result;
    private String timelineN;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
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
         * funcType : 10100
         * data_one : {"id":1,"login":"jiazhen","pwd":"","nickname":"管理员","timeline":"2018-03-07 17:39:50","authorizationKey":"rZl61G8n","groupID":1,"enableVal":1,"registrationid":"191e35f7e04fe2a7530","whichPhone":"Android"}
         * im_identifier : IM_AM_1_1
         */

        private int funcType;
        private DataOneBean data_one;
        private String im_identifier;

        public int getFuncType() {
            return funcType;
        }

        public void setFuncType(int funcType) {
            this.funcType = funcType;
        }

        public DataOneBean getData_one() {
            return data_one;
        }

        public void setData_one(DataOneBean data_one) {
            this.data_one = data_one;
        }

        public String getIm_identifier() {
            return im_identifier;
        }

        public void setIm_identifier(String im_identifier) {
            this.im_identifier = im_identifier;
        }

        public static class DataOneBean {
            /**
             * id : 1
             * login : jiazhen
             * pwd :
             * nickname : 管理员
             * timeline : 2018-03-07 17:39:50
             * authorizationKey : rZl61G8n
             * groupID : 1
             * enableVal : 1
             * registrationid : 191e35f7e04fe2a7530
             * whichPhone : Android
             */

            private int id;
            private String login;
            private String pwd;
            private String nickname;
            private String timeline;
            private String authorizationKey;
            private int groupID;
            private int enableVal;
            private String registrationid;
            private String whichPhone;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLogin() {
                return login;
            }

            public void setLogin(String login) {
                this.login = login;
            }

            public String getPwd() {
                return pwd;
            }

            public void setPwd(String pwd) {
                this.pwd = pwd;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getTimeline() {
                return timeline;
            }

            public void setTimeline(String timeline) {
                this.timeline = timeline;
            }

            public String getAuthorizationKey() {
                return authorizationKey;
            }

            public void setAuthorizationKey(String authorizationKey) {
                this.authorizationKey = authorizationKey;
            }

            public int getGroupID() {
                return groupID;
            }

            public void setGroupID(int groupID) {
                this.groupID = groupID;
            }

            public int getEnableVal() {
                return enableVal;
            }

            public void setEnableVal(int enableVal) {
                this.enableVal = enableVal;
            }

            public String getRegistrationid() {
                return registrationid;
            }

            public void setRegistrationid(String registrationid) {
                this.registrationid = registrationid;
            }

            public String getWhichPhone() {
                return whichPhone;
            }

            public void setWhichPhone(String whichPhone) {
                this.whichPhone = whichPhone;
            }
        }
    }
}
