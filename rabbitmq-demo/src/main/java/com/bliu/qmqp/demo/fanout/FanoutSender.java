package com.bliu.qmqp.demo.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 将消息发送给 fanout 类型的exchange 路由键将会被忽略，并将消息传递到所有绑定的队列
     * When we send a message to a fanout exchange,
     * the routing key is ignored, and the message is passed to all bound queues.
     */
    public void send(){
        //params: [ exchange, routingKey, message ]
        rabbitTemplate.convertAndSend("fanoutExchange","", "hello rabbitMq!");
    }
}
