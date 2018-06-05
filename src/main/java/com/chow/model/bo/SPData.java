package com.chow.model.bo;

import java.math.BigDecimal;

public class SPData {
    private String shopName;
    private String amount;
    private BigDecimal percent;
    private int isIncrease;

    public int getIsIncrease() {
        return isIncrease;
    }

    public void setIsIncrease(int isIncrease) {
        this.isIncrease = isIncrease;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }
}
