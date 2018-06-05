package com.chow.model;

import java.util.Date;

public class Budget {
    private Integer id;

    private String projectId;

    private String projectName;

    private Integer layoutId;

    private String layoutName;

    private String typeDate;

    private Integer type;

    private String budget;

    private Date createDate;

    private Date updateDate;

    public Budget(Integer id, String projectId, String projectName, Integer layoutId, String layoutName, String typeDate, Integer type, String budget, Date createDate, Date updateDate) {
        this.id = id;
        this.projectId = projectId;
        this.projectName = projectName;
        this.layoutId = layoutId;
        this.layoutName = layoutName;
        this.typeDate = typeDate;
        this.type = type;
        this.budget = budget;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Budget() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
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

    public String getTypeDate() {
        return typeDate;
    }

    public void setTypeDate(String typeDate) {
        this.typeDate = typeDate == null ? null : typeDate.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget == null ? null : budget.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}