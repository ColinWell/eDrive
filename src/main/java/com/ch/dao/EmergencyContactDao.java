package com.ch.dao;

import com.ch.pojo.EmergencyContact;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository("EmergencyContactDao")
public class EmergencyContactDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
    //查询所有联系人方式
    public List<EmergencyContact> getALLEmergencyContact(){
        List list=jdbcTemplate.queryForList("SELECT * FROM EmergencyContact");
        Iterator iterator=list.iterator();
        System.out.println(list);
        return list;
    }
    //查询特定紧急联系人
    public EmergencyContact getEmergencyContactById(int EmergencyContact_Id){
        final List<EmergencyContact> list=new ArrayList<EmergencyContact>();
        StringBuffer sql = new StringBuffer("select *  from EmergencyContact where EmergencyContact_Id = ?");
        final EmergencyContact emergencyContact =new EmergencyContact();
        jdbcTemplate.query(
                sql.toString(),
                new Object[]{EmergencyContact_Id},
                new RowCallbackHandler(){
                    @Override
                    public void processRow(java.sql.ResultSet rs)
                            throws SQLException {
                        emergencyContact.setEmergencyContact_Id(rs.getInt("EmergencyContact_Id"));
                        emergencyContact.setEmergencyContact_Name(rs.getString("EmergencyContact_Name"));
                        emergencyContact.setEmergencyContact_EndTime(rs.getTime("EmergencyContact_EndTime"));
                        emergencyContact.setEmergencyContact_StartTime(rs.getTime("EmergencyContact_StartTime"));
                        emergencyContact.setEmergencyContact_Phone(rs.getString("EmergencyContact_Phone"));
                        emergencyContact.setEmergencyContact_Type(rs.getInt("EmergencyContact_Type"));
                    }
                }
        );
        return emergencyContact;
    }
    //修改紧急联系人姓名
    public int updateEmergencyContact(int EmergencyContact_Id,String EmergencyContact_Name){
        EmergencyContact emergencyContact=new EmergencyContact();
        emergencyContact.setEmergencyContact_Id(EmergencyContact_Id);
        emergencyContact.setEmergencyContact_Name(EmergencyContact_Name);
        int i=0;
        StringBuffer sql1 = new StringBuffer("update EmergencyContact set EmergencyContact_Name=? where EmergencyContact_Id=?");
        i=jdbcTemplate.update(sql1.toString(),EmergencyContact_Name,EmergencyContact_Id);
        return i;
    }
    //真修改
    public int editEmergency(EmergencyContact emergencyContact){
        StringBuffer sqlBuffer=new StringBuffer("");
        String EmergencyContact_Name=emergencyContact.getEmergencyContact_Name();
        String EmergencyContact_Phone=emergencyContact.getEmergencyContact_Phone();
        String EmergencyContact_Time=emergencyContact.getEmergencyContact_Time();
        int EmergencyContact_Id=emergencyContact.getEmergencyContact_Id();
        System.out.println(EmergencyContact_Id+EmergencyContact_Name+EmergencyContact_Phone+EmergencyContact_Time);
        int i =jdbcTemplate.update("update EmergencyContact set EmergencyContact_Name = ? ,EmergencyContact_Phone = ?,EmergencyContact_Time = ? where EmergencyContact_Id = ?"
        ,new Object[]{EmergencyContact_Name,EmergencyContact_Phone,EmergencyContact_Time,EmergencyContact_Id});
        return i;
    }
    public int addEmergency(EmergencyContact emergencyContact){
        String EmergencyContact_Name=emergencyContact.getEmergencyContact_Name();
        String EmergencyContact_Phone=emergencyContact.getEmergencyContact_Phone();
        String EmergencyContact_Time=emergencyContact.getEmergencyContact_Time();
        System.out.println(EmergencyContact_Name+EmergencyContact_Phone+EmergencyContact_Time);
        int i=jdbcTemplate.update("insert into EmergencyContact(EmergencyContact_Name,EmergencyContact_Phone,EmergencyContact_Time) values (?,?,?)"
        ,new Object[]{EmergencyContact_Name,EmergencyContact_Phone,EmergencyContact_Time});
        return i;
    }
    public int delE(int id) {
        String sql = new String("delete from EmergencyContact where EmergencyContact_Id = " + id);
        int result = jdbcTemplate.update(sql);
        return result;
    }
}
