package com.tdt.boot.elasticsearch.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @since 2021-08-31
 */
@Data
@Builder
public class OrderGoodsTypeEntity {
    /**
     * 商品类型
     */
    private Integer goodsType;

    /**
     * 商品skuId
     */
    private Long skuId;

    /**
     * 卡类型：1无限次卡，2通卡
     */
    private Integer cardType;

}
