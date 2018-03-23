package com.defence.costomapp.bean;

import java.util.List;

/**
 * Created by author Sgq
 * on 2018/3/13.
 */

public class UserTjBean {


    /**
     * pingtaiNum : 318
     * userList : [{"id":263,"mphone":"13488686332","password":"MTM0ODYzMzI=","timeline":"2018-03-13 15:40:19","authorizationKey":"ToYAS3eb","registrationid":"161a3797c80e2e59ebf","name":"小凯","age":0,"birth":"","height":160,"weight":60,"idcard":"","gender":8,"portrait":"image/50201802081132585490494901116133","bankNo":6010,"hasBand":0,"language":"zh","country":"HANS","whichPhone":"iPhone","address":"Dunn","addr1":12,"addr2":1201,"addr3":120101,"addr4":0,"addrVal":"天津市-天津市-和平区","reg_from":0,"reg_time":""},{"id":257,"mphone":"13264415615","password":"MTMyNjU2MTU=","timeline":"2018-03-13 15:07:24","authorizationKey":"9V9qk76T","registrationid":"1507bfd3f7f1a9257d4","name":"佳振","age":0,"birth":"","height":160,"weight":60,"idcard":"","gender":3,"portrait":"image/50201802131715531732058201163350.jpg","bankNo":3742,"hasBand":0,"language":"zh","country":"CN","whichPhone":"Android","address":"","addr1":11,"addr2":1101,"addr3":110112,"addr4":0,"addrVal":"北京市北京市通州区","reg_from":0,"reg_time":""},{"id":259,"mphone":"13683293908","password":"MTM2ODM5MDg=","timeline":"2018-03-13 14:47:10","authorizationKey":"d2B57B57","registrationid":"120c83f7601999a149c","name":"3908","age":0,"birth":"","height":160,"weight":60,"idcard":"","gender":8,"portrait":"/no-header.jpg","bankNo":5000,"hasBand":0,"language":"zh","country":"CN","whichPhone":"Android","address":"","addr1":0,"addr2":0,"addr3":0,"addr4":0,"addrVal":"","reg_from":0,"reg_time":""},{"id":275,"mphone":"13801202242","password":"MTM4MDIyNDI=","timeline":"2018-03-13 14:20:56","authorizationKey":"GxfpYg0M","registrationid":"13065ffa4e312a80656","name":"2242","age":0,"birth":"","height":160,"weight":60,"idcard":"","gender":8,"portrait":"/no-header.jpg","bankNo":4136,"hasBand":0,"language":"zh","country":"CN","whichPhone":"Android","address":"","addr1":0,"addr2":0,"addr3":0,"addr4":0,"addrVal":"","reg_from":0,"reg_time":""},{"id":261,"mphone":"13651077452","password":"MTM2NTc0NTI=","timeline":"2018-03-13 13:20:04","authorizationKey":"Onuix5J9","registrationid":"13065ffa4e32f0e9d16","name":"李万山","age":0,"birth":"","height":160,"weight":60,"idcard":"","gender":3,"portrait":"/no-header.jpg","bankNo":5000,"hasBand":0,"language":"zh","country":"CN","whichPhone":"Android","address":"","addr1":11,"addr2":1101,"addr3":110106,"addr4":0,"addrVal":"北京市北京市丰台区","reg_from":0,"reg_time":""},{"id":265,"mphone":"13373068170","password":"MTMzNzgxNzA=","timeline":"2018-03-13 13:19:20","authorizationKey":"pwi6MKK4","registrationid":"161a3797c80fb685391","name":"秀荣","age":0,"birth":"","height":160,"weight":60,"idcard":"","gender":8,"portrait":"image/50201802091822287421308001282838.JPG","bankNo":3944,"hasBand":0,"language":"zh","country":"HANS","whichPhone":"iPhone","address":"","addr1":0,"addr2":0,"addr3":0,"addr4":0,"addrVal":"","reg_from":0,"reg_time":""},{"id":251,"mphone":"15944237542","password":"MTU5NDc1NDI=","timeline":"2018-03-13 13:18:56","authorizationKey":"wJclebQb","registrationid":"161a3797c80e1156907","name":"袁","age":0,"birth":"","height":160,"weight":60,"idcard":"","gender":3,"portrait":"image/50201801311206376358925201179857","bankNo":800,"hasBand":0,"language":"zh","country":"HANS","whichPhone":"iPhone","address":"哈哈","addr1":11,"addr2":1101,"addr3":110101,"addr4":0,"addrVal":"北京市北京市东城区","reg_from":0,"reg_time":""},{"id":309,"mphone":"13611289150","password":"MTM2MTkxNTA=","timeline":"2018-03-13 13:10:04","authorizationKey":"tBOU6n5u","registrationid":"18171adc03072a715d9","name":"9150","age":0,"birth":"","height":160,"weight":60,"idcard":"","gender":8,"portrait":"/no-header.jpg","bankNo":75,"hasBand":0,"language":"zh","country":"HANS","whichPhone":"iPhone","address":"","addr1":0,"addr2":0,"addr3":0,"addr4":0,"addrVal":"","reg_from":0,"reg_time":""},{"id":273,"mphone":"18610527597","password":"MTg2MTc1OTc=","timeline":"2018-03-13 11:46:55","authorizationKey":"1OoEE5cs","registrationid":"101d8559094d6d44651","name":"FN911","age":0,"birth":"","height":160,"weight":60,"idcard":"","gender":3,"portrait":"image/50201802091823078073898501250120.JPG","bankNo":2314,"hasBand":0,"language":"zh","country":"HANS","whichPhone":"iPhone","address":"","addr1":11,"addr2":1101,"addr3":110106,"addr4":0,"addrVal":"北京市-北京市-丰台区","reg_from":0,"reg_time":""},{"id":305,"mphone":"15614903762","password":"MTU2MTM3NjI=","timeline":"2018-03-12 21:48:13","authorizationKey":"KgaCsybT","registrationid":"1104a897928bf6f87c3","name":"3762","age":0,"birth":"","height":160,"weight":60,"idcard":"","gender":8,"portrait":"/no-header.jpg","bankNo":662,"hasBand":0,"language":"zh","country":"CN","whichPhone":"Android","address":"","addr1":0,"addr2":0,"addr3":0,"addr4":0,"addrVal":"","reg_from":0,"reg_time":""}]
     * weixinNum : 976
     * reg_user : 180
     */

    private int reg_user;
    private int bankNo;
    private int pingtaiNum;
    private int weixinNum;
    private int chongzhinum;
    private int regnum;


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public int getChongzhinum() {
        return chongzhinum;
    }

    public void setChongzhinum(int chongzhinum) {
        this.chongzhinum = chongzhinum;
    }

    public int getRegnum() {
        return regnum;
    }

    public void setRegnum(int regnum) {
        this.regnum = regnum;
    }



    public int getBankNo() {
        return bankNo;
    }

    public void setBankNo(int bankNo) {
        this.bankNo = bankNo;
    }



    public int getPingtaiNum() {
        return pingtaiNum;
    }

    public void setPingtaiNum(int pingtaiNum) {
        this.pingtaiNum = pingtaiNum;
    }

    public int getWeixinNum() {
        return weixinNum;
    }

    public void setWeixinNum(int weixinNum) {
        this.weixinNum = weixinNum;
    }

    public int getReg_user() {
        return reg_user;
    }

    public void setReg_user(int reg_user) {
        this.reg_user = reg_user;
    }





    public static class ListBean {
        /**
         * wx : W9HYbr7eIY
         * wxOpenID : oAyIL1Zta7UH2qFMHJW9HYbr7eIY
         * pv : 18600
         * orderUID : 0
         */

        private String wx;
        private String wxOpenID;
        private int pv;
        private int orderUID;
        private int userID;
        private int hm;
        private int id;
        private String mphone;
        private String password;
        private String timeline;
        private String authorizationKey;
        private String registrationid;
        private String name;
        private int age;
        private String birth;
        private int height;
        private int weight;
        private String idcard;
        private int gender;
        private String portrait;
        private int bankNo;
        private int hasBand;
        private String language;
        private String country;
        private String whichPhone;
        private String address;
        private int addr1;
        private int addr2;
        private int addr3;
        private int addr4;
        private String addrVal;
        private int reg_from;
        private String reg_time;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAuthorizationKey() {
            return authorizationKey;
        }

        public void setAuthorizationKey(String authorizationKey) {
            this.authorizationKey = authorizationKey;
        }

        public String getRegistrationid() {
            return registrationid;
        }

        public void setRegistrationid(String registrationid) {
            this.registrationid = registrationid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public int getHasBand() {
            return hasBand;
        }

        public void setHasBand(int hasBand) {
            this.hasBand = hasBand;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getWhichPhone() {
            return whichPhone;
        }

        public void setWhichPhone(String whichPhone) {
            this.whichPhone = whichPhone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAddr1() {
            return addr1;
        }

        public void setAddr1(int addr1) {
            this.addr1 = addr1;
        }

        public int getAddr2() {
            return addr2;
        }

        public void setAddr2(int addr2) {
            this.addr2 = addr2;
        }

        public int getAddr3() {
            return addr3;
        }

        public void setAddr3(int addr3) {
            this.addr3 = addr3;
        }

        public int getAddr4() {
            return addr4;
        }

        public void setAddr4(int addr4) {
            this.addr4 = addr4;
        }

        public String getAddrVal() {
            return addrVal;
        }

        public void setAddrVal(String addrVal) {
            this.addrVal = addrVal;
        }

        public int getReg_from() {
            return reg_from;
        }

        public void setReg_from(int reg_from) {
            this.reg_from = reg_from;
        }

        public String getReg_time() {
            return reg_time;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public int getBankNo() {
            return bankNo;
        }

        public void setBankNo(int bankNo) {
            this.bankNo = bankNo;
        }

        public String getTimeline() {
            return timeline;
        }

        public void setTimeline(String timeline) {
            this.timeline = timeline;
        }

        public String getMphone() {
            return mphone;
        }

        public void setMphone(String mphone) {
            this.mphone = mphone;
        }

        public int getHm() {
            return hm;
        }

        public void setHm(int hm) {
            this.hm = hm;
        }

        public int getUserID() {

            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getWx() {
            return wx;
        }

        public void setWx(String wx) {
            this.wx = wx;
        }

        public String getWxOpenID() {
            return wxOpenID;
        }

        public void setWxOpenID(String wxOpenID) {
            this.wxOpenID = wxOpenID;
        }

        public int getPv() {
            return pv;
        }

        public void setPv(int pv) {
            this.pv = pv;
        }

        public int getOrderUID() {
            return orderUID;
        }

        public void setOrderUID(int orderUID) {
            this.orderUID = orderUID;
        }
    }
}
