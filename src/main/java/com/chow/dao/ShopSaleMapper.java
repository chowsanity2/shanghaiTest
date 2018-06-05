package com.chow.dao;

import com.chow.model.ShopSale;

public interface ShopSaleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopSale record);

    int insertSelective(ShopSale record);

    ShopSale selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopSale record);

    int updateByPrimaryKey(ShopSale record);

    void clearAll();
}