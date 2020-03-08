package com.liu.springboot.mapper;

import com.liu.springboot.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMapper {
    Customer selectCustomerOrder(int id);
}
