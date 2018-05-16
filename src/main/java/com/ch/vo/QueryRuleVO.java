package com.ch.vo;

/**
 * Created by Cxy on 2018/3/20.
 */
public class QueryRuleVO {
    private int start;
    private int length;
    private String ruleDateBegin;
    private String ruleDateEnd;
    private int ruleType;
    private int ruleVersion;
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

    public String getRuleDateBegin() {
        return ruleDateBegin;
    }

    public void setRuleDateBegin(String ruleDateBegin) {
        this.ruleDateBegin = ruleDateBegin;
    }

    public String getRuleDateEnd() {
        return ruleDateEnd;
    }

    public void setRuleDateEnd(String ruleDateEnd) {
        this.ruleDateEnd = ruleDateEnd;
    }

    public int getRuleType() {
        return ruleType;
    }

    public void setRuleType(int ruleType) {
        this.ruleType = ruleType;
    }

    public int getRuleVersion() {
        return ruleVersion;
    }

    public void setRuleVersion(int ruleVersion) {
        this.ruleVersion = ruleVersion;
    }
}
