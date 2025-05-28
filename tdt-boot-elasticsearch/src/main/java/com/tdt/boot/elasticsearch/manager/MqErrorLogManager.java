package com.tdt.boot.elasticsearch.manager;

import com.tdt.boot.elasticsearch.domain.MqErrorLogEntity;
import org.springframework.stereotype.Component;

/**
 * @description: error log
 * @date: 2022/1/5 14:41
 * @author: renchuangege
 */
@Component
public class MqErrorLogManager extends DocumentManager<MqErrorLogEntity> {
}
