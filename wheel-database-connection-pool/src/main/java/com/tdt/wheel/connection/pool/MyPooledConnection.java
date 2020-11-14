package com.tdt.wheel.connection.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * description: 对JDBC Connection进行封装而已，但是需要注意isBusy的这个标示。对管道的关闭，实际上只是标示的改变而已！
 *
 * @date: 2020年11月14日 15:47
 * @author: qinrenchuan
 */
public class MyPooledConnection {

    private Connection connection;
    private boolean isBusy = false;

    public MyPooledConnection(Connection connection, boolean isBusy) {
        this.connection = connection;
        this.isBusy = isBusy;
    }

    public void close() {
        this.isBusy = false;
    }

    public ResultSet query(String sql) {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public Connection getConnection() {
        return connection;
    }

    public MyPooledConnection setConnection(Connection connection) {
        this.connection = connection;
        return this;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public MyPooledConnection setBusy(boolean busy) {
        isBusy = busy;
        return this;
    }
}
