package com.tdt.boot.elasticsearch.domain;

import com.tdt.boot.elasticsearch.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @description：统计查询参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsOrderSearchEntity {

    /**
     * 店铺id
     */
    @NotNull(message = "店铺id不能为空",groups = {IPadSearchParamValidateGroup.class})
    private Long storeId;

    /**
     * 查询时间
     */
    @NotNull(message = "查询时间不能为空",groups = {IPadSearchParamValidateGroup.class})
    @Size(min = 7, max = 10, message = "请传入yyyy-MM/yyyy-MM-dd格式",groups = {IPadSearchParamValidateGroup.class})
    private String searchTime;

    /**
     * 查询时间类型
     */
    @NotNull(message = "查询时间类型不能为空",groups = {IPadSearchParamValidateGroup.class,SaaSSearchParamValidateGroup.class})
    private String searchTimeType;

    /**
     * 查询开始日期
     */
    @NotNull(message = "查询开始日期不能为空",groups = {SaaSSearchParamValidateGroup.class})
    @Size(min = 10, max = 10, message = "请传入yyyy-MM-dd格式",groups = {SaaSSearchParamValidateGroup.class})
    private String startDate;

    /**
     * 查询结束日期
     */
    @NotNull(message = "查询结束日期不能为空",groups = {SaaSSearchParamValidateGroup.class})
    @Size(min = 10, max = 10, message = "请传入yyyy-MM-dd格式",groups = {SaaSSearchParamValidateGroup.class})
    private String endDate;

    /**
     * 统计类型 产品0；传统项目1；合作项目2
     */
    private Integer goodsType;

    /**
     * 分组类型 日期分组：day;月分组：month;支付：payType；商品类型；goodsType;商品名称：goodsName
     */
    private String groupType;

    /**
     * 柱状图类型：1门店金额；2门店消耗金额；4服务次数;5进店人数；
     */
    private Integer histogramType;

    /**
     * 统计进店类型：1有消费客户；2做服务客户；12有消费客户+做服务客户
     */
    private String statisticsIntoStoreType;

    /**
     * 是否交易排行
     */
    private Boolean isTradingRank;

    /**
     * 1：进店人数列表；2：进店人次列表
     */
    private Integer goStoreType;

    private Integer current;

    private Integer size;

    public String getSearchTime() {
        if (searchTime == null){
            return DateUtil.format(new Date(), DateUtil.PATTERN_DATE);
        }
        int dateLength = 7;
        if (searchTime.length() == dateLength) {
            return searchTime.concat("-01");
        }
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        int dateLength = 7;
        if (searchTime.length() == dateLength) {
            searchTime = searchTime.concat("-01");
        }
        this.searchTime = searchTime;
    }

    /**
     * 趋势图入参限定
     *
     * @param dto 入参
     * @return
     */
    public static StatisticsOrderSearchEntity buildDateHistogramStatistics(StatisticsOrderSearchEntity dto) {
        return StatisticsOrderSearchEntity.builder()
                .storeId(dto.getStoreId())
                .searchTime(dto.getSearchTime())
                .searchTimeType(dto.getSearchTimeType())
                .goodsType(dto.getGoodsType())
                .histogramType(dto.getHistogramType())
                .build();
    }

    public interface IPadSearchParamValidateGroup {}
    public interface SaaSSearchParamValidateGroup {}
}
