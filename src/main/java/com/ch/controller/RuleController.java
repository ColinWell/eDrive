package com.ch.controller;

import com.ch.pojo.Rule;
import com.ch.service.IRuleInterface;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
负责装修和报修细则的模块控制器
 */
@Controller
public class RuleController {
    @Resource
    private IRuleInterface ruleInterface;
    //获取细则
    @RequestMapping(value="/getRule",method= RequestMethod.POST)
    @ResponseBody
    public Object getRule(HttpServletRequest req, HttpServletResponse res){
        String Rule_Name=req.getParameter("Rule_Name");
        Rule rule=ruleInterface.getRule(Rule_Name);
        Gson gson=new Gson();
        gson.toJson(rule);
        return gson.toJson(rule);
    }
    //获取装修细则
    @RequestMapping(value="/getDRule",method= RequestMethod.POST)
    @ResponseBody
    public Object getDRule(HttpServletRequest req, HttpServletResponse res){
        String Rule_Name="Decoration";
        Rule rule=ruleInterface.getRule(Rule_Name);
        Gson gson=new Gson();
        gson.toJson(rule);
        return gson.toJson(rule);
    }
    //修改细则
    @RequestMapping(value="/setDecorationRule",method= RequestMethod.POST)
    @ResponseBody
    public Object setRule(HttpServletRequest req,HttpServletResponse res){
        String Rule_Name=req.getParameter("Rule_Name");
        String Rule_Main=req.getParameter("Rule_Main");
        int i=ruleInterface.updateRule(Rule_Name,Rule_Main);
        Gson gson=new Gson();
        gson.toJson(i);
        return gson.toJson(i);
    }
}
