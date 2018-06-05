package com.chow.model;

import java.util.Date;

public class Batch {
    private Integer id;

    private String batchNumber;

    private Integer layoutId;

    private String layoutName;

    private Integer totalCount;

    private Integer pageTotal;

    private String statisticsState;

    private String getState;

    private Integer getCount;

    private String isDel;

    private Date createDate;

    private Date updateDate;

    public Batch(Integer id, String batchNumber, Integer layoutId, String layoutName, Integer totalCount, Integer pageTotal, String statisticsState, String getState, Integer getCount, String isDel, Date createDate, Date updateDate) {
        this.id = id;
        this.batchNumber = batchNumber;
        this.layoutId = layoutId;
        this.layoutName = layoutName;
        this.totalCount = totalCount;
        this.pageTotal = pageTotal;
        this.statisticsState = statisticsState;
        this.getState = getState;
        this.getCount = getCount;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Batch() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber == null ? null : batchNumber.trim();
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

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public String getStatisticsState() {
        return statisticsState;
    }

    public void setStatisticsState(String statisticsState) {
        this.statisticsState = statisticsState == null ? null : statisticsState.trim();
    }

    public String getGetState() {
        return getState;
    }

    public void setGetState(String getState) {
        this.getState = getState == null ? null : getState.trim();
    }

    public Integer getGetCount() {
        return getCount;
    }

    public void setGetCount(Integer getCount) {
        this.getCount = getCount;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
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