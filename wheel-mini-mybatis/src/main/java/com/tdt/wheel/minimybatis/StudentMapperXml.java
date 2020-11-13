package com.tdt.wheel.minimybatis;

import java.util.HashMap;
import java.util.Map;

/**
 * description: 为了不牵涉到XML的解析过程，直接提供已经处理完毕的结果。其实就是namespace/statementID/SQL的存储、映射。
 *
 * @date: 2020年11月13日 18:14
 * @author: qinrenchuan
 */
public class StudentMapperXml {
    public static final String namespace = "com.tdt.wheel.minimybatis.StudentMapper";

    private static Map<String, String> methodSqlMap = new HashMap<>();

    static {
        methodSqlMap.put("findStudentById", "select * from student where id=%s");
    }

    public static String getMethodSql(String method) {
        return methodSqlMap.get(method);
    }
}
