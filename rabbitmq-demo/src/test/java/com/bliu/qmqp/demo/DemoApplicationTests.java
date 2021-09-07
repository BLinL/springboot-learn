package com.bliu.qmqp.demo;

import com.bliu.qmqp.demo.fanout.FanoutSender;
import com.bliu.qmqp.demo.topic.TopicSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private HelloSender helloSender;

    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private TopicSender topicSender;

    @Test
    void testDirectExchange() {
       helloSender.send();
//       fanoutSender.send();
    }

    @Test
    void topic() {

//        topicSender.send();
    }

}
