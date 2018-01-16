package com.ch.dao;

import com.ch.service.ALoginInterface;
import com.ch.pojo.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by Cxy on 2018/1/3.
 */
@Repository("ALoginDao")
public class ALoginDao {
    Logger log=LogManager.getLogger(ALoginDao.class);
    @Resource
    private JdbcTemplate jdbcTemplate;

    public Users aLogin(String username, String password) {
        StringBuffer sql = new StringBuffer("select logname,password,role_ids  from user where logname = ? and password = ?");
        final Users users = new Users();
        jdbcTemplate.query(
                sql.toString(),
                new Object[]{username,password},
                new RowCallbackHandler(){
                @Override
                public void processRow(java.sql.ResultSet rs)
										throws SQLException {
                    users.setName(rs.getString("logname"));
                    users.setPassword(rs.getString("password"));
                    users.setRole(rs.getString("role_ids"));
        }});
		return users;
    }
}
