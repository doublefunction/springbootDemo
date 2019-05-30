package com.springbootdemo.controller.viewObject;


import com.springbootdemo.Utils.CheckUtil;
import com.springbootdemo.Utils.MessageUtil;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutImageMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
public class WeixinController {


        @RequestMapping("/wx")
        @ResponseBody
        public String login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
            String signature = httpServletRequest.getParameter("signature");
            String timestamp = httpServletRequest.getParameter("timestamp");
            String nonce = httpServletRequest.getParameter("nonce");
            String echostr = httpServletRequest.getParameter("echostr");
            if (CheckUtil.checkSignatures(signature, timestamp, nonce)) {
                return echostr;
            }
            return null;
        }
    @PostMapping("/wx")
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        //将微信请求xml转为map格式，获取所需的参数
        Map<String, String> map = MessageUtil.xmlToMap(request);
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String MsgType = map.get("MsgType");
        String Content = map.get("Content");
        String Event = map.get("Event");
        String message = null;

        if ("subscribe".equals(Event)) {
            message = MessageUtil.subscribeMessage(FromUserName, ToUserName);
        } else if ("unsubscribe".equals(Event)) {
            return;
        } else if ("text".equals(MsgType)) {
            //用户输入特定内容，输入1，回复相应的封装的内容
            if ("1".equals(Content)) {
                message = MessageUtil.initMessage(FromUserName, ToUserName);
            } else {
                //用户发来其他消息处理
                message = MessageUtil.reversalMessage(FromUserName, ToUserName, Content);
            }
        }
        try {
            out = response.getWriter();
            out.write(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}

