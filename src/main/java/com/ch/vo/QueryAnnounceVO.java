package com.ch.vo;

/**
 * Created by Cxy on 2018/4/13.
 */
public class QueryAnnounceVO {
    private int start;
    private int length;
    private String dateBegin;
    private String dateEnd;
    private int announceType;
    private String userName;
    private String searchVal;
    private String orderColumn;
    private String orderWay;
    private String[] columnName;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getAnnounceType() {
        return announceType;
    }

    public void setAnnounceType(int announceType) {
        this.announceType = announceType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSearchVal() {
        return searchVal;
    }

    public void setSearchVal(String searchVal) {
        this.searchVal = searchVal;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderWay() {
        return orderWay;
    }

    public void setOrderWay(String orderWay) {
        this.orderWay = orderWay;
    }

    public String[] getColumnName() {
        return columnName;
    }

    public void setColumnName(String[] columnName) {
        this.columnName = columnName;
    }
}
