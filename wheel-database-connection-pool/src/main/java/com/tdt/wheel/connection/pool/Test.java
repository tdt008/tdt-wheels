package com.tdt.wheel.connection.pool;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * description: Test
 *
 * @date: 2020年11月14日 16:18
 * @author: qinrenchuan
 */
public class Test {
    public static void main(String[] args) {
        IMyPool myPool = MyPoolFactory.getInstance();

        for (int i = 0; i < 10; i++) {
            MyPooledConnection myPooledConnection = myPool.getMyPooledConnection();
            ResultSet resultSet = myPooledConnection.query("SELECT * FROM student");

            try {
                while (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getInt("id"));
                    student.setSex(resultSet.getInt("sex"));
                    student.setName(resultSet.getString("name"));
                    student.setAddress(resultSet.getString("address"));
                    student.setAge(resultSet.getInt("age"));
                    System.out.println(student);

                    System.out.println("======================================================");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                myPooledConnection.close();
            }

        }
    }
}
