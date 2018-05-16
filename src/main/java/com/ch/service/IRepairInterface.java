package com.ch.service;

import com.ch.pojo.RepairApplication;

import java.util.List;
import java.util.Map;

public interface IRepairInterface {
    public List<RepairApplication> getAllRepair();//获取所有报修记录
    public List<RepairApplication> getRepairByStatus(int RepairApplication_Status);//获取特定状态报修记录
    public int addRepair(RepairApplication repairApplication);
    public boolean editR(Map params);//更改状态
    public int updateRepairApplicationForStatus(int RepairApplication_Id, int RepairApplication_Status);//修改记录状态

    public List<RepairApplication> getRById(int id);
    public int countR();
}
