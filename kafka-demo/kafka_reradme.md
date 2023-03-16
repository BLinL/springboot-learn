创建topic
```bash
/kafka-topics.sh --bootstrap-server localhost:9092 localhost:9093 localhost:9094 --create --replication-factor 3 --partitions 3 --topic test
```
查看topic
```bash
./kafka-topics.sh --bootstrap-server localhost:9092 localhost:9093 localhost:9094 --list
```
查看topic详情
```bash
./kafka-topics.sh --bootstrap-server localhost:9092 localhost:9093 localhost:9094 --describe --topic test
```

生产
```bash
./kafka-console-producer.sh --broker-list localhost:9092 localhost:9093 localhost:9094 --topic test
```

消费
```bash
./kafka-console-consumer.sh --bootstrap-server localhost:9092 localhost:9093 localhost:9094 --topic test --from-beginning 
```


指定分区：直接发到指定分区
指定key：根据key自动路由发到指定分区
都不指定：则使用kafka默认的分区策略，轮询选出一个 patition
producer -->[key,value] 发送--> 自动路由--> [kafka分区]

### java api

添加依赖
```xml
<dependency>
    <groupId>org.springframework.kafka</groupId>
    <artifactId>spring-kafka</artifactId>
</dependency>
```


## 事务提交
```java
@GetMapping("/kafka/transaction")
public void sendMessage7(){
    // 声明事务：后面报错消息不会发出去
    kafkaTemplate.executeInTransaction(operations -> {
        operations.send("topic1","test executeInTransaction");
        throw new RuntimeException("fail");
    });
​
    // 不声明事务：后面报错但前面消息已经发送成功了
   kafkaTemplate.send("topic1","test executeInTransaction");
   throw new RuntimeException("fail");
}
```

## 指定主题分区和偏移
```java
@KafkaListener(id = "consumer1",groupId = "felix-group",topicPartitions = {
        @TopicPartition(topic = "topic1", partitions = { "0" }),
        @TopicPartition(topic = "topic2", partitions = "0", partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "8"))
})
public void onMessage2(ConsumerRecord<?, ?> record) {
    System.out.println("topic:"+record.topic()+"|partition:"+record.partition()+"|offset:"+record.offset()+"|value:"+record.value());
}
```

## 批量消费
```
# 设置批量消费
spring.kafka.listener.type=batch
# 批量消费每次最多消费多少条消息
spring.kafka.consumer.max-poll-records=50

@KafkaListener(id = "consumer2",groupId = "felix-group", topics = "topic1")
public void onMessage3(List<ConsumerRecord<?, ?>> records) {
    System.out.println(">>>批量消费一次，records.size()="+records.size());
    for (ConsumerRecord<?, ?> record : records) {
        System.out.println(record.value());
    }
}
```


## 处理异常
```java
// 新建一个异常处理器，用@Bean注入
@Bean
public ConsumerAwareListenerErrorHandler consumerAwareErrorHandler() {
    return (message, exception, consumer) -> {
        System.out.println("消费异常："+message.getPayload());
        return null;
    };
}
​
// 将这个异常处理器的BeanName放到@KafkaListener注解的errorHandler属性里面
@KafkaListener(topics = {"topic1"},errorHandler = "consumerAwareErrorHandler")
public void onMessage4(ConsumerRecord<?, ?> record) throws Exception {
    throw new Exception("简单消费-模拟异常");
}
​
// 批量消费也一样，异常处理器的message.getPayload()也可以拿到各条消息的信息
@KafkaListener(topics = "topic1",errorHandler="consumerAwareErrorHandler")
public void onMessage5(List<ConsumerRecord<?, ?>> records) throws Exception {
    System.out.println("批量消费一次...");
    throw new Exception("批量消费-模拟异常");
}
```

## 消息过滤：
```java

@Component
public class KafkaConsumer {
    @Autowired
    ConsumerFactory consumerFactory;
​
    // 消息过滤器
    @Bean
    public ConcurrentKafkaListenerContainerFactory filterContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);
        // 被过滤的消息将被丢弃
        factory.setAckDiscarded(true);
        // 消息过滤策略
        factory.setRecordFilterStrategy(consumerRecord -> {
            if (Integer.parseInt(consumerRecord.value().toString()) % 2 == 0) {
                return false;
            }
            //返回true消息则被过滤
            return true;
        });
        return factory;
    }
​
    // 消息过滤监听
    @KafkaListener(topics = {"topic1"},containerFactory = "filterContainerFactory")
    public void onMessage6(ConsumerRecord<?, ?> record) {
        System.out.println(record.value());
    }
}
```

## 消息转发
```java

@KafkaListener(topics = {"topic1"})
@SendTo("topic2")
public String onMessage7(ConsumerRecord<?, ?> record) {
    return record.value()+"-forward message";
}
```

## 定时启动关闭监听器
```java
@EnableScheduling
@Component
public class CronTimer {
​
    /**
     * @KafkaListener注解所标注的方法并不会在IOC容器中被注册为Bean，
     * 而是会被注册在KafkaListenerEndpointRegistry中，
     * 而KafkaListenerEndpointRegistry在SpringIOC中已经被注册为Bean
     **/
    @Autowired
    private KafkaListenerEndpointRegistry registry;
​
    @Autowired
    private ConsumerFactory consumerFactory;
​
    // 监听器容器工厂(设置禁止KafkaListener自启动)
    @Bean
    public ConcurrentKafkaListenerContainerFactory delayContainerFactory() {
        ConcurrentKafkaListenerContainerFactory container = new ConcurrentKafkaListenerContainerFactory();
        container.setConsumerFactory(consumerFactory);
        //禁止KafkaListener自启动
        container.setAutoStartup(false);
        return container;
    }
​
    // 监听器
    @KafkaListener(id="timingConsumer",topics = "topic1",containerFactory = "delayContainerFactory")
    public void onMessage1(ConsumerRecord<?, ?> record){
        System.out.println("消费成功："+record.topic()+"-"+record.partition()+"-"+record.value());
    }
​
    // 定时启动监听器
    @Scheduled(cron = "0 42 11 * * ? ")
    public void startListener() {
        System.out.println("启动监听器...");
        // "timingConsumer"是@KafkaListener注解后面设置的监听器ID,标识这个监听器
        if (!registry.getListenerContainer("timingConsumer").isRunning()) {
            registry.getListenerContainer("timingConsumer").start();
        }
        //registry.getListenerContainer("timingConsumer").resume();
    }
​
    // 定时停止监听器
    @Scheduled(cron = "0 45 11 * * ? ")
    public void shutDownListener() {
        System.out.println("关闭监听器...");
        registry.getListenerContainer("timingConsumer").pause();
    }
}
```