package com.chow.model;

import java.math.BigDecimal;
import java.util.Date;

public class ConsumerPriceReport {
    private Integer id;

    private Date date;

    private Integer layoutId;

    private String layoutName;

    private String comprehensive;

    private BigDecimal price;

    private Date createdTime;

    private Date updateTime;

    private Integer projectId;

    private String projectName;

    private Integer buildingId;

    private String buildingName;

    private String projectLevel;

    public ConsumerPriceReport(Integer id, Date date, Integer layoutId, String layoutName, String comprehensive, BigDecimal price, Date createdTime, Date updateTime, Integer projectId, String projectName, Integer buildingId, String buildingName, String projectLevel) {
        this.id = id;
        this.date = date;
        this.layoutId = layoutId;
        this.layoutName = layoutName;
        this.comprehensive = comprehensive;
        this.price = price;
        this.createdTime = createdTime;
        this.updateTime = updateTime;
        this.projectId = projectId;
        this.projectName = projectName;
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        this.projectLevel = projectLevel;
    }

    public ConsumerPriceReport() {
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

    public Integer getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(Integer layoutId) {
        this.layoutId = layoutId;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName == null ? null : layoutName.trim();
    }

    public String getComprehensive() {
        return comprehensive;
    }

    public void setComprehensive(String comprehensive) {
        this.comprehensive = comprehensive == null ? null : comprehensive.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(String projectLevel) {
        this.projectLevel = projectLevel == null ? null : projectLevel.trim();
    }
}