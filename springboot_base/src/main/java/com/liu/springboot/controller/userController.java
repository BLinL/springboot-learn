package com.liu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Map;

@Controller
public class userController {


    @RequestMapping("user")
    public String user(Map<String, String> map) {
        map.put("hello", "hello");
        return "user";
    }
}
