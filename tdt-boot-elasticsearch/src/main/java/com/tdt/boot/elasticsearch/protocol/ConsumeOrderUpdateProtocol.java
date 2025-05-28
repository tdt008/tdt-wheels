package com.tdt.boot.elasticsearch.protocol;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description: 更新消耗单
 * @date: 2021/7/8 20:15
 * @author: renchuangege
 */
@Data
public class ConsumeOrderUpdateProtocol {
    /**
     * id
     */
    private Long id;
    /**
     * 消耗单编号
     */
    private String code;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 下单门店id
     */
    private Long storeId;
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date gmtModified;
}
