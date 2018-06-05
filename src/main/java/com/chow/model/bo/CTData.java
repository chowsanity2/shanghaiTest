package com.chow.model.bo;

import java.math.BigDecimal;

public class CTData {
    private String itemName;
    private int ticketNum;
    private BigDecimal percent;
    private int isIncrease;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public int getIsIncrease() {
        return isIncrease;
    }

    public void setIsIncrease(int isIncrease) {
        this.isIncrease = isIncrease;
    }
}
