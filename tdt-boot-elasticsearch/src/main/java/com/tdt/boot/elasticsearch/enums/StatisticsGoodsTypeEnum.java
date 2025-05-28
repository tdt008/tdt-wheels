package com.tdt.boot.elasticsearch.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @description：商品类型枚举类
 */
@Getter
@AllArgsConstructor
public enum StatisticsGoodsTypeEnum {

    /**
     * 产品
     */
    PRODUCT(0, "产品"),

    /**
     * 商品
     */
    TRADITIONAL_ITEM(1, "传统项目"),

    /**
     * 合作项目
     */
    COOPERATION_ITEM(2, "合作项目"),

    /**
     * 套餐
     */
    COMBO(4, "套餐");
    final Integer code;
    final String name;

    /**
     * code 匹配值
     *
     * @param code
     * @return
     */
    public static String getName(Integer code) {
        for (StatisticsGoodsTypeEnum it : StatisticsGoodsTypeEnum.values()) {
            if (it.code.equals(code)) {
                return it.name;
            }
        }
        return null;
    }

    /**
     * 获取code列表
     *
     * @return
     */
    public static List<Integer> codeList() {
        List<Integer> list = new ArrayList<>();
        for (StatisticsGoodsTypeEnum it : StatisticsGoodsTypeEnum.values()) {
            list.add(it.getCode());
        }
        return list;
    }
}
