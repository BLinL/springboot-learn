package com.liu.springboot.config;

import com.liu.springboot.component.MyLocal;
import com.liu.springboot.inceptor.MyInceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyConfig implements WebMvcConfigurer {


    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/aa").setViewName("logon");
            }
        };
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("hi").setViewName("test");
        registry.addViewController("/").setViewName("logon");
        registry.addViewController("/index.html").setViewName("logon");
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocal();
    }

    //添加拦截器
    //@Override
    public void addInterceptors(InterceptorRegistry registry) {
        MyInceptor myInceptor = new MyInceptor();
        registry.addInterceptor(myInceptor).addPathPatterns("/**")
                .excludePathPatterns("/webjars", "index.html", "/");
    }
}
