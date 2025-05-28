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
 * @description：商品条目（针对套餐,套餐类的商品列表）
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositOrderGoodsItemEntity {
    /**
     * id
     */
    private Long id;

    /**
     * 定金单商品id
     */
    private Long goodsSpuId;
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
     * 定金单需要的字段
     * 该条目已还款总额
     */
    private BigDecimal totalPayAmount;

    /**
     * 销售单的需要的字段
     * 均摊储值支付金额
     */
    private BigDecimal storeValueShareAmount;
    /**
     * 销售单的需要的字段
     * 均摊最终支付金额
     */
    private BigDecimal finalPayAmount;

    /**
     * 套餐里的卡项
     */
    private List<DepositOrderGoodsItemCardsEntity> depositOrderGoodsItemCards;

    /**
     * 用于日期分组
     */
    private Integer groupByDay;

    /**
     * 用于月分组
     */
    private Integer groupByMonth;

    /**
     * 可选择x个项目划扣(卡项才有)
     */
    private Integer cardsOptional;

    /**
     * 有效期类型：1永久有效；2截止；3购买后X天内有效； 4首次使用后X天内有效（卡项）
     */
    private Integer validityType;

    /**
     * 卡项有效期值
     */
    private String validityValues;

    /**
     * 卡项类型 月卡；无限卡；
     */
    private Integer cardItemType;

    /**
     * 二级商品类型 1：固定消耗；2：自定义消耗
     * 传统项目（固定消耗/自定义消耗）
     * 合作项目（固定消耗/自定义消耗）
     */
    private Integer secondLevelGoodsType;

    //----------V2新增字段----------//
    /**
     * 商品spucode/卡项code
     */
    private String goodsSpuCode;
    /**
     * 商品skucode/卡项code
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
    /**
     * 购买的项目资产是否可转赠
     */
    private Boolean isCanGift;

    public static DepositOrderGoodsItemEntity buildDepositOrderGoodsItem(DepositOrderDetailV2Protocol.DepositOrderGoodsItemV2 param) {
        List<DepositOrderGoodsItemCardsEntity> depositOrderGoodsItemCards = null;
        if (param.getDepositOrderGoodsItemCardList() != null && param.getDepositOrderGoodsItemCardList().size() > 0) {
            depositOrderGoodsItemCards = param.getDepositOrderGoodsItemCardList().stream().map(DepositOrderGoodsItemCardsEntity::buildDepositOrderGoodsItemCards).collect(Collectors.toList());
        }
        return DepositOrderGoodsItemEntity.builder()
                .id(param.getId())
                .goodsSpuId(param.getGoodsSpuId())
                .goodsSkuId(param.getGoodsSkuId())
                .goodsType(param.getGoodsType())
                .goodsName(param.getGoodsName())
                .goodsSpecs(JSONObject.parseArray(param.getGoodsSpecs(), GoodsSpecsEntity.class))
                .quantity(param.getQuantity())
                .price(param.getPrice())
                .totalAmount(param.getTotalAmount())
                .totalPayAmount(param.getTotalPayAmount())
                .validityType(param.getCardValidityType())
                .cardsOptional(param.getCardOptional())
                .validityValues(param.getCardValidityValue())
                .cardItemType(param.getCardType())
                .depositOrderGoodsItemCards(depositOrderGoodsItemCards)
                //V2
                .goodsSpuCode(param.getGoodsSpuCode())
                .goodsSkuCode(param.getGoodsSkuCode())
                .itemDurationMinute(param.getItemDurationMinute())
                .consumptionType(param.getConsumptionType())
                .mainUrl(param.getMainUrl())
                .isCanGift(param.getIsCanGift() != null && param.getIsCanGift() == 1)
                .build();
    }
}
