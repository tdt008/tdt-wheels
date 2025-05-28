package com.tdt.boot.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import com.tdt.boot.elasticsearch.domain.DepositOrderEntity;
import com.tdt.boot.elasticsearch.service.DepositOrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @description: TODO
 * @date: 2022/1/5 12:03
 * @author: renchuangege
 */
@Slf4j(topic = "DepositOrderTest")
public class DepositOrderTest extends AbstractTest {

    @Autowired
    private DepositOrderService depositOrderService;

    /**
     * 根据id查询
     * @author: renchuangege
     * @date: 2022/1/5 12:04
     */
    @Test
    public void testGetById() {
        Long depositOrderId = null;
        DepositOrderEntity depositOrderEntity = depositOrderService.getById(depositOrderId);
        log.info("testGetById================================================================");
        log.info("depositOrderEntity:{}", JSONObject.toJSONString(depositOrderEntity));
    }

    /**
     * 查询包含套餐的定金单
     * @author: renchuangege
     * @date: 2022/1/5 12:23
     */
    @Test
    public void testListIncludeDiscountPackage() {
        Date startTime = new Date();
        Date endTime = new Date();
        Long payStoreId = null;
        List<DepositOrderEntity> list = depositOrderService.listIncludeDiscountPackage(startTime, endTime, payStoreId);
        log.info("testListIncludeDiscountPackage================================================================");
        log.info("list:{}", JSONObject.toJSONString(list));
    }

    /**
     *
     * @author: renchuangege
     * @date: 2022/1/6 18:24
     */
    @Test
    public void testListDepositPackageProductByCodeList() {
        List<String> codes = null;
        List<DepositOrderEntity> list = depositOrderService.listIncludePackageProductByCodest(codes);
        log.info("testGetDepositPackageProductByCodeList================================================================");
        log.info("list:{}", JSONObject.toJSONString(list));
    }
}
