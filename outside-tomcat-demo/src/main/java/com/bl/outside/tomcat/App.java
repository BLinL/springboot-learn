package com.bl.outside.tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class App extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("start app...");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里一定要指向原先用main方法执行的Application启动类
        return builder.sources(App.class);
    }

    @GetMapping("/")
    public String hi() {
        return "hi";
    }
}