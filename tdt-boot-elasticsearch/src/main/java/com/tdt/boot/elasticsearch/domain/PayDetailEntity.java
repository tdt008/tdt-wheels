package com.tdt.boot.elasticsearch.domain;

import lombok.Builder;
import lombok.Data;

/**
 */
@Data
@Builder
public class PayDetailEntity {

    /**
     * 储值支付
     */
    private Integer storePay;

    /**
     * 其他支付
     */
    private Integer otherPay;
}
