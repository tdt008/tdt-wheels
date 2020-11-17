package com.tdt.wheel.rpc.service;

/**
 * description: ProductServiceImpl
 *
 * @date: 2020年11月17日 20:04
 * @author: qinrenchuan
 */
public class ProductServiceImpl implements IProductService {

    @Override
    public Product getById(long id) {
        Product product = new Product()
                .setId(id)
                .setName("water " + id + "ml")
                .setPrice(109D);
        return product;
    }
}
