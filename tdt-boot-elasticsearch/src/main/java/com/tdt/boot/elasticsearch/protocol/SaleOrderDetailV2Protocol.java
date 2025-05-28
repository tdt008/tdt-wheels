package com.tdt.boot.elasticsearch.protocol;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 销售单协议--第二版
 * @since 2021-12
 * @author: renchuangege
 * @date: 2021/12/3 10:35
 */
@Data
public class SaleOrderDetailV2Protocol extends SaleOrderDetailProtocol {
    /**
     * 销售单关闭类型：
     */
    private Integer closeType;
    /**
     * 运费金额
     */
    private BigDecimal freightAmount;
    /**
     * 折扣总金额，包括调价总金额，卡项和活动优惠总金额，已经未来的会员折扣金额
     */
    private BigDecimal totalDiscountAmount;
    /**
     * 订单关闭时间
     */
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date gmtClose;
    /**
     * 订单完成时间
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
     * 已退款总额
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
     * 会员自己是否删除该订单
     */
    private Byte memberDeleted;
    /**
     * 是否补录
     */
    private Byte isAdditional;

    /**
     * 销售单条目
     */
    private List<SaleOrderGoodsV2DTO> saleOrderGoodsList;
    /**
     * 折扣优惠条目
     */
    private List<SaleDiscountRecordV2DTO> saleDiscountRecordList;

    /**
     * 销售单条目
     * @author: renchuangege
     * @date: 2021/6/16 19:41
     */
    @Data
    public static class SaleOrderGoodsV2DTO extends SaleOrderGoodsDTO {
        /**
         * 商品spucode/套餐code/卡项code
         */
        private String goodsSpuCode;
        /**
         * 商品skucode/套餐code/卡项code
         */
        private String goodsSkuCode;
        /**
         * 数据来源：0：iPad  1：小程序 2: 有赞
         */
        private Integer dataSource;
        /**
         * 退货数量
         */
        private Integer returnQuantity;
        /**
         * 项目单次服务时长
         */
        private Integer itemDurationMinute;
        /**
         * 消耗类型
         */
        private Integer consumptionType;
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
         * 赠品（套餐）
         */
        private List<SaleDepositGiftV2DTO> saleDepositGiftList;
        /**
         * 条目
         */
        private List<SaleOrderGoodsItemV2DTO> saleOrderGoodsItemList;
    }

    /**
     * 销售单条目 (21-08-26)
     * @author: renchuangege
     * @date: 2021/8/26 19:41
     */
    @Data
    public static class SaleOrderGoodsItemV2DTO extends SaleOrderGoodsItemDTO {
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
         * 均摊
         */
        private BigDecimal finalPrice;
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
         * 销售单条目
         */
        private List<SaleOrderGoodsItemCardV2DTO> saleOrderGoodsItemCardList;
    }

    /**
     * 销售单条目 (21-08-26)
     * @author: renchuangege
     * @date: 2021/8/26 19:41
     */
    @Data
    public static class SaleOrderGoodsItemCardV2DTO extends SaleOrderGoodsItemCardDTO {
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
    }

    /**
     *  赠品
     * @author: renchuangege
     * @date: 2021/6/16 19:44
     */
    @Data
    public static class SaleDepositGiftV2DTO extends SaleDepositGiftDTO {
        /**
         *  赠品id
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
         *  可选择x个项目划扣
         */
        private Integer cardOptional;
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
         * 项目单次服务时长
         */
        private Integer itemDurationMinute;
        /**
         * 卡项赠品条目
         */
        private List<SaleDepositGiftCardDTO> saleDepositGiftCardList;
    }

    /**
     * @description: 优惠活动使用记录DTO
     * @date: 2021/6/11 18:38
     * @author: renchuangege
     */
    @Data
    public static class SaleDiscountRecordV2DTO extends SaleDiscountRecordDTO {
        /**
         * 折扣商品范围：1适用所有商品；2适用于指定商品
         */
        private Integer discountGoodsScopeType;
        /**
         * 折扣商品sku id集合
         */
        private String discountGoodsScopeDetail;
        /**
         * 赠品
         */
        List<SaleDepositGiftV2DTO> giftList;
    }

    /**
     * 卡项赠品条目
     * @author: renchuangege
     * @date: 2021/12/8 15:43
     */
    @Data
    public static class SaleDepositGiftCardDTO {
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
        private String goodsSpecs;
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
        /**
         * 项目单次服务时长
         */
        private Integer itemDurationMinute;
    }
}
