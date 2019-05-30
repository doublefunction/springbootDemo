package com.springbootdemo.controller;

import com.springbootdemo.Utils.HttpClientUtil;
import com.springbootdemo.model.Template;
import com.springbootdemo.model.TemplateParam;
import com.sun.tools.javac.comp.Todo;
import com.sun.xml.internal.bind.v2.TODO;
import org.apache.http.client.methods.HttpPost;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 微信模板推送的Controller
 */
@Controller
public class TemplateController {
    /**
     * 获取wechat access_token 2小时失效
     * @return
     */
    public String getToken(){
        return null;
    }

    @RequestMapping("/doTemplate")
    public String dosth(){
        Template tem=new Template();
        tem.setTemplateId("ffU4xwfvSbdJ0rF_G0Nqz41cmL8i5Ym1JzpWK7usq2s");
        tem.setTopColor("#00DD00");
        tem.setToUser("oKwMd6LzK_lMJNgBwfZWBB0cBwCE");
        tem.setUrl("");

        List<TemplateParam> paras=new ArrayList<TemplateParam>();
        paras.add(new TemplateParam("first","我们已收到您的货款，开始为您打包商品，请耐心等待: )","#FF3333"));
        paras.add(new TemplateParam("orderMoneySum","¥20.00","#0044BB"));
        paras.add(new TemplateParam("orderProductName","火烧牛干巴","#0044BB"));
        paras.add(new TemplateParam("Remark","感谢你对我们商城的支持!!!!","#AAAAAA"));

        tem.setTemplateParamList(paras);
        // TODO: 2019/5/27 定时任务获取token
        String token ="21_jKHbA_UbyZWoY2PhYiaxyi8jPqq7HnI--iXF56WaiSEUnDstW0hc1qq0koA6uQzvsl6JoL_9fPLN6UM9pClSP6PgZnaJ8VCcb63P2EuROEB4_u_JL5v8ae7y-NQ80_s-Tyjw7kRaIMcC8IwCUSWbAAALJN";
        boolean result=sendTemplateMsg(token,tem);
        return "true";
    }



    /**
     * 发送模板消息
     * @param token
     * @param template
     * @return
     */

    public static boolean sendTemplateMsg(String token, Template template){

        boolean flag=false;

        String requestUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        requestUrl=requestUrl.replace("ACCESS_TOKEN", token);
        String json = HttpClientUtil.doPostJson(requestUrl,template.toJSON());
        JSONObject jsonResult = null;
        try {
            jsonResult = new JSONObject(json);

//        JSONObject jsonResult=CommonUtil.httpsRequest(requestUrl, "POST", template.toJSON());
        if(jsonResult!=null){
            int errorCode=jsonResult.getInt("errcode");
            String errorMessage=jsonResult.getString("errmsg");
            if(errorCode==0){
                flag=true;
            }else{
                System.out.println("模板消息发送失败:"+errorCode+","+errorMessage);
                flag=false;
            }
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return flag;



    }

}
