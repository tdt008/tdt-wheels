package com.tdt.boot.elasticsearch.domain;

import com.tdt.boot.elasticsearch.protocol.DepositOrderDetailV2Protocol;
import com.tdt.boot.elasticsearch.util.DateUtil;
import com.tdt.boot.elasticsearch.util.PayBuildUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description：还款记录
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositRepaymentHistoryEntity {
    /**
     * id
     */
    private Long id;

    /**
     * 还款金额
     */
    private BigDecimal repaymentAmount;
    /**
     * 实付金额
     */
    private BigDecimal actualPayAmount;
    /**
     * 储值支付金额
     */
    private BigDecimal storeValuePayAmount;
    /**
     * 还款状态： 1待支付；2支付成功；3支付失败；4关闭
     */
    private Integer status;
    /**
     * 还款单编号
     */
    private String code;
    /**
     * 支付方式
     */
    private Integer payType;
    /**
     * 支付单号（支付流水）
     */
    private String payOrderCode;
    /**
     * 支付时间
     */
    private Long gmtPay;

    /**
     * 备注说明
     */
    private String remark;
    /**
     * 门店id
     */
    private Long storeId;
    /**
     * 门店名称
     */
    private String storeName;
    /**
     * 做单人id
     */
    private Long createUser;
    /**
     * 做单人姓名
     */
    private String createUserName;

    /**
     * 还款明细
     */
    private List<DiscountPackageRepaymentItemEntity> payGoodsItems;

    /**
     * 支付门店id
     */
    private Long payStoreId;

    /**
     * 支付单门店name
     */
    private String payStoreName;

    /**
     * 用于日期分组
     */
    private String groupByDay;

    /**
     * 用于月分组
     */
    private String groupByMonth;

    /**
     * 商品类型
     */
    private Integer goodsType;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 卡项类型
     */
    private Integer cardItemType;

    /**
     * 储值支付(用于统计) 1
     */
    private Integer storePay;

    /**
     * 其他支付 微信支付 4；支付宝：2；银联支付：8
     */
    private Integer otherPay;

    /**
     * 代理收款总额
     */
    private BigDecimal agentCollectionAmount;

    /**
     * 统计的金额
     */
    private BigDecimal statisticsAmount;

    /**
     * 是否补录
     */
    private Boolean isAdditional;

    /**
     * 补录时间
     */
    private Long gmtPayInput;

    /**
     * 是否首次还款
     */
    private Boolean firstRepayment;

    public static DepositRepaymentHistoryEntity buildDepositRepaymentHistory(DepositOrderDetailV2Protocol.DepositRepaymentHistoryV2 param, DepositOrderGoodsEntity orderGoods) {

        Integer storePay = null, otherPay = null, payType = param.getPayType();
        if (payType != null) {
            PayDetailEntity payDetail = PayBuildUtils.getPayDetail(payType);
            storePay = payDetail.getStorePay();
            otherPay = payDetail.getOtherPay();
        }
        List<DiscountPackageRepaymentItemEntity> discountPackageRepaymentItem = PayBuildUtils.discountPackageRepaymentItem(param, orderGoods);

        BigDecimal statisticsAmount = BigDecimal.ZERO;
        if (param.getActualPayAmount() != null) {
            statisticsAmount = statisticsAmount.add(param.getActualPayAmount());
        }
        if (param.getAgentCollectionAmount() != null) {
            statisticsAmount = statisticsAmount.add(param.getAgentCollectionAmount());
        }

        return DepositRepaymentHistoryEntity.builder()
                .id(param.getId())
                .repaymentAmount(param.getRepaymentAmount())
                .actualPayAmount(param.getActualPayAmount())
                .storeValuePayAmount(param.getStoreValuePayAmount())
                .status(param.getStatus())
                .code(param.getCode())
                .payType(param.getPayType())
                .payOrderCode(param.getPayOrderCode())
                .gmtPay(param.getGmtPay() != null ? param.getGmtPay().getTime() : null)
                .remark(param.getRemark())
                .storeId(param.getStoreId())
                .storeName(param.getStoreName())
                .createUser(param.getCreateUser())
                .createUserName(param.getCreateUserName())
                .payGoodsItems(discountPackageRepaymentItem)
                .payStoreId(param.getPayStoreId())
                .payStoreName(param.getPayStoreName())
                .groupByDay(param.getGmtPayInput() != null ? DateUtil.format(param.getGmtPayInput(), DateUtil.YTD_DATETIME) : null)
                .groupByMonth(param.getGmtPayInput() != null ? DateUtil.format(param.getGmtPayInput(), DateUtil.YEARS_DATETIME) : null)
                .goodsType(orderGoods.getGoodsType())
                .goodsName(orderGoods.getGoodsName())
                .otherPay(otherPay)
                .storePay(storePay)
                .agentCollectionAmount(param.getAgentCollectionAmount())
                .statisticsAmount(statisticsAmount)
                .isAdditional(param.getIsAdditional() != null && param.getIsAdditional() == 1)
                .gmtPayInput(param.getGmtPayInput() != null ? param.getGmtPayInput().getTime() : null)
                //V2
                .firstRepayment(param.getFirstRepayment() != null && param.getFirstRepayment() == 1)
                .build();
    }
}
