package com.tdt.boot.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import com.tdt.boot.elasticsearch.constant.IndexNameConstant;
import com.tdt.boot.elasticsearch.domain.MqErrorLogEntity;
import com.tdt.boot.elasticsearch.manager.MqErrorLogManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: TODO
 * @date: 2021/12/20 22:48
 * @author: renchuangege
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqErrorLogTest {

    @Resource
    private MqErrorLogManager mqErrorLogManager;

    @Test
    public void deleteIndex() {
        //boolean result = mqErrorLogManager.deleteIndex();
        // boolean result = mqErrorLogManager.closeIndex();
        boolean result = mqErrorLogManager.openIndex();

        log.info("Index flag:{}", result);
    }

    @Test
    public void testAddDocument() {
        MqErrorLogEntity mqErrorLogEntity = new MqErrorLogEntity();
        mqErrorLogEntity.setId(1111111L);
        mqErrorLogEntity.setCount(5);
        mqErrorLogEntity.setTags(IndexNameConstant.MQ_ERROR_LOG);
        mqErrorLogEntity.setTags("add");
        mqErrorLogEntity.setMessageBody("-------------------------------mq error log ffffffffff" + System.currentTimeMillis());
        mqErrorLogEntity.setCreateTime(new Date());
        boolean flag = mqErrorLogManager.addDocument(mqErrorLogEntity);
        log.info("add document flag:{}", flag);
    }

    @Test
    public void testAddDoucments() {
        List<MqErrorLogEntity> list = new ArrayList<>();
        MqErrorLogEntity mqErrorLogEntity = new MqErrorLogEntity();
        mqErrorLogEntity.setId(1111112L);
        mqErrorLogEntity.setCount(51);
        mqErrorLogEntity.setTags(IndexNameConstant.MQ_ERROR_LOG);
        mqErrorLogEntity.setTags("add");
        mqErrorLogEntity.setMessageBody("-------------------------------mq error log 1111" + System.currentTimeMillis());
        mqErrorLogEntity.setCreateTime(new Date());
        list.add(mqErrorLogEntity);
        MqErrorLogEntity mqErrorLogEntity2 = new MqErrorLogEntity();
        mqErrorLogEntity2.setId(1111113L);
        mqErrorLogEntity2.setCount(53);
        mqErrorLogEntity2.setTags(IndexNameConstant.MQ_ERROR_LOG);
        mqErrorLogEntity2.setTags("add");
        mqErrorLogEntity2.setMessageBody("-------------------------------mq error log 53" + System.currentTimeMillis());
        mqErrorLogEntity2.setCreateTime(new Date());
        list.add(mqErrorLogEntity2);
        list.add(mqErrorLogEntity2);
        int i = mqErrorLogManager.addDocuments(list);
        log.info("i :{}", i);
    }

    @Test
    public void testGetById() {
        String documentId = "1111111";
        MqErrorLogEntity mqErrorLogEntity = mqErrorLogManager.getById(documentId);
        log.info("doucment:{}", JSONObject.toJSONString(mqErrorLogEntity));
    }

    @Test
    public void testDeleteById() {
        String documentId = "1111111";
        boolean flag = mqErrorLogManager.deleteById(documentId);
        log.info("delete by flag:{}", flag);
    }

    @Test
    public void testsss() {
        LocalDateTime firstDayOfThisMonth = LocalDateTime.now()
                .withDayOfMonth(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
        System.out.printf(firstDayOfThisMonth.toString());

    }
}
