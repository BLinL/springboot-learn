package com.bliu.qmqp.demo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * producer
 * 生产者
 */
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    void send(){
        String context = "hello " + new Date();
        System.out.println("sender " + context);

        /*
        routing key
        message
         */
        this.amqpTemplate.convertAndSend("directExchange","directRouterKey", context);
    }
}

