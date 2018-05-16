package com.ch.service;

import com.ch.pojo.Rule;

public interface IRuleInterface {
    public Rule getRule(String Rule_Name);//获取细则
    public int updateRule(String Rule_Name, String Rule_Main);//更新细则
}
