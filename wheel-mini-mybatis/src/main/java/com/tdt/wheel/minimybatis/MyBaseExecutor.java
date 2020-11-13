package com.tdt.wheel.minimybatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * description: MyBaseExecutor
 *
 * @date: 2020年11月13日 17:59
 * @author: qinrenchuan
 */
public class MyBaseExecutor implements MyExecutor {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/testtest?characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    @Override
    public <T> T query(String statement) {
        // JDBC完成DB操作获取结果
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
            String sql = statement;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            Student student = null;
            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setSex(resultSet.getInt("sex"));
                student.setName(resultSet.getString("name"));
                student.setAddress(resultSet.getString("address"));
                student.setAge(resultSet.getInt("age"));
            }

            return (T)student;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}
