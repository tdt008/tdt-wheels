package com.tdt.wheel.connection.pool;

/**
 * description: 对连接池的一个基本管理API接口
 *              要可以得到数据库的操作管道/要可以创建数据库管道
 *
 * @date: 2020年11月14日 15:51
 * @author: qinrenchuan
 */
public interface IMyPool {
    MyPooledConnection getMyPooledConnection();
    void createMyPooledConenction(int count);
}
