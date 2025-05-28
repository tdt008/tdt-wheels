package com.tdt.boot.elasticsearch.protocol;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 销售单创建协议-已经废弃. 新版本协议 {@link SaleOrderDetailV2Protocol}
 * @date: 2021/6/16 19:30
 * @author: renchuangege
 */
@Data
public class SaleOrderDetailProtocol {

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
     * 会员头像 (21-08-26)
     */
    private String memberAvatar;
    /**
     * 会员身份：1：普通客户；2：活跃会员；3：流失会员；4：休眠会员 (21-08-26)
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
     * 支付门店id
     */
    private Long payStoreId;
    /**
     * 支付单门店name
     */
    private String payStoreName;
    /**
     * 销售单编号
     */
    private String code;
    /**
     * 总划线价（改价之前的总价）
     */
    private BigDecimal totalCrossedAmount;
    /**
     * 订单总额
     */
    private BigDecimal totalAmount;
    /**
     * 实际应支付订单总额(减去满减满赠折扣、减去购物卡折扣)
     */
    private BigDecimal totalPayAmount;
    /**
     * 活动优惠扣减金额
     */
    private BigDecimal activityDiscountAmount;
    /**
     * 卡项优惠扣减金额
     */
    private BigDecimal cardDiscountAmount;
    /**
     * 代理收款总额
     */
    private BigDecimal agentCollectionAmount;
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
     * 状态: 1待结算; 2已经完成;  3已关闭
     *
     * 小程序：1待支付; 2已经完成;  3已关闭;  4:待发货  5：待收货
     */
    private Integer status;

    /**
     * 数据来源：0：iPad  1：小程序 2: 有赞
     */
    private Integer dataSource;

    /**
     * 已关闭订单，逻辑删除
     * 0:有效  1：逻辑删除
     */
    private Integer IsDeleted;
    /**
     * 配送方式 1自提;2送货上门
     */
    private Integer deliverType;
    /**
     * 收货地址id
     */
    private Long deliverAddrId;

    /**
     * 选择(或默认)收货地址
     */
    private DeliverAddrInfo deliverAddrInfo;

    /**
     * 是否包邮
     * 0:不包邮  1:包邮
     */
    private Integer freePost;

    /**
     * 邮费
     */
    private BigDecimal postage;

    /**
     * 发票状态: 0未录入发票信息（录单时不选择开票）  1已经录入发票信息(待审核)   2审核不通过  3开票中（审核通过,待开票）  4开票完成（已开票）
     */
    private Integer invoiceStatus;
    /**
     * 发票抬头
     *  1: 个人   2:单位
     */
    private Integer invoiceType;
    /**
     * 购买商品总件数
     */
    private Integer totalQuantity;
    /**
     * 储值支付金额
     */
    private BigDecimal storeValuePayAmount;
    /**
     * 实际支付金额
     */
    private BigDecimal actualPayAmount;
    /**
     * 支付方式
     */
    private Integer payType;
    /**
     * 支付时间
     */
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date gmtPay;
    /**
     * 支付流水单号
     */
    private String payOrderCode;
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
     * 销售单条目
     * @author: renchuangege
     * @date: 2021/6/16 19:41
     */
    @Data
    public static class SaleOrderGoodsDTO {
        /**
         * id
         */
        private Long id;
        /**
         * 销售单id
         */
        private Long orderId;
        /**
         * 商品spuid/套餐和卡项的id
         */
        private Long goodsSpuId;
        /**
         * 商品skuid/套餐和卡项的id
         */
        private Long goodsSkuId;
        /**
         * 销售单条目类型（商品类型）:0产品  1自营项目   2合作项目   3卡项    4套餐   ; 11 积分(赠品使用)
         */
        private Integer goodsType;
        /**
         * 卡类型：1无限次卡，2通卡 (21-08-26)
         */
        private Integer cardType;
        /**
         * 有效期类型：1永久有效；2截止；3购买后x天内有效； 4首次使用后x天内有效 (21-08-26)
         */
        private Integer cardValidityType;
        /**
         * 有效期值 (21-08-26)
         */
        private String cardValidityValue;
        /**
         *  可选择x个项目划扣 (21-08-26)
         */
        private Integer cardOptional;
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
         * 单价(改价之前)
         */
        private BigDecimal price;
        /**
         * 最终单价
         */
        private BigDecimal finalPrice;
        /**
         * 该条目总价（最终单价与数量之积）
         */
        private BigDecimal totalAmount;
        /**
         * 购物卡优惠均摊金额
         */
        private BigDecimal cardShareDiscountAmount;
        /**
         * 活动优惠均摊金额
         */
        private BigDecimal activityShareDiscountAmount;
        /**
         * 储值支付均摊金额
         */
        private BigDecimal storeValueShareAmount;
        /**
         * 最终支付金额
         */
        private BigDecimal finalPayAmount;
        /**
         * 均摊现金支付金额
         */
        private BigDecimal cashAmount;
    }

    /**
     * 销售单条目 (21-08-26)
     * @author: renchuangege
     * @date: 2021/8/26 19:41
     */
    @Data
    public static class SaleOrderGoodsItemDTO {
        /**
         * id
         */
        private Long id;
        /**
         * 条目id
         */
        private Long orderGoodsId;
        /**
         * 商品spuid/套餐和卡项的id
         */
        private Long goodsSpuId;
        /**
         * 商品skuid/套餐和卡项的id
         */
        private Long goodsSkuId;
        /**
         * 销售单条目类型（商品类型）:0产品  1自营项目   2合作项目   3卡项    4套餐   ; 11 积分(赠品使用)
         */
        private Integer goodsType;
        /**
         * 卡类型：1无限次卡，2通卡 (21-08-26)
         */
        private Integer cardType;
        /**
         * 有效期类型：1永久有效；2截止；3购买后x天内有效； 4首次使用后x天内有效 (21-08-26)
         */
        private Integer cardValidityType;
        /**
         * 有效期值 (21-08-26)
         */
        private String cardValidityValue;
        /**
         *  可选择x个项目划扣
         */
        private Integer cardOptional;
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
         * 储值支付均摊金额
         */
        private BigDecimal storeValueShareAmount;
        /**
         * 最终支付金额
         */
        private BigDecimal finalPayAmount;
        /**
         * 均摊现金支付金额
         */
        private BigDecimal cashAmount;
    }

    /**
     * 销售单条目 (21-08-26)
     * @author: renchuangege
     * @date: 2021/8/26 19:41
     */
    @Data
    public static class SaleOrderGoodsItemCardDTO {
        /**
         * id
         */
        private Long id;
        /**
         * 条目id
         */
        private Long orderGoodsItemId;
        /**
         * 商品spuid/套餐和卡项的id
         */
        private Long goodsSpuId;
        /**
         * 商品skuid/套餐和卡项的id
         */
        private Long goodsSkuId;
        /**
         * 销售单条目类型（商品类型）:0产品  1自营项目   2合作项目   3卡项    4套餐   ; 11 积分(赠品使用)
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
         * 储值支付均摊金额
         */
        private BigDecimal storeValueShareAmount;
        /**
         * 最终支付金额
         */
        private BigDecimal finalPayAmount;
        /**
         * 均摊现金支付金额
         */
        private BigDecimal cashAmount;
    }

    /**
     *  赠品
     * @author: renchuangege
     * @date: 2021/6/16 19:44
     */
    @Data
    public static class SaleDepositGiftDTO {
        /**
         * id
         */
        private Long id;
        /**
         *  类型： 1销售单赠品、2定金单赠品
         */
        private Integer type;
        /**
         *  订单id(销售单id、定金单id)
         */
        private Long orderId;
        /**
         *  销售单销售条目id/定金单id
         */
        private Long relationId;
        /**
         *  赠品id
         */
        private Long goodsId;
        /**
         * 赠品类型  0产品  1自营项目   2合作项目   3卡项    4套餐   ; 11 积分(赠品使用)
         */
        private Integer goodsType;
        /**
         *  赠品名称
         */
        private String goodsName;
        /**
         *  赠品数量
         */
        private Integer quantity;
        /**
         *   赠品状态：1待到账   2已经到账   3已经取货
         */
        private Integer status;
        /**
         *  赠品赠送规则
         */
        private Integer giveawayRule;
        /**
         *  赠品规格
         */
        private String goodsSpecs;
        /**
         *  赠品商品图片
         */
        private String goodsImg;

    }

    /**
     * @description: 优惠活动使用记录DTO
     * @date: 2021/6/11 18:38
     * @author: renchuangege
     */
    @Data
    public static class SaleDiscountRecordDTO {
        private Long id;
        /**
         *  销售单id
         */
        private Long orderId;
        /**
         * 会员id
         */
        private Long memberId;
        /**
         *  折扣名称
         */
        private String discountName;
        /**
         *  折扣类型： 0现金红包；1购物卡；2满减满赠活动
         */
        private Integer discountType;
        /**
         *  购物卡ID/活动id
         */
        private Long relationId;
        /**
         *  使用状态： 1预使用， 2正常完成使用， 3异常退回
         */
        private Integer status;
        /**
         *  折扣或者优惠金额
         */
        private BigDecimal discountAmount;
        /**
         * 购物卡类型 : 1代金券  2优惠劵 3折扣劵
         */
        private Integer shoppingCardType;
    }

    /**
     * 选择的收货地址
     */
    @Data
    public static class DeliverAddrInfo{

        private Long id;
        /**
         * 会员id
         */
        private Long memberId;

        /**
         * 收货人
         */
        private String consignee;

        /**
         * 手机号码
         */
        private String phone;

        /**
         * 省区域编码
         */
        private String provCode;

        /**
         * 市区域编码
         */
        private String cityCode;

        /**
         * 区编码
         */
        private String countyCode;

        /**
         * 省市区
         */
        private String region;

        /**
         * 详细地址
         */
        private String detailedAddress;
    }

}
