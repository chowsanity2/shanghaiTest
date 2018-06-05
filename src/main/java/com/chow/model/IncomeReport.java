package com.chow.model;

import java.math.BigDecimal;
import java.util.Date;

public class IncomeReport {
    private Integer id;

    private String typeId;

    private String typeName;

    private BigDecimal typeAmount;

    private String dateType;

    private Integer type;

    private Integer budgetId;

    private String salePercentum;

    private Integer bookingCount;

    private Date createDate;

    private Integer projectLevel;

    public IncomeReport(Integer id, String typeId, String typeName, BigDecimal typeAmount, String dateType, Integer type, Integer budgetId, String salePercentum, Integer bookingCount, Date createDate, Integer projectLevel) {
        this.id = id;
        this.typeId = typeId;
        this.typeName = typeName;
        this.typeAmount = typeAmount;
        this.dateType = dateType;
        this.type = type;
        this.budgetId = budgetId;
        this.salePercentum = salePercentum;
        this.bookingCount = bookingCount;
        this.createDate = createDate;
        this.projectLevel = projectLevel;
    }

    public IncomeReport() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public BigDecimal getTypeAmount() {
        return typeAmount;
    }

    public void setTypeAmount(BigDecimal typeAmount) {
        this.typeAmount = typeAmount;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType == null ? null : dateType.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
    }

    public String getSalePercentum() {
        return salePercentum;
    }

    public void setSalePercentum(String salePercentum) {
        this.salePercentum = salePercentum == null ? null : salePercentum.trim();
    }

    public Integer getBookingCount() {
        return bookingCount;
    }

    public void setBookingCount(Integer bookingCount) {
        this.bookingCount = bookingCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(Integer projectLevel) {
        this.projectLevel = projectLevel;
    }
}