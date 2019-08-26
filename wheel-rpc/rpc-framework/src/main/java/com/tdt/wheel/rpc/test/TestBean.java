package com.tdt.wheel.rpc.test;

/**
 * @author qrc
 * @description TODO
 * @date 2019/8/26
 */
public class TestBean {
    private String name;
    private Integer age;

    public TestBean(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public TestBean setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public TestBean setAge(Integer age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TestBean{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
