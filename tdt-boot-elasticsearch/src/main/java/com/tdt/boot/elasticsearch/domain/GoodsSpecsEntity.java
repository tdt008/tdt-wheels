package com.tdt.boot.elasticsearch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description：商品规格DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsSpecsEntity {

    /**
     * 规格名
     */
    private String name;

    /**
     * 规格值
     */
    private String value;
}
