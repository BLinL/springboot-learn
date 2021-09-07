package com.example.demo;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
//        userRepository.deleteAll();
//        userRepository.save(new User("张三",12));
//        userRepository.save(new User("李四",22));
//
//        userRepository.findAll().forEach(user -> {
//            System.out.println(user);
//        });
    }
}
