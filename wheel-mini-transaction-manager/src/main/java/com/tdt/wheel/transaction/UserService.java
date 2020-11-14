package com.tdt.wheel.transaction;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * description: UserService
 *
 * @date: 2020年11月14日 16:58
 * @author: qinrenchuan
 */
public class UserService {
    private UserAccountDAO userAccountDAO;
    private UserOrderDAO userOrderDAO;
    private TransactionManager transactionManager;

    public UserService(DataSource dataSource) {
        this.userAccountDAO = new UserAccountDAO(dataSource);
        this.userOrderDAO = new UserOrderDAO(dataSource);
        this.transactionManager = new TransactionManager(dataSource);
    }

    public void action() {
        try {
            transactionManager.start();

            userAccountDAO.buy();
            userOrderDAO.order();

            transactionManager.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback();
        } finally {
            try {
                transactionManager.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
