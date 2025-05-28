package com.tdt.boot.elasticsearch.dao.impl;

import com.tdt.boot.elasticsearch.dao.MqErrorLogDao;
import com.tdt.boot.elasticsearch.domain.MqErrorLogEntity;
import com.tdt.boot.elasticsearch.manager.MqErrorLogManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * @description: error log
 * @date: 2021/12/21 11:51
 * @author: renchuangege
 */
@Slf4j
@Repository
public class MqErrorLogDaoImpl implements MqErrorLogDao {

    @Autowired
    private MqErrorLogManager mqErrorLogManager;

    @Override
    public boolean deleteIndex() {
        return mqErrorLogManager.deleteIndex();
    }


    /*public boolean addDocument(MqErrorLogEntity mqErrorLogEntity) {
        String toJSON = JSONObject.toJSONString(mqErrorLogEntity);
        return mqErrorLogManager.addDocument(
                mqErrorLogEntity.getId().toString(),
                JSONObject.parseObject(toJSON, Map.class));
    }*/

    @Override
    public boolean addDocument(MqErrorLogEntity mqErrorLogEntity) {
        return mqErrorLogManager.addDocument(mqErrorLogEntity);
    }



}
