package com.chow.model;

import java.math.BigDecimal;
import java.util.Date;

public class CheckTicket {
    private Integer id;

    private String projectId;

    private String projectName;

    private String batchNumber;

    private String dealId;

    private String ticketType;

    private String barcode;

    private String ticketName;

    private String gzone;

    private Date inGardenDate;

    private String deviceName;

    private Integer incount;

    private String type;

    private String ticketCode;

    private Date checkDate;

    private BigDecimal ticketPrice;

    private String discount;

    private Integer checkCount;

    private BigDecimal ticketAmount;

    private Integer ticketPeople;

    private String isDel;

    private Date createDate;

    private Date updateDate;

    private String creater;

    private String updater;

    public CheckTicket(Integer id, String projectId, String projectName, String batchNumber, String dealId, String ticketType, String barcode, String ticketName, String gzone, Date inGardenDate, String deviceName, Integer incount, String type, String ticketCode, Date checkDate, BigDecimal ticketPrice, String discount, Integer checkCount, BigDecimal ticketAmount, Integer ticketPeople, String isDel, Date createDate, Date updateDate, String creater, String updater) {
        this.id = id;
        this.projectId = projectId;
        this.projectName = projectName;
        this.batchNumber = batchNumber;
        this.dealId = dealId;
        this.ticketType = ticketType;
        this.barcode = barcode;
        this.ticketName = ticketName;
        this.gzone = gzone;
        this.inGardenDate = inGardenDate;
        this.deviceName = deviceName;
        this.incount = incount;
        this.type = type;
        this.ticketCode = ticketCode;
        this.checkDate = checkDate;
        this.ticketPrice = ticketPrice;
        this.discount = discount;
        this.checkCount = checkCount;
        this.ticketAmount = ticketAmount;
        this.ticketPeople = ticketPeople;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.creater = creater;
        this.updater = updater;
    }

    public CheckTicket() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber == null ? null : batchNumber.trim();
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId == null ? null : dealId.trim();
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType == null ? null : ticketType.trim();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName == null ? null : ticketName.trim();
    }

    public String getGzone() {
        return gzone;
    }

    public void setGzone(String gzone) {
        this.gzone = gzone == null ? null : gzone.trim();
    }

    public Date getInGardenDate() {
        return inGardenDate;
    }

    public void setInGardenDate(Date inGardenDate) {
        this.inGardenDate = inGardenDate;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public Integer getIncount() {
        return incount;
    }

    public void setIncount(Integer incount) {
        this.incount = incount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode == null ? null : ticketCode.trim();
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount == null ? null : discount.trim();
    }

    public Integer getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(Integer checkCount) {
        this.checkCount = checkCount;
    }

    public BigDecimal getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(BigDecimal ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public Integer getTicketPeople() {
        return ticketPeople;
    }

    public void setTicketPeople(Integer ticketPeople) {
        this.ticketPeople = ticketPeople;
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

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }
}