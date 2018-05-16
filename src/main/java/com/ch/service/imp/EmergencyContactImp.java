package com.ch.service.imp;

import com.ch.dao.EmergencyContactDao;
import com.ch.pojo.EmergencyContact;
import com.ch.service.IEmergencyContactInterface;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("emergencyService")
public class EmergencyContactImp implements IEmergencyContactInterface {
    @Resource
    private EmergencyContactDao emergencyContactDao;
    @Override
    public List<EmergencyContact> getALLEmergencyContact() {
        List<EmergencyContact> list=new ArrayList<EmergencyContact>();
        list=emergencyContactDao.getALLEmergencyContact();
        System.out.println("imp");
        System.out.println(list);
        return list;
    }

    @Override
    public EmergencyContact getEmergencyContactById(int EmergencyContact_Id) {
      EmergencyContact emergencyContact=new EmergencyContact();
       emergencyContact=emergencyContactDao.getEmergencyContactById(EmergencyContact_Id);
        return emergencyContact;
    }

    @Override
    public int updateContact(EmergencyContact emergencyContact, int EmergencyContact_Id) {
        return 0;
    }
//"EmergencyContact_Name":ename,
//            "EmergencyContact_Phone":ephone,
//            "EmergencyContact_Time":etime
    @Override
    public boolean editEmergency(Map params) {
        EmergencyContact emergencyContact=new EmergencyContact();
        String id=(String)params.get("EmergencyContact_Id");
        int eid=Integer.parseInt(id);
        emergencyContact.setEmergencyContact_Id(eid);
        emergencyContact.setEmergencyContact_Name((String)params.get("EmergencyContact_Name"));
        emergencyContact.setEmergencyContact_Phone((String)params.get("EmergencyContact_Phone"));
        emergencyContact.setEmergencyContact_Time((String)params.get("EmergencyContact_Time"));
        int result = emergencyContactDao.editEmergency(emergencyContact);
        if(result == 1){
            return true;
        }else {
            return false;
        }
    }
    public boolean addEmergency(Map params){
        EmergencyContact emergencyContact=new EmergencyContact();
        emergencyContact.setEmergencyContact_Name((String)params.get("EmergencyContact_Name"));
        emergencyContact.setEmergencyContact_Phone((String)params.get("EmergencyContact_Phone"));
        emergencyContact.setEmergencyContact_Time((String)params.get("EmergencyContact_Time"));
        int result = emergencyContactDao.addEmergency(emergencyContact);
        if(result == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delE(int id) {
        int i=emergencyContactDao.delE(id);
        return false;
    }
}
