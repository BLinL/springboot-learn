package com.bliu.qmqp.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {

    @Bean
    public Queue queue1(){
        return new Queue("queue1");
    }

    @Bean
    public Queue queue2(){
        return new Queue("queue2");
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    /*
     * 将exchange 和 queue 绑定在一起
     */
    @Bean
    Binding bindingExchangeMessages(Queue queue1, TopicExchange topicExchange){
        return BindingBuilder.bind(queue1).to(topicExchange).with("topic.#");
    }

    @Bean
    Binding bindingExchangeMessage(Queue queue2, TopicExchange topicExchange){
        return BindingBuilder.bind(queue2).to(topicExchange).with("topic.message");
    }
}
