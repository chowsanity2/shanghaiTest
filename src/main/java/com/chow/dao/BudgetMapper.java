package com.chow.dao;

import com.chow.model.Budget;
import com.chow.model.vo.Con4Budget;

import java.util.List;

public interface BudgetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Budget record);

    int insertSelective(Budget record);

    Budget selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Budget record);

    int updateByPrimaryKey(Budget record);

    //根据项目id和日期类型获取预算值
    List<Budget> getBudgetWithProAndDate(Con4Budget con);
    //根据业态id和日期类型获取预算值
    Budget getBudgetWithLayoutAndDate(Con4Budget con);
    //根据日期类型获取预算值(没有商铺字段)
    List<Budget> getBudgetWithBuildAndDate(Con4Budget con);
    //获取所有预算信息
    List<Budget> getAllBudget2Map();
}