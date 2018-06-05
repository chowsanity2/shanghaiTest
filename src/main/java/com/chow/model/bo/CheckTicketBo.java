package com.chow.model.bo;

import java.util.List;

/**
 * 票务核销
 */
public class CheckTicketBo {
    private String code;
    private String msg;
    private int totalTicket;
    private List<CTData> data;

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

    public int getTotalTicket() {
        return totalTicket;
    }

    public void setTotalTicket(int totalTicket) {
        this.totalTicket = totalTicket;
    }

    public List<CTData> getData() {
        return data;
    }

    public void setData(List<CTData> data) {
        this.data = data;
    }
}
