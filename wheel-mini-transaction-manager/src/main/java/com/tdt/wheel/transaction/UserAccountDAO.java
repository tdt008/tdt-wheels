package com.tdt.wheel.transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * description: UserAccountDAO
 *
 * @date: 2020年11月14日 16:55
 * @author: qinrenchuan
 */
public class UserAccountDAO {
    private DataSource dataSource;

    public UserAccountDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void buy() throws SQLException {
        Connection connection = SingleThreadConnectionHolder.getConnection(dataSource);

        // TODO 执行业务逻辑

        System.out.println("当前用户购买线程:" + Thread.currentThread().getName()
                + ", 使用管道（hashCode）: " + connection.hashCode());
    }
}
