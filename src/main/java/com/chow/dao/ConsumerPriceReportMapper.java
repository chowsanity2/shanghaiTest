package com.chow.dao;

import com.chow.model.ConsumerPriceReport;

public interface ConsumerPriceReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConsumerPriceReport record);

    int insertSelective(ConsumerPriceReport record);

    ConsumerPriceReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConsumerPriceReport record);

    int updateByPrimaryKey(ConsumerPriceReport record);
}