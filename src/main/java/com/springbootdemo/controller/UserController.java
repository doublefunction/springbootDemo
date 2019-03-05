package com.springbootdemo.controller;

import com.springbootdemo.controller.viewObject.UserVo;
import com.springbootdemo.error.BusinessException;
import com.springbootdemo.error.EmBusinessError;
import com.springbootdemo.model.UserModel;
import com.springbootdemo.response.CommonReturnType;
import com.springbootdemo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    //用户短信接口
    @RequestMapping("/getOtp")
    @ResponseBody
    public CommonReturnType getOTP(@RequestParam(value = "telphone")String telphone){
          //生成otp验证码
        Random random = new Random();
        int randomint =random.nextInt(999);
        randomint+=10000;
        String otpCode =String.valueOf(randomint);
        //验证码关联手机号
        httpServletRequest.getSession().setAttribute(telphone,otpCode);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(value = "id")Integer id) throws BusinessException {
        UserModel userModel =userService.getUser(id);
        if(userModel==null){
           throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
//            return null;
        }
        UserVo userVo = convertFromModel(userModel);
        return CommonReturnType.create(userVo);
    }

    private UserVo convertFromModel(UserModel userModel){
        if(userModel==null){
            return null;
        }
        UserVo userVo =new UserVo();
        BeanUtils.copyProperties(userModel,userVo);
        return userVo;
    }



}
