package com.chow.dao;

import com.chow.model.TicketSale;

public interface TicketSaleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TicketSale record);

    int insertSelective(TicketSale record);

    TicketSale selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TicketSale record);

    int updateByPrimaryKey(TicketSale record);

    //清空所有数据
    void clearAll();
}