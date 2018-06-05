package com.chow.model;

import java.util.Date;

public class TicketReport {
    private Integer id;

    private Integer projectLevel;

    private String typeId;

    private String typeName;

    private Integer ticketCount;

    private Integer budgetId;

    private Integer retentionNumber;

    private String dateType;

    private Integer type;

    private Integer gener;

    private Integer finishPercentum;

    private Date createDate;

    public TicketReport(Integer id, Integer projectLevel, String typeId, String typeName, Integer ticketCount, Integer budgetId, Integer retentionNumber, String dateType, Integer type, Integer gener, Integer finishPercentum, Date createDate) {
        this.id = id;
        this.projectLevel = projectLevel;
        this.typeId = typeId;
        this.typeName = typeName;
        this.ticketCount = ticketCount;
        this.budgetId = budgetId;
        this.retentionNumber = retentionNumber;
        this.dateType = dateType;
        this.type = type;
        this.gener = gener;
        this.finishPercentum = finishPercentum;
        this.createDate = createDate;
    }

    public TicketReport() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(Integer projectLevel) {
        this.projectLevel = projectLevel;
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

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    public Integer getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
    }

    public Integer getRetentionNumber() {
        return retentionNumber;
    }

    public void setRetentionNumber(Integer retentionNumber) {
        this.retentionNumber = retentionNumber;
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

    public Integer getGener() {
        return gener;
    }

    public void setGener(Integer gener) {
        this.gener = gener;
    }

    public Integer getFinishPercentum() {
        return finishPercentum;
    }

    public void setFinishPercentum(Integer finishPercentum) {
        this.finishPercentum = finishPercentum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}