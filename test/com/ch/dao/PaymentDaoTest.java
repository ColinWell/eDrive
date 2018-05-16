package com.ch.dao;

import com.ch.BaseJunit4Test;
import com.ch.vo.PaymentVO;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;

/**
 * Created by Cxy on 2018/4/12.
 */
public class PaymentDaoTest extends BaseJunit4Test {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private PaymentDao paymentDao;

    @Test
    @Rollback(value = true)
    public void test(){
//        String sql = "insert into user(logname,password,role_ids) values('Colin','123456','ROLE_ADMIN')";
//        jdbcTemplate.execute(sql);
        PaymentVO vo = new PaymentVO();
        vo.setAmount(60);
        vo.setMonths(2);
        vo.setRoomId(302010402);
        vo.setType(1);
        vo.setDateBegin("2018-04-01");
        vo.setUserId("1");
        boolean result = paymentDao.pay(vo);
        System.out.println(result);
    }
}
