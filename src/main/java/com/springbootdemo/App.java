package com.springbootdemo;

import com.springbootdemo.dao.UserDOMapper;
import com.springbootdemo.dataObject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"com.springbootdemo"})
@RestController
@MapperScan("com.springbootdemo.dao")
//开启定时任务
@EnableScheduling
//开启异步调用方法
@EnableAsync
public class App 
{
    @Autowired
    private UserDOMapper userDOMapper;

    @RequestMapping("/")
    public String home(){
        UserDO userDO =userDOMapper.selecrByPrimaryKey(1);
        if(userDO==null)
        {
            return "用户不存在";
        }
        return userDO.getName();
    }

    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }
}
