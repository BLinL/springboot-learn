package com.liu.springboot.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Customer {
    private Integer id;
    private String name;
    private String gender;

    private List<Order> orders;
}
