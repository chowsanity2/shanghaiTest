package com.chow.model.bo;

import java.util.List;

/**
 * 商品零售
 */
public class ShopSaleBo {
    private String code;
    private String totalAmount;
    private List<SPData> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<SPData> getData() {
        return data;
    }

    public void setData(List<SPData> data) {
        this.data = data;
    }
}
