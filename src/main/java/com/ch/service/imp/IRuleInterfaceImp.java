package com.ch.service.imp;


import com.ch.dao.RuleDao;
import com.ch.pojo.Rule;
import com.ch.service.IRuleInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("ruleService")
public class IRuleInterfaceImp implements IRuleInterface {
    Logger log= LogManager.getLogger(RuleDao.class);
    @Resource
    private RuleDao ruleDao;
    @Override
    public Rule getRule(String Rule_Name) {
        Rule rule=new Rule();
        rule=ruleDao.getRule(Rule_Name);
        log.info(rule.getRule_Id()+"  "+rule.getRule_Name()+"  "+rule.getRule_Main());
        return rule;
    }

    @Override
    public int updateRule(String Rule_Name, String Rule_Main) {
        int i=0;
        Rule rule=new Rule();
        i=ruleDao.setRule(Rule_Name,Rule_Main);
        return i;
    }
}
