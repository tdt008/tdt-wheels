package com.tdt.boot.elasticsearch.dao;

import com.tdt.boot.elasticsearch.domain.MqErrorLogEntity;

/**
 * @description: error log
 * @date: 2021/12/21 11:51
 * @author: renchuangege
 */
public interface MqErrorLogDao {
    /**
     * 删除索引
     * @return: boolean
     * @author: renchuangege
     * @date: 2021/12/21 12:05
     */
    boolean deleteIndex();

    /**
     * 插入日志
     * @param mqErrorLogEntity
     * @return: boolean
     * @author: renchuangege
     * @date: 2021/12/23 11:24
     */
    boolean addDocument(MqErrorLogEntity mqErrorLogEntity);

}
