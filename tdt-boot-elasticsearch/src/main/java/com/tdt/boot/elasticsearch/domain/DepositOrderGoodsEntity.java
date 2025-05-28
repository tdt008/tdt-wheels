package com.tdt.boot.elasticsearch.domain;

import com.alibaba.fastjson.JSONObject;
import com.tdt.boot.elasticsearch.protocol.DepositOrderDetailV2Protocol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description：定金单购买商品
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositOrderGoodsEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 商品skuid/套餐和卡项的id
     */
    private Long goodsSkuId;
    /**
     * 条目类型（商品类型）
     */
    private Integer goodsType;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品规格JSON格式
     */
    private List<GoodsSpecsEntity> goodsSpecs;
    /**
     * 主图
     */
    private String mainUrl;
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
     * 定金百分比
     */
    private Integer depositPercentage;
    /**
     * 赠品
     */
    private List<OrderDepositGiftEntity> giftGoodsList;

    /**
     * 商品条目（针对套餐）
     */
    private List<DepositOrderGoodsItemEntity> orderGoodsItems;

    /**
     * 二级商品类型 1：固定消耗；2：自定义消耗
     * 传统项目（固定消耗/自定义消耗）
     * 合作项目（固定消耗/自定义消耗）
     */
    private Integer secondLevelGoodsType;

    //----------V2添加字段---------------//
    /**
     * 商品spuid/套餐和卡项的id
     */
    private Long goodsSpuId;
    /**
     * 商品spucode/套餐code
     */
    private String goodsSpuCode;
    /**
     * 商品skucode/套餐code
     */
    private String goodsSkuCode;
    /**
     * 消耗类型
     */
    private Integer consumptionType;
    /**
     * 退货数量
     */
    private Integer returnQuantity;
    /**
     * 项目单次服务时长
     */
    private Integer itemDurationMinute;

    /**
     * 购买的项目资产是否可转赠
     */
    private Boolean isCanGift;


    public static DepositOrderGoodsEntity buildDepositOrderGoodsDTO(DepositOrderDetailV2Protocol.DepositOrderGoodsV2 param) {
        if (param == null) {
            return null;
        }
        List<OrderDepositGiftEntity> orderGoodsList = null;
        List<DepositOrderGoodsItemEntity> orderGoodsItemList = null;
        if (param.getGiftGoodsList() != null) {
            orderGoodsList = param.getGiftGoodsList().stream().map(OrderDepositGiftEntity::buildOrderDepositGiftDTO).collect(Collectors.toList());
        }

        List<GoodsSpecsEntity> goodsSpecsList = JSONObject.parseArray(param.getGoodsSpecs(), GoodsSpecsEntity.class);

        if (param.getOrderGoodsItems() != null) {
            orderGoodsItemList = param.getOrderGoodsItems().stream()
                    .map(DepositOrderGoodsItemEntity::buildDepositOrderGoodsItem).collect(Collectors.toList());
        }

        return DepositOrderGoodsEntity.builder()
                .id(param.getId())
                .goodsSkuId(param.getGoodsSkuId())
                .goodsType(param.getGoodsType())
                .goodsName(param.getGoodsName())
                .goodsSpecs(goodsSpecsList)
                .mainUrl(param.getMainUrl())
                .quantity(param.getQuantity())
                .price(param.getPrice())
                .totalAmount(param.getTotalAmount())
                .giftGoodsList(orderGoodsList)
                .depositPercentage(param.getDepositPercentage())
                .orderGoodsItems(orderGoodsItemList)
                .goodsSpuId(param.getGoodsSpuId())
                .goodsSpuCode(param.getGoodsSpuCode())
                .goodsSkuCode(param.getGoodsSkuCode())
                .consumptionType(param.getConsumptionType())
                .returnQuantity(param.getReturnQuantity())
                .itemDurationMinute(param.getReturnQuantity())
                .isCanGift(param.getIsCanGift() != null && param.getIsCanGift() == 1)
                .build();
    }
}
