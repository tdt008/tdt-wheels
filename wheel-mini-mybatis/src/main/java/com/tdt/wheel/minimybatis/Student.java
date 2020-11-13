package com.tdt.wheel.minimybatis;

/**
 * description: Student
 *
 * @date: 2020年11月13日 18:04
 * @author: qinrenchuan
 */
public class Student {
    private Integer id;
    private Integer sex;
    private String name;
    private Integer age;
    private String address;


    public Integer getId() {
        return id;
    }

    public Student setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public Student setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Student setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Student setAddress(String address) {
        this.address = address;
        return this;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Student{");
        sb.append("id=").append(id);
        sb.append(", sex=").append(sex);
        sb.append(", name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", address='").append(address).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
