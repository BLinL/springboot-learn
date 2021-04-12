package com.example.cache.demo.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.concurrent.TimeUnit;

/**
 * @author bliu
 * @date 2021-03-02 9:15
 */
@Profile("test")
@Configuration
public class CacheConfig {

    /*
    * CacheManager 配置
    * 可以使用默认配置
    * */
    @Bean

    public CacheManager cacheManager(){
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("USER", "SECOND_CACHE");
        cacheManager.setAllowNullValues(false); //can happen if you get a value from a @Cachable that returns null

        Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)//缓存失效时间
                .maximumSize(1000)
                .removalListener(new CustomRemovalListener())
                .recordStats();

        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }

    class CustomRemovalListener implements RemovalListener<Object, Object> {
        @Override
        public void onRemoval(Object key, Object value, RemovalCause cause) {
            System.out.format("removal listerner called with key [%s], cause [%s], evicted [%S]\n",
                    key, cause.toString(), cause.wasEvicted());
        }
    }
}
