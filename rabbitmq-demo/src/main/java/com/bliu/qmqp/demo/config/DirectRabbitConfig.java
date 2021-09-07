package com.bliu.qmqp.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DirectRabbitConfig {

    @Bean
    public Queue directQ(){
        return new Queue("direct");
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    /**
     * 路由键的意义在于从发送给交换机的众多消息中选择出某些消息，将其路由给绑定的队列。
     * @param directQ queue
     * @param directExchange
     * @return Binding
     */
    @Bean
    public Binding bindingDirect(Queue directQ, DirectExchange directExchange){
        return BindingBuilder.bind(directQ).to(directExchange).with("directRouterKey");
    }
}
