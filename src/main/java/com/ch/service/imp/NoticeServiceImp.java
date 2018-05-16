package com.ch.service.imp;

import com.ch.dao.NoticeDao;
import com.ch.pojo.Announcement;
import com.ch.pojo.Notice;
import com.ch.service.NoticeService;
import com.ch.vo.QueryAnnounceVO;
import com.ch.vo.QueryNoticeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Cxy on 2018/4/10.
 */
@Service("noticeService")
public class NoticeServiceImp implements NoticeService {

    @Resource
    NoticeDao noticeDao;

    @Override
    public int getNoticeTotal() {
        return noticeDao.getNoticeTotal();
    }

    @Override
    public List queryNotice(Map params) {
        String[] columnsName = { "id", "date", "content", "recUserName", "pubUserName","isRead" };
        QueryNoticeVO vo = new QueryNoticeVO();
        vo.setStart((Integer)params.get("start"));
        vo.setLength((Integer)params.get("length"));
        // get order column
        List orderList= (ArrayList)params.get("order");
        Map orderMap = (Map)orderList.get(0);
        int orderNum = (Integer) orderMap.get("column");
        vo.setOrderColumn(columnsName[orderNum]);
        vo.setOrderWay((String) orderMap.get("dir"));
        //
        Map searchMap = (Map)params.get("search");
        vo.setSearchVal((String) searchMap.get("value"));

        vo.setColumnName(columnsName);

        /**** multi conditions search  ***/
        vo.setDateBegin((String) params.get("dateBegin"));
        vo.setDateEnd((String)params.get("dateEnd"));
        vo.setRecUserName((String)params.get("recUserName"));
        vo.setPubUserName((String)params.get("pubUserName"));
        vo.setIsRead(Integer.valueOf((String)params.get("isRead")));
        return noticeDao.queryNotice(vo);
    }

    @Override
    public boolean pubNotice(Map params) {
        Notice notice = new Notice();
        notice.setContent((String)params.get("noticeContent"));
        notice.setPubUserId((String)params.get("pubUserId"));
        notice.setRecUserId((String)params.get("recUserId"));
        int result = noticeDao.pubNotice(notice);
        if(result == 1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int getAnnounceTotal() {
        return noticeDao.getAnnounceTotal();
    }

    @Override
    public List queryAnnounce(Map params) {
        String[] columnsName = { "id", "type", "date", "title", "content", "userName" };
        QueryAnnounceVO vo = new QueryAnnounceVO();
        vo.setStart((Integer)params.get("start"));
        vo.setLength((Integer)params.get("length"));
        // get order column
        List orderList= (ArrayList)params.get("order");
        Map orderMap = (Map)orderList.get(0);
        int orderNum = (Integer) orderMap.get("column");
        vo.setOrderColumn(columnsName[orderNum]);
        vo.setOrderWay((String) orderMap.get("dir"));
        //
        Map searchMap = (Map)params.get("search");
        vo.setSearchVal((String) searchMap.get("value"));

        vo.setColumnName(columnsName);

        /**** multi conditions search  ***/
        vo.setDateBegin((String) params.get("dateBegin"));
        vo.setDateEnd((String)params.get("dateEnd"));
        vo.setAnnounceType(Integer.valueOf((String)params.get("type")));
        vo.setUserName((String)params.get("userName"));
        return noticeDao.queryAnnounce(vo);
    }

    @Override
    public boolean pubAnnounce(Map params) {
        Announcement a = new Announcement();
        a.setType(Integer.valueOf((String)params.get("announceType")));
        a.setTitle((String)params.get("announceTitle"));
        a.setContent((String)params.get("announceContent"));
        a.setPubUserId((String)params.get("userId"));
        int result = noticeDao.pubAnnounce(a);
        if(result == 1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public List getAnnounceList() {
        return noticeDao.getAnnounceList();
    }

    @Override
    public List getNoticeList(String userId) {
        return noticeDao.getNoticeList(userId);
    }
}
