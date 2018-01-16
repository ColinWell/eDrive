package com.ch.service.imp;

import com.ch.dao.SecurityTestDao;
import com.ch.pojo.URLResource;
import com.ch.pojo.Users;
import com.ch.service.SecurityTestInterface;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Cxy on 2018/1/16.
 */
@Service("securityService")
public class SecurityTestInterfaceImp implements SecurityTestInterface {
    Logger log = LogManager.getLogger(SecurityTestDao.class);
    @Resource
    private SecurityTestDao dao;
    @Resource
    private JdbcTemplate jdbcTamplate;

    public boolean getinput() {
        log.info("getinput");
        return true;
    }

    public boolean geoutput() {
        log.info("geoutput");
        return true;
    }

    public boolean addreport_admin() {
        log.info("addreport_admin");
        return true;
    }

    public boolean deletereport_admin() {
        log.info("deletereport_admin");
        return true;
    }

    public Users findbyUsername(String name) {
        Users users = new Users();
        users = dao.findByUsername(name);
        log.info(users.getName()+"    "+users.getPassword()+"    "+users.getRole());
        return users;
    }

    @Override
    public void user_login() {
        log.info("拥有ROLE_USER权限的方法访问：user_login");
    }

    @Override
    //获取所有资源链接
    public List<URLResource> findResource() {
        // 保存所有资源
        List<URLResource> uRLResources =new ArrayList<URLResource>();
        uRLResources = dao.findResource();

        Gson gson =new Gson();
        log.info("权限资源对应关系："+gson.toJson(uRLResources));
        return uRLResources;
    }
}
