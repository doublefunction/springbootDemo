package com.springbootdemo.controller;

import com.springbootdemo.controller.viewObject.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@RequestMapping("/freemarker")
public class FreemakrerController {
    @Autowired
    private Resources resource;

    @RequestMapping("/getBean")
    @ResponseBody
    public Resources getbean(){
        return resource;
    }




    @RequestMapping("/index")
    public String index(ModelMap map){
        map.addAttribute("resource",resource);
        return "/index";
    }

    @RequestMapping("/center")
   // @ResponseBody
    public String center(){
        return "templates/index";
    }
}
