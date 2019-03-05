package com.springbootdemo.controller;



import com.springbootdemo.model.UserModel;
import com.springbootdemo.response.CommonReturnType;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/test")
    public CommonReturnType redisvalue(){
        stringRedisTemplate.opsForValue().set("imooc-cache","hello,nihao");
        UserModel user =new UserModel();
        user.setId(1001);
        user.setAge(20);
        user.setGender(1);
//        JSONUtils.toJSONString(stringRedisTemplate.opsForValue().set("imooc-cache","hello,nihao"))
        return CommonReturnType.create(stringRedisTemplate.opsForValue().get("imooc-cache"));
//        return CommonReturnType.create(JSONOb.);
    }
}
