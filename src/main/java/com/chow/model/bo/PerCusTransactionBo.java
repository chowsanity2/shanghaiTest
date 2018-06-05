package com.chow.model.bo;

import java.util.List;

/**
 * 客单价
 */
public class PerCusTransactionBo {
    private String code;
    private String msg;
    private List<PCTData> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<PCTData> getData() {
        return data;
    }

    public void setData(List<PCTData> data) {
        this.data = data;
    }
}
