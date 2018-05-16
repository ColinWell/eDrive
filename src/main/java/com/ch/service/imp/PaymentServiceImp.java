package com.ch.service.imp;

import com.ch.dao.LoginDao;
import com.ch.dao.PaymentDao;
import com.ch.pojo.PaymentInfo;
import com.ch.service.PaymentService;
import com.ch.vo.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Cxy on 2018/3/17.
 */
@Service("paymentService")
public class PaymentServiceImp implements PaymentService {

    Logger log = LogManager.getLogger(LoginDao.class);
    @Resource
    JdbcTemplate jdbcTemplate;
    @Resource
    PaymentDao paymentDao;


    @Override
    public PaymentStateVO getPaymentState(String userId) {
        Calendar calendar = Calendar.getInstance();
        Date now = new Date();
        calendar.setTime(now);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        String dateBegin = year+"-"+month+"-"+1;
        return paymentDao.getPaymentState(userId,dateBegin);
    }

    @Override
    public List<PaymentInfo> getAPaymentInfo(String userId, String date) {
        return null;
    }

    @Override
    public PaymentInfo getPaymentDetail(String userId, String OrderId) {
        return null;
    }

    @Override
    public List queryPaymentInfo(Map params) {
        QueryPaymentVO vo = new QueryPaymentVO();
        /**** multi conditions search  ***/
        vo.setDateBegin((String)params.get("dateBegin"));
        vo.setDateEnd((String)params.get("dateEnd"));
        try{
            vo.setType(Integer.valueOf((String)params.get("paymentType")));
            vo.setState(Integer.valueOf((String)params.get("paymentState")));
        }catch(IllegalFormatConversionException ex){
            log.warn("Illegal Format Conversion!");
            vo.setType(0);
            vo.setState(2);
        }
        vo.setRoomId((String)params.get("roomId"));
        vo.setRoomName((String)params.get("roomName"));
        String[] columnsName = { "id","roomId", "roomName", "date", "type", "state" };

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

        return paymentDao.queryPaymentInfo(vo);
    }

    @Override
    public List queryPaymentRule(Map params) {
        String[] columnsName = { "id", "rule_name", "rule_version", "rule_date", "rule_fee" };
        QueryRuleVO vo = new QueryRuleVO();
        vo.setStart((Integer)params.get("start"));
        vo.setLength((Integer)params.get("length"));
        // get order column
        List orderList= (ArrayList)params.get("order");
        Map orderMap = (Map)orderList.get(0);
        int orderNum = (Integer) orderMap.get("column");
        vo.setOrderColumn(columnsName[orderNum]);
        vo.setOrderWay((String) orderMap.get("dir"));

        Map searchMap = (Map)params.get("search");
        vo.setSearchVal((String) searchMap.get("value"));

        vo.setColumnName(columnsName);

        /**** multi conditions search  ***/
        vo.setRuleDateBegin((String) params.get("dateBegin"));
        vo.setRuleDateEnd((String)params.get("dateEnd"));
        vo.setRuleType(Integer.valueOf((String)params.get("ruleType")));
        int version;
        try{
            version = Integer.valueOf((String)params.get("ruleVersion"));
        }catch (Exception e){
            version = 0;
        }
        vo.setRuleVersion(version);
        return paymentDao.queryPaymentRule(vo);
    }

    @Override
    public int getNextVersion(String ruleType) {
        return paymentDao.getNextVersion(ruleType);
    }

    @Override
    public boolean isVersionExist(String ruleType, String ruleVersion) {
        if(paymentDao.isVersionExit(ruleType,ruleVersion) == 1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String getRuleNameByType(int ruleType) {
        String[] ruleNames = new String[]{"ALL","物业费","公共水电费","停车费"};
        return ruleNames[ruleType];
    }

    @Override
    public boolean addRule(Map params) {
        RuleVO vo = new RuleVO();
        vo.setRuleVersion(Integer.valueOf((String) params.get("ruleVersion")));
        vo.setPubUserId((String)params.get("userId"));
        vo.setRuleName((String)params.get("ruleName"));
        vo.setRuleType(Integer.valueOf((String)params.get("ruleType")));
        vo.setRuleFee(Double.valueOf((String)params.get("ruleFee")));
        if(paymentDao.addRule(vo)==1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean editRuleFee(String id, String fee) {
        if(paymentDao.editRuleFee(id,fee) == 1){
            return true;
        }
        else{
            return false;
        }
    }


    @Override
    public boolean eidtPaymentInterface(String newInterface) {
        return false;
    }

    @Override
    public int getRuleTotal() {
        return paymentDao.getRuleTotal();
    }

    @Override
    public List queryRuleDis() {
        return paymentDao.queryRuleDis();
    }

    @Override
    public boolean editRuleDis(Map params) {
        String id = (String)params.get("id");
        RuleDisVO vo = new RuleDisVO();
        vo.setId(id);
        vo.setPropertyVer(Integer.valueOf((String)params.get("propertyVer")));
        vo.setUtilitiesVer(Integer.valueOf((String)params.get("utilitiesVer")));
        vo.setParkingVer(Integer.valueOf((String)params.get("parkingVer")));
        int result = paymentDao.editRuleDis(vo);
        if(result == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List queryUserAndRoom() {
        return paymentDao.queryUserAndRoom();
    }

    @Override
    public boolean isRelationExist(String userId, String roomId) {
        int result = paymentDao.isRelationExist(userId,roomId);
        if(result>0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean editUserAndRoom(Map params) {
        return false;
    }

    @Override
    public boolean addRelation(Map params) {
        String userId = (String)params.get("userId");
        String roomId = (String)params.get("roomId");
        int result = paymentDao.addRuleRelation(roomId,userId);
        if(result == 1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean delUR(String id) {
        int result = paymentDao.delUR(id);
        if(result == 1){
            return true;
        }
        else{
            return false;
        }
    }
    /* -------------------------------------------------*/
    @Override
    public double getRuleFee(int roomId, int ruleType) {
        String[] ruleVers = new String[]{"property_ver","utilities_ver","parking_ver"};
        int ruleVer = paymentDao.getRuleVer(roomId,ruleVers[ruleType-1]);
        double fee;
        if(ruleVer == 0){
            fee = -1;
        }
        else {
            fee = paymentDao.getRuleVal(ruleVer, ruleType);
        }
        return fee;
    }

    @Override
    public int getRoomId(String userName) {
        return paymentDao.getRoomId(userName);
    }

    @Override
    public boolean pay(Map params) {
        PaymentVO vo = new PaymentVO();


        return false;
    }

    @Override
    public String getLatestPropertyDate(String roomId) {
        return paymentDao.getLatestPropertyDate(roomId);
    }

    @Override
    public String getLatestUtilitiesDate(String roomId) {
        return null;
    }

    @Override
    public String getLatestParkingDate(String roomId) {
        return null;
    }

    @Override
    public List getOrderList(String userId,String roomId) {
        return paymentDao.getOrderList(userId,roomId);
    }


}
