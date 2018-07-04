package com.defence.costomapp.net;


import com.defence.costomapp.bean.DailyCostBean;
import com.defence.costomapp.bean.DataResponse;
import com.defence.costomapp.bean.LineChartBean;
import com.defence.costomapp.bean.ShouWangVipBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lw on 2018/1/23.
 */

public interface ApiService {


    /**
     * 结算
     *
     * @param adminGroupID
     * @param funcType
     * @return
     */
    @POST("/req-mobile/tong-ji/jie/suan/0data1.json")
    @FormUrlEncoded
    Observable<DataResponse<LineChartBean>> getSearchArticles(@Field("adminGroupID") String adminGroupID, @Field("funcType") String funcType);


    /**
     * 流水
     *
     * @param adminGroupID
     * @param funcType
     * @param date
     * @return
     */
    @POST("/req-mobile/tong-ji/daily/sale/cost/0data1.json")
    @FormUrlEncoded
    Observable<DataResponse<DailyCostBean.ResultBean>> getGailycost(@Field("adminGroupID") String adminGroupID, @Field("funcType") String funcType, @Field("date") String date);


    /**
     * 守望会员统计
     *
     * @param listNum
     * @param pags
     * @param kaishitime
     * @param jieshutime
     * @param iskaitong
     * @param huiyuantype
     * @param paixu
     * @param dora
     * @return
     */
    @POST("/req-mobile/huiyuan/tongji/query/app/chongzhi/huiyuan/info/0data1.json")
    @FormUrlEncoded
    Observable<DataResponse<ShouWangVipBean>> getSWVipData(@Field("funcType") String funcType, @Field("listNum") String listNum, @Field("pags") String pags, @Field("kaishitime") String kaishitime, @Field("jieshutime") String jieshutime, @Field("iskaitong") String iskaitong, @Field("huiyuantype") String huiyuantype, @Field("paixu") String paixu, @Field("dora") String dora);


}
