package com.bl.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class initApp implements CommandLineRunner {
    @Autowired
    KafkaMessageSender kafkaMessageSender;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("start send message...");
        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            kafkaMessageSender.sendMessage("" + i);
        }
    }
}
