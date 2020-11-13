package com.tdt.wheel.minimybatis;

/**
 * description: StudentMapper
 *
 * @date: 2020年11月13日 18:10
 * @author: qinrenchuan
 */
public interface StudentMapper {
    Student findStudentById(int id);
    void insertStudent(Student student);
}
