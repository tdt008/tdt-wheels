package com.tdt.wheel.transaction;

import org.apache.commons.dbcp2.BasicDataSource;


/**
 * description: Test
 *
 * @date: 2020年11月14日 17:02
 * @author: qinrenchuan
 */
public class Test {
    public static void main(String[] args) {
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String JDBC_URL = "jdbc:mysql://localhost:3306/testtest?characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
        String JDBC_USERNAME = "root";
        String JDBC_PASSWORD = "123456";

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(JDBC_DRIVER);
        dataSource.setUsername(JDBC_USERNAME);
        dataSource.setPassword(JDBC_PASSWORD);
        dataSource.setUrl(JDBC_URL);

        UserService userService = new UserService(dataSource);

        for (int i = 0; i < 10; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    userService.action();
                }
            }).start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
