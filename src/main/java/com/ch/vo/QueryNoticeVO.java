package com.ch.vo;

/**
 * Created by Cxy on 2018/4/17.
 */
public class QueryNoticeVO {
    private int start;
    private int length;
    private String dateBegin;
    private String dateEnd;
    private String pubUserName;
    private String recUserName;
    private String searchVal;
    private String orderColumn;
    private String orderWay;
    private int isRead;
    private String[] columnName;

    public QueryNoticeVO() {
    }

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

    public String getPubUserName() {
        return pubUserName;
    }

    public void setPubUserName(String pubUserName) {
        this.pubUserName = pubUserName;
    }

    public String getRecUserName() {
        return recUserName;
    }

    public void setRecUserName(String recUserName) {
        this.recUserName = recUserName;
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

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }
}
