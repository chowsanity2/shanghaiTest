package com.chow.model;

import java.math.BigDecimal;
import java.util.Date;

public class ShopSale {
    private Integer id;

    private String projectId;

    private String projectName;

    private Integer layoutId;

    private String layoutName;

    private String saleType;

    private Integer shopId;

    private String shopName;

    private BigDecimal shopPrice;

    private String saleCount;

    private BigDecimal salePrice;

    private Date saleDate;

    public ShopSale(Integer id, String projectId, String projectName, Integer layoutId, String layoutName, String saleType, Integer shopId, String shopName, BigDecimal shopPrice, String saleCount, BigDecimal salePrice, Date saleDate) {
        this.id = id;
        this.projectId = projectId;
        this.projectName = projectName;
        this.layoutId = layoutId;
        this.layoutName = layoutName;
        this.saleType = saleType;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopPrice = shopPrice;
        this.saleCount = saleCount;
        this.salePrice = salePrice;
        this.saleDate = saleDate;
    }

    public ShopSale() {
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

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType == null ? null : saleType.trim();
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(String saleCount) {
        this.saleCount = saleCount == null ? null : saleCount.trim();
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }
}