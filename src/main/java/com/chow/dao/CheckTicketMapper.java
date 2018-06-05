package com.chow.dao;

import com.chow.model.CheckTicket;

public interface CheckTicketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CheckTicket record);

    int insertSelective(CheckTicket record);

    CheckTicket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CheckTicket record);

    int updateByPrimaryKey(CheckTicket record);

    void clearAll();
}