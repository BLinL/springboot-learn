package com.liu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("user")
public class loginController {

    @PostMapping("logon")
    public String logon(@RequestParam("username") String username,
                        @RequestParam("password") String inputPassword) {
        System.out.println(username);
        System.out.println(inputPassword);
        return "test";
    }
}
