package com.defence.costomapp.net;


import com.defence.costomapp.bean.DataAnGoodsFilterBean;
import com.defence.costomapp.bean.DataAnMachineFilterBean;
import com.defence.costomapp.bean.DataAnalysisFilterBean;
import com.defence.costomapp.bean.DataResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lw on 2018/1/23.
 */

public interface ApiService {


    /**
     * 【统计折线图】/// 根据类型获取查询条件 筛选时选择的 资金 用户///
     *
     * @param funcType
     * @param ctype
     * @return
     */
    @POST("/req-mobile/tong-ji/zhexiantu/query/tiaojian/list/0data.json")
    @FormUrlEncoded
    Observable<DataResponse<DataAnalysisFilterBean>> getFilterData(@Field("funcType") String funcType, @Field("ctype") String ctype);


    /**
     * 【统计折线图】/// 分页查询所有正在使用的机器 ///
     *
     * @param funcType
     * @param begin
     * @param end
     * @return
     */
    @POST("/req-mobile/tong-ji/zhexiantu/query/all/machine/list/0data.json")
    @FormUrlEncoded
    Observable<DataResponse<DataAnMachineFilterBean>> getFilterMachineData(@Field("funcType") String funcType, @Field("begin") String begin, @Field("end") String end);


    /**
     * 【统计折线图】/// 根据机器编号查询所有的商品信息 ///
     *
     * @param funcType
     * @param begin
     * @param end
     * @param machineNumber
     * @return
     */
    @POST("/req-mobile/tong-ji/zhexiantu/query/all/shangping/guige/list/0data.json")
    @FormUrlEncoded
    Observable<DataResponse<DataAnGoodsFilterBean>> getFilterGoodsData(@Field("funcType") String funcType, @Field("begin") String begin, @Field("end") String end, @Field("machineNumber") String machineNumber);


}
