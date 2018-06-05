package com.chow.model;

import java.util.Date;

public class ConsumerFlowReport {
    private Integer id;

    private Date date;

    private Integer buildingId;

    private String buildingName;

    private String inCount;

    private String outCount;

    private String stayCount;

    private Integer projectId;

    private String projectName;

    private Date createdTime;

    private Date updateTime;

    private Integer projectLevel;

    public ConsumerFlowReport(Integer id, Date date, Integer buildingId, String buildingName, String inCount, String outCount, String stayCount, Integer projectId, String projectName, Date createdTime, Date updateTime, Integer projectLevel) {
        this.id = id;
        this.date = date;
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        this.inCount = inCount;
        this.outCount = outCount;
        this.stayCount = stayCount;
        this.projectId = projectId;
        this.projectName = projectName;
        this.createdTime = createdTime;
        this.updateTime = updateTime;
        this.projectLevel = projectLevel;
    }

    public ConsumerFlowReport() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? null : buildingName.trim();
    }

    public String getInCount() {
        return inCount;
    }

    public void setInCount(String inCount) {
        this.inCount = inCount == null ? null : inCount.trim();
    }

    public String getOutCount() {
        return outCount;
    }

    public void setOutCount(String outCount) {
        this.outCount = outCount == null ? null : outCount.trim();
    }

    public String getStayCount() {
        return stayCount;
    }

    public void setStayCount(String stayCount) {
        this.stayCount = stayCount == null ? null : stayCount.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(Integer projectLevel) {
        this.projectLevel = projectLevel;
    }
}