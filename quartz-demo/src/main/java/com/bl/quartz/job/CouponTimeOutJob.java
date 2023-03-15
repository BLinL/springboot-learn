package com.bl.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class CouponTimeOutJob extends QuartzJobBean {
    private final Logger log = LoggerFactory.getLogger(CouponTimeOutJob .class);

    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {

        log.info("=== 执行定时任务 ===");
    }
}