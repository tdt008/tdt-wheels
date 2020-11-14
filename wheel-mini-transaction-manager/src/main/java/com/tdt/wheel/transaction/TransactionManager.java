package com.tdt.wheel.transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * description: TransactionManager
 *
 * @date: 2020年11月14日 16:44
 * @author: qinrenchuan
 */
public class TransactionManager {
    private DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return SingleThreadConnectionHolder.getConnection(dataSource);
    }

    /**
     * 开启事物
     */
    public void start() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
    }

    /**
     * 回滚事物
     */
    public void rollback() {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事物
     */
    public void commit() throws SQLException {
        Connection connection = getConnection();
        connection.commit();
    }

    /**
     * 关闭事物
     */
    public void close() throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        connection.close();
    }
}
