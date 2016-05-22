package com.chenzhicheng.quartz.job;

import org.apache.log4j.Logger;
import org.quartz.*;

import java.util.Date;

/**
 * Created by Administrator on 2016/5/18.
 */
public class ScanDirectoryJob implements Job {

    private static Logger logger = Logger.getLogger(ScanDirectoryJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        String name = jobDetail.getKey().getName();
        logger.info("job: " + name + " execute at " + new Date());

        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        String basedir = jobDataMap.getString("basedir");
        if(basedir == null){
            logger.warn("cannot find basedir");
        }

        logger.info("scan " + basedir + " finished...");
    }
}
