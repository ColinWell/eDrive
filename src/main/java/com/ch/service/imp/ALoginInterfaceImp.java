package com.ch.service.imp;

import com.ch.dao.ALoginDao;
import com.ch.pojo.Users;
import com.ch.service.ALoginInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by Cxy on 2018/1/16.
 */
@Service("aLoginService")
public class ALoginInterfaceImp implements ALoginInterface {
    Logger log= LogManager.getLogger(ALoginDao.class);
    @Resource
    private ALoginDao dao;
    @Override
    public boolean aLogin(String username, String password) {
        Users users = new Users();
        users = dao.aLogin(username,password);
        log.info(users.getName()+"    "+users.getPassword()+"    "+users.getRole());
        if (users.getName()==null)
            return false;
        else
            return true;
    }
}
