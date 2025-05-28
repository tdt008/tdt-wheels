package com.tdt.boot.elasticsearch.domain;

import com.tdt.boot.elasticsearch.constant.StatisticsOrderConst;
import com.tdt.boot.elasticsearch.enums.StatisticsGoodsTypeEnum;
import com.tdt.boot.elasticsearch.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @description：
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountSearchEntity {

    /**
     * 开始时间
     */
    private String startDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 门店id
     */
    private Long storeId;

    /**
     * 分组类型 day：日期；month：月份
     */
    private String groupType;

    /**
     * 商品类型
     */
    private Integer goodsType;

    /**
     * 商品类型列表
     */
    private List<Integer> goodsTypeList;

    /**
     * 趋势图类型：1门店金额；2门店消耗金额；4服务次数；
     */
    private Integer histogramType;

    /**
     * 卡类型：1无限次卡，2通卡
     */
    private Integer cardItemType;

    /**
     * 二级商品类型 1：固定消耗；2：自定义消耗
     * 传统项目（固定消耗/自定义消耗）
     * 合作项目（固定消耗/自定义消耗）
     */
    private Integer secondLevelGoodsType;

    /**
     * 值不为空则统计储值+第三方支付金额
     */
    private String all;

    /**
     * 是否统计卡项
     */
    private String notStatisticCard;

    private String notStatisticGoodsCard;

    public static CountSearchEntity buildCountSearchDTO(CountSearchEntity param, StatisticsOrderSearchEntity search) {
        List<Integer> goodsTypeList = null;
        String startTime = param.getStartDate(), endTime = param.getEndDate();
        if (search.getGoodsType() != null && search.getGoodsType() == StatisticsOrderConst.SETTLE_ENT) {
            //添加产品类型
            goodsTypeList = new ArrayList<>(StatisticsGoodsTypeEnum.codeList());
            goodsTypeList.removeIf(it -> it.equals(StatisticsGoodsTypeEnum.COMBO.getCode()));
        }
        if (search.getStartDate() != null && search.getEndDate() != null) {
            startTime = DateUtil.format(DateUtil.strToDate(search.getStartDate()), DateUtil.YTD_DATETIME);
            endTime = DateUtil.format(DateUtil.strToDate(search.getEndDate()), DateUtil.YTD_DATETIME);
        }
        return CountSearchEntity.builder()
                .startDate(startTime)
                .endDate(endTime)
                .storeId(search.getStoreId())
                .groupType(search.getGroupType() == null ? search.getSearchTimeType() : search.getGroupType())
                .goodsType(search.getGoodsType())
                .goodsTypeList(goodsTypeList)
                .histogramType(search.getHistogramType())
                .build();
    }

    public CountSearchEntity(String startDate, String endDate, Long storeId, String groupType) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.storeId = storeId;
        this.groupType = groupType;
    }
}
