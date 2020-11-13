package com.tdt.wheel.minimybatis;

/**
 * description: Bootstrap
 *
 * @date: 2020年11月13日 18:29
 * @author: qinrenchuan
 */
public class Bootstrap {
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        MySqlSession sqlSession = new MyDefaultSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Student student = studentMapper.findStudentById(11);
        System.out.println(student);
    }
}
