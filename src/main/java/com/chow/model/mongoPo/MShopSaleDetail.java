package com.chow.model.mongoPo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "shopsaledetail")
public class MShopSaleDetail {
    private Object id;
    @Field("client-time")
    private String client_time;
    private String buildingName;
    private String totalCount;
    private String pageTotal;
    private String layoutId;
    private String seqId;
    private String buildingCode;
    @Field("user-ip")
    private String user_ip;
    @Field("sys-time")
    private String sys_time;
    private String pageIndex;
    @Field("Data")
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getClient_time() {
        return client_time;
    }

    public void setClient_time(String client_time) {
        this.client_time = client_time;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(String pageTotal) {
        this.pageTotal = pageTotal;
    }

    public String getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(String layoutId) {
        this.layoutId = layoutId;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getUser_ip() {
        return user_ip;
    }

    public void setUser_ip(String user_ip) {
        this.user_ip = user_ip;
    }

    public String getSys_time() {
        return sys_time;
    }

    public void setSys_time(String sys_time) {
        this.sys_time = sys_time;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public String toString() {
        return "MShopSaleDetail{" +
                "id=" + id +
                ", client_time='" + client_time + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", pageTotal='" + pageTotal + '\'' +
                ", layoutId='" + layoutId + '\'' +
                ", seqId='" + seqId + '\'' +
                ", buildingCode='" + buildingCode + '\'' +
                ", user_ip='" + user_ip + '\'' +
                ", sys_time='" + sys_time + '\'' +
                ", pageIndex='" + pageIndex + '\'' +
                '}';
    }
}
