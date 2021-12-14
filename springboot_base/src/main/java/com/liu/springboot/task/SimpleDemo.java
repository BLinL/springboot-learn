package com.liu.springboot.task;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleDemo {
    public static void main(String[] args) throws SchedulerException {
//        simple();
        cornSchedule();
    }

    public static void simple() throws SchedulerException {
        // 通过调度工厂获取调度
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        // Job 信息
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("myJob").build();

        // 定义如何触发
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withDescription("myTrigger")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.
                        simpleSchedule().
                        withIntervalInSeconds(5)
                        .repeatForever())
                .build();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    public static void cornSchedule() throws SchedulerException {
        // 通过调度工厂获取调度
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        // 由于每次都要创建新的Job 所以存没有直接new job
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("myJob").build();

        // 定义触发时间
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withDescription("myTrigger")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0 5 17 ? * *"))
                .build();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
