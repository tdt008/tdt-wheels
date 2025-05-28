package com.tdt.boot.elasticsearch.protocol;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 销售单信息更新协议
 * @date: 2021/6/17 20:43
 * @author: renchuangege
 */
@Data
public class SaleOrderUpdateProtocol {
    /**
     * 销售单id
     */
    private Long id;
    /**
     * 销售单编号
     */
    private String orderCode;

    /**
     * 会员id
     */
    private Long memberId;


    /**
     * 销售单状态： 1待结算; 2已经完成;  3已关闭
     * 小程序：1待支付; 2已经完成;  3已关闭;  4:待发货  5：待收货
     */
    private Integer status;
    /**
     * 数据源
     * 0： ipad;  1 小程序
     */
    private Integer dataSource;

    /**
     * 已关闭订单，逻辑删除
     * 0:有效  1：逻辑删除
     */
    private Integer IsDeleted;

    /**
     * 销售单关闭类型：
     * 1、待结算订单12小时未支付，系统自动关闭
     * 2、待结算订单，手工取消订单
     * 3、已支付的订单整单退款
     */
    private Integer closeType;
    /**
     * 发票状态 发票状态: 0未录入发票信息（录单时不选择开票）  1已经录入发票信息(待审核)   2审核不通过  3开票中（审核通过,待开票）  4开票完成（已开票）
     */
    private Integer invoiceStatus;
    /**
     * 发票抬头
     *  1: 个人   2:单位
     */
    private Integer invoiceType;

    /**
     * 物流信息
     */
    private LogisticsInfo logisticsInfo;



    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date gmtModified;

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
     * 会员自己是否删除该订单
     */
    private Byte memberDeleted;
    /**
     * 物流信息详细
     */
    @Data
    public static class LogisticsInfo{

        /**
         * 订单id
         */
        private Long customerOrderId;

        /**
         * 销售出库单id
         */
        private Long outboundOrderId;

        /**
         * 配送方式 1-快递 2-派车
         */
        private Integer deliveryType;

        /**
         * 快递公司
         */
        private String courierCompany;

        /**
         * 快递公司编号
         */
        private String companyNumber;

        /**
         * 快递单号
         */
        private String courierNumber;

        /**
         * 司机名称
         */
        private String driverName;

        /**
         * 司机电话
         */
        private String driverPhone;

        /**
         * 车牌号
         */
        private String plateNumber;
    }
}
