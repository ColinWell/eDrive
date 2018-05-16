package com.ch.service.imp;

import com.ch.dao.DecorationDao;
import com.ch.pojo.DecorationApplication;
import com.ch.service.IDecorationInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("decorationService")
public class DecorationInterfaceServiceImp implements IDecorationInterface {
    Logger log= LogManager.getLogger(DecorationDao.class);
    @Resource
    private DecorationDao decorationDao;
    @Override
    public List<DecorationApplication> getAllDecoration() {
        List<DecorationApplication> list=new ArrayList<DecorationApplication>();
        list=decorationDao.getAllDRecord();
        return list;
    }

    @Override
    public List<DecorationApplication> getDecorationByStatus(int DecorationApplication_Status) {
        List<DecorationApplication> list=new ArrayList<DecorationApplication>();
        list=decorationDao.getDRecordByStatus(DecorationApplication_Status);
        return list;
    }

    @Override
    public int updateDecorationForStatus(int DecorationApplication_Id, int DecorationApplication_Status) {
        int i=0;

        i=decorationDao.updateDStatus(DecorationApplication_Id,DecorationApplication_Status);
        return i;
    }

    @Override
    public int updateDecorationForTime(int DecorationApplication_Id, Date DecorationApplication_EndTime) {
        int i=0;

        i=decorationDao.updateDTime(DecorationApplication_Id,DecorationApplication_EndTime);
        return i;
    }

    @Override
    public int updateDecorationForReply(int DecorationApplication_Id, String DecorationApplication_Reply) {
        int i=0;

        i=decorationDao.updateDReply(DecorationApplication_Id,DecorationApplication_Reply);
        return i;
    }

    @Override
    public int addDecoration(DecorationApplication decorationApplication) {
        int i=0;
        i=decorationDao.addDecoration(decorationApplication);
        return i;
    }

    @Override
    public List<DecorationApplication> getDecorationById(int Id) {
        List<DecorationApplication> list=new ArrayList<DecorationApplication>();
        list=decorationDao.getDRecordById(1);
        return list;
    }

    @Override
    public boolean editD(Map params) {
        DecorationApplication decorationApplication=new DecorationApplication();
        String did=(String)params.get("DecorationApplication_Id");
        int id=Integer.parseInt(did);
        decorationApplication.setDecorationApplication_Id(id);
        String sta=(String)params.get("DecorationApplication_Status");
        int status=Integer.parseInt(sta);
        String reply=(String)params.get("DecorationApplication_Reply");
        decorationApplication.setDecorationApplication_Reply(reply);
        System.out.println(id+status);
        decorationApplication.setDecorationApplication_Status(status);
        int result=decorationDao.editD(decorationApplication);
        if(result == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int countD() {
        int i=decorationDao.countD();
        return i;
    }

    @Override
    public List<DecorationApplication> queryD(DecorationApplication decorationApplication) {
        return null;
    }
}
