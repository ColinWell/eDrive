package com.ch.controller;

import com.alibaba.fastjson.JSONObject;
import com.ch.pojo.RepairApplication;
import com.ch.service.IRepairInterface;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class RepairApplicationController {

    @Resource
    private IRepairInterface repairInterface;
//获得所有报修记录
    @RequestMapping(value = "/Repair/getAllRepair",method = RequestMethod.POST)
    @ResponseBody
    public Object getAllRepair(HttpServletRequest req, HttpServletResponse res){
        List<RepairApplication> list=repairInterface.getAllRepair();
        Gson gson=new Gson();
        gson.toJson(list);
        return gson.toJson(list);
    }
    //根据报修记录状态获得报修记录
    @RequestMapping(value = "/getRepairByStatus",method = RequestMethod.POST)
    @ResponseBody
    public Object getRepairByStatus(HttpServletRequest req, HttpServletResponse res){
        int RepairApplication_Status=req.getIntHeader("RepairApplication_Status");
        List<RepairApplication> list=repairInterface.getRepairByStatus(RepairApplication_Status);
        Gson gson=new Gson();
        gson.toJson(list);
        return gson.toJson(list);
    }
    //根据报修记录状态获得报修记录
    @RequestMapping(value = "/getRepairByS",method = RequestMethod.POST)
    @ResponseBody
    public Object getRepairByS(HttpServletRequest req, HttpServletResponse res){
        int RepairApplication_Status=0;
        System.out.println("0");
        List<RepairApplication> list=repairInterface.getRepairByStatus(RepairApplication_Status);
                System.out.println("here a");
        res.setContentType("text/xml;charset=UTF-8");
        res.setHeader("Cache-Controller","no-cache");
        res.setCharacterEncoding("UTF-8");
        Gson gson=new Gson();
        gson.toJson(list);
        System.out.println(list);
        return gson.toJson(list);
    }
    //更改报修记录状态
    @RequestMapping(value = "/updateRepairByStatus",method = RequestMethod.POST)
    @ResponseBody
    public Object updateRepairForStatus(HttpServletRequest req, HttpServletResponse res){
        int RepairApplication_Id=req.getIntHeader("RepairApplication_Id");
        int RepairApplication_Status=1;
        List<RepairApplication> list=new ArrayList<RepairApplication>();
        boolean b=false;
        int i=repairInterface.updateRepairApplicationForStatus(RepairApplication_Id,RepairApplication_Id);
        if(i>0){
            b=true;
        }
        Gson gson=new Gson();
        gson.toJson(b);
        return gson.toJson(b);
    }
    //新增报修
    @RequestMapping(value = "/addRepair",method =RequestMethod.POST)
    @ResponseBody
    public String addRepair(HttpServletRequest req, HttpServletResponse res){
        System.out.println("in");
        String LogName=req.getParameter("LogName");
        System.out.println(LogName);
        String phone=req.getParameter("RepairApplication_Phone");
        System.out.println(phone);
        String Rname=req.getParameter("RepairApplication_Name");
        System.out.println(Rname);
        String Rplace=req.getParameter("RepairApplication_Place");
        System.out.println(Rplace);
        String stype=req.getParameter("RepairApplication_Type");
        int type=Integer.valueOf(stype);
        System.out.println(type);
        String sid=req.getParameter("Id");
        int id=Integer.parseInt(sid);
        System.out.println("sid:"+sid);
        System.out.println("id:"+id);
        String s4=req.getParameter("Processor_Id");
        System.out.println("pid:"+s4);
        int p_id=Integer.parseInt(s4);
        System.out.println(p_id);
        RepairApplication repairApplication=new RepairApplication();
        repairApplication.setLogname(LogName);
        repairApplication.setRepairApplication_Type(type);
        repairApplication.setId(id);
        repairApplication.setProcessor_Id(p_id);
        repairApplication.setRepairApplication_Phone(phone);
        repairApplication.setRepairApplication_Name(Rname);
        repairApplication.setRepairApplication_Place(Rplace);
        //DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        repairApplication.setRepairApplication_Time(date);
        System.out.println(repairApplication);
        System.out.println(repairApplication.getLogname());
        int i=repairInterface.addRepair(repairApplication);
        System.out.println(i);
        Gson gson=new Gson();
        return gson.toJson(i);
    }
    @RequestMapping(value = "/editR",method = RequestMethod.POST)
    @ResponseBody
    public Object editR(@RequestBody JSONObject dataMap,HttpServletRequest req){
        boolean result=repairInterface.editR(dataMap);
        return result;
    }
    @RequestMapping(value = "/getRById.do",method = RequestMethod.POST)
    @ResponseBody
    public Object getDById(HttpServletRequest req, HttpServletResponse res){
        String id=req.getParameter("Id");
        int Id=Integer.valueOf(id);
        System.out.println(Id);
        List<RepairApplication> list=repairInterface.getRById(Id);
        System.out.println(list);
        return list;
    }
    @RequestMapping(value = "/countR",method = RequestMethod.POST)
    @ResponseBody
    public Object countD(HttpServletRequest req, HttpServletResponse res){
        int i=repairInterface.countR();
        System.out.println(i);
        Gson gson=new Gson();
        return  gson.toJson(i);
    }
}
