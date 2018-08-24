package com.edrive.service.imp;

import com.edrive.dao.LoginDao;
import com.edrive.pojo.URLResource;
import com.edrive.pojo.Users;
import com.edrive.service.LoginService;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Cxy on 2018/1/16.
 */
@Service("loginService")
public class LoginServiceImp implements LoginService {
    Logger log = LogManager.getLogger(LoginDao.class);
    @Resource
    private LoginDao loginDao;
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

    @Override
    public String getUserName() {
        String userName = null;
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
            userName = userDetails.getUsername();
        }catch (ClassCastException e){
            log.info(e.getMessage());
        }
        return userName;
    }

    // username --> logname
    @Override
    public String getIdByName(String username) {
        return loginDao.getIdByName(username);
    }
    @Override
    public Users findByUsername(String name) {
        Users users = new Users();
        users = loginDao.findByUsername(name);
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
        uRLResources = loginDao.findResource();

        Gson gson =new Gson();
        log.info("Correspondence between resource permissions:"+gson.toJson(uRLResources));
        return uRLResources;
    }
}
