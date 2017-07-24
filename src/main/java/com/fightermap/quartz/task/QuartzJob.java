package com.fightermap.quartz.task;

import com.fightermap.quartz.model.ScheduleJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.Method;

/**
 * Created by zengqk on 2017/7/24.
 */
public class QuartzJob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");
        invokeMethod(scheduleJob);
    }

    private void invokeMethod(ScheduleJob scheduleJob){
        Object object = null;
        Class clazz = null;
        Method method = null;
        try {
            if (scheduleJob.getBeanClass() != null) {
                clazz = Class.forName(scheduleJob.getBeanClass());
                object = clazz.newInstance();
            }

            method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
            if (method != null){
                method.invoke(object);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
