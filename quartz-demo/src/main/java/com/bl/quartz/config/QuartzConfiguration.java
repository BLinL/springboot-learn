package com.bl.quartz.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {
//    // 使用jobDetail包装job
//    @Bean
//    public JobDetail myCronJobDetail() {
//        return JobBuilder.newJob(CouponTimeOutJob.class).withIdentity("couponTimeOutJob").storeDurably().build();
//    }
//
//    // 把jobDetail注册到Cron表达式的trigger上去
//    @Bean
//    public Trigger CronJobTrigger() {
//        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ?");
//        return TriggerBuilder.newTrigger()
//                .forJob(myCronJobDetail())
//                .withIdentity("CouponTimeOutJobTrigger")
//                .withSchedule(cronScheduleBuilder)
//                .build();
//    }
}