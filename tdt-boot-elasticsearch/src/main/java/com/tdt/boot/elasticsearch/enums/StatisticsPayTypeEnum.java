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
public enum StatisticsPayTypeEnum {

    /**
     * 储值支付
     */
    STORED_PAY(1, "储值支付"),

    /**
     * 微信支付
     */
    WE_CHAT_PAY(4, "微信支付"),

    /**
     * 支付宝支付
     */
    ALI_PAY(2, "支付宝支付"),

    /**
     * 银联支付
     */
    UNION_PAY(8, "银联支付");


    final Integer code;
    final String name;

    /**
     * code 匹配值
     *
     * @param code
     * @return
     */
    public static String getName(Integer code) {
        for (StatisticsPayTypeEnum it : StatisticsPayTypeEnum.values()) {
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
        for (StatisticsPayTypeEnum it : StatisticsPayTypeEnum.values()) {
            list.add(it.getCode());
        }
        return list;
    }
}
