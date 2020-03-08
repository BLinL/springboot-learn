package com.liu.springboot.pojo;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class Order {
    private Integer id;
    private String orderNO;
    private String productName;
    private Integer custId;
    private LocalDateTime date;

}
