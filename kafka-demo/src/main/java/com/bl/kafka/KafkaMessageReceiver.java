package com.bl.kafka;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

/**
 * 使用spring data stream api
 */
@Component
@EnableBinding(Sink.class)
public class KafkaMessageReceiver {

    @StreamListener(Sink.INPUT)
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }

}
