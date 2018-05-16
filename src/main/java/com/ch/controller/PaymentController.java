package com.ch.controller;

import com.alibaba.fastjson.JSONObject;
import com.ch.service.LoginService;
import com.ch.service.PaymentService;
import com.ch.vo.DataTableVO;
import com.ch.vo.PaymentStateVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cxy on 2018/3/17.
 */

@Controller
@RequestMapping(value = "/payment")
public class PaymentController {

    Logger log = LogManager.getLogger(PaymentController.class);
    @Resource
    private PaymentService paymentService;
    @Resource
    private LoginService loginService;
    /* --------------- 缴费相关 -------------------*/
    @RequestMapping(value="/queryPaymentInfo",method = RequestMethod.POST)// 获取费用支付情况
    @ResponseBody
    public Object queryPaymentInfo(@RequestBody JSONObject dataMap, HttpServletRequest req){
        Map params = dataMap;
        DataTableVO result = new DataTableVO<String>();
        int draw = (Integer)params.get("draw");
        int recordsTotal = paymentService.getRuleTotal();
        int recordsFiltered = paymentService.getRuleTotal();
        List data =  paymentService.queryPaymentInfo(params);
        result.setDraw(draw+1);
        result.setRecordsTotal(recordsTotal);
        result.setRecordsFiltered(recordsFiltered);
        result.setData(data);
        log.info(result.toString());
        return result;
    }

    @RequestMapping(value="/getPaymentState",method = RequestMethod.GET)// 获取费用支付情况
    @ResponseBody
    public Object getPaymentState(HttpServletRequest req,HttpServletResponse res){
        String userId = req.getParameter("userId");
        return paymentService.getPaymentState(userId);
    }


    /* --------------- 缴费规则相关 --------------------*/
    @RequestMapping(value="/queryPaymentRule",method = RequestMethod.POST)// 获取缴费规则
    @ResponseBody
    public Object queryPaymentRule(@RequestBody JSONObject dataMap, HttpServletRequest req){
        Map params = dataMap;
        DataTableVO result = new DataTableVO<String>();
        int draw = (Integer)params.get("draw");
        int recordsTotal = paymentService.getRuleTotal();
        int recordsFiltered = paymentService.getRuleTotal();
        List data =  paymentService.queryPaymentRule(params);
        result.setDraw(draw+1);
        result.setRecordsTotal(recordsTotal);
        result.setRecordsFiltered(recordsFiltered);
        result.setData(data);
        log.info(result.toString());
        return result;
    }
    @RequestMapping(value="/addRule",method = RequestMethod.POST)// 获取缴费规则
    @ResponseBody
    public Object addRule(@RequestBody JSONObject dataMap, HttpServletRequest req){
        String ruleVersion = (String)dataMap.get("ruleVersion");
        String ruleType = (String)dataMap.get("ruleType");
        if(StringUtils.isEmpty(ruleVersion)){
            dataMap.put("ruleVersion",String.valueOf(paymentService.getNextVersion(ruleType)));
        }
        if(paymentService.isVersionExist(ruleType,ruleVersion)){
            dataMap.put("ruleVersion",String.valueOf(paymentService.getNextVersion(ruleType)));
        }
        String userId = loginService.getIdByName(loginService.getUserName());
        dataMap.put("userId",userId);
        dataMap.put("ruleName",paymentService.getRuleNameByType(Integer.valueOf(ruleType)));
        boolean result = paymentService.addRule(dataMap);
        if(result){
            return true;
        }
        else{
            return false;
        }
    }

    @RequestMapping(value="/editRuleFee",method = RequestMethod.POST)// 获取缴费规则
    @ResponseBody
    public Object editRuleFee(HttpServletRequest req,HttpServletResponse res){
        String id = (String)req.getParameter("id");
        String fee = (String)req.getParameter("fee");
        return paymentService.editRuleFee(id,fee);
    }

    /* -------------------------缴费规则分配相关-------------------*/
    @RequestMapping(value="/queryRuleDis",method = RequestMethod.POST)// 查询缴费规则分配
    @ResponseBody
    public Object queryRuleDis(HttpServletRequest req, HttpServletResponse res){
        return paymentService.queryRuleDis();
    }

    @RequestMapping(value="/editRuleDis",method = RequestMethod.POST)// 修改缴费规则分配
    @ResponseBody
    public Object editRuleDis(@RequestBody JSONObject dataMap, HttpServletRequest req){
        boolean result = paymentService.editRuleDis(dataMap);
        return result;
    }

    /* ------------------------ 用户版本分配相关 -------------------*/
    @RequestMapping(value="/queryUserAndRoom",method = RequestMethod.POST)// 获取费用支付情况
    @ResponseBody
    public Object queryUserAndRoom(HttpServletRequest req, HttpServletResponse res){
        return paymentService.queryUserAndRoom();
    }

    @RequestMapping(value="/addRelation",method = RequestMethod.POST)// 获取费用支付情况
    @ResponseBody
    public Object addRelation(@RequestBody JSONObject dataMap,HttpServletRequest req){
        String userId = dataMap.getString("userId");
        String roomId = dataMap.getString("roomId");
        if(paymentService.isRelationExist(userId,roomId)){
            return false;
        }
        else {
            return paymentService.addRelation(dataMap);
        }
    }


    @RequestMapping(value="/editUserAndRoom",method = RequestMethod.POST)// 获取费用支付情况
    @ResponseBody
    public Object editUserAndRoom(@RequestBody JSONObject dataMap, HttpServletRequest req){
        return paymentService.editUserAndRoom(dataMap);
    }

    @RequestMapping(value="/delUR",method = RequestMethod.GET)
    @ResponseBody
    public Object delUR(HttpServletRequest req , HttpServletResponse res){
        String id = req.getParameter("id");
        log.info(id);
        boolean result = paymentService.delUR(id);
        return result;
    }

    /* ------------------------------- 手机端 ----------------------------------- */
    @RequestMapping(value="/getRuleVer",method = RequestMethod.GET)
    @ResponseBody
    public Object getRuleVer(HttpServletRequest req ,HttpServletResponse res){
        String userName = req.getParameter("userName");
        int roomId = paymentService.getRoomId(userName);
        int ruleType = Integer.valueOf(req.getParameter("ruleType"));
        double fee = paymentService.getRuleFee(roomId,ruleType);      // -1 则表示为分配版本
        Map result = new HashMap<String,String>();
        if(fee == -1){
            result.put("result",false);
        }
        else{
            result.put("result",true);
        }
        result.put("ruleFee",fee);
        return result;
    }

    @RequestMapping(value="/pay",method = RequestMethod.POST)// 支付
    @ResponseBody
    public Object pay(@RequestBody JSONObject dataMap, HttpServletRequest req){
        return paymentService.pay(dataMap);
    }

    @RequestMapping(value = "/getOrderList", method = RequestMethod.GET)
    @ResponseBody
    public Object queryPaymentRule(HttpServletRequest req, HttpServletResponse res) {
        Map result = new HashMap();
        String userId = req.getParameter("userId");
        String roomId = req.getParameter("roomId");
        List data = paymentService.getOrderList(userId,roomId);
        result.put("result", true);
        result.put("data", data);
        return result;
    }
}
