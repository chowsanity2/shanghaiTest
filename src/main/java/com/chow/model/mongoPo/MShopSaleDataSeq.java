package com.chow.model.mongoPo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "shopsaledataseq")
public class MShopSaleDataSeq {
    private Object id;
    @Field("client-time")
    private String client_time;
    private String pageTotal;
    private String totalCount;
    private String layoutId;
    private String layoutName;
    private String seqId;
    @Field("user-ip")
    private String user_ip;
    @Field("sys-time")
    private String sys_time;

    public String getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
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

    public String getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(String pageTotal) {
        this.pageTotal = pageTotal;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
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

    @Override
    public String toString() {
        return "MShopSaleDataSeq{" +
                "id=" + id +
                ", client_time='" + client_time + '\'' +
                ", pageTotal='" + pageTotal + '\'' +
                ", totalCount='" + totalCount + '\'' +
                ", layoutId='" + layoutId + '\'' +
                ", seqId='" + seqId + '\'' +
                ", user_ip='" + user_ip + '\'' +
                ", sys_time='" + sys_time + '\'' +
                '}';
    }
}
