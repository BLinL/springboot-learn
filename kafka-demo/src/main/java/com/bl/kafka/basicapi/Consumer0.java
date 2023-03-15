package com.bl.kafka.basicapi;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class Consumer0 {

    public static void main(String[] args) {

        //1、添加配置文件
        Properties props = new Properties();
        //指定kafka服务器
        props.put("bootstrap.servers", "124.221.94.153:9092,124.221.94.153:9093,124.221.94.153:9094");
        //消费组
        props.put("group.id", "testGroup");
        //以下两行代码 ---消费者自动提交offset值
        props.put("enable.auto.commit", "true");
        //自动提交的周期
        props.put("auto.commit.interval.ms",  "1000");
        //kafka   key 和value的反序列化
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        //2、实例消费者对象
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(props);

        //3、设置读取的topic
        kafkaConsumer.subscribe(Arrays.asList("test"));

        //循环遍历
        while (true){
            //4、拉取数据，并输出
            //获取到所有的数据
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(1000);
            //遍历所有数据，获得到一条
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                //一条数据
                System.out.println("当前数据："+consumerRecord.value()+", 偏移量:offset:"+consumerRecord.offset());
            }

        }
    }
}


