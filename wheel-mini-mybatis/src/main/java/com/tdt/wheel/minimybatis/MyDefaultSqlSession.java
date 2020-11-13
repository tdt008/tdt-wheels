package com.tdt.wheel.minimybatis;


import java.lang.reflect.Proxy;

/**
 * description: MyDefaultSqlSession
 *
 * @date: 2020年11月13日 18:20
 * @author: qinrenchuan
 */
public class MyDefaultSqlSession implements MySqlSession {

    private MyExecutor executor = new MyBaseExecutor();

    @Override
    public <T> T selectOne(String sql) {
        return executor.query(sql);
    }

    @Override
    public <T> T getMapper(Class<T> interfaces) {
        return (T) Proxy.newProxyInstance(
                interfaces.getClassLoader(),
                new Class[]{interfaces},
                new MyMapperProxy(this));
    }
}
