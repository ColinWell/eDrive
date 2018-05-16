package com.ch.dao;

import com.ch.BaseJunit4Test;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;

/**
 * Created by Cxy on 2018/4/13.
 */
public class UserDaoTest extends BaseJunit4Test {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private UserDao userDao;

    @Test
    @Rollback(value = true)
    public void test(){
        userDao.getUserInfo("1");
    }
}
