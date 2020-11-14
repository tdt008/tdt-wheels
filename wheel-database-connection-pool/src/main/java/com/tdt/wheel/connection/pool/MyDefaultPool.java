package com.tdt.wheel.connection.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 * description:
 *  连接池： 加载外部配置进行初始化
 *          加载数据库驱动
 *          要考虑使用什么集合对连接管道进行存储
 *
 * @date: 2020年11月14日 15:53
 * @author: qinrenchuan
 */
public class MyDefaultPool implements IMyPool {
    /** MyDefaultPool持有一个管道集合，基于多线程的考虑，这里使用了Vector */
    private Vector<MyPooledConnection> myPooledConnections = new Vector<>();
    private static String JDBC_DRIVER;
    private static String JDBC_URL;
    private static String JDBC_USERNAME;
    private static String JDBC_PASSWORD;
    private static int INIT_CONNECTION_COUNT;
    private static int CONNECTION_COUNT_INCREASE_STEP;
    private static int MAX_CONNECTION_COUNT;

    public MyDefaultPool() {
        // 初始化数据库连接池配置
        init();

        // 加载驱动
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 初始化数据库连接池管道
        createMyPooledConenction(INIT_CONNECTION_COUNT);
    }

    private void init() {
        JDBC_DRIVER = DbConfig.JDBC_DRIVER;
        JDBC_URL = DbConfig.JDBC_URL;
        JDBC_USERNAME = DbConfig.JDBC_USERNAME;
        JDBC_PASSWORD = DbConfig.JDBC_PASSWORD;
        INIT_CONNECTION_COUNT = DbConfig.INIT_CONNECTION_COUNT;
        CONNECTION_COUNT_INCREASE_STEP = DbConfig.CONNECTION_COUNT_INCREASE_STEP;
        MAX_CONNECTION_COUNT = DbConfig.MAX_CONNECTION_COUNT;
    }

    @Override
    public MyPooledConnection getMyPooledConnection() {
        if (myPooledConnections.size() < 1) {
            throw new RuntimeException("连接池初始化错误！");
        }

        MyPooledConnection myPooledConnection = null;

        try {
            myPooledConnection = getRealConnectionFromPool();
            while (myPooledConnection == null) {
                createMyPooledConenction(CONNECTION_COUNT_INCREASE_STEP);
                myPooledConnection = getRealConnectionFromPool();
                return myPooledConnection;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myPooledConnection;
    }

    /**
     * 这里需要注意的是：如果得不到操作管道，需要去创建管道！
     * 这里使用了synchronized，就是为了避免多线程下产生问题
     */
    private synchronized MyPooledConnection getRealConnectionFromPool() throws SQLException {
        for (MyPooledConnection myPooledConnection : myPooledConnections) {
            if (!myPooledConnection.isBusy()) {
                // 要知道Connection是有超时机制的，如果我们得到的管道的Connection已经超时了怎么办呢？
                if (myPooledConnection.getConnection().isValid(3000)) {
                    // 得到管道后，一定注意isBusy的设置。
                    myPooledConnection.setBusy(true);
                    return myPooledConnection;
                } else {
                    Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
                    myPooledConnection.setConnection(connection);
                    myPooledConnection.setBusy(true);
                    return myPooledConnection;
                }
            }
        }

        return null;
    }

    @Override
    public void createMyPooledConenction(int count) {
        // 数据库连接池在创建管道时，应该去看一下是否达到上限，如果没有，则可以创建
        if (myPooledConnections.size() > MAX_CONNECTION_COUNT
                || myPooledConnections.size() + count > MAX_CONNECTION_COUNT) {
            throw new RuntimeException("连接池已满！");
        }

        for (int i = 0; i < count; i++) {
            try {
                Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
                // 不仅仅要创建出来，还要标示每一个管道的isBusy标志
                MyPooledConnection myPooledConnection = new MyPooledConnection(connection, false);
                myPooledConnections.add(myPooledConnection);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
