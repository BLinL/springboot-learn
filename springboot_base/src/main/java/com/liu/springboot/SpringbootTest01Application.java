package com.liu.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = "classpath:/application-dao.xml")
@MapperScan(basePackages = "com.liu.springboot.mapper")
public class SpringbootTest01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTest01Application.class, args);
    }


}
