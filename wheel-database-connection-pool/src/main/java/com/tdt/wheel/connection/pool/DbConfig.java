package com.tdt.wheel.connection.pool;

/**
 * description: 很多数据库都需要xml或者properties配置。
 *              为了简化解析过程此处直接使用常量类定义
 *
 * @date: 2020年11月14日 15:27
 * @author: qinrenchuan
 */
public class DbConfig {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/testtest?characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
    public static final String JDBC_USERNAME = "root";
    public static final String JDBC_PASSWORD = "123456";

    // 数据库连接池初始化大小
    public static final int INIT_CONNECTION_COUNT = 10;
    // 连接池不足的时候增长的步值
    public static final int CONNECTION_COUNT_INCREASE_STEP = 2;
    // 数据库连接池最大数
    public static final int MAX_CONNECTION_COUNT = 10;


    private DbConfig() {
    }
}
