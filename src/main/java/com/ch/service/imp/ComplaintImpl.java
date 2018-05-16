package com.ch.service.imp;

import com.ch.dao.ComplaintDao;
import com.ch.pojo.Complaint;
import com.ch.service.IComplaintService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("complaintService")
public class ComplaintImpl implements IComplaintService {
    @Resource
    private ComplaintDao complaintDao;
    @Override
    public int addComplaint(Complaint complaint) {
        int i=0;
        i=complaintDao.addComplaint(complaint);
        return i;
    }

    @Override
    public List<Complaint> getCByS(int s) {
        List<Complaint> list=new ArrayList<Complaint>();
        list=complaintDao.getCByS(s);
        return list;
    }

    @Override
    public List<Complaint> getCById(int id) {
        List<Complaint> list1=new ArrayList<Complaint>();
        list1=complaintDao.getCById(id);
        return list1;
    }

    @Override
    public int contC() {
        int i=complaintDao.countC();
        return i;
    }
}
