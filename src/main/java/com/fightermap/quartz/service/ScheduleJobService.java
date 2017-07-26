package com.fightermap.quartz.service;

import com.fightermap.quartz.dao.ScheduleJobDao;
import com.fightermap.quartz.model.ScheduleJob;
import com.fightermap.quartz.task.QuartzJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zengqk on 2017/7/24.
 */
@Service
public class ScheduleJobService {
    @Autowired
    private ScheduleJobDao scheduleJobDao;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    public ScheduleJobService() {
    }

    public List<ScheduleJob> getAllTask(){
        return scheduleJobDao.getAllTask();
    }

    @PostConstruct
    public void init() throws Exception{
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        List<ScheduleJob> jobList = scheduleJobDao.getAllTask();

        for (ScheduleJob job : jobList){
            addJob(job);
        }
    }

    public void addJob(ScheduleJob job) throws Exception{
        if (job == null || !"1".equals(job.getJobStatus())){
            return;
        }

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(),job.getJobGroup());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        if (trigger == null){
            Class clazz = QuartzJob.class;
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(),job.getJobGroup()).build();
            jobDetail.getJobDataMap().put("scheduleJob",job);

            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(),job.getJobGroup()).withSchedule(cronScheduleBuilder).build();

            scheduler.scheduleJob(jobDetail,trigger);
        }else {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

            trigger = trigger.getTriggerBuilder().withIdentity(job.getJobName(),job.getJobGroup()).withSchedule(cronScheduleBuilder).build();
            scheduler.rescheduleJob(triggerKey,trigger);
        }
    }

}
