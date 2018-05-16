package com.ch.dao;

import com.ch.pojo.DecorationApplication;
import com.ch.pojo.RepairApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

/**
 *
 */
@Repository("DecorationDao")
public class DecorationDao {
    Logger log= LogManager.getLogger(DecorationDao.class);
    @Resource
    private JdbcTemplate jdbcTemplate;
//查询所有记录
    public List<DecorationApplication> getAllDRecord(){
        List<DecorationApplication> list=new ArrayList<DecorationApplication>();
        List list1=jdbcTemplate.queryForList("SELECT * FROM DecorationApplication");
        Iterator iterator=list1.iterator();
        return list1;
        /*
        final List<DecorationApplication> list=new ArrayList<DecorationApplication>();
        String sql = "select *  from DecorationApplication";
        final DecorationApplication decorationApplication = new DecorationApplication();
        List<Map<String, Object>> list1=jdbcTemplate.queryForList(sql);

        jdbcTemplate.query(
                sql.toString(),
                new Object[]{},
                new RowCallbackHandler(){
                    @Override
                    public void processRow(java.sql.ResultSet rs)
                            throws SQLException {
                        decorationApplication.setDecorationApplication_Id(rs.getInt("DecorationApplication _Id"));
                        decorationApplication.setDecorationApplication_EndTime(rs.getDate("DecorationApplication _EndTime"));
                        decorationApplication.setDecorationApplication_Phone(rs.getInt("DecorationApplication _Phone"));
                        decorationApplication.setDecorationApplication_Place(rs.getString("DecorationApplication _Place"));
                        decorationApplication.setDecorationApplication_Reply(rs.getString("DecorationApplication _Reply"));
                        decorationApplication.setDecorationApplication_Status(rs.getInt("DecorationApplication _Status"));
                        decorationApplication.setDecorationApplication_StartTime(rs.getDate("DecorationApplication _StartTime"));
                        decorationApplication.setProcessor_Id(rs.getInt("Processor_Id"));
                        decorationApplication.setId(rs.getInt("Id"));
                        decorationApplication.setLogname(rs.getString("logname"));
                        list.add(decorationApplication);
                        System.out.println(decorationApplication);
                    }});
        return list;
*/
    }
    //查询特定记录
    public List<DecorationApplication> getDRecordByStatus(int DecorationApplication_Status){
        List list1=jdbcTemplate.queryForList("SELECT * FROM DecorationApplication WHERE DecorationApplication_Status=?",DecorationApplication_Status);
        Iterator iterator=list1.iterator();
        return list1;
    }
    //更改装修记录状态
    public int updateDStatus(int DecorationApplication_Id,int DecorationApplication_Status){
            DecorationApplication d=new DecorationApplication();
            d.setDecorationApplication_Status(DecorationApplication_Status);
            d.setDecorationApplication_Id(DecorationApplication_Id);
        int i=0;
        StringBuffer sql = new StringBuffer("update DecorationApplication set DecorationApplication_Status=? where DecorationApplication_Id=?");
        i=jdbcTemplate.update(sql.toString(),new Object[]{d.getDecorationApplication_Status(),d.getDecorationApplication_Id()},
                new int[]{java.sql.Types.INTEGER, java.sql.Types.INTEGER});
        return i;
    }
    //更改装修记录时间
    public int updateDTime(int DecorationApplication_Id,Date DecorationApplication_EndTime){
        int i=0;
        StringBuffer sql = new StringBuffer("update DecorationApplication set DecorationApplication_EndTime=? where DecorationApplication_Id=?");
        i=jdbcTemplate.update(sql.toString(),DecorationApplication_EndTime,DecorationApplication_Id);
        return i;
    }
    //更改装修记录回复
    public int updateDReply(int DecorationApplication_Id,String DecorationApplication_Reply){
        int i=0;
        StringBuffer sql = new StringBuffer("update DecorationApplication set DecorationApplication_Reply = ? where DecorationApplication_Id = ?");
        i=jdbcTemplate.update(sql.toString(),DecorationApplication_Reply,DecorationApplication_Id);
        return i;
    }
    public int addDecoration(DecorationApplication decorationApplication){
        int i=0;
         int Id=decorationApplication.getId();
         System.out.println(Id);
         String logname=decorationApplication.getLogname();
         System.out.println(decorationApplication.getLogname());
         int Processor_Id=decorationApplication.getProcessor_Id();
         String DecorationApplication_Place=decorationApplication.getDecorationApplication_Place();
         Date DecorationApplication_StartTime=decorationApplication.getDecorationApplication_StartTime();
         Date DecorationApplication_EndTime=decorationApplication.getDecorationApplication_EndTime();
         int DecorationApplication_Status=decorationApplication.getDecorationApplication_Status();
         String DecorationApplication_Phone=decorationApplication.getDecorationApplication_Phone();
        StringBuffer sql=new StringBuffer("insert into DecorationApplication(Id,logname,Processor_Id,DecorationApplication_Place,DecorationApplication_StartTime,DecorationApplication_EndTime,DecorationApplication_Status,DecorationApplication_Phone) values("+Id+","+logname+","+Processor_Id+","+DecorationApplication_Place+","+DecorationApplication_StartTime+","+DecorationApplication_EndTime+","+DecorationApplication_Status+","+DecorationApplication_Phone+")");
        i=jdbcTemplate.update("insert into DecorationApplication(Id,logname,Processor_Id,DecorationApplication_Place,DecorationApplication_StartTime,DecorationApplication_EndTime,DecorationApplication_Status,DecorationApplication_Phone) values(?,?,?,?,?,?,?,?)"
        ,new Object[]{Id,logname,Processor_Id,DecorationApplication_Place,DecorationApplication_StartTime,DecorationApplication_EndTime,DecorationApplication_Status,DecorationApplication_Phone});
        return i;
    }
    public int editD(DecorationApplication decorationApplication){
        int Status=decorationApplication.getDecorationApplication_Status();
        int Id=decorationApplication.getDecorationApplication_Id();
        String r=decorationApplication.getDecorationApplication_Reply();
        int i=jdbcTemplate.update("update DecorationApplication set DecorationApplication_Status = ? where DecorationApplication_Id = ?"
                ,new Object[]{Status,Id});
        return i;
    }
    //查询特定记录
    public List<DecorationApplication> getDRecordById(int Id){
        List list1=jdbcTemplate.queryForList("SELECT * FROM DecorationApplication WHERE Id=?",Id);
        Iterator iterator=list1.iterator();
        return list1;
    }
    public int countD(){
        int DecorationApplication_Status=1;
        int i=jdbcTemplate.queryForObject("select count(*) from DecorationApplication where DecorationApplication_Status=?",
                new Object[]{DecorationApplication_Status},Integer.class);
        return i;
    }
//    public List<DecorationApplication> queryD(DecorationApplication d){
//        StringBuffer sql=new StringBuffer("");
//        sql.append("select * from DecorationApplication where ");
//        int i=0;
//        if (!"".equals(d.getDecorationApplication_Status())){
//            i=1;
//            sql.append("DecorationApplication_Status=").append(d.getDecorationApplication_Status());
//        }
//        if (d.getDecorationApplication_Id()!=0){
//            if(i==1) {
//                sql.append(" and DecorationApplication_Id=").append(d.getDecorationApplication_Id());
//            }
//            else {
//                i=1;
//                sql.append("DecorationApplication_Id=").append(d.getDecorationApplication_Id());
//            }
//        }
//        if(d.getDecorationApplication_StartTime()!=null){
//            if(i==1) {
//                sql.append(" and DecorationApplication_StartTime=").append(d.getDecorationApplication_StartTime());
//            }
//            else {
//                i=1;
//                sql.append("DecorationApplication_StartTime=").append(d.getDecorationApplication_StartTime());
//            }
//        }
//    }
}

