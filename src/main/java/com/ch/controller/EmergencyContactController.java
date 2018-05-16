package com.ch.controller;

import com.alibaba.fastjson.JSONObject;
import com.ch.pojo.EmergencyContact;
import com.ch.service.IEmergencyContactInterface;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class EmergencyContactController {
    @Resource
    private IEmergencyContactInterface emergencyContactInterface;

    @RequestMapping(value="/getAllEmergency",method= RequestMethod.POST)
    @ResponseBody
    public Object getAllEmergency(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<EmergencyContact> list=emergencyContactInterface.getALLEmergencyContact();
        System.out.println("here to e");
        Gson gson=new Gson();
        gson.toJson(list);
        res.setContentType("text/xml;charset=UTF-8");
        res.setHeader("Cache-Controller","no-cache");
        res.setCharacterEncoding("UTF-8");
        System.out.println(gson.toJson(list));
        return list;
    }
    @RequestMapping(value = "/getContactById",method = RequestMethod.POST)
    @ResponseBody
    public String getContactById(HttpServletRequest req,HttpServletResponse res){
        String i=req.getParameter("emergencyId");
        EmergencyContact emergencyContact=new EmergencyContact();
        int id=Integer.parseInt(i);
        emergencyContact=emergencyContactInterface.getEmergencyContactById(id);
        res.setContentType("text/xml;charset=UTF-8");
        res.setHeader("Cache-Controller","no-cache");
        res.setCharacterEncoding("UTF-8");
        Gson gson=new Gson();
        gson.toJson(emergencyContact);
        System.out.println(gson.toJson(emergencyContact));
        return gson.toJson(emergencyContact);
    }
    @RequestMapping(value ="/editEmergency",method = RequestMethod.POST)
    @ResponseBody
    public Object editEmergency(@RequestBody JSONObject dataMap,HttpServletRequest req){
        boolean result=emergencyContactInterface.editEmergency(dataMap);
        return result;
    }
    @RequestMapping(value = "/addEmergency",method = RequestMethod.POST)
    @ResponseBody
    public Object addEmergency(@RequestBody JSONObject dataMap,HttpServletRequest req){
        boolean result=emergencyContactInterface.addEmergency(dataMap);
        return result;
    }
    @RequestMapping(value = "/delE",method = RequestMethod.POST)
    @ResponseBody
    public Object delUR(@RequestBody JSONObject dataMap,HttpServletRequest req){
        System.out.println("del");
        int iD=(Integer)dataMap.get("EmergencyContact_Id");
        System.out.println(iD);
        boolean i=emergencyContactInterface.delE(iD);
        return i;
    }
}
