package com.example.cache.demo.service;

import com.example.cache.demo.dao.UserRepository;
import com.example.cache.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author bliu
 * @date 2021-03-02 9:23
 */
@Service
@CacheConfig(cacheNames = {"abc"})
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Cacheable(cacheNames = "abc",unless = "#result == null", key = "#username")
    public User getUserByName(String username) throws InterruptedException {
        System.out.println("get user form db");
        User user = userRepository.getUserByName(username);
        System.out.println(user);
        return user;
    }

    @CachePut(key = "#user.username", condition = "#user.password.length() > 16", unless = "#user.username == null")
    public User addUser(User user) throws InterruptedException {
        System.out.println("add user");
        userRepository.addUser(user);
        return userRepository.getUserByName(user.getUsername());
    }

    @CacheEvict(key = "#username")
    public void removeUser(String username) throws InterruptedException {
        System.out.println("remove user " + username);
        userRepository.delUser(username);
    }

    @CacheEvict(key = "#user.username")
    public void UpdateUserByUsername(User user) throws InterruptedException {
        System.out.println("update user " + user);
        userRepository.updateUserByname(user);
    }

    @Caching(evict = {
            @CacheEvict(value = "USER", allEntries = true),
            @CacheEvict(value = "SECOND_CACHE", allEntries = true)
    })
    public void clearAllCaches() {
        System.out.println("Cleared all caches");
    }

}
