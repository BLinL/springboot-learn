package com.bliu.qmqp.demo.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue2")
public class TopicReceiver1 {

    @RabbitHandler
    public void process(String message){
        System.out.println("Topic Receiver2  : " + message);
    }
}
