package com.springbootdemo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component   //作为组件被容器扫描
public class TestTask {
    private static final SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(fixedRate = 3000)
    //cron表达式生成地址 ： http://cron.qqe2.com
//    @Scheduled(cron = "3-50 * * * * ?")
//    public void reportCurrentTime(){
//         System.out.println("现在时间："+sdf.format(new Date()));
//    }
}
