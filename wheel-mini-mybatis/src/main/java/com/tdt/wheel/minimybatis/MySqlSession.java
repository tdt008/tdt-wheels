package com.tdt.wheel.minimybatis;

/**
 * description: MySqlSession
 *
 * @date: 2020年11月13日 18:18
 * @author: qinrenchuan
 */
public interface MySqlSession {
    <T> T selectOne(String val);
    <T> T getMapper(Class<T> val);
}
