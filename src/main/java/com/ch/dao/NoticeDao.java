package com.ch.dao;

import com.ch.pojo.Announcement;
import com.ch.pojo.Notice;
import com.ch.vo.QueryAnnounceVO;
import com.ch.vo.QueryNoticeVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Cxy on 2018/4/10.
 */
@Repository("noticeDao")
public class NoticeDao {
    @Resource
    JdbcTemplate jdbcTemplate;

    Logger log = LogManager.getLogger(NoticeDao.class);

    public int getNoticeTotal(){
        return jdbcTemplate.queryForObject("select count(1) from notice;",Integer.class);
    }

    public List queryNotice(QueryNoticeVO vo){
        //分页相关
        int pageSize = vo.getLength();
        int rowNumFrom = vo.getStart();
        int rowNumTo = rowNumFrom + pageSize;

        StringBuffer sqlBuffer = new StringBuffer("");
        sqlBuffer.append("select a.id,")
                .append(" a.pub_date date,")
                .append(" a.notice_content content,")
                .append(" u1.logname recUserName,")
                .append(" u.logname pubUserName,")
                .append(" a.is_read isRead")
                .append(" from notice a ")
                .append(" left join user u ")
                .append(" on a.pub_user_id = u.id ")
                .append(" left join user u1 ")
                .append(" on a.rec_user_id = u1.id ")
                .append(" where 1=1 ");
        String searchValue = vo.getSearchVal();
        if (!StringUtils.isEmpty(vo.getPubUserName())) {
            sqlBuffer.append(" and u.logname like '" + vo.getPubUserName() + "' ");
        }
        if (!StringUtils.isEmpty(vo.getRecUserName())) {
            sqlBuffer.append(" and u1.logname like '" + vo.getRecUserName() + "' ");
        }
        if (vo.getIsRead()!=2) {
            sqlBuffer.append(" and a.is_read = " + vo.getIsRead() + " ");
        }
        if (!StringUtils.isEmpty(searchValue)) {
            StringBuffer temp = new StringBuffer(" ");
            for (String column : vo.getColumnName()) {
                temp.append(column + " like '%" + searchValue + "%' or ");
            }
            sqlBuffer.append(temp.substring(0, temp.length() - 3));
        }

        sqlBuffer.append(" order by " + vo.getOrderColumn() + " " + vo.getOrderWay() + " ");
        sqlBuffer.append(" limit " + rowNumFrom + "," + rowNumTo + " ");
        log.info(sqlBuffer.toString());
        List list = jdbcTemplate.queryForList(sqlBuffer.toString());
        return list;
    }

    public int pubNotice(Notice a){
        int result = jdbcTemplate.update("insert into notice(pub_user_id,rec_user_id,notice_content) values(?,?,?);",new Object[]{a.getPubUserId(),a.getRecUserId(),a.getContent()});
        return result;
    }

    
    public int getAnnounceTotal(){
        return jdbcTemplate.queryForObject("select count(1) from announcement;",Integer.class);
    }

    public List queryAnnounce(QueryAnnounceVO vo){
        //分页相关
        int pageSize = vo.getLength();
        int rowNumFrom = vo.getStart();
        int rowNumTo = rowNumFrom + pageSize;

        StringBuffer sqlBuffer = new StringBuffer("");
        sqlBuffer.append("select a.id,")
                .append(" a.pub_type type,")
                .append(" a.pub_date date,")
                .append(" a.pub_title title,")
                .append(" a.pub_content content,")
                .append(" u.logname userName")
                .append(" from announcement a ")
                .append(" left join user u ")
                .append(" on a.pub_user_id = u.id ")
                .append(" where 1=1 ");
        String searchValue = vo.getSearchVal();
        if (vo.getAnnounceType() != 0) {
            sqlBuffer.append(" and a.pub_type = " + vo.getAnnounceType() + " ");
        }
        if (!StringUtils.isEmpty(vo.getUserName())) {
            sqlBuffer.append(" and u.logname like '" + vo.getUserName() + "' ");
        }
        if (!StringUtils.isEmpty(searchValue)) {
            StringBuffer temp = new StringBuffer(" ");
            for (String column : vo.getColumnName()) {
                temp.append(column + " like '%" + searchValue + "%' or ");
            }
            sqlBuffer.append(temp.substring(0, temp.length() - 3));
        }

        sqlBuffer.append(" order by " + vo.getOrderColumn() + " " + vo.getOrderWay() + " ");
        sqlBuffer.append(" limit " + rowNumFrom + "," + rowNumTo + " ");
        log.info(sqlBuffer.toString());
        List list = jdbcTemplate.queryForList(sqlBuffer.toString());
        return list;
    }

    public int pubAnnounce(Announcement a){
        int result = jdbcTemplate.update("insert into announcement(pub_user_id,pub_title,pub_content,pub_type) values(?,?,?,?);",new Object[]{a.getPubUserId(),a.getTitle(),a.getContent(),a.getType()});
        return result;
    }

    public List getAnnounceList(){
        StringBuffer sqlBuffer = new StringBuffer("");
        sqlBuffer.append("select a.pub_title title,a.pub_content content,a.pub_date date,a.pub_type type,u.nick_name ")
                .append(" from announcement a ")
                .append(" left join user u ")
                .append(" on a.pub_user_id = u.id ")
                .append(" order by date desc ");
        List list = jdbcTemplate.queryForList(sqlBuffer.toString());
        return list;
    }
    public List getNoticeList(String userId){
        StringBuffer sqlBuffer = new StringBuffer("");
        sqlBuffer.append("select n.ID id,n.notice_content content,n.pub_date date ")
                .append(" from notice n ")
                .append(" where n.rec_user_id = ?")
                .append(" order by date desc ");
        List list = jdbcTemplate.queryForList(sqlBuffer.toString(),new Object[]{userId});
        return list;
    }

}
