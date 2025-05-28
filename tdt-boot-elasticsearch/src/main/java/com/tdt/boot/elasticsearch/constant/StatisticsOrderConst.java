package com.tdt.boot.elasticsearch.constant;

/**
 * @description：订单统计常量
 */
public interface StatisticsOrderConst {

    /**
     * 支付总金额
     */
    String PAY_AMOUNT = "actualPayAmountSum";

    /**
     * 退款总金额
     */
    String AMOUNT_TOTAL = "amountTotal";
    /**
     * es聚合返回值字段
     */
    String VALUE = "value";

    /**
     * 统计金额
     */
    Integer STATISTICS_TYPE_ORDER_MONEY = 1;

    /**
     * 统计消耗金额
     */
    Integer STATISTICS_TYPE_CONSUME_MONEY = 2;

    /**
     * 定金单统计
     */
    Integer STATISTICS_TYPE_DEPOSIT = 2;

    /**
     * 统计消耗次数_卡项
     */
    Integer STATISTICS_TYPE_DEPOSIT_CARD = 4;

    /**
     * 统计消耗金额
     */
    Integer SERVICE_TIMES = 5;
    /**
     * 百分比符号
     */
    String SYMBOL = "%";

    /**
     * 百分比符号
     */
    Double RESULT_SYMBOL = 0.0;

    /**
     * -符号
     */
    String SMALL_ACROSS = "-";

    /**
     * 分母为零返回——
     */
    String ACROSS = "——";

    /**
     * es聚合桶命名
     */
    String BUCKETS = "esBuckets";

    /**
     * es聚合嵌套桶命名
     */
    String BUCKETS_NESTED = "bucketsNested";

    /**
     * 订单查询List
     */
    int SETTLE_ENT = 8;

    /**
     * 进店人次
     */
    Integer GO_STORE_NUMBER = 2;

    /**
     * 模糊查询
     */
    String LIKE_QUERY = "*%*";

    String LIKE_SYMBOL = "%";
}
