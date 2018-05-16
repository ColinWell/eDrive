package com.ch.controller;

import com.alibaba.fastjson.JSONObject;
import com.ch.dao.DecorationDao;
import com.ch.dao.RuleDao;
import com.ch.pojo.DecorationApplication;
import com.ch.service.IDecorationInterface;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
装修管理操作
 */
@Controller
public class DecorationController {
    @Resource
    private DecorationDao decorationDao;
    @Resource
    private RuleDao ruleDao;
    @Resource
    private IDecorationInterface decorationInterface;


    //获得所有装修记录//Decoration/getDARecord
    @RequestMapping(value="/Decoration/getDARecord",method=RequestMethod.POST)
    @ResponseBody
    public String getDARecord(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<DecorationApplication> list=decorationInterface.getAllDecoration();
        System.out.println("here to all");
        Gson gson=new Gson();
        gson.toJson(list);
        res.setContentType("text/xml;charset=UTF-8");
        res.setHeader("Cache-Controller","no-cache");
        res.setCharacterEncoding("UTF-8");
        System.out.println(gson.toJson(list));
        return gson.toJson(list);
    }
    @RequestMapping(value="/getDRecordByStatus",method= RequestMethod.POST)
    @ResponseBody
    //获取特定状态的装修申请记录
    public Object getDARecordByStatus(HttpServletRequest req, HttpServletResponse res){
        int DecorationApplication_Status=req.getIntHeader("DecorationApplication_Status");
        System.out.println("here");
        List<DecorationApplication> list=decorationInterface.getDecorationByStatus(DecorationApplication_Status);
        Gson gson=new Gson();
        gson.toJson(list);
        return gson.toJson(list);
    }
    @RequestMapping(value="/getDRecordByS",method= RequestMethod.POST)
    @ResponseBody
    //获取未处理的装修申请记录
    public Object getDARecordByS(HttpServletRequest req,HttpServletResponse res){
        int DecorationApplication_Status=1;
        System.out.println("here to 0");
        List<DecorationApplication> list=decorationInterface.getDecorationByStatus(DecorationApplication_Status);
        System.out.println("here a");
        res.setContentType("text/xml;charset=UTF-8");
        res.setHeader("Cache-Controller","no-cache");
        res.setCharacterEncoding("UTF-8");
        Gson gson=new Gson();
        gson.toJson(list);
        System.out.println(gson.toJson(list));
        return gson.toJson(list);
    }
    @RequestMapping(value="/changDRStatus",method= RequestMethod.POST)
    @ResponseBody
    //修改装修记录状态
    public Object changeDRStatus(HttpServletRequest req,HttpServletResponse res){
        int DecorationApplication_Status=req.getIntHeader("DecorationApplication_Status");
        int DecorationApplication_Id=req.getIntHeader("DecorationApplication_Id");
        boolean b=false;
        int i=decorationInterface.updateDecorationForStatus(DecorationApplication_Id,DecorationApplication_Status);
        if(i>0){
            b=true;
        }
        Gson gson=new Gson();
        gson.toJson(b);
        return gson.toJson(b);
    }
    @RequestMapping(value="/changeDRStatusToA",method= RequestMethod.POST)
    @ResponseBody
    //修改装修记录状态
    public Object changeDRStatusToA( HttpServletRequest req,HttpServletResponse res){
        System.out.println("here to a");
        int DecorationApplication_Status=1;
        int DecorationApplication_Id=3;//req.getIntHeader("DecorationApplication_Id");
        System.out.println(DecorationApplication_Id);
        boolean b=false;
        int i=decorationInterface.updateDecorationForStatus(DecorationApplication_Id,DecorationApplication_Status);
        if(i>0){
            b=true;
        }
        Gson gson=new Gson();
        gson.toJson(b);
        return gson.toJson(b);
    }
    @RequestMapping(value="/changeDRStatusToB",method= RequestMethod.POST)
    @ResponseBody
    public Object changeDRStatusToB( HttpServletRequest req,HttpServletResponse res){
        System.out.println("here to B ");
        int DecorationApplication_Status=2;
        int DecorationApplication_Id=4;//req.getIntHeader("DecorationApplication_Id");
        System.out.println(DecorationApplication_Id);
        boolean b=false;
        int i=decorationInterface.updateDecorationForStatus(DecorationApplication_Id,DecorationApplication_Status);
        if(i>0){
            b=true;
        }
        Gson gson=new Gson();
        gson.toJson(b);
        return gson.toJson(b);
    }
    @RequestMapping(value="/changDREndTime",method= RequestMethod.POST)
    @ResponseBody
    //修改装修记录时间
    public Object changDREndTime(HttpServletRequest req,HttpServletResponse res) throws ParseException {
        int DecorationApplication_Id=req.getIntHeader("DecorationApplication_Id");
        String a=req.getParameter("DecorationApplication_EndTime");
        SimpleDateFormat dateFormat = new SimpleDateFormat(a);
        Date DecorationApplication_EndTime=dateFormat.parse(a);
        boolean b=false;
        int i=decorationInterface.updateDecorationForTime(DecorationApplication_Id,DecorationApplication_EndTime);
        if(i>0){
            b=true;
        }
        Gson gson=new Gson();
        gson.toJson(b);
        return gson.toJson(b);
    }
    @RequestMapping(value="/changDRReply",method= RequestMethod.POST)
    @ResponseBody
    //修改装修记录回复
    public Object changeDRReply(HttpServletRequest req,HttpServletResponse res){
        int DecorationApplication_Id=req.getIntHeader("DecorationApplication_Id");
        String DecorationApplication_Reply=req.getParameter("DecorationApplication_Reply");
        boolean b=false;
        int i=decorationInterface.updateDecorationForReply(DecorationApplication_Id,DecorationApplication_Reply);
        if(i>0){
            b=true;
        }
        Gson gson=new Gson();
        gson.toJson(b);
        return gson.toJson(b);
    }
    @RequestMapping(value = "/addDecoration",method=RequestMethod.POST)
    @ResponseBody
    public String addDecoration(HttpServletRequest req, HttpServletResponse res){
        DecorationApplication decorationApplication=new DecorationApplication();
        String sId=req.getParameter("Id");
        int Id=Integer.valueOf(sId);
        decorationApplication.setDecorationApplication_Id(Id);
        String LogName=req.getParameter("LogName");
        System.out.println(LogName);
        System.out.println(Id);
        decorationApplication.setLogname(LogName);
        String Phone=null;
        Phone=req.getParameter("DecorationApplication_Phone");
        decorationApplication.setDecorationApplication_Phone(Phone);
        String StartTime=null;
        StartTime=req.getParameter("DecorationApplication_StartTime");
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date DecorationApplication_StartTime=null;
        Date DecorationApplication_EndTime=null;
        String EndTime=null;
        EndTime=req.getParameter("DecorationApplication_EndTime");
        System.out.println(StartTime);
        System.out.println(EndTime);
        try {
            DecorationApplication_EndTime=format1.parse(EndTime);
            DecorationApplication_StartTime=format1.parse(StartTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(DecorationApplication_EndTime);
        decorationApplication.setDecorationApplication_StartTime(DecorationApplication_StartTime);
        decorationApplication.setDecorationApplication_EndTime(DecorationApplication_EndTime);
        String Place=req.getParameter("DecorationApplication_Place");
        decorationApplication.setDecorationApplication_Place(Place);
        String sStatus=req.getParameter("DecorationApplication_Status");
        int Status=Integer.valueOf(sStatus);
        decorationApplication.setDecorationApplication_Status(Status);
        System.out.println(decorationApplication.getId());
        decorationApplication.setProcessor_Id(1);
        System.out.println(decorationApplication);
        boolean b=false;
        int i=decorationInterface.addDecoration(decorationApplication);
        System.out.println("here add");
        if(i>0){
            b=true;
        }
        Gson gson=new Gson();
        return gson.toJson(i);

    }
    @RequestMapping(value = "/editD",method = RequestMethod.POST)
    @ResponseBody
    public Object editR(@RequestBody JSONObject dataMap, HttpServletRequest req){
        boolean result=decorationInterface.editD(dataMap);
        return result;
    }
    @RequestMapping(value = "/getDById",method = RequestMethod.POST)
    @ResponseBody
    public Object getDById(HttpServletRequest req, HttpServletResponse res){
        String id=req.getParameter("Id");
        int Id=Integer.valueOf(id);
        List<DecorationApplication> list=decorationInterface.getDecorationById(Id);
        System.out.println(list);
        return list;
    }
    @RequestMapping(value="/queryDecoration",method = RequestMethod.POST)
    @ResponseBody
    public Object queryDecoration(@RequestBody JSONObject dataMap, HttpServletRequest req){
        DecorationApplication decorationApplication=new DecorationApplication();
        String phone= (String) dataMap.get("DecorationApplication_Phone");
        String status=(String) dataMap.get("DecorationApplication_Status");
        System.out.println("status:string"+status);
        int Status=Integer.parseInt(status);
        System.out.println("status:int"+Status);
        String id=(String) dataMap.get("DecorationApplication_Id");
        System.out.println("id:string"+id);
        int Id=0;
        Id=Integer.parseInt(id);
        System.out.println("id:int"+Id);
        String logname= (String) dataMap.get("Logname");
        System.out.println(logname);
        String start=(String)dataMap.get("DecorationApplication_StartTime");
        String end=(String)dataMap.get("DecorationApplication_EndTime");
        System.out.println(start);
        System.out.println(end);
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date DecorationApplication_StartTime=null;
        Date DecorationApplication_EndTime=null;
        try {
            DecorationApplication_EndTime=format1.parse(end);
            DecorationApplication_StartTime=format1.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("start"+DecorationApplication_StartTime);
        System.out.println("end"+DecorationApplication_EndTime);
        decorationApplication.setDecorationApplication_Status(Status);
        decorationApplication.setDecorationApplication_Id(Id);
        decorationApplication.setDecorationApplication_EndTime(DecorationApplication_EndTime);
        decorationApplication.setDecorationApplication_StartTime(DecorationApplication_StartTime);
        decorationApplication.setLogname(logname);
        decorationApplication.setDecorationApplication_Phone(phone);
        return true;
    }
    @RequestMapping(value = "/countD",method = RequestMethod.POST)
    @ResponseBody
    public Object countD(HttpServletRequest req, HttpServletResponse res){
        int i=decorationInterface.countD();
        System.out.println(i);
        Gson gson=new Gson();
        return  gson.toJson(i);
    }
}
