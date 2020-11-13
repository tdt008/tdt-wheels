package com.tdt.wheel.minimybatis;

/**
 * description:
 *  在MyBatis中，比如说select有多种形式，比如selectOne,selectList，那么其实到最后，
 *  还是向JDBC发出一个SQL而已。对于执行器而言，其实对于查询，提供一个query接口就可以了。
 *  这里，为了简便，直接执行已经处理好的SQL语句（动态SQL以及输入类型，这不是迷你版MyBatis关心的）。
 *  另外执行器的实现类MyBaseExecutor其实就是一段JDBC的操作代码。
 *
 * @date: 2020年11月13日 16:55
 * @author: qinrenchuan
 */
public interface MyExecutor {

    <T> T query(String statement);
}
