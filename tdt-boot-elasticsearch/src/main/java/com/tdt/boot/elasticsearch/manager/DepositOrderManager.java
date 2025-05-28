package com.tdt.boot.elasticsearch.manager;

import com.tdt.boot.elasticsearch.domain.DepositOrderEntity;
import org.apache.lucene.search.join.ScoreMode;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @date: 2022/1/5 14:39
 * @author: renchuangege
 */
@Component
public class DepositOrderManager extends DocumentManager<DepositOrderEntity> {

    /**
     *
     * @param startTime
     * @param endTime
     * @param payStoreId
     * @return: java.util.List<com.tdt.boot.elasticsearch.domain.DepositOrderEntity>
     * @author: renchuangege
     * @date: 2022/1/6 16:13
     */
    public List<DepositOrderEntity> listIncludeDiscountPackage(Date startTime, Date endTime, Long payStoreId) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .filter(QueryBuilders.nestedQuery("depositOrderGoods",
                        QueryBuilders.boolQuery().filter(QueryBuilders.termQuery("depositOrderGoods.goodsType", 4)),
                        ScoreMode.None));

        BoolQueryBuilder repaymentHistoryBoolQuery = QueryBuilders.boolQuery();
        if (payStoreId != null) {
            repaymentHistoryBoolQuery.filter(QueryBuilders.termQuery("repaymentHistoryList.payStoreId", payStoreId));
        }

        if (startTime != null || endTime != null) {
            repaymentHistoryBoolQuery.filter(QueryBuilders.rangeQuery("repaymentHistoryList.groupByDay").gte(startTime).lte(endTime));
        }
        boolQueryBuilder.filter(QueryBuilders.nestedQuery("repaymentHistoryList", repaymentHistoryBoolQuery, ScoreMode.None))
                .mustNot(QueryBuilders.termsQuery("status", 1, 3));

        searchSourceBuilder.query(boolQueryBuilder)
                .size(9999)
                .explain(true)
                .trackTotalHits(true)
                .sort(SortBuilders.fieldSort("gmtModified").order(SortOrder.DESC).unmappedType("keyword"));

        return super.list(searchSourceBuilder);
    }



    /**
     * 根据订单编号查询包含套餐的定金单
     * @param codes 订单编号列表
     * @return: java.util.List<com.tdt.boot.elasticsearch.domain.DepositOrderEntity>
     * @author: renchuangege
     * @date: 2022/1/6 18:26
     */
    public List<DepositOrderEntity> listIncludePackageProductByCodest(List<String> codes) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .filter(QueryBuilders.nestedQuery("depositOrderGoods",
                        QueryBuilders.boolQuery().filter(QueryBuilders.termQuery("depositOrderGoods.goodsType", 4)),
                        ScoreMode.None))
                .filter(QueryBuilders.termsQuery("code.keyword", codes))
                .mustNot(QueryBuilders.termsQuery("status", 1, 3));
        searchSourceBuilder.query(boolQueryBuilder)
                .size(9999)
                .explain(true)
                .trackTotalHits(true)
                .sort(SortBuilders.fieldSort("gmtModified").order(SortOrder.DESC).unmappedType("keyword"));
        return super.list(searchSourceBuilder);
    }



















    /**
     * DSL 提供
     * @author: renchuangege
     * @date: 2022/1/6 16:15
     */
    private static class DepositOrderDslProvider {

        public String listIncludeDiscountPackageDSL(Date startTime, Date endTime, Long payStoreId) {
            String dsl = "{" +
                    "                            \"query\": {" +
                    "                \"bool\": {" +
                    "                    \"filter\": [" +
                    "                    {" +
                    "                        \"nested\": {" +
                    "                        \"path\": \"depositOrderGoods\"," +
                    "                                \"query\": {" +
                    "                            \"bool\": {" +
                    "                                \"filter\": [" +
                    "                                {" +
                    "                                    \"term\": {" +
                    "                                    \"depositOrderGoods.goodsType\": 4" +
                    "                                }" +
                    "                                }" +
                    "                            ]" +
                    "                            }" +
                    "                        }" +
                    "                    }" +
                    "                    }," +
                    "                    {" +
                    "                        \"nested\": {" +
                    "                        \"path\": \"repaymentHistoryList\"," +
                    "                                \"query\": {" +
                    "                            \"bool\": {" +
                    "                                \"filter\": [";
            if (payStoreId != null) {
                dsl += " {" +
                    "  \"term\": {" +
                    "    \"repaymentHistoryList.payStoreId\": " + payStoreId +
                    "    }" +
                    "},";
            }
            if (startTime != null || endTime != null) {
                dsl += "                                {" +
                        "                                    \"range\": {" +
                        "                                    \"repaymentHistoryList.groupByDay\": {" ;
                if (startTime != null) {
                    dsl += "                                        \"gte\": #[startTime]," ;
                }
                if (endTime != null) {
                    dsl += "                                        \"lte\": #[endTime]";
                }

                dsl += "                                    }" +
                        "                                }" +
                        "                                }";
            }

            dsl += "                            ]" +
                    "                            }" +
                    "                        }" +
                    "                    }" +
                    "                    }" +
                    "                  ]," +
                    "                    \"must_not\": [" +
                    "                    {" +
                    "                        \"terms\": {" +
                    "                        \"status\": [ 1, 3]" +
                    "                    }" +
                    "                    }" +
                    "                  ]" +
                    "                }" +
                    "" +
                    "            }," +
                    "            \"size\":9999," +
                    "                    \"explain\":true," +
                    "                    \"track_total_hits\":true," +
                    "                    \"sort\": [{" +
                    "                \"gmtModified\": {" +
                    "                    \"order\":  \"desc\"," +
                    "                            \"unmapped_type\": \"keyword\"" +
                    "                }" +
                    "            }]" +
                    "        }";
           return dsl;
        }

    }
}
