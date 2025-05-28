package com.tdt.boot.elasticsearch.service;

import com.tdt.boot.elasticsearch.domain.DepositOrderEntity;

import java.util.Date;
import java.util.List;

/**
 * @description: 定金单
 * @date: 2022/1/5 12:00
 * @author: renchuangege
 */
public interface DepositOrderService {
    /**
     * 根据id查询
     * @param id 订单id
     * @return: com.tdt.boot.elasticsearch.domain.DepositOrderEntity
     * @author: renchuangege
     * @date: 2022/1/5 12:05
     */
    DepositOrderEntity getById(Long id);

    /**
     * 查询包含套餐的定金单
     * @param startTime 支付开始时间
     * @param endTime 支付结束时间
     * @param payStoreId 支付门店id
     * @return: java.util.List<com.tdt.boot.elasticsearch.domain.DepositOrderEntity>
     * @author: renchuangege
     * @date: 2022/1/5 12:28
     */
    List<DepositOrderEntity> listIncludeDiscountPackage(Date startTime, Date endTime, Long payStoreId);

    /**
     * 根据订单编号查询包含套餐的定金单
     * @param codes 订单编号列表
     * @return: java.util.List<com.tdt.boot.elasticsearch.domain.DepositOrderEntity>
     * @author: renchuangege
     * @date: 2022/1/6 18:26
     */
    List<DepositOrderEntity> listIncludePackageProductByCodest(List<String> codes);
}
