package com.example.cache.demo.controller;

import com.example.cache.demo.pojo.User;
import com.example.cache.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private CacheManager cacheManager;

    @GetMapping("api/user/{username}")
    public User getUser(@PathVariable String username){

//        System.out.println(cacheManager.getClass());

        User user = null;
        try {
            user = userService.getUserByName(username);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }
}
