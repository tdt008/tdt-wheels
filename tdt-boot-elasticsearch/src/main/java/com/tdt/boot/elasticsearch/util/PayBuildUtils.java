package com.tdt.boot.elasticsearch.util;


import com.tdt.boot.elasticsearch.constant.GoodsTypeConstant;
import com.tdt.boot.elasticsearch.domain.*;
import com.tdt.boot.elasticsearch.enums.StatisticsPayTypeEnum;
import com.tdt.boot.elasticsearch.protocol.DepositOrderDetailProtocol;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @description：
 */
public class PayBuildUtils {

    public static PayDetailEntity getPayDetail(Integer payType) {
        Integer storePay = null, otherPay;
        switch (payType) {
            //支付宝+储额支付
            case 3:
                otherPay = StatisticsPayTypeEnum.ALI_PAY.getCode();
                storePay = 1;
                break;
            //微信+储值支付
            case 5:
                otherPay = StatisticsPayTypeEnum.WE_CHAT_PAY.getCode();
                storePay = 1;
                break;
            case 9:
                //储值+银联
                otherPay = StatisticsPayTypeEnum.UNION_PAY.getCode();
                storePay = 1;
                break;
            default:
                otherPay = payType;
        }
        return PayDetailEntity.builder().storePay(storePay).otherPay(otherPay).build();
    }

    /**
     * @param param      还款历史记录
     * @param orderGoods 商品信息
     * @return
     */
    public static List<DiscountPackageRepaymentItemEntity> discountPackageRepaymentItem(DepositOrderDetailProtocol.DepositRepaymentHistory param, DepositOrderGoodsEntity orderGoods) {
        String groupByDay = param.getGmtPayInput() != null ? DateUtil.format(param.getGmtPayInput(), DateUtil.YTD_DATETIME) : null,
                groupByMonth = param.getGmtPayInput() != null ? DateUtil.format(param.getGmtPayInput(), DateUtil.YEARS_DATETIME) : null;
        List<DiscountPackageRepaymentItemEntity> result = new ArrayList<>();
        Integer groupByDayInteger = groupByDay != null ? Integer.valueOf(groupByDay) : null;
        Integer groupByMonthInteger = groupByMonth != null ? Integer.valueOf(groupByMonth) : null;
        //购买商品为套餐且有储值需要走均摊逻辑
        //需支付总金额
        BigDecimal repaymentAmount = param.getRepaymentAmount();
        //实付金额
        BigDecimal actualPayAmount = param.getActualPayAmount(), payAmount = param.getRepaymentAmount();
        BigDecimal calculate = null;
        Integer goodsType = null, cardItemType = null;
        Long goodsSkuId = null;
        boolean isSingle = (orderGoods.getOrderGoodsItems() == null || orderGoods.getOrderGoodsItems().size() <= 0 ||
                param.getPayGoodsItems() == null || param.getPayGoodsItems().size() <= 0) &&
                (!GoodsTypeConstant.DISCOUNT_PACKAGE.equals(orderGoods.getGoodsType()) ||
                        !GoodsTypeConstant.SHOPING_CARD.equals(orderGoods.getGoodsType()));

        if (param.getAgentCollectionAmount() != null && actualPayAmount != null) {
            payAmount = payAmount.add(param.getAgentCollectionAmount());
            actualPayAmount = actualPayAmount.add(param.getAgentCollectionAmount());
        }
        if (isSingle) {
            DiscountPackageRepaymentItemEntity build = DiscountPackageRepaymentItemEntity.builder()
                    .orderGoodItemId(orderGoods.getId())
                    .groupByDay(groupByDayInteger)
                    .groupByMonth(groupByMonthInteger)
                    .payAmount(payAmount)
                    .goodsType(orderGoods.getGoodsType())
                    .actualPayAmount(actualPayAmount)
                    .storedAmount(param.getStoreValuePayAmount())
                    .goodsSkuId(orderGoods.getGoodsSkuId())
                    .payStoreId(param.getPayStoreId())
                    .build();
            result.add(build);
            return result;
        }
        for (DepositOrderDetailProtocol.DiscountPackageRepaymentItem it : param.getPayGoodsItems()) {
            //实际支付和储值支付的占比
            if (actualPayAmount != null && GoodsTypeConstant.DISCOUNT_PACKAGE.equals(orderGoods.getGoodsType())) {
                calculate = calculate(actualPayAmount, repaymentAmount);
                OrderGoodsTypeEntity goodsTypeEntity = getGoodsType(orderGoods, it);
                if (goodsTypeEntity != null) {
                    goodsType = goodsTypeEntity.getGoodsType();
                    goodsSkuId = goodsTypeEntity.getSkuId();
                    cardItemType = goodsTypeEntity.getCardType();
                }
            }

            BigDecimal shareActualPayAmount = it.getPayAmount().multiply(calculate).setScale(2, RoundingMode.HALF_UP);
            DiscountPackageRepaymentItemEntity build = DiscountPackageRepaymentItemEntity.builder()
                    .orderGoodItemId(it.getOrderGoodItemId())
                    .groupByDay(groupByDayInteger)
                    .groupByMonth(groupByMonthInteger)
                    .payAmount(it.getPayAmount())
                    .goodsType(goodsType)
                    .actualPayAmount(shareActualPayAmount)
                    .storedAmount(it.getPayAmount().subtract(shareActualPayAmount))
                    .goodsSkuId(goodsSkuId)
                    .cardItemType(cardItemType)
                    .payStoreId(param.getPayStoreId())
                    .build();
            result.add(build);
        }

        return result;
    }

    /**
     * 获取商品类型
     *
     * @param orderGoods 商品明细
     * @param it         还款明细
     * @return 商品类型
     */
    private static OrderGoodsTypeEntity getGoodsType(DepositOrderGoodsEntity orderGoods, DepositOrderDetailProtocol.DiscountPackageRepaymentItem it) {
        List<DepositOrderGoodsItemEntity> orderGoodsItems = orderGoods.getOrderGoodsItems();
        if (orderGoodsItems == null) {
            return OrderGoodsTypeEntity.builder().goodsType(orderGoods.getGoodsType()).skuId(orderGoods.getGoodsSkuId()).build();
        }
        Optional<DepositOrderGoodsItemEntity> first = orderGoodsItems.stream().filter(its -> it.getOrderGoodItemId().equals(its.getId())).findFirst();
        DepositOrderGoodsItemEntity item = null;
        if (first.isPresent()) {
            item = first.get();
        }
        if (item == null) {
            return null;
        }

        if (GoodsTypeConstant.SHOPING_CARD.equals(item.getGoodsType())) {
            DepositOrderGoodsItemCardsEntity itemCard = (item.getDepositOrderGoodsItemCards() != null && item.getDepositOrderGoodsItemCards().size() > 0)
                    ? item.getDepositOrderGoodsItemCards().get(0) : null;
            return OrderGoodsTypeEntity.builder().goodsType(itemCard != null ? itemCard.getGoodsType() : null)
                    .skuId(itemCard != null ? itemCard.getGoodsSkuId() : null)
                    .cardType(item.getCardItemType())
                    .build();
        } else {
            return OrderGoodsTypeEntity.builder().goodsType(item.getGoodsType()).skuId(item.getGoodsSkuId()).build();
        }
    }

    /**
     * 除数运算
     *
     * @param divisor  除数
     * @param divisors 被除数
     * @return BigDecimal 返回两位小数
     */
    public static BigDecimal calculate(BigDecimal divisor, BigDecimal divisors) {
        if (divisor == null || divisor.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return divisor.divide(divisors, 6, RoundingMode.HALF_UP);
    }

    /**
     * BigDecimal相加处理
     *
     * @param offline  加数1
     * @param realTime 加数2
     * @return
     */
    public static BigDecimal bigDecimalAdd(BigDecimal offline, BigDecimal realTime) {
        if (offline != null && realTime != null) {
            return offline.add(realTime);
        } else {
            return Objects.requireNonNullElseGet(offline, () -> realTime == null ? BigDecimal.ZERO : realTime);
        }
    }

//    /**
//     * 构建
//     *
//     * @param order 退款
//     * @return List<OrderBackGoodsItemDTO>
//     */
//    public static List<OrderBackGoodsItemDTO> orderBackGoodsItemBuild(OrderBackProtocol order) {
//        List<OrderBackGoodsItemDTO> list = null;
//        List<OrderBackProtocol.OrderBackGoodsDetailDTO> detailList = order.getOrderBackGoodsDetailDTOList();
//        //如果退款为单个项目
//
//        return null;
//    }
}
