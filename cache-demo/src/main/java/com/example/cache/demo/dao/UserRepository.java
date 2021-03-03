package com.example.cache.demo.dao;

import com.example.cache.demo.pojo.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bliu
 * @date 2021-03-02 9:30
 */
@Repository
public class UserRepository {

    static final List<User> userList = new ArrayList<>();

    static {
        userList.add(new User("张三", "123"));
        userList.add(new User("里斯", "1234"));
        userList.add(new User("杰克", "12345"));
        userList.add(new User("布鲁斯", "123456"));
    }

    public User getUserByName(String username) throws InterruptedException {
        //test
        Thread.sleep(2000);
        List<User> result = userList.stream().filter(p -> p.getUsername().equals(username)).collect(Collectors.toList());
        return result.size() == 0 ? null : result.get(0);
    }

    public void addUser(User user) throws InterruptedException {
        //test
        Thread.sleep(2000);
        userList.add(user);
        System.out.println("after add "+userList);
    }

    public void delUser(String username) throws InterruptedException {
        //test
        Thread.sleep(2000);
        userList.removeIf(p -> p.getUsername().equals(username));
    }

    public void updateUserByname(User user) throws InterruptedException {
        //test
        Thread.sleep(2000);
        User newUser = user;
        User oldUser = getUserByName(user.getUsername());
        oldUser.setPassword(newUser.getPassword());
    }
}
