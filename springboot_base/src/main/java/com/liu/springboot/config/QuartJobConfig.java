package com.liu.springboot.config;

import com.liu.springboot.task.MyJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置job
 */
@Configuration
public class QuartJobConfig {

    @Bean
    public JobDetail simpleJob(){
        return JobBuilder.newJob(MyJob.class)
                .withIdentity("bliu")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger simple(){
        return TriggerBuilder
                .newTrigger()
                .forJob(simpleJob())
                .withIdentity("bliu")
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2))
                .build();
    }
}
