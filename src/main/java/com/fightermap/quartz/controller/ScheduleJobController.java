package com.fightermap.quartz.controller;

import com.fightermap.quartz.model.ScheduleJob;
import com.fightermap.quartz.service.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zengqk on 2017/7/24.
 */
@Controller
@RequestMapping("/task")
public class ScheduleJobController {
    @Autowired
    private ScheduleJobService scheduleJobService;

    @RequestMapping(value = "/getAllTask",method = RequestMethod.GET)
    public List<ScheduleJob> getAllTask(HttpServletRequest request){
        return scheduleJobService.getAllTask();
    }
}
