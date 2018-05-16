package com.ch.pojo;

import java.util.Date;

public class Complaint {
    private int Complaint_Id;
    private int Id;
    private int Complaint_Status;
    private String Complaint_Content;
    private int Processor_Id;
    private Date Complaint_Time;
    private Date ComplaintProcessor_Time;
    private String Complaint_Phone;
    private String Complaint_Reply;
    private String Complaint_Name;

    public int getComplaint_Id() {
        return Complaint_Id;
    }

    public void setComplaint_Id(int complaint_Id) {
        Complaint_Id = complaint_Id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getComplaint_Status() {
        return Complaint_Status;
    }

    public void setComplaint_Status(int complaint_Status) {
        Complaint_Status = complaint_Status;
    }

    public String getComplaint_Content() {
        return Complaint_Content;
    }

    public void setComplaint_Content(String complaint_Content) {
        Complaint_Content = complaint_Content;
    }

    public int getProcessor_Id() {
        return Processor_Id;
    }

    public void setProcessor_Id(int processor_Id) {
        Processor_Id = processor_Id;
    }

    public Date getComplaint_Time() {
        return Complaint_Time;
    }

    public void setComplaint_Time(Date complaint_Time) {
        Complaint_Time = complaint_Time;
    }

    public Date getComplaintProcessor_Time() {
        return ComplaintProcessor_Time;
    }

    public void setComplaintProcessor_Time(Date complaintProcessor_Time) {
        ComplaintProcessor_Time = complaintProcessor_Time;
    }

    public String getComplaint_Phone() {
        return Complaint_Phone;
    }

    public void setComplaint_Phone(String complaint_Phone) {
        Complaint_Phone = complaint_Phone;
    }

    public String getComplaint_Reply() {
        return Complaint_Reply;
    }

    public void setComplaint_Reply(String complaint_Reply) {
        Complaint_Reply = complaint_Reply;
    }

    public String getComplaint_Name() {
        return Complaint_Name;
    }

    public void setComplaint_Name(String complaint_Name) {
        Complaint_Name = complaint_Name;
    }
}
