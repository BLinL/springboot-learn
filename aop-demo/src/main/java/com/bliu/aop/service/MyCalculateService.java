package com.bliu.aop.service;

import com.bliu.aop.AllNeedLog;
import com.bliu.aop.NeedLog;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * spring会自动在JDK动态代理(实现接口)和CGLIB之间转换
 */
@AllNeedLog
@Service
public class MyCalculateService implements AddService, DevideService, Welcome{
    @Override
    public long add(int a, int b) {
        return a + b;
    }

    @Override
    public double devide(String a, String b) {
        BigDecimal result = new BigDecimal(a).divide(new BigDecimal(b), 2, RoundingMode.HALF_UP);
        return result.doubleValue();
    }

    @Override
    public void hi() {
        System.out.println("Hi! ");
    }
}
