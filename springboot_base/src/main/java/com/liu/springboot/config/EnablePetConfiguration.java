package com.liu.springboot.config;

import com.liu.springboot.pojo.Pet;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(Pet.class)
public class EnablePetConfiguration {

    private Pet pet;


}
