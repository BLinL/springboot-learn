package com.bliu.aop;

import com.bliu.aop.service.AddService;
import com.bliu.aop.service.DevideService;
import com.bliu.aop.service.MyCalculateService;
import com.bliu.aop.service.Welcome;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class AopApplicationTest {
    @Autowired
    DevideService devideService;
    @Autowired
    AddService addService;
    @Autowired
    Welcome welcome;

    @Test
    public void testCalculateService() {
       assertEquals(2L, addService.add(1,1));
//       assertEquals(3.333d, devideService.devide("10","0"));
    }

    @Test
    public void testWelcomeService() {
        ((MyCalculateService)welcome).hi();
    }
}
