package com.defence.costomapp.base;

/**
 * Created by fangfafengfu on 2017/12/5.
 */

public class Urls {
    public static String BaseUrl = "http://swz.bj-defence.com/";

    public static String BuhuoLogin() {
        return BaseUrl + "req-mobile/login/witttth/account0";
    }

    public static String getBuhuoMessage() {
        return BaseUrl + "req-mobile/alarm/list100/0data1.json";
    }

    public static String getBuhuoMessageInfo() {
        return BaseUrl + "req-mobile/alarm/list/machine/goods/0data1.json";
    }

    public static String setRegistrationid() {
        return BaseUrl + "req-mobile/app/push/replace3/registrationid";
    }

    public static String setBuhuoMessageRead() {
        return BaseUrl + "req-mobile/deal-alarm/repair/machine/que/0huo1.json";
    }

    public static String setMachineLocation() {
        return BaseUrl + "req-mobile/machine/mob/Update/machineInfoByMachineNumber.json";
    }

    public static String checkNewVersion() {
        return BaseUrl + "phone/version/check4.json";
    }

    //查看工作日志
    public static String worklog() {
        return BaseUrl + "req-mobile/guan-li/work/log100/0data1.json";
    }

    //查看工作日志详情
    public static String workloginfo() {
        return BaseUrl + "req-mobile/guan-li/work/log/daily/0data1.json";
    }

    //查看工作日志日期
    public static String worklogdate() {
        return BaseUrl + "req-mobile/machine/mob/s_q/machineshelvesByuserId.json";
    }

    //查看机器报警
    public static String alarmPolice() {
        return BaseUrl + "req-mobile/alarm/list100/0data1.json";
    }

    //查看机器未解决报警
    public static String alarmNot() {
        return BaseUrl + "req-mobile/alarm/list/machine/alarm0/detail100/0data1.json";
    }

    //查看机器已解决报警
    public static String alarmYet() {
        return BaseUrl + "req-mobile/alarm/list/machine/alarm1/detail100/0data1.json";
    }

    //查看统计查询
    public static String tjserach() {
        return BaseUrl + "req-mobile/tong-ji/between/date/0data1.json";
    }

}
