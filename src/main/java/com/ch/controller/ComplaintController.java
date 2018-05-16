package com.ch.controller;

import com.ch.pojo.Complaint;
import com.ch.service.IComplaintService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class ComplaintController {
    @Resource
    private IComplaintService complaintService;
    @RequestMapping(value="/addComplaint",method= RequestMethod.POST)
    @ResponseBody
    public String addComplaint(HttpServletRequest req, HttpServletResponse res){
        Complaint complaint=new Complaint();
        String name=req.getParameter("Complaint_Name");
        complaint.setComplaint_Name(name);
        String sid=req.getParameter("Id");
        int id=Integer.parseInt(sid);
        complaint.setId(id);
        System.out.println("sid:"+sid);
        System.out.println("id:"+id);
        String spid=req.getParameter("Processor_Id");
        System.out.println("pid:"+spid);
        int p_id=Integer.parseInt(spid);
        complaint.setProcessor_Id(p_id);
        complaint.setComplaint_Status(0);
        Date date=new Date();
        complaint.setComplaint_Time(date);
        String phone=req.getParameter("Complaint_Phone");
        System.out.println(phone);
        complaint.setComplaint_Phone(phone);
        String con=req.getParameter("Complaint_Content");
        complaint.setComplaint_Content(con);
        int i=0;
        i=complaintService.addComplaint(complaint);
        System.out.println(i);
        Gson gson=new Gson();
        return gson.toJson(i);
    }
    @RequestMapping(value="/getCByS",method= RequestMethod.POST)
    @ResponseBody
    public Object getCByS(HttpServletRequest req, HttpServletResponse res){
        int s=0;
        List<Complaint> list=complaintService.getCByS(s);
        System.out.println(list);
        return list;
    }
    @RequestMapping(value="/getCById",method= RequestMethod.POST)
    @ResponseBody
    public Object getDById(HttpServletRequest req, HttpServletResponse res){
        String id=req.getParameter("Id");
        int Id=Integer.valueOf(id);
        System.out.println(Id);
        List<Complaint> list=complaintService.getCById(Id);
        System.out.println(list);
        return list;
    }
    @RequestMapping(value = "/countC",method = RequestMethod.POST)
    @ResponseBody
    public Object countC(HttpServletRequest req, HttpServletResponse res){
        int i=complaintService.contC();
        System.out.println(i);
        Gson gson=new Gson();
        return  gson.toJson(i);
    }
}
