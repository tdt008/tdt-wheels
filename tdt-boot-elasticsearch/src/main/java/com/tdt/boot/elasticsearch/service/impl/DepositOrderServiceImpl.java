package com.tdt.boot.elasticsearch.service.impl;

import com.tdt.boot.elasticsearch.dao.DepositOrderDao;
import com.tdt.boot.elasticsearch.domain.DepositOrderEntity;
import com.tdt.boot.elasticsearch.service.DepositOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description: 定金单
 * @date: 2022/1/5 12:00
 * @author: renchuangege
 */
@Service
public class DepositOrderServiceImpl implements DepositOrderService {

    @Autowired
    private DepositOrderDao depositOrderDao;

    @Override
    public DepositOrderEntity getById(Long id) {
        return depositOrderDao.getById(id);
    }

    @Override
    public List<DepositOrderEntity> listIncludeDiscountPackage(Date startTime, Date endTime, Long payStoreId) {
        return depositOrderDao.listIncludeDiscountPackage(startTime, endTime, payStoreId);
    }

    @Override
    public List<DepositOrderEntity> listIncludePackageProductByCodest(List<String> codes) {
        return depositOrderDao.listIncludePackageProductByCodest(codes);
    }
}
