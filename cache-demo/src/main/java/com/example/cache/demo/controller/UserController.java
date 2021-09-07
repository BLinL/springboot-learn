package com.example.cache.demo.controller;

import com.example.cache.demo.pojo.User;
import com.example.cache.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private CacheManager cacheManager;

    @GetMapping("api/user/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username){

//        System.out.println(cacheManager.getClass());

        User user = null;
        try {
            user = userService.getUserByName(username);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("api/user")
    public String update(User user) {
        System.out.println("request params" + user);
        try {
            userService.UpdateUserByUsername(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "{status: ok}";
    }

    @PostMapping("api/user")
    public User add(User user) {
        User resp = null;
        System.out.println("request params" + user);
        try {
            resp = userService.addUser(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return resp;
    }
}
