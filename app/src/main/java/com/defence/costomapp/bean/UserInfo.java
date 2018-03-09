package com.defence.costomapp.bean;

import java.io.Serializable;

/**
 * Created by Sgq
 * on 2018/3/5.
 */

public class UserInfo implements Serializable {

    private int id;
    private String username;
    private String password;
    private String realname;
    private String timeline;
    private String authorizationKey;
    private String registrationid;
    private String whichPhone;
    private String idcard;
    private String telphone;
    private String homeaddress;
    private String presentaddress;
    private String bathday;
    private int onthejob;
    private String userphoto;
    private int iscontroller;
    private String machineList;
    private int funcType;

    public int getFuncType() {
        return funcType;
    }

    public void setFuncType(int funcType) {
        this.funcType = funcType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getRealname() {
        return realname;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setAuthorizationKey(String authorizationKey) {
        this.authorizationKey = authorizationKey;
    }

    public String getAuthorizationKey() {
        return authorizationKey;
    }

    public void setRegistrationid(String registrationid) {
        this.registrationid = registrationid;
    }

    public String getRegistrationid() {
        return registrationid;
    }

    public void setWhichPhone(String whichPhone) {
        this.whichPhone = whichPhone;
    }

    public String getWhichPhone() {
        return whichPhone;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }

    public String getHomeaddress() {
        return homeaddress;
    }

    public void setPresentaddress(String presentaddress) {
        this.presentaddress = presentaddress;
    }

    public String getPresentaddress() {
        return presentaddress;
    }

    public void setBathday(String bathday) {
        this.bathday = bathday;
    }

    public String getBathday() {
        return bathday;
    }

    public void setOnthejob(int onthejob) {
        this.onthejob = onthejob;
    }

    public int getOnthejob() {
        return onthejob;
    }

    public void setUserphoto(String userphoto) {
        this.userphoto = userphoto;
    }

    public String getUserphoto() {
        return userphoto;
    }

    public void setIscontroller(int iscontroller) {
        this.iscontroller = iscontroller;
    }

    public int getIscontroller() {
        return iscontroller;
    }

    public void setMachineList(String machineList) {
        this.machineList = machineList;
    }

    public String getMachineList() {
        return machineList;
    }


}
