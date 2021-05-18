package com.bliu.qmqp.demo.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Component;

@Component
@RabbitListeners({
        @RabbitListener(queues = "fanout.A"),
        @RabbitListener(queues = "fanout.B")
})
public class FanoutReceiverA {

    @RabbitHandler
    public void process(String message){
        System.out.println("fanout ReceiveA re: "+ message);
    }
}
