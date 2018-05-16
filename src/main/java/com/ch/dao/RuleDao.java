package com.ch.dao;

import com.ch.pojo.Rule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;

@Repository("RuleDao")
public class RuleDao {
    org.apache.logging.log4j.Logger log= org.apache.logging.log4j.LogManager.getLogger(RuleDao.class);
    @Resource
    private JdbcTemplate jdbcTemplate;

    //查询细则
    public Rule getRule(String Rule_Name){
        StringBuffer sql = new StringBuffer("select Rule_Main  from Rule where Rule_Name= ?");
        final Rule rule=new Rule();
        jdbcTemplate.query(
                sql.toString(),
                new Object[]{Rule_Name},
                new RowCallbackHandler(){
                    @Override
                    public void processRow(java.sql.ResultSet rs)
                            throws SQLException {
                        rule.setRule_Name(rs.getString("Rule_Name"));
                        rule.setRule_Main(rs.getString("Rule_Main"));
                    }});
        return rule;
    }
    //修改细则
    public int setRule(String Rule_Name,String Rule_Main){
        int i=0;
        StringBuffer sql = new StringBuffer("update Rule set Rule_Main=? where Rule_Name=?");
        i=jdbcTemplate.update(sql.toString(),Rule_Main,Rule_Name);
        return i;
    }



}
