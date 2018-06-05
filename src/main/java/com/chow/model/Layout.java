package com.chow.model;

import java.util.Date;

public class Layout {
    private Integer id;

    private String layoutName;

    private String layoutDescribe;

    private Date createDate;

    private Date updateDate;

    public Layout(Integer id, String layoutName, String layoutDescribe, Date createDate, Date updateDate) {
        this.id = id;
        this.layoutName = layoutName;
        this.layoutDescribe = layoutDescribe;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Layout() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName == null ? null : layoutName.trim();
    }

    public String getLayoutDescribe() {
        return layoutDescribe;
    }

    public void setLayoutDescribe(String layoutDescribe) {
        this.layoutDescribe = layoutDescribe == null ? null : layoutDescribe.trim();
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