package com.liu.springboot;

import com.liu.springboot.mapper.CustomerMapper;
import com.liu.springboot.pojo.Customer;
import com.liu.springboot.pojo.Pet;
import com.liu.springboot.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTest01ApplicationTests {

    @Autowired
    private Student student;
    @Autowired
    private Pet pet;


    @Autowired
    ApplicationContext ioc;

    @Autowired
    CustomerMapper customerMapper;

    @Test
    public void contextContains() {
//      boolean b = ioc.containsBean("pet");
//        System.out.println(b);
        System.out.println(pet);
    }

    @Test
    public void contextLoads() {
        System.out.println(student);
    }


    @Test
    public void testMybatis() {
        Customer customerOrder = customerMapper.selectCustomerOrder(1);
        System.out.println(customerOrder);
    }

}
