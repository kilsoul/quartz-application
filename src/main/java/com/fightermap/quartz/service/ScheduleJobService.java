package com.fightermap.quartz.service;

import com.fightermap.quartz.dao.ScheduleJobDao;
import com.fightermap.quartz.model.ScheduleJob;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zengqk on 2017/7/24.
 */
@Service
public class ScheduleJobService {
    @Resource
    private ScheduleJobDao scheduleJobDao;

    public ScheduleJobService() {
    }

    public List<ScheduleJob> getAllTask(){
        return scheduleJobDao.getAllTask();
    }
}
