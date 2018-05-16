package com.ch.dao;

import com.ch.pojo.Complaint;
import com.ch.pojo.EmergencyContact;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Repository("ComplaintDao")
public class ComplaintDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    //增加投诉记录
    public int addComplaint(Complaint complaint){
        int i=0;
        int Id=complaint.getId();
        String Complaint_Name=complaint.getComplaint_Name();
        int Processor_Id=complaint.getProcessor_Id();
        Date Complaint_Time=complaint.getComplaint_Time();
        String Complaint_Phone=complaint.getComplaint_Phone();
        String Complaint_Content=complaint.getComplaint_Content();
        i=jdbcTemplate.update("INSERT INTO Complaints(Id,Complaint_Name,Processor_Id,Complaint_Time,Complaint_Phone,Complaint_Content)VALUES (?,?,?,?,?,?)"
                ,new Object[]{Id,Complaint_Name,Processor_Id,Complaint_Time,Complaint_Phone,Complaint_Content});

        return i;
    }
    public List<Complaint> getCByS(int Complaint_Status) {
        System.out.println("test");
        List list1=jdbcTemplate.queryForList("select * from Complaints WHERE Complaint_Status=?",Complaint_Status);
        System.out.println(list1);
        Iterator iterator=list1.iterator();
        return list1;
    }
    public List<Complaint> getCById(int Id){
        List list2=jdbcTemplate.queryForList("SELECT * FROM Complaints WHERE Id=?",Id);
        Iterator iterator=list2.iterator();
        return list2;
    }
    public int countC(){
        int Complaint_Status=0;
        int i=jdbcTemplate.queryForObject("select count(*) from Complaints where Complaint_Status=?",
                new Object[]{Complaint_Status},Integer.class);
        return i;
    }
}
