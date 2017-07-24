package com.fightermap.quartz.dao;

import com.fightermap.quartz.model.ScheduleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zengqk on 2017/7/24.
 */
@Repository
public class ScheduleJobDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ScheduleJobDao() {

    }

    public List<ScheduleJob> getAllTask(){
        String sql = "select * from task_schedule_job";

        RowMapper<ScheduleJob> rowMapper = new BeanPropertyRowMapper<ScheduleJob>(ScheduleJob.class);
        return jdbcTemplate.query(sql,rowMapper);
    }

}
