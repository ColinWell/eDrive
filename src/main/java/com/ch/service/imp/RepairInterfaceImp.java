package com.ch.service.imp;

import com.ch.dao.RepairApplicationDao;
import com.ch.pojo.RepairApplication;
import com.ch.service.IRepairInterface;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("repairService")
public class RepairInterfaceImp implements IRepairInterface {
    @Resource
    private RepairApplicationDao repairApplicationDao;
    @Override
    public List<RepairApplication> getAllRepair() {
        List<RepairApplication> list=new ArrayList<RepairApplication>();
        list =repairApplicationDao.getAllRRecord();
        return list;
    }

    @Override
    public List<RepairApplication> getRepairByStatus(int RepairApplication_Status) {
        List<RepairApplication> list=new ArrayList<RepairApplication>();
        list =repairApplicationDao.getRRecordByStatus(RepairApplication_Status);
        return list;
    }

    @Override
    public int addRepair(RepairApplication repairApplication) {
        int i=0;
        i=repairApplicationDao.addRepair(repairApplication);
        return 0;
    }

    @Override
    public boolean editR(Map params) {
        RepairApplication repairApplication=new RepairApplication();
        String rid=(String)params.get("RepairApplication_Id");
        int id=Integer.parseInt(rid);
        repairApplication.setRepairApplication_Id(id);
        String sta=(String)params.get("RepairApplication_Status");
        int status=Integer.parseInt(sta);
        System.out.println(id+status);
        repairApplication.setRepairApplication_Status(status);
        int result=repairApplicationDao.editR(repairApplication);
        if(result == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public int updateRepairApplicationForStatus(int RepairApplication_Id, int RepairApplication_Status) {
        int i=0;
        i=repairApplicationDao.updateRStatus(RepairApplication_Id,RepairApplication_Status);
        return i;
    }

    @Override
    public List<RepairApplication> getRById(int id) {
        List<RepairApplication> list=new ArrayList<RepairApplication>();
        list=repairApplicationDao.getRById(id);
        return list;
    }

    @Override
    public int countR() {

        return 0;
    }
}
