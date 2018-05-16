package com.ch.service;
import java.util.List;
import java.util.Map;

/**
 * Created by Cxy on 2018/4/10.
 */
public interface NoticeService {

    int getNoticeTotal();
    List queryNotice(Map params);
    boolean pubNotice(Map params);

    int getAnnounceTotal();
    List queryAnnounce(Map params);
    boolean pubAnnounce(Map params);

    List getAnnounceList();

    List getNoticeList(String userId);
}
