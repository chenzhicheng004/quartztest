package com.chenzhicheng.quartz.scheduler;

import com.chenzhicheng.quartz.job.ScanDirectoryJob;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

import java.util.Date;

/**
 * Created by Administrator on 2016/5/18.
 */
public class SimpleScheduler {

    private static Logger logger = Logger.getLogger(SimpleScheduler.class);

    public static void main(String[] args) throws Exception{
        SimpleScheduler scheduler = new SimpleScheduler();
        scheduler.startSchedule();
    }

    private void startSchedule() throws Exception{
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail jobDetail = new JobDetailImpl("first_job", ScanDirectoryJob.class);
        jobDetail.getJobDataMap().put("basedir", "e:a/b/c");

        JobDetail jobDetail2 = new JobDetailImpl("second_job", ScanDirectoryJob.class);
        jobDetail2.getJobDataMap().put("basedir", "e:a/b/f");

        Trigger trigger = new SimpleTriggerImpl("first_trigger",5,1000);
        Trigger trigger2 = new SimpleTriggerImpl("second_trigger",5,1000);

        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.scheduleJob(jobDetail2,trigger2);

        scheduler.start();
        logger.info("scheduler start at " + new Date());
    }
}
