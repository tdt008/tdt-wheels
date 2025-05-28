package com.tdt.boot.elasticsearch.service.impl;

import com.tdt.boot.elasticsearch.dao.MqErrorLogDao;
import com.tdt.boot.elasticsearch.service.MqErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: error log
 * @date: 2021/12/20 21:24
 * @author: renchuangege
 */
@Service
public class MqErrorLogServiceImpl implements MqErrorLogService {

    @Autowired
    private MqErrorLogDao mqErrorLogDao;

    @Override
    public boolean deleteIndex() {
        return mqErrorLogDao.deleteIndex();
    }
}
