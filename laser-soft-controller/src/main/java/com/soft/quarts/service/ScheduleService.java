package com.soft.quarts.service;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    private Scheduler scheduler;

    public void testScheduleTask() throws SchedulerException {

        /*for (int i = 1; i < 3; i++) {
            JobDetail jobDetail = JobBuilder.newJob(UploadTask.class).withIdentity("updateMatch" + i, "updateMatch")
                            .withDescription("定时比赛Id为" + i).build();
            //cron表达式 表示每隔i秒执行
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ?")
                            .withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("updateMatch" + i, "updateMatch")
                            .withDescription("定时比赛Id为" + i).withSchedule(scheduleBuilder).startNow().build();
            scheduler.scheduleJob(jobDetail, cronTrigger);

        }*/

    }
}
