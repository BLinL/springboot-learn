package com.liu.springboot.controller;

import com.liu.springboot.mapper.CustomerMapper;
import com.liu.springboot.pojo.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class Hellowcontroller {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CustomerMapper customerMapper;

    @RequestMapping("hello")
    public String hello() {
        return "hello springboot!";
    }

    @RequestMapping("{id}")
    public Customer getCustomer(@PathVariable int id) {
        logger.debug("查询id为: " + id + "的用户订单");
        Customer customer = customerMapper.selectCustomerOrder(id);
        return customer;
    }
}
