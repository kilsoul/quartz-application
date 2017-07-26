package com.fightermap.quartz;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zengqk on 2017/7/25.
 */
@Component
public class TaskTest {

    public void run(){
        System.out.println("run......"+new Date());
    }

    public void run1(){
        System.out.println("run1....."+new Date());
    }


}
