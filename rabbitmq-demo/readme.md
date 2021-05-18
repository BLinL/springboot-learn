# RabbitMq
@[TOC](rabbitMq初使用)

> RabbitMQ 是一个由 Erlang 语言开发的 AMQP 的开源实现。AMQP ：Advanced Message Queue，高级消息队列协议。它是应用层协议的一个开放标准，为面向消息的中间件设计，基于此协议的客户端与消息中间件可传递消息，并不受产品、开发语言等条件的限制。


![在这里插入图片描述](https://img-blog.csdnimg.cn/20210518092550279.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xza19zaGFo,size_16,color_FFFFFF,t_70#pic_center)

## 优点： 

不限语言

有管理界面

支持多种协议

可靠

## 理解rabbit的基本概念

1. 交换机 excange  用来把数据路由到队列
2. 队列 queue  存储消息
3. 绑定 binding  将队列和交换机绑定
4. 消息 message 
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210518092600362.png)

交换机类型

- fanout 不处理路由键，你只需要简单的将队列绑定到exchange上， routigngKey不需要

- direct 根据键**完全匹配** 的单播模式

- topic 可以使用* 和 #模糊匹配
 	路由器跟据key进行匹配， 将消息发到匹配到的queue里面
- header


消息发送到交换机，交换机跟据routingKey属性 将消息发到 对应的队列

![JCccc-RabbitMq](https://img-blog.csdnimg.cn/20190903141227300.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM1Mzg3OTQw,size_16,color_FFFFFF,t_70)

## springboot 集成 rabbitMq

1. 加入依赖

```xml
 <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

2. application.yml

   ```yaml
   spring:
     application:
       name: Springboot-mq-demo
     rabbitmq:
       host: 192.168.10.10
       port: 5672 #default
       username: user
       password: pass
   ```

3. javaConfig配置 

   ```java
   package com.bliu.qmqp.demo.config;
   
   import org.springframework.amqp.core.Binding;
   import org.springframework.amqp.core.BindingBuilder;
   import org.springframework.amqp.core.FanoutExchange;
   import org.springframework.amqp.core.Queue;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;
   
   @Configuration
   public class RabbitConfig {
   
       @Bean
       public Queue queueA(){
           return new Queue("fanout.A");
       }
   
       @Bean
       public Queue queueB(){
           return new Queue("fanout.B");
       }
   
       /**
       *fanout交换机
       */
       @Bean
       FanoutExchange fanoutExchange(){
           return new FanoutExchange("fanoutExchange");
       }
   
     	/**
       *绑定queue
       */
       @Bean
       Binding bindingExchangeA(Queue queueA, FanoutExchange fanoutExchange){
           return BindingBuilder.bind(queueA).to(fanoutExchange);
       }
       @Bean
       Binding bindingExchangeB(Queue queueB, FanoutExchange fanoutExchange){
           return BindingBuilder.bind(queueB).to(fanoutExchange);
       }
   
       //另一种方式
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
   
   ```
如你所见 fanout类型的exchange 只需要使用Binding 和 queue 绑在一起即可
**``return BindingBuilder.bind(queueA).to(fanoutExchange);``**

4. sender  和 receiver

      ```java
      package com.bliu.qmqp.demo.fanout;
      
      import org.springframework.amqp.core.AmqpTemplate;
      import org.springframework.beans.factory.annotation.Autowired;
      import org.springframework.stereotype.Component;
      
      @Component
      public class FanoutSender {
          //spring 帮我们封装的发送工具
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
      
      ```

      ```java
      package com.bliu.qmqp.demo.fanout;
      
      import org.springframework.amqp.rabbit.annotation.RabbitHandler;
      import org.springframework.amqp.rabbit.annotation.RabbitListener;
      import org.springframework.amqp.rabbit.annotation.RabbitListeners;
      import org.springframework.stereotype.Component;
      
      @Component
      @RabbitListeners({
              @RabbitListener(queues = "fanout.A"),
              @RabbitListener(queues = "fanout.B")
      })//可以写一个@RabbitListener
      public class FanoutReceiverA {
      
          //处理接收到的消息
          @RabbitHandler
          public void process(String message){
              System.out.println("fanout ReceiveA re: "+ message);
          }
      }
      
      ```

      ### convertAndSend

      转换并发送消息

      [更多](https://docs.spring.io/spring-amqp/docs/current/api/org/springframework/amqp/rabbit/core/RabbitTemplate.html#convertAndSend-java.lang.String-java.lang.String-java.lang.Object-)

5. 测试

  ```java
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
          private FanoutSender fanoutSender;
      
          @Test
          void contextLoads() {
              fanoutSender.send();
          }
      }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210517180354541.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2xza19zaGFo,size_16,color_FFFFFF,t_70)

参考连接：
> https://www.baeldung.com/rabbitmq-spring-amqp
https://blog.csdn.net/qq_35387940/article/details/100514134
