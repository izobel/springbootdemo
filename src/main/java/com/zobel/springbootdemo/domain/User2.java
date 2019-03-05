package com.zobel.springbootdemo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class User2 {
    private String name;
    @JsonIgnore
    private String password;
    private String age;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",locale="zh",timezone = "GMT+8")
    private Date date;
    public User2(){

    }

    public User2(String name, String password, String age, Date date){
        this.age = age;
        this.password = password;
        this.name = name;
        this.date = date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
