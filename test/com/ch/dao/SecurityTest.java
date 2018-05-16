package com.ch.dao;

import com.ch.BaseJunit4Test;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;

/**
 * Created by Cxy on 2018/1/16.
 */
public class SecurityTest extends BaseJunit4Test{
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private LoginDao loginDao;
    @Test
    @Rollback(value = true)
    public void test(){
//        String sql = "insert into user(logname,password,role_ids) values('Colin','123456','ROLE_ADMIN')";
//        jdbcTemplate.execute(sql);
        loginDao.findResource();
    }
}
