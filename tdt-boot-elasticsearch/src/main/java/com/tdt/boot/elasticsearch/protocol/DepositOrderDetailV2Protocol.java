package com.tdt.boot.elasticsearch.protocol;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 定金单创建 --第二版
 * @author: renchuangege
 * @date: 2021/12/3 11:45
 */
@Data
public class DepositOrderDetailV2Protocol extends DepositOrderDetailProtocol {
    /**
     * 商品数据来源：0：iPad  1：小程序 2: 有赞
     */
    private Integer dataSource;
    /**
     * 关闭类型
     */
    private String closeType;
    /**
     * 运费金额
     */
    private BigDecimal freightAmount;
    /**
     * 关闭时间
     */
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date gmtClose;
    /**
     * 完成时间
     */
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date gmtFinish;
    /**
     * 退款时间
     */
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date gmtRefund;
    /**
     * 已经退款总金额
     */
    private BigDecimal totalRefundAmount;
    /**
     * 订单创建后超时关闭的时间
     */
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date gmtExpire;
    /**
     * 用户订单是否已经删除
     */
    private Byte memberDeleted;
    /**
     *  是否补录
     */
    private Byte isAdditional;

    /**
     * 购买条目
     */
    private DepositOrderGoodsV2 purchaseGoodsItem;
    /**
     * 还款记录
     */
    private List<DepositRepaymentHistoryV2> repaymentHistoryList;

    /**
     * 定金单购买商品
     * @author: renchuangege
     * @date: 2021/12/6 16:20
     */
    @Data
    public static class DepositOrderGoodsV2 extends DepositOrderGoods {
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
         * 消耗业绩单价
         */
        private BigDecimal performancePrice;
        /**
         * 消耗业绩总额
         */
        private BigDecimal totalPerformanceAmount;
        /**
         * 购买的项目资产是否可转赠
         */
        private Byte isCanGift;
        /**
         * 赠品
         */
        private List<SaleOrderDetailV2Protocol.SaleDepositGiftV2DTO> giftGoodsList;

        /**
         * 商品条目（针对套餐）
         */
        private List<DepositOrderGoodsItemV2> orderGoodsItems;
    }

    /**
     * 商品条目（针对套餐,套餐类的商品列表）
     * @author: renchuangege
     * @date: 2021/6/22 13:57
     */
    @Data
    public static class DepositOrderGoodsItemV2 extends DepositOrderGoodsItem {
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
        private Byte isCanGift;
        /**
         * 消耗业绩单价
         */
        private BigDecimal performancePrice;
        /**
         * 消耗业绩总额
         */
        private BigDecimal totalPerformanceAmount;
        /**
         * 套餐-卡项明细
         */
        private List<DepositOrderGoodsItemCardV2> depositOrderGoodsItemCardList;
    }

    /**
     * 套餐卡项的条目明细
     * @author: renchuangege
     * @date: 2021/9/2 10:58
     */
    @Data
    public static class DepositOrderGoodsItemCardV2 extends DepositOrderGoodsItemCard {
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
    }

    /**
     *  还款记录
     * @author: renchuangege
     * @date: 2021/6/22 13:48
     */
    @Data
    public static class DepositRepaymentHistoryV2 extends DepositRepaymentHistory {
        /**
         * 是否首次还款
         */
        private Byte firstRepayment;
    }

}
