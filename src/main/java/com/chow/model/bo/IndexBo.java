package com.chow.model.bo;

import java.math.BigDecimal;

public class    IndexBo {
    private String code;
    private String msg;
    private BigDecimal dayAmount;
    //年累计完成
    private BigDecimal yearAmount;
    //年度收入目标
    private BigDecimal budgetAmount;
    //上周同比
    private BigDecimal weekOnWeek;
    //去年同比
    private BigDecimal yearOnYear;
    private String yearPercent;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BigDecimal getDayAmount() {
        return dayAmount;
    }

    public void setDayAmount(BigDecimal dayAmount) {
        this.dayAmount = dayAmount;
    }

    public BigDecimal getYearAmount() {
        return yearAmount;
    }

    public void setYearAmount(BigDecimal yearAmount) {
        this.yearAmount = yearAmount;
    }

    public BigDecimal getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(BigDecimal budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public BigDecimal getWeekOnWeek() {
        return weekOnWeek;
    }

    public void setWeekOnWeek(BigDecimal weekOnWeek) {
        this.weekOnWeek = weekOnWeek;
    }

    public BigDecimal getYearOnYear() {
        return yearOnYear;
    }

    public void setYearOnYear(BigDecimal yearOnYear) {
        this.yearOnYear = yearOnYear;
    }

    public String getYearPercent() {
        return yearPercent;
    }

    public void setYearPercent(String yearPercent) {
        this.yearPercent = yearPercent;
    }
}
