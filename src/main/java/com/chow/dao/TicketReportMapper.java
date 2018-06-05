package com.chow.dao;

import com.chow.model.TicketReport;
import com.chow.model.vo.Con4Budget;

import java.util.List;

public interface TicketReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TicketReport record);

    int insertSelective(TicketReport record);

    TicketReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TicketReport record);

    int updateByPrimaryKey(TicketReport record);

    void clearAll();

    Integer getPeople(Con4Budget con);

    List<TicketReport> findCheckTicketData(Con4Budget con);
}