package com.tdt.boot.elasticsearch.protocol;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 消耗单 --第二版
 * @author: renchuangege
 * @date: 2021/12/3 15:39
 */
@Data
public class ConsumeOrderDetailV2Protocol extends ConsumeOrderDetailProtocol {
    /**
     * 是否已评价
     */
    private Integer isEvaluation;
    /**
     * 评价时间
     */
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date gmtEvaluation;
    /**
     * 用户订单是否已经删除
     */
    private Byte memberDeleted;
    /**
     * 验证类型： 1签名，2小程序， 3短信
     */
    private Integer verifyType;
    /**
     * 总消耗类目数
     */
    private Integer totalAssetQuantity;
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
     * 总消耗金额
     */
    private BigDecimal totalConsumeAmount;
    /**
     *  是否补录
     */
    private Byte isAdditional;

    /**
     * 消耗单条目
     */
    private List<ConsumeOrderAssetV2DTO> consumeOrderAssets;

    /**
     * 消耗单条目
     * @author: renchuangege
     * @date: 2021/7/8 18:03
     */
    @Data
    public static class ConsumeOrderAssetV2DTO extends ConsumeOrderAssetDTO {
        /**
         * 消耗单id
         */
        private Long consumeOrderId;
        /**
         * 购买订单id
         */
        private Long purchaseOrderId;
        /**
         * 购买订单编号
         */
        private String purchaseOrderCode;
        /**
         * 购买订单类型：1销售单，2定金单
         */
        private String purchaseOrderType;
        /**
         * 商品spucode/卡项code
         */
        private String goodsSpuCode;
        /**
         * 商品skucode/卡项code
         */
        private String goodsSkuCode;

        /**
         * 消耗资产详情-卡项
         */
        private List<ConsumeOrderAssetItemV2DTO> consumeOrderAssetItemList;

        /**
         * 物料-非卡项
         */
        private List<ConsumeOrderItemConsumableDTO> consumeOrderItemConsumableList;
    }


    /**
     * @description: 消耗资产条目DTO
     * @date: 2021/7/7 16:39
     * @author: renchuangege
     */
    @Data
    public static class ConsumeOrderAssetItemV2DTO extends ConsumeOrderAssetItemDTO {
        /**
         * 资产消耗类型
         */
        private Integer assetConsumptionType;
        /**
         * 商品spucode/卡项code
         */
        private String goodsSpuCode;
        /**
         * 商品skucode/卡项code
         */
        private String goodsSkuCode;

        /**
         * 物料
         */
        private List<ConsumeOrderItemConsumableDTO> consumeOrderItemConsumableList;

    }
}
