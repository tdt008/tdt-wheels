package com.tdt.boot.elasticsearch.protocol;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 消耗单 -已经废弃. 新版本协议
 * @date: 2021/7/8 18:01
 * @author: renchuangege
 */
@Data
public class ConsumeOrderDetailProtocol {
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
     * 消耗单编号
     */
    private String code;
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
     * 状态
     */
    private Integer status;
    /**
     * 消耗商品总件数
     */
    private Integer totalQuantity;
    /**
     * 确认签名
     */
    private String signatureUrl;
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
     * 消耗单条目
     * @author: renchuangege
     * @date: 2021/7/8 18:03
     */
    @Data
    public static class ConsumeOrderAssetDTO {
        /**
         * id
         */
        private Long id;
        /**
         * 资产id
         */
        private Long assetId;
        /**
         * 商品spu id
         */
        private Long goodsSpuId;
        /**
         * 商品sku id
         */
        private Long goodsSkuId;
        /**
         * 条目类型（商品类型）
         */
        private Integer goodsType;
        /**
         * 卡项类型
         */
        private Integer cardType;
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
         * （合作项目）选择的供应商id
         */
        private Long supplierId;
        /**
         * （合作项目）选择的供应商名称
         */
        private String supplierName;
        /**
         * 消耗数量
         */
        private Integer quantity;
        /**
         * 服务时长
         */
        private Integer itemDurationMinute;
        /**
         * 总时长
         */
        private Integer totalDurationMinute;
        /**
         * 是否赠品资产
         */
        private Byte isGiftAsset;
        /**
         * 资产名称
         */
        private String assetItemName;
        /**
         * 资产消耗类型
         */
        private Integer assetConsumptionType;
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
         * 消耗金额
         */
        private BigDecimal consumeAmount;
    }

    /**
     * @description: 消耗物料DTO
     * @date: 2021/7/7 16:49
     * @author: renchuangege
     */
    @Data
    public static class ConsumeOrderItemConsumableDTO {
        /**
         * id
         */
        private Long id;
        /**
         * 消耗条目id
         */
        private Long consumeItemId;
        /**
         * 耗料详情id
         */
        private Long consumableSkuId;
        /**
         * 物料sKu code
         */
        private String consumableSkuCode;
        /**
         * 物料名称
         */
        private String name;
        /**
         * 消耗单位
         */
        private String consumeUnitName;
        /**
         * 耗材数量
         */
        private Integer amount;
        /**
         * 消耗总数量
         */
        private Integer totalAmount;
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
     * @description: 消耗资产条目DTO
     * @date: 2021/7/7 16:39
     * @author: renchuangege
     */
    @Data
    public static class ConsumeOrderAssetItemDTO {
        /**
         * id
         */
        private Long id;
        /**
         * 资产消耗id
         */
        private Long consumeOrderAssetId;
        /**
         * 商品spu id
         */
        private Long goodsSpuId;
        /**
         * 商品sku id
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
        private String goodsSpecs;
        /**
         * 主图
         */
        private String mainUrl;
        /**
         * 消耗数量
         */
        private Integer quantity;
        /**
         * 服务时长
         */
        private Integer itemDurationMinute;
        /**
         * 总时长
         */
        private Integer totalDurationMinute;
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
         * 消耗金额
         */
        private BigDecimal consumeAmount;

        /**
         * （卡项-合作项目）选择的供应商id
         */
        private Long supplierId;
        /**
         * （卡项-合作项目）选择的供应商名称
         */
        private String supplierName;
    }
}
