package com.ch.vo;

/**
 * Created by Cxy on 2018/4/2.
 */
public class RuleVO {
    private int ruleVersion;
    private String pubUserId;
    private String ruleName;
    private int ruleType;
    private double ruleFee;

    public int getRuleVersion() {
        return ruleVersion;
    }

    public void setRuleVersion(int ruleVersion) {
        this.ruleVersion = ruleVersion;
    }

    public String getPubUserId() {
        return pubUserId;
    }

    public void setPubUserId(String pubUserId) {
        this.pubUserId = pubUserId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public int getRuleType() {
        return ruleType;
    }

    public void setRuleType(int ruleType) {
        this.ruleType = ruleType;
    }

    public double getRuleFee() {
        return ruleFee;
    }

    public void setRuleFee(double ruleFee) {
        this.ruleFee = ruleFee;
    }
}
