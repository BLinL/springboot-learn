package com.example.cache.demo;

import com.example.cache.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.io.Serializable;

@ActiveProfiles("dev")
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    @Test
    void testString() {
        stringRedisTemplate.opsForValue().set("user", "zhangsan");
        System.out.println(stringRedisTemplate.opsForValue().get("user"));
    }

    @Test
    void testSerializable() {
        User user = new User("zhangsan", "123456");
        serializableRedisTemplate.opsForValue().set("current", user);

        System.out.println((User)serializableRedisTemplate.opsForValue().get("current"));
    }

}
