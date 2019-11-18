package com.shizir.serial;

import java.io.Serializable;

public class Person2 implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;

    public String name;
    public Integer age;
    public String birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
