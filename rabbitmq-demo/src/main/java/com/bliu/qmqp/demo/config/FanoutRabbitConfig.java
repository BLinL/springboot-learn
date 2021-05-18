package com.bliu.qmqp.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfig {

    @Bean
    public Queue queueA(){
        return new Queue("fanout.A");
    }

    @Bean
    public Queue queueB(){
        return new Queue("fanout.B");
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }


    @Bean
    Binding bindingExchangeA(Queue queueA, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }
    @Bean
    Binding bindingExchangeB(Queue queueB, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }

//    @Bean
//    public Declarables fanoutBindings() {
//        Queue fanoutQueue1 = new Queue("fanout.queue1", false);
//        Queue fanoutQueue2 = new Queue("fanout.queue2", false);
//        FanoutExchange fanoutExchange = new FanoutExchange("fanout.exchange");
//
//        return new Declarables(
//                fanoutQueue1,
//                fanoutQueue2,
//                fanoutExchange,
//                bind(fanoutQueue1).to(fanoutExchange),
//                BindingBuilder.bind(fanoutQueue2).to(fanoutExchange));
//    }
}
