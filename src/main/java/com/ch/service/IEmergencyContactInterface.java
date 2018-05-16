package com.ch.service;

import com.ch.pojo.EmergencyContact;

import java.util.List;
import java.util.Map;

public interface IEmergencyContactInterface {
    public List<EmergencyContact> getALLEmergencyContact();//获得所有紧急联系方式
    public EmergencyContact getEmergencyContactById(int EmergencyContact_Id);//获得特定联系方式
    public int updateContact(EmergencyContact emergencyContact, int EmergencyContact_Id);//修改紧急联系人
    public boolean editEmergency(Map params);//zhen!修改紧急联系人
    public boolean addEmergency(Map params);//xinzeng
    public boolean delE(int id);
}
