package com.defence.costomapp.base;

import com.defence.costomapp.utils.SharePerenceUtil;


/**
 * Created by fangfafengfu on 2017/12/5.
 */

public class Urls {

//    public static String BaseUrl = "http://swz.landgy.com/";
    //    测试 yang
//    public static String BaseUrl = "http://192.168.0.127:8080/mp2017/";


//    public static String BaseUrl = "http://swz-test.landgy.com/";

    //  李
    public static String BaseUrl = "http://192.168.0.234:8080/";
//


    public static String BuhuoLogin() {
        return BaseUrl + "req-mobile/login/witttth/account0";
    }

    public static String getBuhuoMessage() {
        return BaseUrl + "req-mobile/alarm/list100/0data1.json";
    }

    /*补货通知详情*/
    public static String getBuhuoMessageInfo() {
        return BaseUrl + "req-mobile/alarm/list/machine/goods/0data1.json";
    }

    //极光注册id
    public static String setRegistrationid() {
        int loginType = SharePerenceUtil.getIntValueFromSP("loginType");
        if (0 == loginType) {
            return BaseUrl + "req-mobile/app/push/replace3/registrationid";
        }
        return BaseUrl + "req-mobile/app/push/replace2/registrationid";
    }

    public static String setBuhuoMessageRead() {
        return BaseUrl + "req-mobile/deal-alarm/repair/machine/que/0huo1.json";
    }

    public static String setMachineLocation() {
        return BaseUrl + "req-mobile/machine/mob/Update/machineInfoByMachineNumber.json";
    }

    //扫码开门
    public static String kaimen() {
        return BaseUrl + "/req-mobile/machine/kaimen/log/add/machine/kaimen/0data1.json";
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


    //退款退款
    public static String dingdantuikuan() {
        return BaseUrl + "req-mobile/ding/dan/dev/qr/refund/order/0item";
    }

    //最后一次成交时间，物品及价格表req-mobile/tong-ji/machine/last/0data.json
    public static String last() {
        return BaseUrl + "req-mobile/tong-ji/machine/last/0data.json";
    }


    //查询消费卡的总数、库存、出库未售、已售的数量
    public static String xiaofeika() {
        return BaseUrl + "req-mobile/huiyuan/tongji/query/list/xiaofeika/0data1.json";
    }

    //守望会员
    public static String user_huiyuan() {
        return BaseUrl + "req-mobile/huiyuan/tongji/query/user/huiyuan/0data1.json";
    }

    //储值卡  会员非会员
    public static String iaofeika_huiyuan() {
        return BaseUrl + "req-mobile/huiyuan/tongji/query/xiaofeika/huiyuan/0data1.json";
    }


    //储值卡 销售人员
    public static String xiaoshou_xiaofeika() {
        return BaseUrl + "req-mobile/huiyuan/tongji/query/xiaoshou/xiaofeika/0data1.json";
    }

    //储值卡 储值情况
    public static String xiaofeika_xiangxi() {
        return BaseUrl + "req-mobile/huiyuan/tongji/query/xiaofeika/xiangxi/info/0data1.json";
    }


    //守望会员筛选
    public static String shouwang_vip() {
        return BaseUrl + "req-mobile/huiyuan/tongji/query/app/chongzhi/huiyuan/info/0data1.json";
    }

    //消费卡会员筛选
    public static String card_vip() {
        return BaseUrl + "req-mobile/huiyuan/tongji/query/xiaofeika/chongzhi/huiyuan/info/0data1.json";
    }


}
