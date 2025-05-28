package com.tdt.boot.elasticsearch.domain;

import com.alibaba.fastjson.JSONObject;
import com.tdt.boot.elasticsearch.protocol.SaleOrderDetailV2Protocol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description：订单赠品DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDepositGiftEntity {

    private Long id;

    /**
     * 类型： 销售单赠品、定金单赠品
     */
    private Integer type;

    /**
     * 销售单销售条目id/定金单id
     */
    private Long relationId;

    /**
     * 赠品id
     */
    private Long goodsId;

    /**
     * 赠品类型
     */
    private Integer goodsType;

    /**
     * 赠品名称
     */
    private String goodsName;

    /**
     * 赠品数量
     */
    private Integer quantity;

    /**
     * 赠品状态：1待到账   2已经到账   3已经取货
     */
    private Integer status;

    /**
     * 赠品赠送规则
     */
    private Integer giveawayRule;

    /**
     * 赠品规格
     */
    private List<GoodsSpecsEntity> goodsSpecs;
    /**
     * 赠品商品图片
     */
    private String goodsImg;

    //---V2新增字段-------//
    /**
     * 赠品id
     */
    private Long goodsDetailId;
    /**
     * 赠品code
     */
    private String goodsCode;
    /**
     * 赠品详情code
     */
    private String goodsDetailCode;
    /**
     * 消耗类型
     */
    private Integer consumptionType;
    /**
     * 赠品单价
     */
    private BigDecimal price;

    /**
     * 卡类型：1无限次卡，2通卡
     */
    private Integer cardType;
    /**
     * 有效期类型：1永久有效；2截止；3购买后x天内有效； 4首次使用后x天内有效 (21-08-26)
     */
    private Integer cardValidityType;
    /**
     * 有效期值
     */
    private String cardValidityValue;
    /**
     * 可选择x个项目划扣
     */
    private Integer cardOptional;
    /**
     * 卡项赠品条目
     */
    private List<SaleDepositGiftCardEsEntity> saleDepositGiftCardList;
    /**
     * 购买的项目资产是否可转赠
     */
    private Boolean isCanGift;


    public static OrderDepositGiftEntity buildOrderDepositGiftDTO(SaleOrderDetailV2Protocol.SaleDepositGiftV2DTO param) {
        if (param == null) {
            return null;
        }
        List<SaleDepositGiftCardEsEntity> saleDepositGiftCardList = null;
        if(param.getSaleDepositGiftCardList() != null && param.getSaleDepositGiftCardList().size() > 0){
            saleDepositGiftCardList = param.getSaleDepositGiftCardList().stream().map(SaleDepositGiftCardEsEntity::buildSaleDepositGiftCardEs).collect(Collectors.toList());
        }

        List<GoodsSpecsEntity> goodsSpecsList = JSONObject.parseArray(param.getGoodsSpecs(), GoodsSpecsEntity.class);
        return OrderDepositGiftEntity.builder()
                .id(param.getId())
                .type(param.getType())
                .relationId(param.getRelationId())
                .goodsId(param.getGoodsId())
                .goodsType(param.getGoodsType())
                .goodsName(param.getGoodsName())
                .quantity(param.getQuantity())
                .status(param.getStatus())
                .giveawayRule(param.getGiveawayRule())
                .goodsSpecs(goodsSpecsList)
                .goodsImg(param.getGoodsImg())
                //---V2新增字段-------//
                .goodsDetailId(param.getGoodsDetailId())
                .goodsCode(param.getGoodsCode())
                .goodsDetailCode(param.getGoodsDetailCode())
                .consumptionType(param.getConsumptionType())
                .price(param.getPrice())
                .cardType(param.getCardType())
                .cardValidityType(param.getCardValidityType())
                .cardValidityValue(param.getCardValidityValue())
                .cardOptional(param.getCardOptional())
                .saleDepositGiftCardList(saleDepositGiftCardList)
                .isCanGift(param.getIsCanGift() != null && param.getIsCanGift() == 1)
                .build();
    }
}
