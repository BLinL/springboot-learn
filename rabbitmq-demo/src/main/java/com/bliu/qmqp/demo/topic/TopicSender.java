package com.bliu.qmqp.demo.topic;

import com.bliu.qmqp.demo.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        System.out.println("send topic message");
        User user = new User();
        user.setName("zhangsan");
        user.setPass("123");
        rabbitTemplate.convertAndSend("topicExchange", "topic.1", user);
    }
}
