package com.tdt.wheel.minimybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * description: MyMapperProxy
 *
 * @date: 2020年11月13日 18:23
 * @author: qinrenchuan
 */
public class MyMapperProxy implements InvocationHandler {

    private MySqlSession sqlSession;

    public MyMapperProxy() {
    }

    public MyMapperProxy(MySqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mapperClass = method.getDeclaringClass().getName();
        if (StudentMapperXml.namespace.equals(mapperClass)) {
            String methodName = method.getName();
            String methodSql = StudentMapperXml.getMethodSql(methodName);

            // sql格式化
            String formattedSql = String.format(methodSql, String.valueOf(args[0]));
            Object o = sqlSession.selectOne(formattedSql);
            System.out.println("sssssss" + o);
            return o;
        }

        return null;
    }
}
