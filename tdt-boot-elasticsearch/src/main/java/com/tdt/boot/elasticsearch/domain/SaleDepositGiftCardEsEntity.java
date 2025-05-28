package com.tdt.boot.elasticsearch.domain;

import com.alibaba.fastjson.JSONObject;
import com.tdt.boot.elasticsearch.protocol.SaleOrderDetailV2Protocol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @since 2021-12-17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleDepositGiftCardEsEntity {
    /**
     * id
     */
    private Long id;
    /**
     *  order_sale_deposit_gift的主键
     */
    private Long orderGoodsItemId;
    /**
     *  销售单销售条目id/定金单id
     */
    private Long relationId;
    /**
     *  赠品spu id
     */
    private Long goodsSpuId;
    /**
     *  赠品sku id
     */
    private Long goodsSkuId;
    /**
     * 赠品spu code
     */
    private String goodsSpuCode;
    /**
     * 赠品sku code
     */
    private String goodsSkuCode;
    /**
     * 赠品类型
     */
    private Integer goodsType;
    /**
     * 消耗类型
     */
    private Integer consumptionType;

    /**
     *  赠品名称
     */
    private String goodsName;
    /**
     *  赠品规格
     */
    private List<GoodsSpecsEntity> goodsSpecs;
    /**
     *  赠品商品图片
     */
    private String mainUrl;
    /**
     *  赠品数量
     */
    private Integer quantity;
    /**
     * 赠品单价
     */
    private BigDecimal price;

    public static SaleDepositGiftCardEsEntity buildSaleDepositGiftCardEs(SaleOrderDetailV2Protocol.SaleDepositGiftCardDTO param) {
        List<GoodsSpecsEntity> goodsSpecsList = JSONObject.parseArray(param.getGoodsSpecs(), GoodsSpecsEntity.class);
        return SaleDepositGiftCardEsEntity.builder()
                .id(param.getId())
                .orderGoodsItemId(param.getOrderGoodsItemId())
                .relationId(param.getRelationId())
                .goodsSpuId(param.getGoodsSpuId())
                .goodsSkuId(param.getGoodsSkuId())
                .goodsSpuCode(param.getGoodsSpuCode())
                .goodsSkuCode(param.getGoodsSkuCode())
                .goodsType(param.getGoodsType())
                .consumptionType(param.getConsumptionType())
                .goodsName(param.getGoodsName())
                .goodsSpecs(goodsSpecsList)
                .mainUrl(param.getMainUrl())
                .quantity(param.getQuantity())
                .price(param.getPrice())
                .build();
    }
}
