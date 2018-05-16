package com.ch.service;

import com.alibaba.fastjson.JSONObject;
import com.ch.pojo.PaymentInfo;
import com.ch.vo.PaymentStateVO;

import java.util.List;
import java.util.Map;

/**
 * 有关缴费相关的接口
 * Created by Cxy on 2018/3/17.
 */
public interface PaymentService {



    PaymentStateVO getPaymentState(String userId);

    // 手机端获取支付账单信息
    List<PaymentInfo> getAPaymentInfo(String userId,String date);

    // 获取某个支付订单详情
    PaymentInfo getPaymentDetail(String userId,String OrderId);

    // 查询物业缴费情况
    List queryPaymentInfo(Map params);

    // property parking waterAndE
    List queryPaymentRule(Map params);

    int getNextVersion(String ruleType);

    boolean isVersionExist(String ruleType,String ruleVersion);

    String getRuleNameByType(int ruleType);

    boolean addRule(Map params);

    boolean editRuleFee(String id,String fee);


    boolean eidtPaymentInterface(String newInterface);

    int getRuleTotal();

    List queryRuleDis();

    boolean editRuleDis(Map params);

    List queryUserAndRoom();

    boolean isRelationExist(String userId,String roomId);

    boolean addRelation(Map params);

    boolean editUserAndRoom(Map params);

    boolean delUR(String id);

    /* ------------------------ 手机端接口 ------------------------- */
    double getRuleFee(int roomId ,int ruleType);

    int getRoomId(String userName);

    boolean pay(Map params);

    String getLatestPropertyDate(String roomId);

    String getLatestUtilitiesDate(String roomId);

    String getLatestParkingDate(String roomId);

    List getOrderList(String userId,String roomId);


}
