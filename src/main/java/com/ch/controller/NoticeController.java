package com.ch.controller;

import com.alibaba.fastjson.JSONObject;
import com.ch.service.LoginService;
import com.ch.service.NoticeService;
import com.ch.vo.DataTableVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Cxy on 2018/4/10.
 */
@Controller
@RequestMapping(value = "notice")
public class NoticeController {
    Logger log = LogManager.getLogger(UserController.class);

    @Resource
    NoticeService noticeService;

    @Resource
    LoginService loginService;

    @RequestMapping(value="/pubNotice",method = RequestMethod.POST)// 获取费用支付情况
    @ResponseBody
    public Object pubNotice(@RequestBody JSONObject dataMap, HttpServletRequest req){
        return noticeService.pubNotice(dataMap);
    }

    @RequestMapping(value="/queryNotice",method = RequestMethod.POST)// 获取费用支付情况
    @ResponseBody
    public Object queryNotice(@RequestBody JSONObject dataMap, HttpServletRequest req){
        Map params = dataMap;
        DataTableVO result = new DataTableVO<String>();
        int draw = (Integer)params.get("draw");
        int recordsTotal = noticeService.getNoticeTotal();
        int recordsFiltered = recordsTotal;
        List data =  noticeService.queryNotice(params);
        result.setDraw(draw+1);
        result.setRecordsTotal(recordsTotal);
        result.setRecordsFiltered(recordsFiltered);
        result.setData(data);
        log.info(result.toString());
        return result;
    }
    
    @RequestMapping(value="/pubAnnounce",method = RequestMethod.POST)// 获取费用支付情况
    @ResponseBody
    public Object pubAnnounce(@RequestBody JSONObject dataMap, HttpServletRequest req){
        String userId = loginService.getIdByName(loginService.getUserName());
        dataMap.put("userId",userId);
        return noticeService.pubAnnounce(dataMap);
    }

    @RequestMapping(value="/queryAnnounce",method = RequestMethod.POST)// 获取费用支付情况
    @ResponseBody
    public Object queryAnnounce(@RequestBody JSONObject dataMap, HttpServletRequest req){
        Map params = dataMap;
        DataTableVO result = new DataTableVO<String>();
        int draw = (Integer)params.get("draw");
        int recordsTotal = noticeService.getAnnounceTotal();
        int recordsFiltered = recordsTotal;
        List data =  noticeService.queryAnnounce(params);
        result.setDraw(draw+1);
        result.setRecordsTotal(recordsTotal);
        result.setRecordsFiltered(recordsFiltered);
        result.setData(data);
        log.info(result.toString());
        return result;
    }

    /* -------------------------- 手机端接口 ----------------------*/
    @RequestMapping(value = "/getAnnounceList", method = RequestMethod.GET)
    @ResponseBody
    public Object queryPaymentRule(HttpServletRequest req, HttpServletResponse res) {
        Map result = new HashMap();
        List data = noticeService.getAnnounceList();
        result.put("result", true);
        result.put("data", data);
        return result;
    }

    @RequestMapping(value = "/getNoticeList", method = RequestMethod.GET)
    @ResponseBody
    public Object getNoticeList(HttpServletRequest req, HttpServletResponse res) {
        Map result = new HashMap();
        String userId = req.getParameter("userId");
        List data = noticeService.getNoticeList(userId);
        result.put("result", true);
        result.put("data", data);
        return result;
    }
}
