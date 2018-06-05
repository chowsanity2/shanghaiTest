package com.chow.model;

import java.util.Date;

public class ErrorLog {
    private Integer id;

    private String reqId;

    private String errorCause;

    private Date createDate;

    public ErrorLog(Integer id, String reqId, String errorCause, Date createDate) {
        this.id = id;
        this.reqId = reqId;
        this.errorCause = errorCause;
        this.createDate = createDate;
    }

    public ErrorLog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId == null ? null : reqId.trim();
    }

    public String getErrorCause() {
        return errorCause;
    }

    public void setErrorCause(String errorCause) {
        this.errorCause = errorCause == null ? null : errorCause.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}