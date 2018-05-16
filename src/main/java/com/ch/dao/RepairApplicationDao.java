package com.ch.dao;

import com.ch.pojo.RepairApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Repository("RepairApplicationDao")
public class RepairApplicationDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    //查询所有记录
    public List<RepairApplication> getAllRRecord() {
        List list=jdbcTemplate.queryForList("SELECT * FROM RepairApplication");
        return list;
    }
    //查询特定记录
    public List<RepairApplication> getRRecordByStatus(int RepairApplication_Status) {
        List list1=jdbcTemplate.queryForList("SELECT * FROM RepairApplication WHERE RepairApplication_Status=?",RepairApplication_Status);
        Iterator iterator=list1.iterator();
        return list1;

    }
        //更改报修状态
        public int updateRStatus(int RepairApplication_Id, int RepairApplication_Status){
            int i=0;
            StringBuffer sql = new StringBuffer("update RepairApplication set RepairApplication_Status=? where RepairApplication_Id=?");
            i=jdbcTemplate.update(sql.toString(),RepairApplication_Status,RepairApplication_Id);
            return i;
        }
        //新增保修
    public int addRepair(RepairApplication repairApplication){
        int i=0;
        int Id=repairApplication.getId();
        String Logname=repairApplication.getLogname();
        int Processor_Id=repairApplication.getProcessor_Id();
        int RepairApplication_Type=repairApplication.getRepairApplication_Type();
        String RepairApplication_Phone=repairApplication.getRepairApplication_Phone();
        String RepairApplication_Place=repairApplication.getRepairApplication_Place();
        String RepairApplication_Name=repairApplication.getRepairApplication_Name();
        Date RepairApplication_Time=repairApplication.getRepairApplication_Time();
        i=jdbcTemplate.update("insert into RepairApplication(Id,logname,Processor_Id,RepairApplication_Time,RepairApplication_Place,RepairApplication_Type,RepairApplication_Phone,RepairApplication_Name) values(?,?,?,?,?,?,?,?)"
        ,new Object[]{Id,Logname,Processor_Id,RepairApplication_Time,RepairApplication_Place,RepairApplication_Type,RepairApplication_Phone,RepairApplication_Name});
        return i;
    }
    //修改状态
    public int editR(RepairApplication repairApplication){
        int Status=repairApplication.getRepairApplication_Status();
        int Id=repairApplication.getRepairApplication_Id();
        int i=jdbcTemplate.update("update RepairApplication set RepairApplication_Status = ? where RepairApplication_Id = ?"
        ,new Object[]{Status,Id});
        return i;
    }
    public List<RepairApplication> getRById(int Id){
        List list1=jdbcTemplate.queryForList("SELECT * FROM RepairApplication WHERE Id=?",Id);
        Iterator iterator=list1.iterator();
        return list1;
    }
    public int countR(){
        int RepairApplication_Status=0;
        int i=jdbcTemplate.queryForObject("select count(*) from RepairApplication where RepairApplication_Status=?",
                new Object[]{RepairApplication_Status},Integer.class);
        return i;
    }
}
