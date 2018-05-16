package com.ch.service;

import com.ch.pojo.DecorationApplication;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IDecorationInterface {
    public List<DecorationApplication> getAllDecoration();//获取所有装修记录
    public List<DecorationApplication> getDecorationByStatus(int DecorationApplication_Status);//获取特定状态装修记录
    public int updateDecorationForStatus(int DecorationApplication_Id, int DecorationApplication_Status);//修改装修记录状态
    public int updateDecorationForTime(int DecorationApplication_Id, Date DecorationApplication_EndTime);//修改装修记录时间
    public int updateDecorationForReply(int DecorationApplication_Id, String DecorationApplication_Reply);//修改回复
    public int addDecoration(DecorationApplication decorationApplication);//新增装修记录
    public List<DecorationApplication> getDecorationById(int Id);//获取特定状态装修记录
    public boolean editD(Map params);
    public int countD();
    public List<DecorationApplication> queryD(DecorationApplication decorationApplication);
}
