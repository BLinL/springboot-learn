package com.example.cache.demo;

import com.example.cache.demo.pojo.User;
import com.example.cache.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.github.benmanes.caffeine.cache.Cache;

/**
 * @author bliu
 * @date 2021-03-02 9:38
 */
@Profile("!test")
@Component
public class CommandRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("=================================================");
//        System.out.println("using cache manager:" + cacheManager.getClass().getSimpleName());
//
//        userService.getUserByName("张三");
//        userService.getUserByName("张三");//hit
//
//        userService.getUserByName("布鲁斯");
//        userService.getUserByName("布鲁斯");//hit
//        getCoffeeStats();
//
//        User afanda = new User("阿凡达", "123");
//        userService.addUser(afanda);
//
//        userService.getUserByName("阿凡达");//miss
//
//        userService.removeUser("张三");//evict
//        userService.getUserByName("张三");//miss
//
//        afanda.setPassword("1111111111111111");
//        userService.UpdateUserByUsername(afanda);
//        userService.getUserByName("阿凡达");//miss
//        userService.getUserByName("阿凡达");//hit
//
//        userService.clearAllCaches();
//        System.out.println("===================");
//        getCoffeeStats();

    }

    public void getCoffeeStats() {
//        org.springframework.cache.Cache cache = cacheManager.getCache("USER");
//        Cache nativeCache = (Cache) cache.getNativeCache();
//        System.out.println(nativeCache.stats());
    }
}
