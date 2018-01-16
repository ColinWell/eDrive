package com.ch.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.ch.service.SecurityTestInterface;

@Controller
public class SecurityTest {
    @Resource
    private SecurityTestInterface securityService;

    @RequestMapping(value="/getinput")//查看最近收入
    @ResponseBody
    public boolean getinput(HttpServletRequest req,HttpServletRequest res){
        boolean b=securityService.getinput();
        return b;
    }


    @RequestMapping(value="/geoutput")//查看最近支出
    @ResponseBody
    public boolean geoutput(HttpServletRequest req,HttpServletResponse res){
        boolean b=securityService.geoutput();
        return b;
    }

    @RequestMapping(value="/addreport_admin")//添加报表管理员
    @ResponseBody
    public boolean addreport_admin(HttpServletRequest req,HttpServletResponse res){
        boolean b=securityService.addreport_admin();
        return b;
    }

    @RequestMapping(value="/deletereport_admin")//删除报表管理员
    @ResponseBody
    public boolean deletereport_admin(HttpServletRequest req,HttpServletResponse res){
        boolean b=securityService.deletereport_admin();
        return b;
    }

    @RequestMapping(value="/user")//普通用户登录
    public ModelAndView user_login(HttpServletRequest req,HttpServletResponse res){
        securityService.user_login();
        return new ModelAndView("user");
    }

}
