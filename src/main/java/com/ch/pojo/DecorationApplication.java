package com.ch.pojo;

import java.util.Date;

public class DecorationApplication {
    private int Id;
    private String logname;
    private int Processor_Id;
    private String DecorationApplication_Reply;
    private String DecorationApplication_Place;
    private Date DecorationApplication_StartTime;
    private Date DecorationApplication_EndTime;
    private int DecorationApplication_Id;
    private int DecorationApplication_Status;
    private String DecorationApplication_Phone;


    public int getDecorationApplication_Id() {
        return DecorationApplication_Id;
    }

    public int getDecorationApplication_Status() {
        return DecorationApplication_Status;
    }

    public String getDecorationApplication_Phone() {
        return DecorationApplication_Phone;
    }

    public int getProcessor_Id() {
        return Processor_Id;
    }

    public String getDecorationApplication_Reply() {
        return DecorationApplication_Reply;
    }

    public String getDecorationApplication_Place() {
        return DecorationApplication_Place;
    }

    public Date getDecorationApplication_StartTime() {
        return DecorationApplication_StartTime;
    }

    public Date getDecorationApplication_EndTime() {
        return DecorationApplication_EndTime;
    }

    public void setDecorationApplication_Id(int decorationApplication_Id) {
        DecorationApplication_Id = decorationApplication_Id;
    }

    public void setDecorationApplication_Status(int decorationApplication_Status) {
        DecorationApplication_Status = decorationApplication_Status;
    }

    public void setDecorationApplication_Phone(String decorationApplication_Phone) {
        DecorationApplication_Phone = decorationApplication_Phone;
    }

    public void setProcessor_Id(int processor_Id) {
        Processor_Id = processor_Id;
    }

    public void setDecorationApplication_Reply(String decorationApplication_Reply) {
        DecorationApplication_Reply = decorationApplication_Reply;
    }

    public void setDecorationApplication_Place(String decorationApplication_Place) {
        DecorationApplication_Place = decorationApplication_Place;
    }

    public void setDecorationApplication_StartTime(Date decorationApplication_StartTime) {
        DecorationApplication_StartTime = decorationApplication_StartTime;
    }

    public void setDecorationApplication_EndTime(Date decorationApplication_EndTime) {
        DecorationApplication_EndTime = decorationApplication_EndTime;
    }

    public int getId() {
        return Id;
    }

    public String getLogname() {
        return logname;
    }
    public void setId(int id) {
        Id = id;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }


}
