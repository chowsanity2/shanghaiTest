package com.chow.model;

import java.math.BigDecimal;

public class TicketSale {
    private Integer id;

    private String projectId;

    private String projectName;

    private Integer layoutId;

    private String layoutName;

    private String dealId;

    private String dealType;

    private String agencyName;

    private BigDecimal ticketPrice;

    private Integer ticketCount;

    private String discount;

    private BigDecimal ticketAmount;

    private Integer ticketPeople;

    private String barcode;

    private String ticketCode;

    private String ticketName;

    private String ticketType;

    private String gzone;

    private String empName;

    private String collectTerminalName;

    private String dealRemark;

    private String saleDate;

    private String terminalName;

    public TicketSale(Integer id, String projectId, String projectName, Integer layoutId, String layoutName, String dealId, String dealType, String agencyName, BigDecimal ticketPrice, Integer ticketCount, String discount, BigDecimal ticketAmount, Integer ticketPeople, String barcode, String ticketCode, String ticketName, String ticketType, String gzone, String empName, String collectTerminalName, String dealRemark, String saleDate, String terminalName) {
        this.id = id;
        this.projectId = projectId;
        this.projectName = projectName;
        this.layoutId = layoutId;
        this.layoutName = layoutName;
        this.dealId = dealId;
        this.dealType = dealType;
        this.agencyName = agencyName;
        this.ticketPrice = ticketPrice;
        this.ticketCount = ticketCount;
        this.discount = discount;
        this.ticketAmount = ticketAmount;
        this.ticketPeople = ticketPeople;
        this.barcode = barcode;
        this.ticketCode = ticketCode;
        this.ticketName = ticketName;
        this.ticketType = ticketType;
        this.gzone = gzone;
        this.empName = empName;
        this.collectTerminalName = collectTerminalName;
        this.dealRemark = dealRemark;
        this.saleDate = saleDate;
        this.terminalName = terminalName;
    }

    public TicketSale() {
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

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId == null ? null : dealId.trim();
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType == null ? null : dealType.trim();
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName == null ? null : agencyName.trim();
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount == null ? null : discount.trim();
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode == null ? null : ticketCode.trim();
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName == null ? null : ticketName.trim();
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType == null ? null : ticketType.trim();
    }

    public String getGzone() {
        return gzone;
    }

    public void setGzone(String gzone) {
        this.gzone = gzone == null ? null : gzone.trim();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getCollectTerminalName() {
        return collectTerminalName;
    }

    public void setCollectTerminalName(String collectTerminalName) {
        this.collectTerminalName = collectTerminalName == null ? null : collectTerminalName.trim();
    }

    public String getDealRemark() {
        return dealRemark;
    }

    public void setDealRemark(String dealRemark) {
        this.dealRemark = dealRemark == null ? null : dealRemark.trim();
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate == null ? null : saleDate.trim();
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName == null ? null : terminalName.trim();
    }
}