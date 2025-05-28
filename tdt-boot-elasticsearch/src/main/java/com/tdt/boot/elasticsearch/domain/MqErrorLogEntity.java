package com.tdt.boot.elasticsearch.domain;

import com.tdt.boot.elasticsearch.annotation.EsDocumnetId;
import com.tdt.boot.elasticsearch.annotation.EsIndex;
import com.tdt.boot.elasticsearch.constant.IndexNameConstant;
import lombok.Data;

import java.util.Date;

/**
 * @description: error log
 * @date: 2021/12/23 11:22
 * @author: renchuangege
 */
@Data
@EsIndex(indexName = IndexNameConstant.MQ_ERROR_LOG)
public class MqErrorLogEntity {
    @EsDocumnetId
    private Long id;
    private String topic;
    private String tags;
    private String messageBody;
    private Integer count;
    private Date createTime;
}
