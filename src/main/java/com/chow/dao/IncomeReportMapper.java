package com.chow.dao;

import com.chow.model.IncomeReport;
import com.chow.model.vo.Con4Budget;

import java.util.List;

public interface IncomeReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IncomeReport record);

    int insertSelective(IncomeReport record);

    IncomeReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IncomeReport record);

    int updateByPrimaryKey(IncomeReport record);

    void clearAll();

    Integer findAmountByLayoutId(Con4Budget con);

    Integer findTotalAmount(Con4Budget con);

    List<IncomeReport> test();

}