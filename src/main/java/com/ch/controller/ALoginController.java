package com.ch.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ch.dao.ALoginDao;
import com.ch.service.ALoginInterface;
import com.ch.service.SecurityTestInterface;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 安卓端登录相关服务
 * Created by Cxy on 2018/1/3.
 */

@Controller
public class ALoginController {
    @Resource
    private ALoginInterface dao;

    @RequestMapping(value="/android/login",method=RequestMethod.POST) // 登录验证
    @ResponseBody
    public Object aLogin(HttpServletRequest req,HttpServletResponse res){
        String logname = (String)req.getParameter("logname");
        String password = (String)req.getParameter("password");
        boolean b=dao.aLogin(logname,password);
        Gson gson = new Gson();
        gson.toJson(b);
        return gson.toJson(b);
    }
}
