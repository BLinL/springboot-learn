package com.bl.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)
public class KafkaMessageSender {

    @Autowired
    private Source source;

    public void sendMessage(String message) {
        source.output().send(MessageBuilder.withPayload(message).build());
    }

}
