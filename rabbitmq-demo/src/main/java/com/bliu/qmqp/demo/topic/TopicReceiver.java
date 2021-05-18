package com.bliu.qmqp.demo.topic;

import com.bliu.qmqp.demo.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue1")
public class TopicReceiver {

    @RabbitHandler
    public void process(User message){
        System.out.println("Topic Receiver1  : " + message);
    }
}
