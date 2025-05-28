package com.tdt.boot.elasticsearch.protocol;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @description: 定金单更新
 * @date: 2021/6/28 15:35
 * @author: renchuangege
 */
@Data
public class DepositOrderUpdateProtocol {
    /**
     * 销售单id
     */
    private Long id;
    /**
     * 销售单编号
     */
    private String orderCode;

    /**
     * 待支付还款单code
     *
     */
    private List<String> depositHistoryCodeList;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 定金单状态
     */
    private Integer status;
    /**
     *  是否申请延期
     */
    private Byte isDelayed;
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
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date gmtModified;
    /**
     * 关闭类型：
     */
    private Integer closeType;
    /**
     * 发票状态 发票状态: 0未录入发票信息（录单时不选择开票）  1已经录入发票信息(待审核)   2审核不通过  3开票中（审核通过,待开票）  4开票完成（已开票）
     */
    private Integer invoiceStatus;
}
