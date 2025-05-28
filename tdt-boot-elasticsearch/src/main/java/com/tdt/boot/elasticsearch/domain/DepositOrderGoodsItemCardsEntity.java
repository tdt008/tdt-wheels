package com.tdt.boot.elasticsearch.domain;

import com.alibaba.fastjson.JSONObject;
import com.tdt.boot.elasticsearch.protocol.DepositOrderDetailV2Protocol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description 套餐卡项的明细
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositOrderGoodsItemCardsEntity implements Serializable {
    private Long id;

    /**
     * 定金单商品idorder_sale_order_goods_item的主键)
     */
    private Long orderGoodsItemId;

    private Long goodsSpuId;

    /**
     * 商品skuid/卡项的id
     */
    private Long goodsSkuId;

    /**
     * 销售单条目类型（商品类型）
     */
    private Integer goodsType;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品规格JOSN格式
     */
    private List<GoodsSpecsEntity> goodsSpecs;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 该条目总价
     */
    private BigDecimal totalAmount;

    /**
     * 均摊储值支付金额
     */
    private BigDecimal storeValueShareAmount;

    /**
     * 均摊最终支付金额
     */
    private BigDecimal finalPayAmount;

    //-----------V2新增字段-----------//
    /**
     * 商品spucode
     */
    private String goodsSpuCode;
    /**
     * 商品skucode
     */
    private String goodsSkuCode;
    /**
     * 项目单次服务时长
     */
    private Integer itemDurationMinute;
    /**
     * 消耗类型
     */
    private Integer consumptionType;

    /**
     * 主图
     */
    private String mainUrl;

    public static DepositOrderGoodsItemCardsEntity buildDepositOrderGoodsItemCards(DepositOrderDetailV2Protocol.DepositOrderGoodsItemCardV2 param) {
        return DepositOrderGoodsItemCardsEntity.builder()
                .id(param.getId())
                .orderGoodsItemId(param.getOrderGoodsItemId())
                .goodsSpuId(param.getGoodsSpuId())
                .goodsSkuId(param.getGoodsSkuId())
                .goodsType(param.getGoodsType())
                .goodsName(param.getGoodsName())
                .goodsSpecs(JSONObject.parseArray(param.getGoodsSpecs(), GoodsSpecsEntity.class))
                .quantity(param.getQuantity())
                .price(param.getPrice())
                .totalAmount(param.getTotalAmount())
                .storeValueShareAmount(param.getStoreValueShareAmount())
                .finalPayAmount(param.getFinalPayAmount())
                //-----------V2新增字段-----------//
                .goodsSpuCode(param.getGoodsSpuCode())
                .goodsSkuCode(param.getGoodsSkuCode())
                .itemDurationMinute(param.getItemDurationMinute())
                .consumptionType(param.getConsumptionType())
                .mainUrl(param.getMainUrl())
                .build();
    }

}