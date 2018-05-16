package com.ch.dao;

import com.ch.common.utils.MyDateUtil;
import com.ch.pojo.PaymentInfo;
import com.ch.vo.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Cxy on 2018/3/17.
 */
@Repository("paymentDao")
public class PaymentDao {
    Logger log = LogManager.getLogger(ALoginDao.class);
    @Resource
    private JdbcTemplate jdbcTemplate;

    public PaymentStateVO getPaymentState(String userId, String dateBegin) {
        PaymentStateVO vo = new PaymentStateVO();
        int counter = 0;
        counter = jdbcTemplate.queryForObject("select count(1) from user_room_rela r left join room_state s on r.room_id = s.room_id where user_id = ? and date >= ? and payment_type = 1 and payment_state = 1;", new Object[]{userId,dateBegin}, Integer.class);
        if (counter > 0) {
            vo.setPropertyState(1);
        } else {
            vo.setPropertyState(0);
        }
        counter = 0;
        counter = jdbcTemplate.queryForObject("select count(1) from user_room_rela r left join room_state s on r.room_id = s.room_id where user_id = ? and date >= ? and payment_type = 2 and payment_state = 1;", new Object[]{userId,dateBegin}, Integer.class);
        if (counter > 0) {
            vo.setUtilitiesState(1);
        }else{
            vo.setUtilitiesState(0);
        }

        counter = 0;
        counter = jdbcTemplate.queryForObject("select count(1) from user_room_rela r left join room_state s on r.room_id = s.room_id where user_id = ? and date >= ? and payment_type = 3 and payment_state = 1;", new Object[]{userId,dateBegin}, Integer.class);
        if (counter > 0) {
            vo.setParkingState(1);
        }
        else{
            vo.setParkingState(0);
        }
        return vo;
    }


    // 手机端获取支付账单信息
    List getAPaymentInfo(String userId, String date1, String date2) {
        String sql = new String("select * from payment p where p.pay_user_id = ? and p.pay_date between ? and ?;");
        List paymentInfoList = jdbcTemplate.queryForList(sql, new Object[]{userId, date1, date2});
        return paymentInfoList;
    }

    // 获取某个支付订单详情
    public PaymentInfo getPaymentDetail(String userId, String orderId) {
        String sql = new String("select * from payment p where p.pay_id = ? and p.pay_user_id = ?;");
        PaymentInfo paymentInfo = jdbcTemplate.queryForObject(sql, new Object[]{orderId, userId}, PaymentInfo.class);
        return paymentInfo;
    }

    // 查询物业缴费情况
    public List queryPaymentInfo(QueryPaymentVO vo) {
        //分页相关
        int pageSize = vo.getLength();
        int rowNumFrom = vo.getStart();
        int rowNumTo = rowNumFrom + pageSize;

        // 获取参数
        String roomId = vo.getRoomId();
        String roomName = vo.getRoomName();
        int payType = vo.getType();
        int payState = vo.getState();
        String dateBegin = vo.getDateBegin();
        String dateEnd = vo.getDateEnd();
        // 拼接SQL
        StringBuffer sqlBuffer = new StringBuffer("");
        sqlBuffer.append("select r.id ,r.room_id roomId,")
                .append(" i.room_name roomName,")
                .append(" r.date, ")
                .append(" r.payment_type type,")
                .append(" r.payment_state state")
                .append(" from room_state r ")
                .append(" left join room_info i on r.room_id = i.room_id ")
                .append(" where 1=1 ");
        if (!StringUtils.isEmpty(roomId)) {
            sqlBuffer.append(" and r.room_id = ").append(roomId);
        }
        if (!StringUtils.isEmpty(roomName)) {
            sqlBuffer.append(" and i.room_name like '").append(roomName).append("' ");
        }

        if (payType != 0) {
            sqlBuffer.append(" and r.payment_type = ").append(payType);
        }
        // 0:未缴费  1:已缴费  2:全部
        if (payState == 0 || payState == 1) {
            sqlBuffer.append(" and r.payment_state = ").append(payState);
        }
        if (!StringUtils.isEmpty(dateBegin) && !StringUtils.isEmpty(dateEnd)) {
            sqlBuffer.append(" and r.date between '")
                    .append(dateBegin)
                    .append("' and '")
                    .append(dateEnd)
                    .append("' ");
        } else if (!StringUtils.isEmpty(dateBegin) && StringUtils.isEmpty(dateEnd)) {
            sqlBuffer.append(" and r.date > '")
                    .append(dateBegin)
                    .append("' ");
        } else if (StringUtils.isEmpty(dateBegin) && !StringUtils.isEmpty(dateEnd)) {
            sqlBuffer.append(" and r.date < '")
                    .append(dateEnd)
                    .append("' ");
        }
        String searchValue = vo.getSearchVal();
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
        log.info(list.toString());
        return list;
    }

    // property parking waterAndE
    public List queryPaymentRule(QueryRuleVO vo) {
        //分页相关
        int pageSize = vo.getLength();
        int rowNumFrom = vo.getStart();
        int rowNumTo = rowNumFrom + pageSize;

        StringBuffer sqlBuffer = new StringBuffer("");
        sqlBuffer.append("select r.id,")
                .append(" r.rule_name ruleName,")
                .append(" r.rule_version ruleVersion,")
                .append(" r.rule_date ruleDate,")
                .append(" r.rule_fee ruleFee,")
                .append(" r.rule_type ruleType")
                .append(" from payment_rule r ")
                .append(" where 1=1 ");
        String searchValue = vo.getSearchVal();
        if (vo.getRuleType() != 0) {
            sqlBuffer.append(" and r.rule_type = " + vo.getRuleType() + " ");
        }
        if (vo.getRuleVersion() != 0) {
            sqlBuffer.append(" and r.rule_version = " + vo.getRuleVersion() + " ");
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

    public int isVersionExit(String ruleType, String ruleVersion) {
        String sql = new String("select 1 from payment_rule where rule_version = ? and rule_type = ?");
        int result;
        try {
            result = jdbcTemplate.queryForObject(sql, new Object[]{ruleVersion, ruleType}, Integer.class);
        } catch (EmptyResultDataAccessException ex) {
            result = 0;
        }
        return result;
    }

    public int getNextVersion(String ruleType) {
        String sql = new String("select max(rule_version) from payment_rule where rule_type = ?");
        int maxVersion = jdbcTemplate.queryForObject(sql, new Object[]{ruleType}, Integer.class);
        return maxVersion + 1;
    }

    public int addRule(RuleVO vo) {
        String sql = new String("insert into payment_rule(rule_version,pub_user_id,rule_name,rule_type,rule_fee) values(?,?,?,?,?)");
        int result = jdbcTemplate.update(sql, new Object[]{vo.getRuleVersion(), vo.getPubUserId(), vo.getRuleName(), vo.getRuleType(), vo.getRuleFee()});
        return result;
    }

    public int editRuleFee(String id, String fee) {
        String sql = new String("update payment_rule set rule_fee = ? where id = ?");
        return jdbcTemplate.update(sql, new Object[]{fee, id});
    }

    public boolean editPaymentRule(String id, String fee) {
        return false;
    }

    public boolean eidtPaymentInterface(String newInterface) {
        return false;
    }

    public int getRuleTotal() {
        String sql = new String("select count(1) from payment_rule;");
        int sum = jdbcTemplate.queryForObject(sql, Integer.class);
        return sum;
    }

    public List queryRuleDis() {
        String sql = new String("select i.room_id roomId,i.room_name roomName,i.room_size roomSize,i.property_ver propertyVer,i.utilities_ver utilitiesVer,i.parking_ver parkingVer from room_info i;");
        List list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public int isRelationExist(String userId,String roomId){
        return jdbcTemplate.queryForObject("select count(1) from user_room_rela u where u.room_id = ? and user_id = ?;",new Object[]{roomId,userId},Integer.class);
    }

    public int addRuleRelation(String roomId,String userId){
        return jdbcTemplate.update("insert into user_room_rela(room_id,user_id) values(?,?);",new Object[]{roomId,userId});
    }
    public int editRuleDis(RuleDisVO vo) {
        StringBuffer sqlBuffer = new StringBuffer("");
        sqlBuffer.append("update room_info set property_ver = ").append(vo.getPropertyVer())
                .append(" , utilities_ver = ").append(vo.getUtilitiesVer())
                .append(" , parking_ver = ").append(vo.getParkingVer())
                .append(" where room_id = ").append(vo.getId());
        log.info(sqlBuffer);
        int result = jdbcTemplate.update(sqlBuffer.toString());
        return result;
    }

    public List queryUserAndRoom() {
        String sql = new String("select ur.id,ur.room_id roomId,ur.user_id userId,i.room_name roomName from user_room_rela ur left join room_info i on ur.room_id = i.room_id;");
        List list = jdbcTemplate.queryForList(sql);
        return list;
    }

    public boolean editUserAndRoom(UserAndRoomVO vo) {
        return false;
    }

    public boolean insertUserAndRoom(UserAndRoomVO vo) {

        return true;
    }

    public int delUR(String id) {
        String sql = new String("delete from user_room_rela where id = " + id);
        int result = jdbcTemplate.update(sql);
        return result;
    }

    /* -------------------------- 手机端接口 ---------------------------- */
    public int getRuleVer(int roomId, String ruleVer) {
        String sql = new String("select " + ruleVer + "  from room_info  where room_id = " + roomId + " ;");
        int result;
        try {
            result = jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (Exception ex) {
            result = 0;
        }
        return result;
    }

    public double getRuleVal(int ruleVer, int ruleType) {
        String sql = new String("select r.rule_fee ruleFee from payment_rule r where r.rule_version = ? and r.rule_type = ? ;");
        double fee = jdbcTemplate.queryForObject(sql, new Object[]{ruleVer, ruleType}, Double.class);
        return fee;
    }

    public int getRoomId(String userName) {
        String sql = new String("select r.room_id from user u left join user_room_rela r on u.id = r.user_id where logname = ?;");
        int roomId = jdbcTemplate.queryForObject(sql, new Object[]{userName}, Integer.class);
        return roomId;
    }

    public String getLatestPropertyDate(String roomId) {
        String sql = new String("select date from room_state r where r.payment_type = 1 and payment_type = 1 and r.room_id = ? order by r.date,r.property_fee_state desc limit 1");
        String lastestDate;
        try {
            lastestDate = jdbcTemplate.queryForObject(sql, new Object[]{roomId}, String.class);
        } catch (EmptyResultDataAccessException e) {
            lastestDate = jdbcTemplate.queryForObject("select pu_date_begin from room_info where room_id = ? ", new Object[]{roomId}, String.class);
        }
        return lastestDate;
    }

    @Transactional
    public boolean pay(PaymentVO vo) {
        String sql = new String("update payment p set p.pay_amount = ? , p.pay_months = ? , p.pay_type = ? , p.pay_user_id = ? ");
        int payResult = 0, roomResult = 0;
        payResult = jdbcTemplate.update(sql, new Object[]{vo.getAmount(), vo.getMonths(), vo.getType(), vo.getUserId()});

        // 多个月一次缴纳
        String[] payDates = new String[vo.getMonths()];
        if (payResult != 0) {
            StringBuffer sqlBuffer = new StringBuffer("");
            sqlBuffer.append("insert into room_state(room_id,date,payment_type,payment_state) values");
            String dateBegin = vo.getDateBegin();
            for (int i = 0; i < vo.getMonths(); i++) {
                if (i == 0) {
                    payDates[i] = getDateNextMonth(dateBegin);
                } else {
                    payDates[i] = getDateNextMonth(payDates[i - 1]);
                }
            }
            for (int i = 0; i < vo.getMonths(); i++) {
                sqlBuffer.append("( ")
                        .append(vo.getRoomId())
                        .append(",'")
                        .append(payDates[i])
                        .append("',")
                        .append(vo.getType())
                        .append(",1)");
                if (i != vo.getMonths() - 1) {
                    sqlBuffer.append(',');
                }
            }
            log.info(sqlBuffer.toString());
            roomResult = jdbcTemplate.update(sqlBuffer.toString());
        }
        if (payResult == 1 && roomResult == 1) {
            return true;
        } else {
            return false;
        }
    }

    private String getDateNextMonth(String dateBegin) {
        String dateNext = null;
        try {
            dateNext = MyDateUtil.longToString(((MyDateUtil.stringToLong(dateBegin, "yy-MM-dd") / (1000 * 60 * 60 * 24) + 30) * 1000 * 60 * 60 * 24), "yy-MM-dd");
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return dateNext;
    }

    public List getOrderList(String userId, String roomId) {
        StringBuffer sqlBuffer = new StringBuffer("");
        sqlBuffer.append("select p.pay_amount amount,p.pay_date date,p.pay_months months,p.pay_type ")
                .append(" from payment p ")
                .append(" where p.pay_user_id = ? or p.room_id = ? ");
        log.info(sqlBuffer.toString());
        List list = jdbcTemplate.queryForList(sqlBuffer.toString(), new Object[]{userId, roomId});
        return list;
    }
}

