package com.liu.springboot.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Map;

/**
 * @ConfigurationProperties(prefix = "student")默认从spring配置文件读取内容
 * @PropertySource可以设定配置文件 value={配置文件路径}
 * @Validated可以和@ConfigurationProperties一起使用对参数进行校验
 */


@Component
@PropertySource(value = {"classpath:/student.properties"})
@ConfigurationProperties(prefix = "student")
public class Student {

    private String firstName;
    private String password;
    private Pet pet;
    private Map<String, String> map;
    private List<String> list;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", password='" + password + '\'' +
                ", pet=" + pet +
                ", map=" + map +
                ", list=" + list +
                '}';
    }
}
