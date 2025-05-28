package com.tdt.boot.elasticsearch.dao.impl;

import com.tdt.boot.elasticsearch.dao.DepositOrderDao;
import com.tdt.boot.elasticsearch.domain.DepositOrderEntity;
import com.tdt.boot.elasticsearch.manager.DepositOrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description: 定金单
 * @date: 2022/1/5 12:00
 * @author: renchuangege
 */
@Service
@Repository
public class DepositOrderDaoImpl implements DepositOrderDao {

    @Autowired
    private DepositOrderManager depositOrderManager;

    @Override
    public DepositOrderEntity getById(Long id) {
        return depositOrderManager.getById(id.toString());
    }

    @Override
    public List<DepositOrderEntity> listIncludeDiscountPackage(Date startTime, Date endTime, Long payStoreId) {
        return depositOrderManager.listIncludeDiscountPackage(startTime, endTime, payStoreId);
    }

    @Override
    public List<DepositOrderEntity> listIncludePackageProductByCodest(List<String> codes) {
        return depositOrderManager.listIncludePackageProductByCodest(codes);
    }
}
