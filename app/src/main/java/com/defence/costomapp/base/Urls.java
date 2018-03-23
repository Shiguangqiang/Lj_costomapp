package com.defence.costomapp.base;

/**
 * Created by fangfafengfu on 2017/12/5.
 */

public class Urls {
    public static String BaseUrl = "http://swz.bj-defence.com/";
//    测试 yang
//    public static String BaseUrl = "http://192.168.0.127:8080/mp2017/";

    //  李
//    public static String BaseUrl = "http://192.168.0.234:8080/";


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


    //查看统计查询_商品
    public static String tjserach_shop() {
        return BaseUrl + "req-mobile/tong-ji/between/date/goods/sale/0data1.json";
    }

    //查看统计查询_机器
    public static String tjserach_machine() {
        return BaseUrl + "req-mobile/tong-ji/between/date/machine/sale/0data1.json";
    }

    //查看用户统计
    public static String userTj() {
        return BaseUrl + "req-mobile/user-tongji/list/user/0data1.json";
    }

    //查看微信支付
    public static String wxpay() {
        return BaseUrl + "req-mobile/user-tongji/list/weixinzhifu/0data1.json";
    }

    //查看充值记录
    public static String chongzhi() {
        return BaseUrl + "req-mobile/user-tongji/list/user/chongzhijilu/0data1.json";
    }

    //查看充值记录详情
    public static String chongzhidetail() {
        return BaseUrl + "req-mobile/user-tongji/list/user/chongzhi/info/0data1.json";
    }

    //查看消费记录
    public static String shophistory() {
        return BaseUrl + "req-mobile/user-tongji/list/user/dingdannumber/0data1.json";
    }

    //时间段统计销量
    public static String timesale() {
        return BaseUrl + "req-mobile/ding/dan/time/area/sale/0count.json";
    }

    //购买数量统计
    public static String shopnum() {
        return BaseUrl + "req-mobile/user-tongji/list/user/dingdanshangpin/0data1.json";
    }

    //机器出货量统计
    public static String macchunum() {
        return BaseUrl + "req-mobile/user-tongji/list/user/dingdanshangpin/machine/0data1.json";
    }

    //今日流水
    public static String dailycost() {
        return BaseUrl + "req-mobile/tong-ji/daily/sale/cost/0data1.json";
    }

    //结算查询
    public static String jiesuan() {
        return BaseUrl + "req-mobile/tong-ji/jie/suan/0data1.json";
    }

    //订单管理
    public static String dingdan() {
        return BaseUrl + "req-mobile/ding/dan/list10/machine/0data1.json";
    }

    //订单详情
    public static String dingdandetail() {
        return BaseUrl + "req-mobile/ding/dan/order/detail/0data.json";
    }

    //用户订单历史
    public static String dingdhistoryl() {
        return BaseUrl + "req-mobile/ding/dan/someone/order/count/0data.json";

    }

    //用户订单历史列表
    public static String dingdhistoryllist() {
        return BaseUrl + "req-mobile/ding/dan/someone/order/list10/0data.json";

    }

    //退款查询 机器
    public static String dingdtuikuan() {
        return BaseUrl + "req-mobile/user-tuikuan/list/user/tuikuan/machine/0data1.json";

    }

    //退款查询 商品
    public static String shangpintuikuan() {
        return BaseUrl + "req-mobile/user-tuikuan/list/user/tuikuan/shangpinguige/0data1.json";

    }

    //退款查询 listall
    public static String allisttuikuan() {
        return BaseUrl + "req-mobile/user-tuikuan/list/tuikuan/info/0data1.json";


    }

    //退款查询 订单机器组
    public static String listtkgroup() {
        return BaseUrl + "req-mobile/user-tuikuan/list/info/dingdangroupmachine/0data1.json";
    }

}
