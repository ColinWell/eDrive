package com.ch.service;

import com.ch.pojo.Complaint;

import java.util.List;

public interface IComplaintService {
    public int addComplaint(Complaint complaint);
    public List<Complaint> getCByS(int s);
    public List<Complaint> getCById(int id);
    public int contC();
}
