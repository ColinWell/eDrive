package com.ch.vo;

import java.util.List;

/**
 * Created by Cxy on 2018/3/20.
 */
public class DataTableVO<T>{
    private int draw;
    private int recordsTotal; // Total records number without conditions
    private int recordsFiltered; // Total records number with conditions
    private List<T> data; // The data we should display on the page
    // getter and setter method


    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
