package com.tdt.boot.elasticsearch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @description：套餐里面商品条目信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountPackageRepaymentItemEntity {
    /**
     * 订单条目id
     */
    private Long orderGoodItemId;
    /**
     * 本次还款总金额
     */
    private BigDecimal payAmount;

    /**
     * 商品类型
     */
    private Integer goodsType;

    /**
     * 实际支付金额(本次还款总金额-(实际支付金额/本次还款总金额))
     */
    private BigDecimal actualPayAmount;

    /**
     * 均摊的储值支付金额
     */
    private BigDecimal storedAmount;

    /**
     * SkuId
     */
    private Long goodsSkuId;

    /**
     * 日分组
     */
    private Integer groupByDay;

    /**
     * 月分组
     */
    private Integer groupByMonth;

    /**
     * 卡项类型
     */
    private Integer cardItemType;

    /**
     * 二级商品类型 1：固定消耗；2：自定义消耗
     * 传统项目（固定消耗/自定义消耗）
     * 合作项目（固定消耗/自定义消耗）
     */
    private Integer secondLevelGoodsType;

    /**
     * 付款门店
     */
    private Long payStoreId;


}
