package com.tdt.boot.elasticsearch.protocol;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description: 定金单创建-已经废弃. 新版本协议
 * @date: 2021/6/22 13:44
 * @author: renchuangege
 */
@Data
public class DepositOrderDetailProtocol {
    /**
     * id
     */
    private Long id;
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 会员姓名
     */
    private String memberName;
    /**
     * 会员卡号（电话）
     */
    private String memberPhone;
    /**
     * 会员等级
     */
    private String memberLevel;
    /**
     * 会员头像
     */
    private String memberAvatar;
    /**
     * 会员身份：1：普通客户；2：活跃会员；3：流失会员；4：休眠会员
     */
    private Integer memberIdentity;
    /**
     * 下单门店id
     */
    private Long storeId;
    /**
     * 下单门店name
     */
    private String storeName;
    /**
     * 定金单编号
     */
    private String code;
    /**
     * 状态  {@link DepositOrderConstant.Status}
     */
    private Integer status;
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    /**
     * 已付总额
     */
    private BigDecimal payTotalAmount;

    /**
     *  有效期
     */
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date expirationTime;
    /**
     *  是否申请延期
     */
    private Byte isDelayed;
    /**
     * 录单时间
     */
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date gmtInput;
    /**
     * 配送方式 1自提;2送货上门
     */
    private Integer deliverType;
    /**
     * 收货地址id
     */
    private Long deliverAddrId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人
     */
    private Long createUser;
    /**
     * create_user_name
     */
    private String createUserName;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date gmtCreate;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date gmtModified;
    /**
     * 发票状态 发票状态: 0未录入发票信息（录单时不选择开票）  1已经录入发票信息(待审核)   2审核不通过  3开票中（审核通过,待开票）  4开票完成（已开票）
     */
    private Integer invoiceStatus;

    /**
     * 定金单购买商品
     * @author: renchuangege
     * @date: 2021/6/22 13:53
     */
    @Data
    public static class DepositOrderGoods {
        /**
         * id
         */
        private Long id;
        /**
         * 定金单id
         */
        private Long depositOrderId;
        /**
         * 商品skuid/套餐和卡项的id
         */
        private Long goodsSkuId;
        /**
         * 条目类型（商品类型） {@link GoodsTypeConstant}
         */
        private Integer goodsType;
        /**
         * 商品名称
         */
        private String goodsName;
        /**
         * 商品规格JOSN格式
         */
        private String goodsSpecs;
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
         *  定金百分比
         */
        private Integer depositPercentage;
        @DateTimeFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        @JsonFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        private Date gmtCreate;
        @DateTimeFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        @JsonFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        private Date gmtModified;
    }

    /**
     * 商品条目（针对套餐,套餐类的商品列表）
     * @author: renchuangege
     * @date: 2021/6/22 13:57
     */
    @Data
    public static class DepositOrderGoodsItem {
        /**
         * id
         */
        private Long id;
        /**
         * 定金单商品id
         */
        private Long orderGoodsId;
        /**
         * 定金单商品id
         */
        private Long goodsSpuId;
        /**
         * 商品skuid/套餐和卡项的id
         */
        private Long goodsSkuId;
        /**
         * 条目类型（商品类型） {@link GoodsTypeConstant}
         */
        private Integer goodsType;
        /**
         * 商品名称
         */
        private String goodsName;
        /**
         * 商品规格JOSN格式
         */
        private String goodsSpecs;
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
         * 该条目已还款总额
         */
        private BigDecimal totalPayAmount;
        /**
         * 卡类型：1无限次卡，2通卡 (21-09-01)
         */
        private Integer cardType;
        /**
         * 有效期类型：1永久有效；2截止；3购买后x天内有效； 4首次使用后x天内有效 (21-09-01)
         */
        private Integer cardValidityType;
        /**
         * 有效期值 (21-09-01)
         */
        private String cardValidityValue;
        /**
         *  可选择x个项目划扣 (21-09-01)
         */
        private Integer cardOptional;
        @DateTimeFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        @JsonFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        private Date gmtCreate;
        @DateTimeFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        @JsonFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        private Date gmtModified;
    }

    /**
     * 套餐卡项的条目明细
     * @author: renchuangege
     * @date: 2021/9/2 10:58
     */
    @Data
    public static class DepositOrderGoodsItemCard {
        /**
         * id
         */
        private Long id;
        /**
         * 定金单商品idorder_sale_order_goods_item的主键)
         */
        private Long orderGoodsItemId;
        /**
         * goods_spu_id
         */
        private Long goodsSpuId;
        /**
         * 商品skuid/卡项的id
         */
        private Long goodsSkuId;
        /**
         * 销售单条目类型（商品类型）
         * {@link com.tinyframework.saas.common.constant.commodity.GoodsTypeConstant}
         */
        private Integer goodsType;
        /**
         * 商品名称
         */
        private String goodsName;
        /**
         * 商品规格JOSN格式
         */
        private String goodsSpecs;
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
        /**
         * 均摊现金支付金额
         */
        private BigDecimal cashAmount;
        @DateTimeFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        @JsonFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        private Date gmtCreate;
        @DateTimeFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        @JsonFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        private Date gmtModified;
    }

    /**
     *  还款记录
     * @author: renchuangege
     * @date: 2021/6/22 13:48
     */
    @Data
    public static class DepositRepaymentHistory {
        /**
         * id
         */
        private Long id;
        /**
         * 定金单id
         */
        private Long orderId;
        /**
         * 支付门店id
         */
        private Long payStoreId;
        /**
         * 支付单门店name
         */
        private String payStoreName;
        /**
         * 还款金额
         */
        private BigDecimal repaymentAmount;
        /**
         * 代收
         */
        private BigDecimal agentCollectionAmount;
        /**
         * 实付金额
         */
        private BigDecimal actualPayAmount;
        /**
         * 储值支付金额
         */
        private BigDecimal storeValuePayAmount;
        /**
         * 现金支付金额
         */
        private BigDecimal cashPayAmount;
        /**
         * 还款状态： {@link DepositOrderConstant.RepaymentStatus}
         */
        private Integer status;
        /**
         * 还款单编号
         */
        private String code;
        /**
         * 支付方式 {@link PaymentTypeConstant}
         */
        private Integer payType;
        /**
         * 支付单号（支付流水）
         */
        private String  payOrderCode;
        /**
         * 是否补录
         */
        private Byte isAdditional;
        /**
         *  支付时间
         */
        @DateTimeFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        @JsonFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        private Date gmtPay;
        /**
         *  统计时间，如果为补录单那么首次支付的时间为补录单的时间
         */
        @DateTimeFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        @JsonFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        private Date gmtPayInput;
        /**
         * 备注说明
         */
        private String  remark;
        /**
         * 门店id
         */
        private Long  storeId;
        /**
         * 门店名称
         */
        private String  storeName;
        /**
         * 做单人id
         */
        private Long  createUser;
        /**
         * 做单人姓名
         */
        private String  createUserName;
        @DateTimeFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        @JsonFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        private Date gmtCreate;
        @DateTimeFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        @JsonFormat(
                pattern = "yyyy-MM-dd HH:mm:ss"
        )
        private Date gmtModified;
        /**
         * 还款明细
         */
        private List<DiscountPackageRepaymentItem> payGoodsItems;
    }



    /**
     * 套餐里面商品条目信息
     */
    @Data
    public static class DiscountPackageRepaymentItem {
        /**
         * 订单条目id
         */
        private Long orderGoodItemId;
        /**
         * 本次还款金额
         */
        private BigDecimal payAmount;
    }
}
